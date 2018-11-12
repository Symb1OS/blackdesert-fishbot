package ru.namibios.arduino.model.state.service.input;

import ru.namibios.arduino.model.command.Command;

import java.io.IOException;

public interface InputService {

    boolean send(Command command) throws IOException;

}
