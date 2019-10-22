package ru.namibios.bdofishbot.bot;

import org.junit.Assert;
import org.junit.Test;
import ru.namibios.bdofishbot.bot.template.Debuf;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;

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