package ru.namibios.arduino.model.command;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;

import java.awt.*;

public class PersonalMessage {
	
	private Screen screen;
	
	private double allowableCoef;
	
	public PersonalMessage(double coef) throws AWTException {
		this.screen = new Screen(Application.getInstance().CHAT());
		this.allowableCoef = coef;
	}
	
	public boolean isDetected() {
		ImageParser imageParser = new ImageParser(screen);
		imageParser.parse(Screen.GRAY);
		return imageParser.getCoefWhite() > allowableCoef;
	}
	
	
}