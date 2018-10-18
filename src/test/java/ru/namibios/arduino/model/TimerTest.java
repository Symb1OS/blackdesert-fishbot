package ru.namibios.arduino.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimerTest {

    @Test
    public void testHasDelayAndPeriod() throws InterruptedException {

        Timer timer = new Timer(50, 100);

        assertEquals(false, timer.hasReady());

        Thread.sleep(51);

        assertEquals(true, timer.hasReady());

        timer.resetTimeLastRun();

        Thread.sleep(90);

        assertEquals(false, timer.hasReady());

        Thread.sleep(11);

        assertEquals(true, timer.hasReady());
    }

    @Test
    public void testWithoutDelay() throws InterruptedException {

        Timer timer = new Timer(0,100);

        assertEquals(true, timer.hasReady());

        timer.resetTimeLastRun();

        Thread.sleep(50);
        assertEquals(false, timer.hasReady());

        Thread.sleep(51);
        assertEquals(true, timer.hasReady());

    }

    @Test
    public void testDefaultTimer() throws InterruptedException {

        Timer timer = new Timer();
        long period = 15;

        Thread.sleep(10);
        assertEquals(false, timer.isOver(period));

        Thread.sleep(10);
        assertEquals(true, timer.isOver(period));

    }
}