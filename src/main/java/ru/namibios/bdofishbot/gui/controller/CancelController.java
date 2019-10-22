package ru.namibios.bdofishbot.gui.controller;

import ru.namibios.bdofishbot.gui.view.TabSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelController implements ActionListener{

	private TabSettingsView view;

	public CancelController(TabSettingsView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.dispose();
	}

}