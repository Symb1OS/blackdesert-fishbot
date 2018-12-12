package ru.namibios.arduino.gui;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
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

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

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
