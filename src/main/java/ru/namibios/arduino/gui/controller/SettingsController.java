package ru.namibios.arduino.gui.controller;

import ru.namibios.arduino.gui.view.TabSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		TabSettingsView tabSettingsView = new TabSettingsView();
	}
	
}
