package ru.namibios.arduino.model.command;

import java.awt.AWTException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;

public class Line implements Command {

	private Screen screen;
	
	private ImageParser imageParser;
	
	public Line() throws AWTException {
		screen = new Screen(Screen.SUB_LINE);
		
		imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(Screen.GRAY);
		
	}
	
	@Override
	public String getKey() {
		return imageParser.getNumber();
	}
	
}