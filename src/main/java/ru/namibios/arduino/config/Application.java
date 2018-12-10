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

	private static ApplicationConfig config;

	public static ApplicationConfig getInstance() {

		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}

		return config;
	}

    public static void main(String[] args) {
        getHash();
    }

	public static String getHash(){

		if (HASH != null) {
			return HASH;

		} else {
			String home = System.getProperty("user.home") + "/fishbotkey";

			if (!Files.exists(Paths.get(home))) {
				LOG.debug("File does not exist");

				String uuid = UUID.randomUUID().toString();
				LOG.debug("New key: " + uuid);

				try {

					LOG.debug("Load hash " + home);
					Files.write(Paths.get(home), uuid.getBytes());

				} catch (IOException e) {
				    LOG.error(ExceptionUtils.getString(e));
				}

				return uuid;

			} else {

				LOG.debug("Load hash");
				try {

					return Files.readAllLines(Paths.get(home)).get(0);

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