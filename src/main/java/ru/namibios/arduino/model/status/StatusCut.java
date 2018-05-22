package ru.namibios.arduino.model.status;

import java.awt.AWTException;
import java.io.IOException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.StatusCutTemplate;

public class StatusCut implements Status<StatusCutTemplate>{
	
	private Screen screen;
	
	public StatusCut() throws AWTException {
		screen = new Screen(Screen.STATUS_CUT);
	}
	
	
	public StatusCut(String filename) throws IOException{
		screen = new Screen(filename);
	}

	@Override
	public StatusCutTemplate getNameTemplate() {
		ImageParser parser = new ImageParser(screen, StatusCutTemplate.values());
		parser.parse(Screen.WHITE);
		
		return (StatusCutTemplate) parser.getNameTemplate();
	}
	
}