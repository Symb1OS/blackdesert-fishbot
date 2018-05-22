package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;

public class PersonalMessage {
	
	private Screen screen;
	
	private double allowableCoef;
	
	public PersonalMessage(double coef) throws AWTException, IOException {
		this.screen = new Screen(Screen.CHAT);
		this.allowableCoef = coef;
	}
	
	public boolean isDetected() {
		ImageParser imageParser = new ImageParser(screen);
		imageParser.parse(Screen.GRAY);
		return imageParser.getCoefWhite() > allowableCoef;
	}
	
	
}