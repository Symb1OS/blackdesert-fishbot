package ru.namibios.arduino.model;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Ignore
public class TimerTest {

    @Test
    public void testHasDelayAndPeriod() throws InterruptedException {

        Timer timer = new Timer(1000, 2000);

        assertEquals(false, timer.hasReady());

        Thread.sleep(1001);

        assertEquals(true, timer.hasReady());

        timer.resetTimeLastRun();

        Thread.sleep(1500);

        assertEquals(false, timer.hasReady());

        Thread.sleep(1000);

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
        long period = 500;

        Thread.sleep(300);
        assertEquals(false, timer.isOver(period));

        Thread.sleep(400);
        assertEquals(true, timer.isOver(period));

    }
}