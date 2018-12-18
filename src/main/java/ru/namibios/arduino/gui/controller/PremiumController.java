package ru.namibios.arduino.gui.controller;

import ru.namibios.arduino.gui.view.PremiumView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PremiumController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        PremiumView premiumView = new PremiumView();
    }
}
