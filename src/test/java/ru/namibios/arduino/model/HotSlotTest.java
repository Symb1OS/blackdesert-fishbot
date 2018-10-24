package ru.namibios.arduino.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HotSlotTest {

    @Test
    public void testConverter() {

        HotSlot hotSlot = new HotSlot();

        hotSlot.setDelay("100s");
        hotSlot.setPeriod("2m");

        assertEquals(1000 * 60 * 2, hotSlot.getPeriod() );
        assertEquals(100 * 1000, hotSlot.getDelay());

    }

    @Test
    public void testStringLong() {
        HotSlot hotSlot = new HotSlot();

        hotSlot.setDelay("10000");
        hotSlot.setPeriod("20000");

        assertEquals(10000, hotSlot.getDelay());
        assertEquals(20000, hotSlot.getPeriod());
    }

    @Test
    public void testOneLong() {
        HotSlot hotSlot = new HotSlot();

        hotSlot.setDelay("0");
        hotSlot.setPeriod("5");

        assertEquals(0, hotSlot.getDelay());
        assertEquals(5, hotSlot.getPeriod());
    }
}