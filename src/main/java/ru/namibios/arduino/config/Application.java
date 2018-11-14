package ru.namibios.arduino.config;

import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileOutputStream;

public class Application {

	private static final String PROPERTY_FILE_NAME = "resources/application.properties";
	
	private static ApplicationConfig config;
	
	private Application() {}
	
	public static ApplicationConfig getInstance() {
		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}
		
		return config;
	}

	public static void record() {
		try {
			Application.config.store(new FileOutputStream(new File(PROPERTY_FILE_NAME)), "");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}