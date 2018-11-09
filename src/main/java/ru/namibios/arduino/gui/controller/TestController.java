package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.arduino.utils.WinFunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TestController implements ActionListener{

	private static final Logger logger = Logger.getLogger(TestController.class);
	
	public TestController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Action for button..");

		WinFunction.sendToInput(KeyEvent.VK_SPACE);

		WinFunction.sendToInput("wasdr");

		WinFunction.clickLeft(500, 500);

		WinFunction.robotSend(KeyEvent.VK_SPACE);
		WinFunction.robotSend(KeyEvent.VK_W);
		WinFunction.robotSend(KeyEvent.VK_A);
		WinFunction.robotSend(KeyEvent.VK_S);
		WinFunction.robotSend(KeyEvent.VK_D);
		WinFunction.robotSend(KeyEvent.VK_R);

		WinFunction.robotClick(500, 500, KeyEvent.BUTTON1_MASK);

	}

}
