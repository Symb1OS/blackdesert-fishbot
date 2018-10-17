package ru.namibios.arduino.model.state;

import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

public class CommandSender {

    public boolean send(Command command){
        return Keyboard.send(command);
    }

}
