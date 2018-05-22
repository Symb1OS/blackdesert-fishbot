package ru.namibios.arduino.gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ru.namibios.arduino.gui.view.RootView;

public class Launcher {

	public static void main(String[] args) {
		
		UIManager.getDefaults().addResourceBundle("locale");
		
		try {
			SwingUtilities.invokeLater( () -> new RootView() );
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
