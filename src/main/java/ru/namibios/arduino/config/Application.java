package ru.namibios.arduino.config;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class Application {

	private static final Logger LOG = Logger.getLogger(Application.class);

	private static final String PROPERTY_FILE_NAME = "resources/application.properties";

	private static String HASH;

	private static String VERSION;

	private static ApplicationConfig config;

	public static ApplicationConfig getInstance() {

		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}

		return config;
	}

	public static String getHash(){

		if (HASH != null) {
			return HASH;

		} else {
			String home = System.getProperty("user.home") + "/fishbotkey";

			if (!Files.exists(Paths.get(home))) {
				LOG.debug("File does not exist");

				HASH = UUID.randomUUID().toString();
				LOG.debug("New key: " + HASH);

				try {

					LOG.debug("Load hash " + home);
					Files.write(Paths.get(home), HASH.getBytes());

				} catch (IOException e) {
				    LOG.error(ExceptionUtils.getString(e));
				}

				return HASH;

			} else {

				LOG.debug("Load hash");
				try {

					HASH = Files.readAllLines(Paths.get(home)).get(0);
					return HASH;

				} catch (IOException e) {
                    LOG.error(ExceptionUtils.getString(e));
				}
			}

			LOG.error("HASH not loaded!");
			System.exit(1);
		}

		return null;
	}

	public static String getVersion(){

        try {

			if (VERSION != null) {
				return VERSION;
			} else {

				VERSION = Files.readAllLines(Paths.get("version")).get(0);
				return VERSION;
			}

        } catch (IOException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

        return null;
    }

	public static void record() {
		try {
			Application.config.store(new FileOutputStream(new File(PROPERTY_FILE_NAME)), "");
		}catch (Exception e) {
            LOG.error(ExceptionUtils.getString(e));
		}
	}

}