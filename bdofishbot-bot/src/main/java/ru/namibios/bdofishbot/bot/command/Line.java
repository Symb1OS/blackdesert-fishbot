package ru.namibios.bdofishbot.bot.command;

import ru.namibios.bdofishbot.bot.PaletteParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.ColorChars;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Path;

import java.awt.*;
import java.io.IOException;

public class Line implements Command {

    private Screen screen;
	
	private PaletteParser paletteParser;

	public Line(String file) throws IOException {
		screen = new Screen(file, Application.getInstance().SUB_LINE(), false);
	}

	public Line() throws AWTException {
		screen = new Screen(Application.getInstance().SUB_LINE(), false);
	}

	@Override
	public String getKey() {

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_SUBLINE()) {
			screen.saveImage(Path.DEBUG_SUBLINE);
		}

		paletteParser = new PaletteParser(screen, ColorChars.values());
		paletteParser.parse();

		MatrixTemplate value = paletteParser.getValue();
		return value != null ? value.toString() : "";
	}

}