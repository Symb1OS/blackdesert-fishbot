package ru.namibios.bdofishbot.bot.status;

import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.StatusCutTemplate;
import ru.namibios.bdofishbot.config.Application;
import ru.namibios.bdofishbot.config.Path;

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