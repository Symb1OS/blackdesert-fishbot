package ru.namibios.bdofishbot.bot.service.input;

import ru.namibios.bdofishbot.bot.command.Command;

import java.io.IOException;

public interface InputService {

    boolean send(Command command) throws IOException;

}
