package ru.namibios.arduino.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

// Парсинг капчи перенесен с отдельный сервис
@Ignore
public class PythonExecTest {

	@Test
	public void parseCaptcha() throws IOException {
		
		File[] files = new File("src/test/resources/parsing/captcha/").listFiles();
		for (File file : files) {

			String absolute = file.getAbsolutePath();
            String expected = file.getName().substring(0, file.getName().indexOf('.'));
            Assert.assertEquals(expected, PythonExec.exec(absolute));

		}
	}
	
}
