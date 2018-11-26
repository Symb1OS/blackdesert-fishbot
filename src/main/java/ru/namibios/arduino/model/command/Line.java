package ru.namibios.arduino.model.command;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;

import java.awt.*;

public class Line implements Command {

    private Screen screen;
	
	private ImageParser imageParser;
	
	public Line() throws AWTException {

	    screen = new Screen(Application.getInstance().SUB_LINE());

        if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_SUBLINE()) {
            screen.saveImage(Path.DEBUG_SUBLINE);
        }
		
		imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(Screen.GRAY);
		
	}

	@Override
	public String getKey() {
		return imageParser.getNumber();
	}
	
}