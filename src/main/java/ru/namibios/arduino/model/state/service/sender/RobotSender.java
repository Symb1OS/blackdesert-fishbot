package ru.namibios.arduino.model.state.service.sender;

import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;

public class RobotSender extends AbstractSender {

    private Robot robot;

    public RobotSender() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveMouse(int x, int y) {
        robot.mouseMove(x, y);
    }

    @Override
    public void pressMouse(int button) {
        robot.mousePress(button);
        DelayUtils.delay(200);
        robot.mouseRelease(button);
        DelayUtils.delay(200);
    }

    @Override
    public void pressKey(int key) {
        robot.keyPress(key);
        DelayUtils.delay(200);
        robot.keyRelease(key);

    }

}