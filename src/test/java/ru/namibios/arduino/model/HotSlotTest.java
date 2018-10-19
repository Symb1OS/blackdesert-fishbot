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
}