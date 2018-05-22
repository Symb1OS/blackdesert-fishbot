package ru.namibios.arduino.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.namibios.arduino.gui.view.SettingsView;

public class CancelController implements ActionListener{

	private SettingsView view;

	public CancelController(SettingsView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.dispose();
	}

}