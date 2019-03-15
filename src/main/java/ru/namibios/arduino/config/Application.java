package ru.namibios.arduino.config;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import ru.namibios.arduino.gui.Launcher;
import ru.namibios.arduino.model.bot.service.HttpService;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

public class Application {

	public static final int CODE_OK = 0;
	public static final int CODE_INVALID_KEY = 1;
	public static final int CODE_USER_BLOCKED = 2;
    public static final int CODE_GAME_NOT_RUNNING = 3;
	public static final int CODE_CLOSE_COM_PORT = 4;
	public static final int CODE_RESTART_APPLICATION = 5;
	public static final int CODE_SHUTDOWN_PC = 6;
	public static final int CODE_GAME_CLIENT_CRASH = 7;
    public static final int CODE_UNSUPPORTED_RESOLUTION = 8;
	public static final int CODE_ALREADY_RUNNING = 9;
	public static final int CODE_EMPTY_STATUS_CUT = 10;
	public static final int CODE_EMPTY_STATUS_CAPTCHA = 11;
	public static final int CODE_EMPTY_LOOT = 12;
	public static final int CODE_EMPTY_CHARS = 13;
	public static final int CODE_EMPTY_TEMPLATE = 14;
	public static final int CODE_CLOSE_GUI = 15;
	public static final int CODE_USER_ALREADY_LOGGED = 16;

	private static final Logger LOG = Logger.getLogger(Application.class);

	private static final String PROPERTY_FILE_NAME = "resources/application.properties";

	public static final String SESSION = UUID.randomUUID().toString();

	private static User user;

	private static ApplicationConfig config;

	public static ApplicationConfig getInstance() {

		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}

		return config;
	}

	public static User getUser(){
		if (user == null) {
			LOG.debug("User initialization..");

			HttpService httpService = new HttpService();
			user = new User();

			try {

				user = httpService.getUserStatus(user);
				user.saveHash();

				if (user.getCode() != Application.CODE_OK) {
					LOG.error("Code " + user.getCode() + ". Description: " + user.getMessage());
					JOptionPane.showMessageDialog(null, "Description: " + user.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
					Application.closeBot(user.getCode());
				}

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

	public static void closeBot(int status){

		LOG.info("Close bot with status - " + status);

		Launcher.close();

		HttpService httpService = new HttpService();

		try {

			httpService.close(status);

		} catch (IOException e) {
			LOG.error(ExceptionUtils.getString(e));
		}

		System.exit(status);

	}

	public static void restart() throws IOException {

		LOG.info("Restart application..");

		Launcher.close();

		StringBuilder cmd = new StringBuilder();
		cmd.append(System.getProperty("user.dir"))
			.append(File.separator)
			.append(Application.getUser().isWin() ? "run.bat" : "run.sh");

		Runtime.getRuntime().exec(cmd.toString());

		closeBot(Application.CODE_RESTART_APPLICATION);

	}

	public static void shutdownPc() {

		LOG.info("Shutdown PC..");

		Launcher.close();

		try {

			Runtime.getRuntime().exec("shutdown /s");

		} catch (IOException e) {
			LOG.error(ExceptionUtils.getString(e));
		}

		closeBot(CODE_SHUTDOWN_PC);

	}
}