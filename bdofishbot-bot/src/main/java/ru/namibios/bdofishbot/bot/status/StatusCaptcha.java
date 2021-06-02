package ru.namibios.bdofishbot.bot.status;

import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.StatusCaptchaTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Path;

import java.awt.*;
import java.io.IOException;

public class StatusCaptcha implements Status<StatusCaptchaTemplate>{

	private Screen screen;

	public StatusCaptcha() throws AWTException {
		this.screen = new Screen(Application.getInstance().STATUS_CAPTCHA());

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_STATUS_CAPTCHA()) {
			new Screen(Application.getInstance().FULL_SCREEN()).saveImage(Path.DEBUG_STATUSCAPTCHA);
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