package ru.namibios.arduino.model.state.service.input.emulation;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;

public class AWTRobot extends AbstractEmulationInput {

    private Robot robot;

    public AWTRobot() {
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
        DelayUtils.delay(Application.getInstance().PRESS_KEY_DELAY());
        robot.keyRelease(key);

    }

}