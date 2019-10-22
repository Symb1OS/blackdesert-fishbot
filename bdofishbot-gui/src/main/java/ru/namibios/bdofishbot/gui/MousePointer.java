package ru.namibios.bdofishbot.gui;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

import javax.swing.*;
import java.awt.*;

public class MousePointer implements NativeMouseMotionListener {

    private JLabel mouseXY;

    public MousePointer(JLabel mouseXY) {
        this.mouseXY = mouseXY;
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        mouseXY.setText(String.format("[x: %s, y: %s]",
                (int) MouseInfo.getPointerInfo().getLocation().getLocation().getX(),
                (int) MouseInfo.getPointerInfo().getLocation().getLocation().getY()));
    }

}
