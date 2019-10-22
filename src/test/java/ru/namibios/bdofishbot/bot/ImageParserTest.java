package ru.namibios.bdofishbot.bot;

import org.junit.Assert;
import org.junit.Test;
import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.bot.template.StatusCaptchaTemplate;
import ru.namibios.bdofishbot.config.Path;

import java.io.File;
import java.io.IOException;

public class ImageParserTest {
	
	@Test
	public void testGetNumber() throws IOException {
		
		Screen screen = new Screen(Path.TEST_RESOURCES + "parsing/loot/ok/key/key.jpg");
		ImageParser parser = new ImageParser(screen, Loot.values());
		
		parser.parse(Screen.GRAY);
		String key = parser.getNumber();
		
		Assert.assertEquals("KEY", key);
	}
	
	@Test
	public void testGetKey() throws IOException {
		
		Screen screen = new Screen(Path.TEST_RESOURCES + "parsing/loot/ok/key/key.jpg");
		ImageParser parser = new ImageParser(screen, Loot.values());
		
		parser.parse(Screen.GRAY);
		String key = parser.getKey();
		Assert.assertEquals("1,", key);
	}

	@Test
	public void testSubImageSuccess() throws IOException {

		File[] files = new File(Path.TEST_RESOURCES + "parsing/statuscaptcha/success").listFiles();
		for (File file : files) {

			Screen screen = new Screen(file.getAbsolutePath());

			ImageParser imageParser = new ImageParser(screen, StatusCaptchaTemplate.values());
			imageParser.parse(Screen.GRAY);

			MatrixTemplate nameTemplateBySubImage = imageParser.getNameTemplateBySubImage();
			Assert.assertEquals(StatusCaptchaTemplate.SUCCESS, nameTemplateBySubImage);
		}

	}

	@Test
	public void testSubImageFailed() throws IOException {

		File[] files = new File(Path.TEST_RESOURCES + "parsing/statuscaptcha/failded").listFiles();
		for (File file : files) {

			Screen screen = new Screen(file.getAbsolutePath());

			ImageParser imageParser = new ImageParser(screen, StatusCaptchaTemplate.values());
			imageParser.parse(Screen.GRAY);

			MatrixTemplate nameTemplateBySubImage = imageParser.getNameTemplateBySubImage();
			Assert.assertEquals(StatusCaptchaTemplate.FAILED, nameTemplateBySubImage);
		}

	}

}
