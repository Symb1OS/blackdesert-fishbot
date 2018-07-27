package ru.namibios.arduino.model.status;

import java.awt.AWTException;
import java.io.IOException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

public class StatusKapcha implements Status<StatusKapchaTemplate>{

	private Screen screen;

	public StatusKapcha() throws AWTException {
		this.screen = new Screen(Screen.STATUS_KAPCHA);
	}
	
	public StatusKapcha(String filename) throws IOException {
		this.screen = new Screen(filename);
	}
	
	public StatusKapchaTemplate getNameTemplate() {
		ImageParser parser = new ImageParser(screen, StatusKapchaTemplate.values());
		parser.parse(Screen.GRAY);
		
		return (StatusKapchaTemplate) parser.getNameTemplate();
	}
	
}