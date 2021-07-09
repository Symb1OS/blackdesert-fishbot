package ru.namibios.bdofishbot.bot.command;

import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.Chars;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Path;

import java.awt.*;
import java.io.IOException;

public class WaitFish implements Command{

    private final Screen screen;

    private int coef = Application.getInstance().COEF_WHITE();

	public WaitFish(String file, Rectangle rectangle) throws AWTException, IOException {
		this.screen = new Screen(file,rectangle);
	}

	public WaitFish(Rectangle rectangle) throws AWTException {
		this.screen = new Screen(rectangle);
	}

	@Override
	public String getKey() {

		ImageParser imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(new Color(coef,coef,coef));

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_WAITFISH()) {
			screen.saveImage(Path.DEBUG_WAITFISH);
		}

		MatrixTemplate template = imageParser.getNameTemplateBySubImage();
		return template != null ? template.toString() : ShortCommand.IGNORE.getKey();
	}

}