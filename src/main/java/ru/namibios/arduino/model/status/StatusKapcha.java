package ru.namibios.arduino.model.status;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

import java.awt.*;
import java.io.IOException;

public class StatusKapcha implements Status<StatusKapchaTemplate>{

	private Screen screen;

	public StatusKapcha() throws AWTException {
		this.screen = new Screen(Application.getInstance().STATUS_KAPCHA());

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_STATUS_CAPTCHA()) {
			screen.saveImage(Path.DEBUG_STATUSCAPTCHA);
		}

	}

	public StatusKapcha(String filename) throws IOException {
		this.screen = new Screen(filename);
	}

	public StatusKapchaTemplate getNameTemplate() {
		ImageParser parser = new ImageParser(screen, StatusKapchaTemplate.values());
		parser.parse(Screen.GRAY);
		
		return (StatusKapchaTemplate) parser.getNameTemplateBySubImage();
	}

}