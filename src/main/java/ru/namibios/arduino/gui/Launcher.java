package ru.namibios.arduino.gui;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.NRootView;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Launcher {
	
	private static final Logger LOG = Logger.getLogger(Launcher.class);

	private static final Set<String> LOCALES = new HashSet<>();
	
	static {
		LOCALES.add("ru_RU");
		LOCALES.add("en_US");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		
		Locale currentLocale = Locale.getDefault();
		
		if(!LOCALES.contains(currentLocale.toString())) {
			Locale.setDefault(new Locale("en", "US"));
		}

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (Exception e) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LOG.error(ExceptionUtils.getString(e));
        }

		UIManager.getDefaults().addResourceBundle("locale");

		try {

			SwingUtilities.invokeLater( () -> new NRootView() );

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}
	}
}
