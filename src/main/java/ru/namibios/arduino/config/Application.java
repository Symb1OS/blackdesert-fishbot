package ru.namibios.arduino.config;

import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

	private static final String PROPERTY_FILE_NAME = "resources/application.properties";

	private static ApplicationConfig config;

	public static ApplicationConfig getInstance() {

		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}

		return config;
	}

	public static String getVersion(){

        try {

            return Files.readAllLines(Paths.get("version")).get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

	public static void record() {
		try {
			Application.config.store(new FileOutputStream(new File(PROPERTY_FILE_NAME)), "");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}