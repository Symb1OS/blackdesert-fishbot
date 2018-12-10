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

        double correctionX = 1;
        double correctionY = 1;

        while (true) {

            robot.mouseMove(x, y);

            int cX = (int) MouseInfo.getPointerInfo().getLocation().getLocation().getX();
            int cY = (int) MouseInfo.getPointerInfo().getLocation().getLocation().getY();

            int dx = cX - x;
            int dy = cY - y;

            boolean okX = (dx >= -2) && (dx <= 2);
            boolean okY = (dy >= -2) && (dy <= 2);

            if (okX && okY) {
                return;
            }

            if (!okX) {
                if (dx > 0) {
                    correctionX -= 0.01;
                    x *= correctionX;
                }

                if (dx < 0) {
                    correctionX += 0.01;
                    x *= correctionX;
                }
            }

            if (!okY) {
                if (dy > 0) {
                    correctionY -= 0.01;
                    y *= correctionY;
                }

                if (dy < 0) {
                    correctionY += 0.01;
                    y *= correctionY;
                }
            }

            DelayUtils.delay(20);
        }

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