package ru.namibios.bdofishbot.cli;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.service.HttpService;
import ru.namibios.bdofishbot.cli.config.ApplicationConfig;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.cli.config.Path;
import ru.namibios.bdofishbot.cli.config.User;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
	public static final int CODE_INIT_STATUS_CUT = 10;
	public static final int CODE_INIT_STATUS_CAPTCHA = 11;
	public static final int CODE_INIT_LOOT = 12;
	public static final int CODE_INIT_CHARS = 13;
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

	public static void sync(){

		LOG.info("Start sync..");

		try {

			LOG.info("Create archive..");

			String lootFolder = Path.RESOURCES + "loot";
			String archive = lootFolder + ".zip";

			Files.deleteIfExists(Paths.get(archive));

			ZipFile zipFile = new ZipFile(archive);
			zipFile.addFolder(lootFolder, new ZipParameters());

			LOG.info("Archive " + archive + " created..");

			LOG.info("Start sync with server..");

			HttpService httpService = new HttpService();
			httpService.sync(new File(archive));

		} catch (ZipException | IOException e) {
            LOG.error(ExceptionUtils.getString(e));
		}

    }

	public static void closeBot(int status){

		LOG.info("Close bot with status - " + status);

		if (Application.getInstance().LOOT_SYNC()) {
			sync();
		}

		close();

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

		close();

		StringBuilder cmd = new StringBuilder();
		cmd.append(System.getProperty("user.dir"))
			.append(File.separator)
			.append(Application.getUser().isWin() ? "run.bat" : "run.sh");

		Runtime.getRuntime().exec(cmd.toString());

		closeBot(Application.CODE_RESTART_APPLICATION);

	}

	public static void shutdownPc() {

		LOG.info("Shutdown PC..");

		close();

		try {

			Runtime.getRuntime().exec("shutdown /s");

		} catch (IOException e) {
			LOG.error(ExceptionUtils.getString(e));
		}

		closeBot(CODE_SHUTDOWN_PC);

	}

	public static final Map<String, Locale> LOCALES = new HashMap<>();

	private static ServerSocket socket;
	private static final int PORT = Application.getInstance().LOCAL_PORT();

	static {
		LOCALES.put("English", new Locale("en", "US"));
		LOCALES.put("Русский", new Locale("ru", "RU"));
	}

	private static void close(){

		try {

			socket.close();

		} catch (Exception e) {
			LOG.error(ExceptionUtils.getString(e));
		}
	}

	public static void check() {

		Check checks = new InstanceCheck();
		checks.setNext(new HostAvailabilityCheck())
				.setNext(new ResolutionCheck());

		checks.check();

	}

	public static Locale getLocale() {
		String language = Application.getInstance().LANGUAGE();
		LOG.debug("Language: " + language);

		Locale locale = LOCALES.get(language);
		if (locale == null) {
			locale = LOCALES.get("English");
			LOG.debug("Unknown locale. Setting by default");
		}

		return locale;
	}

	private static abstract class Check {

		private Check nextCheck;

		public Check setNext(Check nextCheck) {
			this.nextCheck = nextCheck;
			return nextCheck;
		}

		protected boolean next(){

			if (nextCheck == null) {
				return true;
			}

			return nextCheck.check();
		}

		public abstract boolean check();

	}

	private static class InstanceCheck extends Check {

		@Override
		public boolean check() {

			try {

				socket = new ServerSocket(PORT,0, InetAddress.getByAddress(new byte[] {127,0,0,1}));
			}

			catch (BindException e) {
				JOptionPane.showMessageDialog(null, "Program already running on port: " + Application.getInstance().LOCAL_PORT(), "Warning", JOptionPane.ERROR_MESSAGE);
				LOG.error("Program already running on port: " + Application.getInstance().LOCAL_PORT());
				LOG.error(ExceptionUtils.getString(e));
				Application.closeBot(Application.CODE_ALREADY_RUNNING);
				return false;
			}
			catch (IOException e) {
				LOG.error("Unexpected error.");
				LOG.error(ExceptionUtils.getString(e));
				Application.closeBot(Application.CODE_ALREADY_RUNNING);
				return false;
			}

			return next();
		}

	}

	private static class HostAvailabilityCheck extends Check {

		private static final Logger LOG = Logger.getLogger(HostAvailabilityCheck.class);

		@Override
		public boolean check() {

			try (Socket s = new Socket()) {

				String url = Application.getInstance().URL_CAPTCHA_SERVICE();
				String ip = url.substring(0, url.indexOf(":"));
				String port = url.substring(url.indexOf(":") + 1 );

				s.connect(new InetSocketAddress(ip, Integer.valueOf(port)), 5000);
				return next();

			} catch (IOException e) {
				LOG.error(ExceptionUtils.getString(e));
			}

			LOG.error(Message.SERVER_NOT_AVAILABLE_EN);
			JOptionPane.showMessageDialog(null, Message.SERVER_NOT_AVAILABLE_EN, "Warning", JOptionPane.ERROR_MESSAGE);

			return false;
		}
	}

	private static class ResolutionCheck extends Check {

		private static final Logger LOG = Logger.getLogger(ResolutionCheck.class);

		@Override
		public boolean check() {

			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();

			LOG.debug("width = " + width);
			LOG.debug("height = " + height);

			if (width != 1920 || height != 1080) {
				LOG.error("Unsupported resolution: " + width + "x" + height);
				JOptionPane.showMessageDialog(null, "Supporting only 1920x1080", "Unsupported resolution", JOptionPane.ERROR_MESSAGE);
				Application.closeBot(Application.CODE_UNSUPPORTED_RESOLUTION);
				return false;
			}

			return next();
		}
	}


}