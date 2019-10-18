package ru.namibios.arduino.gui;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Launcher {
	
	private static final Logger LOG = Logger.getLogger(Launcher.class);

	public static final Map<String, Locale> LOCALES = new HashMap<>();

	private static ServerSocket socket;
    private static final int PORT = Application.getInstance().LOCAL_PORT();

	static {
		LOCALES.put("English", new Locale("en", "US"));
		LOCALES.put("Русский", new Locale("ru", "RU"));
	}

	public static void close(){

		try {

			socket.close();

		} catch (Exception e) {
			LOG.error(ExceptionUtils.getString(e));
		}
	}

	private static abstract class Check {

		private Check nextCheck;

		public Check setNext(Check nextCheck) {
			this.nextCheck = nextCheck;
			return nextCheck;
		}

		protected boolean next(){

			if (nextCheck == null) {
				return true;
			}

			return nextCheck.check();
		}

		public abstract boolean check();

	}

	private static class InstanceCheck extends Check {

		@Override
		public boolean check() {

			try {

				socket = new ServerSocket(PORT,0, InetAddress.getByAddress(new byte[] {127,0,0,1}));
			}

			catch (BindException e) {
				JOptionPane.showMessageDialog(null, "Program already running on port: " + Application.getInstance().LOCAL_PORT(), "Warning", JOptionPane.ERROR_MESSAGE);
				LOG.error("Program already running on port: " + Application.getInstance().LOCAL_PORT());
				LOG.error(ExceptionUtils.getString(e));
				Application.closeBot(Application.CODE_ALREADY_RUNNING);
				return false;
			}
			catch (IOException e) {
				LOG.error("Unexpected error.");
				LOG.error(ExceptionUtils.getString(e));
				Application.closeBot(Application.CODE_ALREADY_RUNNING);
				return false;
			}

			return next();
		}

	}

	private static class HostAvailabilityCheck extends Check {

		private static final Logger LOG = Logger.getLogger(HostAvailabilityCheck.class);

		@Override
		public boolean check() {

			try (Socket s = new Socket()) {

				String url = Application.getInstance().URL_SERVER_HTTPS();
				String ip = url.substring(0, url.indexOf(":"));
				String port = url.substring(url.indexOf(":") + 1 );

				s.connect(new InetSocketAddress(ip, Integer.valueOf(port)), 5000);
				return next();

			} catch (IOException e) {
				LOG.error(ExceptionUtils.getString(e));
			}

			LOG.error(Message.SERVER_NOT_AVAILABLE_EN);
			JOptionPane.showMessageDialog(null, Message.SERVER_NOT_AVAILABLE_EN, "Warning", JOptionPane.ERROR_MESSAGE);

			return false;
		}
	}

	private static class ResolutionCheck extends Check {

		private static final Logger LOG = Logger.getLogger(ResolutionCheck.class);

		@Override
		public boolean check() {

			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();

			LOG.debug("width = " + width);
			LOG.debug("height = " + height);

			if (width != 1920 || height != 1080) {
				LOG.error("Unsupported resolution: " + width + "x" + height);
				JOptionPane.showMessageDialog(null, "Supporting only 1920x1080", "Unsupported resolution", JOptionPane.ERROR_MESSAGE);
				Application.closeBot(Application.CODE_UNSUPPORTED_RESOLUTION);
				return false;
			}

			return next();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {

		LOG.info("Start program..");

		Check checks = new InstanceCheck();
		checks.setNext(new HostAvailabilityCheck())
				.setNext(new ResolutionCheck());

		checks.check();

        String language = Application.getInstance().LANGUAGE();
        LOG.debug("Language: " + language);

        Locale locale = LOCALES.get(language);
        if (locale == null) {
            locale = LOCALES.get("English");
            LOG.debug("Unknown locale. Setting by default");
        }

        Locale.setDefault(locale);

        try {

            UIManager.setLookAndFeel(Application.getInstance().THEME());

        } catch (Exception e) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LOG.error(ExceptionUtils.getString(e));
        }

		UIManager.getDefaults().addResourceBundle("locale");

		try {

			SwingUtilities.invokeLater(RootView::new);

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}
	}

}
