package ru.namibios.arduino.model.state.service.sender;

public interface Sender {

    void sendInput(String input);
    void sendInput(int key);

    void clickLeft(int x, int y);
    void clickRight(int x, int y);

}
