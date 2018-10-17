package ru.namibios.arduino.model.command;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;

import java.awt.*;

public class WaitFish implements Command{

	private Screen screen;
	
	private ImageParser imageParser;
	
	public WaitFish() throws AWTException{
		init();
	}

	public void init() throws AWTException {
		this.screen = new Screen(Application.getInstance().SPACE());
		this.imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(Screen.WHITE);
	}

	@Override
	public String getKey() {
		return imageParser.getNumber();
	}


}