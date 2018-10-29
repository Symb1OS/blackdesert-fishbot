package ru.namibios.arduino.gui;


import javax.swing.*;
import java.awt.*;

public class CustomVerifier extends InputVerifier {

    private String message;
    private String regEx;

    public CustomVerifier(String message, String regEx) {
        this.message = message;
        this.regEx = regEx;
    }

    @Override
    public boolean verify(JComponent input) {

        String test = ((JTextField) input).getText();

        if (test.matches(regEx)) {
            input.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            return true;
        }

        input.setBorder(BorderFactory.createLineBorder(Color.RED));
        JOptionPane.showMessageDialog(input, message, "Format error", JOptionPane.ERROR_MESSAGE);

        return false;

    }
}
