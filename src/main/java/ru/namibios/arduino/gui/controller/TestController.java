package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestController implements ActionListener{

	private static final Logger logger = Logger.getLogger(TestController.class);
	
	public TestController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Action for button..");


	}

}
