package ru.namibios.arduino.model;

import org.junit.Assert;
import org.junit.Test;
import ru.namibios.arduino.model.template.Loot;
import ru.namibios.arduino.model.template.MatrixTemplate;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

import java.io.File;
import java.io.IOException;

public class ImageParserTest {
	
	@Test
	public void testGetNumber() throws IOException {
		
		Screen screen = new Screen("resources/loot/ok/key/key.jpg");
		ImageParser parser = new ImageParser(screen, Loot.values());
		
		parser.parse(Screen.GRAY);
		String key = parser.getNumber();
		
		Assert.assertEquals("KEY", key);
	}
	
	@Test
	public void testGetKey() throws IOException {
		
		Screen screen = new Screen("resources/loot/ok/key/key.jpg");
		ImageParser parser = new ImageParser(screen, Loot.values());
		
		parser.parse(Screen.GRAY);
		String key = parser.getKey();
		Assert.assertEquals("1,", key);
	}

	@Test
	public void testSubImageSuccess() throws IOException {

		File[] files = new File("resources/test/statuscaptcha/success").listFiles();
		for (File file : files) {

			Screen screen = new Screen(file.getAbsolutePath());

			ImageParser imageParser = new ImageParser(screen, StatusKapchaTemplate.values());
			imageParser.parse(Screen.GRAY);

			MatrixTemplate nameTemplateBySubImage = imageParser.getNameTemplateBySubImage();
			Assert.assertEquals(StatusKapchaTemplate.SUCCESS, nameTemplateBySubImage);
		}

	}

	@Test
	public void testSubImageFailed() throws IOException {

		File[] files = new File("resources/test/statuscaptcha/failed").listFiles();
		for (File file : files) {

			Screen screen = new Screen(file.getAbsolutePath());

			ImageParser imageParser = new ImageParser(screen, StatusKapchaTemplate.values());
			imageParser.parse(Screen.GRAY);

			MatrixTemplate nameTemplateBySubImage = imageParser.getNameTemplateBySubImage();
			Assert.assertEquals(StatusKapchaTemplate.FAILED, nameTemplateBySubImage);
		}

	}

}
