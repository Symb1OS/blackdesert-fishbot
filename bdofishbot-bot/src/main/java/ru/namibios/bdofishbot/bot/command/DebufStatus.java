package ru.namibios.bdofishbot.bot.command;

import ru.namibios.bdofishbot.bot.PaletteParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.Debuf;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Path;

import java.awt.*;

public class DebufStatus implements Command {

    private Screen screen;

    public DebufStatus() throws AWTException {
        this.screen = new Screen(Application.getInstance().DEBUFF_DESERT(), false);
        if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_DEBUF()) {
            screen.saveImage(Path.DEBUG_DEBUF);
        }
    }

    @Override
    public String getKey() {

        PaletteParser parser = new PaletteParser(screen, Debuf.values());
        parser.parse();

        MatrixTemplate value = parser.getValue();
        return value != null ? value.toString() : "";
    }

}