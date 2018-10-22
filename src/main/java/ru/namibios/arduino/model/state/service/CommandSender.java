package ru.namibios.arduino.model.state.service;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Timer;
import ru.namibios.arduino.model.command.Command;

import java.io.IOException;
import java.io.PrintWriter;

public class CommandSender {

    private static final Logger LOG = Logger.getLogger(CommandSender.class);

    private static final String COMPLETE_COMMAND = "END";

    private SerialPort serialPort;

    public CommandSender() {

        serialPort = SerialPort.getCommPort(Application.getInstance().PORT());
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

    }

    private boolean isComplete() throws IOException {

        Timer timer = new Timer();

        while (!timer.isOver(1000 * 15)) {

            if (serialPort.isOpen()) {
                if (serialPort.getInputStream().available() > 0) {

                    int length = serialPort.getInputStream().available();
                    byte[] array = new byte[length];

                    int read = serialPort.getInputStream().read(array, 0, length);

                    String callback = new String(array);

                    LOG.debug("Reading " + read + " bytes: " + callback);

                    return callback.equals(COMPLETE_COMMAND);

                }

            } else {
                LOG.debug("Port is closed");
                return  false;
            }
        }

        LOG.debug("Time is over");

        return false;
    }

    public boolean send(Command command) throws IOException {

        String message = command.getKey();
        if(!"".equals(message)){
            PrintWriter output = new PrintWriter(serialPort.getOutputStream());
            output.println(message);
            output.flush();

            LOG.info("Send message[" + message.length() + "]: " + message);

            return isComplete();
        }

        return false;
    }

    public boolean isOpen(){
        return serialPort.isOpen();
    }

    public boolean openPort(){
        return serialPort.openPort();
    }

    public boolean closePort() {
        return serialPort.closePort();
    }

    public static void main(String[] args) throws IOException {

        CommandSender commandSender = new CommandSender();
        commandSender.openPort();

        if (commandSender.isOpen()) {
            System.out.println("Port is open");
            commandSender.send(() -> "sadasdasdasdsawww");
            if (commandSender.isComplete()) {
                System.out.println("Message sended");
            }

        } else {
            System.out.println("Port is closed");
        }

        commandSender.closePort();
    }

}