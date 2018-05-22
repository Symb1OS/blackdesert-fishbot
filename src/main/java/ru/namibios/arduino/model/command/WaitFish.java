package ru.namibios.arduino.model.command;

import java.awt.AWTException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;

public class WaitFish implements Command{

	private Screen screen;
	
	private ImageParser imageParser;
	
	public WaitFish() throws AWTException{
		this.screen = new Screen(Screen.SPACE);
		this.imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(Screen.WHITE);
	}

	@Override
	public String getKey() {
		return imageParser.getNumber();
	}
}