package ru.namibios.arduino.model.command;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;

import java.awt.*;

public class PersonalMessage {

	private static final Logger LOG = Logger.getLogger(PersonalMessage.class);

	private Screen screen;
	
	private double allowableCoef;
	
	public PersonalMessage(double coef) throws AWTException {
		LOG.info("Check chat");

		this.screen = new Screen(Application.getInstance().CHAT());
		this.allowableCoef = coef;

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_PM_MESSAGE()) {
			screen.saveImage(Path.DEBUG_PM_MESSAGE);

		}
	}
	
	public boolean isDetected() {
		ImageParser imageParser = new ImageParser(screen);
		imageParser.parse(Screen.GRAY);

		double coefWhite = imageParser.getCoefWhite();
		LOG.debug("Chat coef: " + coefWhite);

		return coefWhite > allowableCoef;
	}
}