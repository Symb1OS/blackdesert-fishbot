package ru.namibios.arduino.model.command;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Debuf;

import java.awt.*;
import java.io.IOException;

public class DebufStatus implements Command {

    private Screen screen;

    public DebufStatus() throws AWTException {
        this.screen = new Screen(Application.getInstance().DEBUFF_DESERT());
    }

    public DebufStatus(String file) throws IOException {
        this.screen = new Screen(file, Application.getInstance().DEBUFF_DESERT());

        if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_DEBUF()) {
            screen.saveImage(Path.DEBUG_DEBUF);
        }
    }

    @Override
    public String getKey() {

        ImageParser imageParser = new ImageParser(screen, Debuf.values());
        imageParser.parse(Screen.GRAY);

        return imageParser.getNumber();
    }

}