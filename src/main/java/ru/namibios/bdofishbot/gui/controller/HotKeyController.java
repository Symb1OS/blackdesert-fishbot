package ru.namibios.bdofishbot.gui.controller;

import org.apache.log4j.Logger;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import ru.namibios.bdofishbot.cli.Application;

import javax.swing.*;

public class HotKeyController extends NativeKeyAdapter {

    private static final Logger LOG = Logger.getLogger(HotKeyController.class);

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
            LOG.debug("Click start");
            buttonStart.doClick();
        }

        if (keyCode == Application.getInstance().HOT_KEY_STOP()) {
            LOG.debug("Click Stop");
            buttonStop.doClick();
        }

    }
}