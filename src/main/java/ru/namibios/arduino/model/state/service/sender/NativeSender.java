package ru.namibios.arduino.model.state.service.sender;

import me.coley.simplejna.Keyboard;
import me.coley.simplejna.Mouse;
import ru.namibios.arduino.utils.DelayUtils;

public class NativeSender implements Sender {

    @Override
    public void sendInput(String input) {
        input = input.toUpperCase();

        for (char key : input.toCharArray()) {
            Keyboard.pressKey(key);
        }
    }

    @Override
    public void sendInput(int key) {
        pressKey(key);
    }

    @Override
    public void clickLeft(int x, int y) {
        Mouse.mouseLeftClick(x, y);
    }

    @Override
    public void clickRight(int x, int y) {
        Mouse.mouseRightClick(x, y);
    }

    private void pressKey(int key){
        Keyboard.sendKeyDown(key);
        DelayUtils.delay(100);
        Keyboard.sendKeyUp(key);
    }
}