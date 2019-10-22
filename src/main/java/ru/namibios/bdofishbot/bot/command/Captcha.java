package ru.namibios.bdofishbot.bot.command;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.state.service.HttpService;
import ru.namibios.bdofishbot.config.Application;
import ru.namibios.bdofishbot.config.Message;
import ru.namibios.bdofishbot.config.Path;
import ru.namibios.bdofishbot.utils.ExceptionUtils;
import ru.namibios.bdofishbot.utils.ImageUtils;

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