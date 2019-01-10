package ru.namibios.arduino.model.command;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.bot.service.HttpService;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.ImageUtils;

import java.awt.*;
import java.util.Date;

public class Captcha implements Command{

	private final static Logger LOG = Logger.getLogger(Captcha.class);

	public static final String REGEX = "[awsd]{0,10}";

	private String filename;

	private Screen screen;
	private String key;

	public Captcha(String filename) {
		this.filename = filename;
	}
	
	public Screen getScreen() {
		return screen;
	}

	private void init() throws AWTException {

		String name = String.valueOf(new Date().getTime());

		this.screen = new Screen(Application.getInstance().CAPTCHA());
		this.screen.saveDirty(name);
		this.screen.clearNoise(Application.getInstance().CNT_KAPCHA());
		this.screen.saveClean(name);
		this.key = "";

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_CAPTCHA()) {
			screen.saveImage(Path.DEBUG_CAPTCHA);
		}
	}

	@Override
	public String getKey(){

		try {

			init();

			HttpService http = new HttpService();

			key = http.parseByteCaptcha(filename, ImageUtils.imageToBytes(screen.getScreenShot()));

		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

		return key.replaceAll("\"", "").replaceAll("\n", "");
		
	}

}