package ru.namibios.arduino.config;

import org.junit.Test;
import ru.namibios.arduino.config.converter.HotSlotConverter;
import ru.namibios.arduino.model.HotSlot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testConvertSmartTask() {

        HotSlotConverter hotSlotConverter = new HotSlotConverter();
        HotSlot hotSlot = hotSlotConverter.convert(null, "true, 10s, Autofish");

        assertEquals(true, hotSlot.isActive());
        assertEquals(null, hotSlot.getKey());
        assertEquals(10 * 1000, hotSlot.getDelay());
        assertEquals(0, hotSlot.getPeriod());
        assertEquals("Autofish", hotSlot.getCommand());

    }

    @Test
    public void testConvertSlotTask() {

        HotSlotConverter hotSlotConverter = new HotSlotConverter();
        HotSlot hotSlot = hotSlotConverter.convert(null, "false, 7, 0m, 120m, Beer");

        assertEquals(false, hotSlot.isActive());
        assertEquals("7", hotSlot.getKey());
        assertEquals(0, hotSlot.getDelay());
        assertEquals(120 * 60 * 1000, hotSlot.getPeriod());
        assertEquals("Beer", hotSlot.getCommand());

    }

    @Test
    public void testWithoutFormat() {
        HotSlotConverter hotSlotConverter = new HotSlotConverter();
        HotSlot hotSlot = hotSlotConverter.convert(null, "false, 1, 1000, 3000");

        assertEquals(false, hotSlot.isActive());
        assertEquals("1", hotSlot.getKey());
        assertEquals(1000, hotSlot.getDelay());
        assertEquals(3000, hotSlot.getPeriod());
    }

    @Test
    public void testUnknownConstructor() {
        HotSlotConverter hotSlotConverter = new HotSlotConverter();
        HotSlot hotSlot = hotSlotConverter.convert(null, "Unknown");

        assertNull(hotSlot);
    }
}