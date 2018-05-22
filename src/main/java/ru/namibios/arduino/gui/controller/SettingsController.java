package ru.namibios.arduino.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.namibios.arduino.gui.view.SettingsView;

public class SettingsController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		SettingsView settingsView = new SettingsView();
		settingsView.setVisible(true);
	}
	
}
