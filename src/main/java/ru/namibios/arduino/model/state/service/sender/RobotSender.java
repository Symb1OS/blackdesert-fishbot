package ru.namibios.arduino.model.state.service.sender;


import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotSender implements Sender{

    private final Robot robot;

    public RobotSender() throws AWTException {
        robot = new Robot();
    }

    @Override
    public void sendInput(String input) {
        input = input.toUpperCase();

        for (char key : input.toCharArray()) {
            pressKey(key);
        }
    }

    @Override
    public void sendInput(int input) {
        pressKey(input);
    }

    @Override
    public void clickLeft(int x, int y) {
        moveMouse(x, y);
        DelayUtils.delay(200);
        pressMouse(KeyEvent.BUTTON1_MASK);
    }

    @Override
    public void clickRight(int x, int y) {
        moveMouse(x, y);
        DelayUtils.delay(200);
        pressMouse(KeyEvent.BUTTON3_MASK);
    }

    private void moveMouse(int x, int y){
        robot.mouseMove(x, y);
    }

    private void pressMouse(int button){
        robot.mousePress(button);
        DelayUtils.delay(120);
        robot.mouseRelease(button);
    }

    private void pressKey(int key){
        robot.keyPress(key);
        DelayUtils.delay(100);
        robot.keyRelease(key);
    }

}