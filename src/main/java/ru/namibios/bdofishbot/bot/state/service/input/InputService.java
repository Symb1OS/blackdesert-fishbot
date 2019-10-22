package ru.namibios.bdofishbot.bot.state.service.input;

import ru.namibios.bdofishbot.bot.command.Command;

import java.io.IOException;

public interface InputService {

    boolean send(Command command) throws IOException;

}
