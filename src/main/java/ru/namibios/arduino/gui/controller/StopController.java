package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.arduino.Transfer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopController implements ActionListener{

	private static final Logger LOG = Logger.getLogger(StopController.class);

	private Transfer threadTransfer;

	public StopController(Transfer threadTransfer) {
		this.threadTransfer = threadTransfer;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(threadTransfer != null)
			LOG.info("Thread stop");
			threadTransfer.getFishBot().setRunned(false);
	}
	
}