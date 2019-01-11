package ru.namibios.arduino.gui.controller;

import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import ru.namibios.arduino.config.Application;

import javax.swing.*;

public class HotKeyController extends NativeKeyAdapter {

    private JButton buttonStart;
    private JButton buttonStop;

    public HotKeyController(JButton buttonStart, JButton buttonStop) {

        this.buttonStart = buttonStart;
        this.buttonStop = buttonStop;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        int keyCode = nativeKeyEvent.getKeyCode();

        if (keyCode == Application.getInstance().HOT_KEY_START()) {
            buttonStart.doClick();
        }

        if (keyCode == Application.getInstance().HOT_KEY_STOP()) {
            buttonStop.doClick();
        }

    }
}