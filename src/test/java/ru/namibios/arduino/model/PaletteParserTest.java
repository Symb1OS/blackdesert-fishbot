package ru.namibios.arduino.model;

import org.junit.Assert;
import org.junit.Test;
import ru.namibios.arduino.model.template.Debuf;
import ru.namibios.arduino.model.template.MatrixTemplate;

import java.io.IOException;

public class PaletteParserTest {

    @Test
    public void testDayBuff() throws IOException {

        Screen screen = new Screen("resources/templates/debuff/day.jpg", false);
        PaletteParser paletteParser = new PaletteParser(screen, Debuf.values());
        paletteParser.parse();

        MatrixTemplate value = paletteParser.getValue();
        Assert.assertEquals(Debuf.DAY, value);

    }

    @Test
    public void testNightBuff() throws IOException {

        Screen screen = new Screen("resources/templates/debuff/night.jpg", false);
        PaletteParser paletteParser = new PaletteParser(screen, Debuf.values());
        paletteParser.parse();

        MatrixTemplate value = paletteParser.getValue();
        Assert.assertEquals(Debuf.NIGHT, value);

    }
}