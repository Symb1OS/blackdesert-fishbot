package ru.namibios.arduino.model.status;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.StatusCutTemplate;

import java.awt.*;
import java.io.IOException;

public class StatusCut implements Status<StatusCutTemplate>{

	private Screen screen;
	
	public StatusCut() throws AWTException {

		screen = new Screen(Application.getInstance().STATUS_CUT());

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_STATUS_CUT()) {
			screen.saveImage(Path.DEBUG_STATUSCUT);
		}

	}

	public static void main(String[] args) throws AWTException {
		new StatusCut();
	}

	public StatusCut(String filename) throws IOException{
		screen = new Screen(filename);
	}

	@Override
	public StatusCutTemplate getNameTemplate() {

		ImageParser parser = new ImageParser(screen, StatusCutTemplate.values());
		parser.parse(Screen.GRAY);
		
		return (StatusCutTemplate) parser.getNameTemplate();
	}
	
}