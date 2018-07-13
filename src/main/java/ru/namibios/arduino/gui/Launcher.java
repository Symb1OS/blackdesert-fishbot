package ru.namibios.arduino.gui;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ru.namibios.arduino.gui.view.RootView;

public class Launcher {

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
			e.printStackTrace();
		}
	}
}
