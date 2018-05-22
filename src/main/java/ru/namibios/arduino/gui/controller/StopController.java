package ru.namibios.arduino.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.namibios.arduino.Transfer;

public class StopController implements ActionListener{

	private Transfer threadTransfer;

	public StopController(Transfer threadTransfer) {
		this.threadTransfer = threadTransfer;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(threadTransfer != null)
			threadTransfer.getFishBot().setRunned(false);
	}
	
}