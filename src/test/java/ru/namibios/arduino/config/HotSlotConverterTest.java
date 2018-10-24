package ru.namibios.arduino.config;

import org.junit.Test;
import ru.namibios.arduino.config.converter.HotSlotConverter;
import ru.namibios.arduino.model.HotSlot;

import static org.junit.Assert.assertEquals;

public class HotSlotConverterTest {

    @Test
    public void testConvert() {

        HotSlotConverter hotSlotConverter = new HotSlotConverter();
        HotSlot hotSlot = hotSlotConverter.convert(null, "false, 1, 20s, 30m");

        assertEquals(false, hotSlot.isActive());
        assertEquals("1", hotSlot.getKey());
        assertEquals(20 * 1000, hotSlot.getDelay());
        assertEquals(30 * 60 * 1000, hotSlot.getPeriod());

    }
}