package ru.namibios.arduino.gui.components;

import ru.namibios.arduino.model.Touch;

import javax.swing.*;

public class TouchComponent {

    private JTextField x;
    private JTextField y;

    public TouchComponent(JTextField x, JTextField y, InputVerifier verifier) {
        this.x = x;
        this.y = y;

        this.x.setInputVerifier(verifier);
        this.y.setInputVerifier(verifier);
    }

    public void init(Touch touch){
        this.x.setText(String.valueOf(touch.getX()));
        this.y.setText(String.valueOf(touch.getY()));

    }

}
