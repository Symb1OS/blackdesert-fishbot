package ru.namibios.arduino.model.state.service.input.emulation;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;
import java.util.Random;

public class AWTRobot extends AbstractEmulationInput {

    private static final Logger LOG = Logger.getLogger(AWTRobot.class);

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

        int overflow = 0;
        while (overflow < 250) {

            robot.mouseMove(x, y);

            int cX = (int) MouseInfo.getPointerInfo().getLocation().getLocation().getX();
            int cY = (int) MouseInfo.getPointerInfo().getLocation().getLocation().getY();

            LOG.debug(String.format("Destination[%s, %s]; Position[%s, %s]", x, y, cX, cY));

            if (cX == x && cY == y) {
                LOG.debug("Mouse move success");
                return;
            }

            DelayUtils.delay(20);
            overflow++;

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