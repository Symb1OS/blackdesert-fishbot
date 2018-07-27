package ru.namibios.arduino.gui;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.utils.ExceptionUtils;

public class Launcher {
	
	private static final Logger LOG = Logger.getLogger(Launcher.class);

	private static final Set<String> LOCALES = new HashSet<>();
	
	static {
		LOCALES.add("ru_RU");
		LOCALES.add("en_US");
	}
	
	public static void main(String[] args) {
		
		Locale currentLocale = Locale.getDefault();
		
		if(!LOCALES.contains(currentLocale.toString())) {
			Locale.setDefault(new Locale("en", "US"));
		}
		
		UIManager.getDefaults().addResourceBundle("locale");
		try {
			SwingUtilities.invokeLater( () -> new RootView() );
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}
	}
}
