package ru.namibios.arduino.model.command;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.PaletteParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Debuf;
import ru.namibios.arduino.model.template.MatrixTemplate;

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