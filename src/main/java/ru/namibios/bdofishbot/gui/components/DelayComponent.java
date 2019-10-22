package ru.namibios.bdofishbot.gui.components;

import javax.swing.*;

public class DelayComponent {

    private JTextField before;
    private JTextField after;

    public DelayComponent(JTextField before, JTextField after, InputVerifier verifier) {
        this.before = before;
        this.after = after;

        this.before.setInputVerifier(verifier);
        this.after.setInputVerifier(verifier);
    }

    public void init(int before, int after){
        this.before.setText(String.valueOf(before));
        this.after.setText(String.valueOf(after));
    }

}