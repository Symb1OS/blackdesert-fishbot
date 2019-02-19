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

	private static boolean hostAvailabilityCheck() {

		try(Socket s = new Socket()) {

			String url = Application.getInstance().URL_CAPTCHA_SERVICE();
			String ip = url.substring(0, url.indexOf(":"));
			String port = url.substring(url.indexOf(":") + 1 );

			s.connect(new InetSocketAddress(ip, Integer.valueOf(port)), 5000);
			return true;
		} catch (IOException e) {
			LOG.error(ExceptionUtils.getString(e));
		}
		return false;
	}

	private static void checkIfRunning() {

		try {

			socket = new ServerSocket(PORT,0, InetAddress.getByAddress(new byte[] {127,0,0,1}));
		}

		catch (BindException e) {
            JOptionPane.showMessageDialog(null, "Program already running on port: " + Application.getInstance().LOCAL_PORT(), "Warning", JOptionPane.ERROR_MESSAGE);
			LOG.error("Program already running on port: " + Application.getInstance().LOCAL_PORT());
			LOG.error(ExceptionUtils.getString(e));
			System.exit(1);
		}
		catch (IOException e) {
			LOG.error("Unexpected error.");
			LOG.error(ExceptionUtils.getString(e));
			System.exit(2);
		}
	}

	private static void checkResolution() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		LOG.debug("width = " + width);
		LOG.debug("height = " + height);

		if (width != 1920 || height != 1080) {
			LOG.error("Unsupported resolution: " + width + "x" + height);
			JOptionPane.showMessageDialog(null, "Supporting only 1920x1080", "Unsupported resolution", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public static void close(){

		try {

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

		boolean isAvailable = hostAvailabilityCheck();
		if (!isAvailable) {
			LOG.error(Message.SERVER_NOT_AVAILABLE_EN);
			JOptionPane.showMessageDialog(null, Message.SERVER_NOT_AVAILABLE_EN, "Warning", JOptionPane.ERROR_MESSAGE);
		}

		checkIfRunning();
		checkResolution();

		LOG.info("Start program..");

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
