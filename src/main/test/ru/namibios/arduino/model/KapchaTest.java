package ru.namibios.arduino.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import ru.namibios.arduino.model.command.Kapcha;

public class KapchaTest {
	
	@Test
	public void testCorrectKapcha() throws IOException {
		
		File dir = new File("resources/debug/correct");
		
		for (File file : dir.listFiles()) {
			if(file.isFile()) {
				String name = file.getName().replaceAll(".jpg", "");
				
				Kapcha kapcha = new Kapcha(file.toString());
				String key = kapcha.getKey();
				
				System.out.println(key + " == " + name);
				assertEquals(name, key);
			} 
		}
	}
}