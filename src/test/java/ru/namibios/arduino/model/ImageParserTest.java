package ru.namibios.arduino.model;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Loot;

public class ImageParserTest {
	
	@Test
	public void getNumberTest() throws IOException {
		
		Screen screen = new Screen("resources/loot/ok/key/key.jpg");
		ImageParser parser = new ImageParser(screen, Loot.values());
		
		parser.parse(Screen.GRAY);
		String key = parser.getNumber();
		
		Assert.assertEquals("KEY", key);
	}
	
	@Test
	public void getKeyTest() throws IOException {
		
		Screen screen = new Screen("resources/loot/ok/key/key.jpg");
		ImageParser parser = new ImageParser(screen, Loot.values());
		
		parser.parse(Screen.GRAY);
		String key = parser.getKey();
		Assert.assertEquals("1,", key);
	}
	
}
