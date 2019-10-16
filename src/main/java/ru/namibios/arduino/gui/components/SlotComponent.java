package ru.namibios.arduino.gui.components;

import ru.namibios.arduino.gui.TimeMouseConverter;
import ru.namibios.arduino.model.HotSlot;

import javax.swing.*;

public class SlotComponent {

    private JCheckBox isActive;
    private JTextField key;
    private JTextField delay;
    private JTextField period;

    public SlotComponent(JCheckBox isActive, JTextField key, JTextField delay, JTextField period) {
        this.isActive = isActive;
        this.key = key;
        this.delay = delay;
        this.period = period;
    }

    public static class Builder{

        private JCheckBox isActive;
        private JTextField key;
        private JTextField delay;
        private JTextField period;

        private HotSlot hotSlot;

        public static SlotComponent.Builder config(){
            return new SlotComponent.Builder();
        }

        public SlotComponent build(){

            isActive.setSelected(hotSlot.isActive());
            key.setText(hotSlot.getKey());
            delay.setText(String.valueOf(hotSlot.getDelay()));
            period.setText(String.valueOf(hotSlot.getPeriod()));

            delay.addMouseListener(new TimeMouseConverter(delay));
            period.addMouseListener(new TimeMouseConverter(period));

            return new SlotComponent(isActive, key, delay, period);
        }

        public Builder setActiveComponent(JCheckBox checkBox){
            this.isActive = checkBox;
            return this;
        }

        public Builder setKeyComponent(JTextField key, InputVerifier verifier){
            this.key = key;
            if (verifier != null) {
                this.key.setInputVerifier(verifier);
            }

            return this;
        }

        public Builder setDelayComponent(JTextField delay, InputVerifier verifier){
            this.delay = delay;
            if (verifier != null) {
                this.delay.setInputVerifier(verifier);
            }

            return this;
        }

        public Builder setPeriodComponent(JTextField period, InputVerifier verifier){
            this.period = period;
            if (verifier != null) {
                this.period.setInputVerifier(verifier);
            }

            return this;
        }

        public Builder setHotSlot(HotSlot hotSlot){
            this.hotSlot = hotSlot;
            return this;
        }

    }
}
