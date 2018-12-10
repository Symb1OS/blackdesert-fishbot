package ru.namibios.arduino.model.state.service.input.emulation;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;
import java.util.Random;

public class AWTRobot extends AbstractEmulationInput {

    private Robot robot;

    private Random random = new Random();

    public AWTRobot() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveMouse(int x, int y) {
        x *= Application.getInstance().MOUSE_CORRECTION_FACTOR();
        y *= Application.getInstance().MOUSE_CORRECTION_FACTOR();

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
        DelayUtils.delay(random.nextInt(50) + Application.getInstance().PRESS_KEY_DELAY());
        robot.keyRelease(key);

    }

}