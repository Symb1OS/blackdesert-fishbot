package ru.namibios.arduino.model;

import org.junit.Ignore;
import org.junit.Test;
import ru.namibios.arduino.config.Application;

import java.awt.*;

import static org.junit.Assert.assertEquals;


@Ignore
public class ScreenMigrationTest {

    public static void rectangletEq(Rectangle expected, Rectangle actual, double delta){
        assertEquals(expected.getX(), actual.getX(), delta);
        assertEquals(expected.getY(), actual.getY(), delta);
        assertEquals(expected.getWidth(), actual.getWidth(), delta);
        assertEquals(expected.getHeight(), actual.getHeight(), delta);
    }

    @Test
    public void testFullScreen() {

        Rectangle expected = Screen.FULL_SCREEN;
        Rectangle actual = Application.getInstance().FULL_SCREEN();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testSpace() {

        Rectangle expected = Screen.SPACE;
        Rectangle actual = Application.getInstance().SPACE();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testLine() {

        Rectangle expected = Screen.LINE;
        Rectangle actual = Application.getInstance().LINE();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testSubLine() {

        Rectangle expected = Screen.SUB_LINE;
        Rectangle actual = Application.getInstance().SUB_LINE();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testStatusCut() {

        Rectangle expected = Screen.STATUS_CUT;
        Rectangle actual = Application.getInstance().STATUS_CUT();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testCaptcha() {

        Rectangle expected = Screen.KAPCHA;
        Rectangle actual = Application.getInstance().KAPCHA();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testStatusCaptcha() {

        Rectangle expected = Screen.STATUS_KAPCHA;
        Rectangle actual = Application.getInstance().STATUS_KAPCHA();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testLootOne() {

        Rectangle expected = Screen.LOOT_SLOT_ONE;
        Rectangle actual = Application.getInstance().LOOT_SLOT_ONE();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testLootTwo() {

        Rectangle expected = Screen.LOOT_SLOT_TWO;
        Rectangle actual = Application.getInstance().LOOT_SLOT_TWO();

        rectangletEq(expected,actual,0.1);

    }

    @Test
    public void testChat() {

        Rectangle expected = Screen.CHAT;
        Rectangle actual = Application.getInstance().CHAT();

        rectangletEq(expected,actual,0.1);

    }
}
