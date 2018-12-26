package ru.namibios.arduino.gui.controller;

import ru.namibios.arduino.gui.view.TabDelayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelController implements ActionListener{

	private TabDelayView view;

	public CancelController(TabDelayView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.dispose();
	}

}