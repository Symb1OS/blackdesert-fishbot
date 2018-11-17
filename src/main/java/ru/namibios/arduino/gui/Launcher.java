package ru.namibios.arduino.gui;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Launcher {
	
	private static final Logger LOG = Logger.getLogger(Launcher.class);

	public static final Map<String, Locale> LOCALES = new HashMap<>();

	static {
		LOCALES.put("English", new Locale("en", "US"));
		LOCALES.put("Русский", new Locale("ru", "RU"));
	}

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

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

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

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
