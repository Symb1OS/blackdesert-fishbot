package ru.namibios.arduino.model;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import ru.namibios.arduino.utils.PythonExec;

public class PythonExecTest {

	@Test
	public void parseCaptcha() throws IOException {
		
		File file = new File("resources/model/13.jpg");
		String absolute = file.getAbsolutePath();
		
		Assert.assertEquals("1111044444", PythonExec.exec(absolute));
	}
	
}
