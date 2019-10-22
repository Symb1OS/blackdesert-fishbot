package ru.namibios.bdofishbot.bot.service.input.emulation;

import ru.namibios.bdofishbot.utils.DelayUtils;

import java.awt.event.KeyEvent;

abstract public class AbstractEmulationInput {

    public void sendInput(String input){
        input = input.toUpperCase();

        for (char key : input.toCharArray()) {
            pressKey(key);
        }
    }
    public void sendInput(int key){
        pressKey(key);
    }

    public void clickLeft(int x, int y){
        moveMouse(x, y);
        DelayUtils.delay(200);
        pressMouse(KeyEvent.BUTTON1_MASK);
    }

    public void clickRight(int x, int y){
        moveMouse(x, y);
        DelayUtils.delay(200);
        pressMouse(KeyEvent.BUTTON3_MASK);
    }

    public abstract void moveMouse(int x, int y);

    public abstract void pressMouse(int button);

    public abstract void pressKey(int key);

}