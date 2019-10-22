package ru.namibios.bdofishbot.bot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testConverterWithRandom() {

        HotSlot hotSlot = new HotSlot();

        hotSlot.setDelay("2m");
        hotSlot.setRandomDelay("1m");

        hotSlot.setPeriod("3m");
        hotSlot.setRandomDelay("2m");

        assertEquals(1000 * 60 * 2, hotSlot.getDelay());
        assertEquals(1000 * 60 * 3, hotSlot.getPeriod());

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

    @Test
    public void testRandomDelay() {

        int a = 100;
        int b = 200;

        HotSlot hotSlot = new HotSlot();
        hotSlot.setDelay(a);
        hotSlot.setRandomDelay(b);

        for (int i = 0; i < 1000; i++) {
            assertTrue(hotSlot.getDelay() >= a);
            assertTrue(hotSlot.getDelay() < b);
        }

    }

    @Test
    public void testRandomPeriod() {

        int min = 500;
        int max = 600;

        HotSlot hotSlot = new HotSlot();
        hotSlot.setPeriod(min);
        hotSlot.setRandomPeriod(max);

        for (int i = 0; i < 1000; i++) {
            assertTrue(hotSlot.getPeriod() >= min);
            assertTrue(hotSlot.getPeriod() < max);
        }

    }
}