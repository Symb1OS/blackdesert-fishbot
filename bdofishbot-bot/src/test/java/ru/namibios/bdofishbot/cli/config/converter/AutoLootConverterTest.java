package ru.namibios.bdofishbot.cli.config.converter;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AutoLootConverterTest {

    private AutoLootConverter autoLootConverter;

    @Before
    public void setUp() throws Exception {
        this.autoLootConverter = new AutoLootConverter();
    }

    @Test
    public void testCount() {

        for (int i = 3; i <= 9; i++) {
            List<Rectangle> convert = autoLootConverter.convert(null, "1539, 597, 43, 43, 47, 47, " + i);
            assertEquals(i, convert.size());
        }

    }
}