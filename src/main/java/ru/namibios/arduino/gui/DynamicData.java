package ru.namibios.arduino.gui;

import javax.swing.*;
import java.awt.*;

public class DynamicData extends Thread {

    private JLabel mouseXY;

    public DynamicData(JLabel mouseXY) {
        this.mouseXY = mouseXY;
    }

    @Override
    public void run() {
        super.run();

        if (mouseXY != null) {

            while (true) {

                mouseXY.setText(String.format("[x: %s, y: %s]",
                        (int) MouseInfo.getPointerInfo().getLocation().getLocation().getX(),
                        (int) MouseInfo.getPointerInfo().getLocation().getLocation().getY()));
            }

        }
    }
}
