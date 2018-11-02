package ru.namibios.arduino.model.command;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.state.service.HttpService;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.ImageUtils;
import ru.namibios.arduino.utils.PythonExec;

import java.awt.*;
import java.io.IOException;

public class Captcha implements Command{

	private final static Logger LOG = Logger.getLogger(Captcha.class);

	private Screen screen;
	private String key;
	private String filename;
	
	public Captcha() throws AWTException  {
		this.screen = new Screen(Application.getInstance().KAPCHA());
		this.screen.clearNoise(Application.getInstance().CNT_KAPCHA());
		this.filename = screen.saveImage("kapcha");
		this.key = "";
	}
	
	public Captcha(String file) throws IOException{
		this.screen = new Screen(file);
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public String getRezultKey() {
		return key;
	}

	public String localParsing(){

		try {

			key = PythonExec.exec(filename);
			key = key.replaceAll("0", "w")
					.replaceAll("1", "s")
					.replaceAll("2", "a")
					.replaceAll("3", "d")
					.replaceAll("4", "");

		}catch (IOException e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

		return key;
	}

	@Override
	public String getKey(){

		HttpService http = new HttpService();

		try {

			key = http.parseByteCaptcha(System.getProperty("user.name"), ImageUtils.imageToBytes(screen.getScreenShot()));

		} catch (IOException e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

		return key.replaceAll("\"", "").replaceAll("\n", "");
		
	}

}