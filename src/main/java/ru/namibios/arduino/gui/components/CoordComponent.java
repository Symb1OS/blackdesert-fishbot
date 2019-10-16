package ru.namibios.arduino.gui.components;

import javax.swing.*;
import java.awt.*;

public class CoordComponent {

    private static final String FORMAT_SCREEN = "%s,%s,%s,%s";

    private JTextField x;
    private JTextField y;
    private JTextField width;
    private JTextField height;

    public CoordComponent(JTextField x, JTextField y, JTextField width, JTextField height, InputVerifier verifier) {
        this.x = x;
        this.x.setInputVerifier(verifier);

        this.y = y;
        this.y.setInputVerifier(verifier);

        this.width = width;
        this.width.setInputVerifier(verifier);

        this.height = height;
        this.height.setInputVerifier(verifier);
    }

    private void init(Rectangle rectangle) {
        x.setText(String.valueOf(rectangle.x));
        y.setText(String.valueOf(rectangle.y));
        width.setText(String.valueOf(rectangle.width));
        height.setText(String.valueOf(rectangle.height));
    }

    public static class Builder{

        private JTextField x;
        private JTextField y;
        private JTextField w;
        private JTextField h;

        private InputVerifier verifier;

        private Rectangle rectangle;

        public static Builder config(){
            return new Builder();
        }

        public CoordComponent build(){

            CoordComponent coord = new CoordComponent(x, y, w, h, verifier);
            coord.init(rectangle);

            return coord;
        }

        public Builder setComponentX(JTextField field){
            x = field;
            return this;
        }

        public Builder setComponentY(JTextField field){
            y = field;
            return this;
        }

        public Builder setComponentWidth(JTextField field){
            w = field;
            return this;
        }

        public Builder setComponentHeight(JTextField field){
            h = field;
            return this;
        }

        public Builder setRectangle(Rectangle rectangle){
            this.rectangle = rectangle;
            return this;
        }

        public Builder setVerifier(InputVerifier verifier){
            this.verifier = verifier;
            return this;
        }
    }

}
