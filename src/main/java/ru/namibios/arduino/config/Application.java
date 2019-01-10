package ru.namibios.arduino.config;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import ru.namibios.arduino.gui.Launcher;
import ru.namibios.arduino.model.bot.service.HttpService;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

	private static final Logger LOG = Logger.getLogger(Application.class);

	private static final String PROPERTY_FILE_NAME = "resources/application.properties";

	private static User user;

	private static ApplicationConfig config;

	public static ApplicationConfig getInstance() {

		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}

		return config;
	}

	public static void main(String[] args) {

		User user = Application.getUser();
		System.out.println(user);
	}

	public static User getUser(){
		if (user == null) {
			LOG.debug("User initialization..");

			HttpService httpService = new HttpService();
			user = new User();

			try {

				user = httpService.getUserStatus(user);

			} catch (IOException | URISyntaxException e) {
				LOG.error(ExceptionUtils.getString(e));
			}

		}
		return user;
	}

	public static void record() {
		try {
			Application.config.store(new FileOutputStream(new File(PROPERTY_FILE_NAME)), "");
		}catch (Exception e) {
            LOG.error(ExceptionUtils.getString(e));
		}
	}

	public static void restart() throws IOException {

		LOG.info("Restart application..");

		Launcher.close();

		StringBuilder cmd = new StringBuilder();
		String dir = System.getProperty("user.dir");
		cmd.append(dir);
		cmd.append(File.separator);
		cmd.append(Application.getUser().isWin() ? "run.bat" : "run.sh");
		Runtime.getRuntime().exec(cmd.toString());

		System.exit(0);

	}

}