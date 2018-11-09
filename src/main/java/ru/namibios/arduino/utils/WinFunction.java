package ru.namibios.arduino.utils;

import me.coley.simplejna.Keyboard;
import me.coley.simplejna.Mouse;

import java.awt.*;

public class WinFunction {

    public static void sendToInput(String input) {
        input = input.toUpperCase();

        System.out.println("Send " + input);
        for (char key : input.toCharArray()) {
            Keyboard.pressKey(key);
        }
    }

    public static void sendToInput(int input) {
        System.out.println("Send " + input);
        Keyboard.pressKey(input);
    }

    public static void clickLeft(int x, int y){
        System.out.println("Left click : ["  + x + ", " + y + "]");
        Mouse.mouseLeftClick(x, y);
    }

    public static void robotSend(int input){
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(input);
    }

    public static void robotClick(int x, int y, int button){
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.mousePress(button);
        DelayUtils.delay(100);
        robot.mouseRelease(button);

    }

}