package ru.namibios.arduino.model.status;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.StatusCaptchaTemplate;

import java.awt.*;
import java.io.IOException;

public class StatusCaptcha implements Status<StatusCaptchaTemplate>{

	private Screen screen;

	public StatusCaptcha() throws AWTException {
		this.screen = new Screen(Application.getInstance().STATUS_CAPTCHA());

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_STATUS_CAPTCHA()) {
			screen.saveImage(Path.DEBUG_STATUSCAPTCHA);
		}

	}

	public StatusCaptcha(String filename, Rectangle zone) throws IOException {
		this.screen = new Screen(filename, zone);
	}

	public StatusCaptchaTemplate getNameTemplate() {
		ImageParser parser = new ImageParser(screen, StatusCaptchaTemplate.values());
		parser.parse(Screen.GRAY);
		
		return (StatusCaptchaTemplate) parser.getNameTemplateBySubImage();
	}

}