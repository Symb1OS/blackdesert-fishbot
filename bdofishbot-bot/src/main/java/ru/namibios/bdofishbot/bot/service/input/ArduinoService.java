package ru.namibios.bdofishbot.bot.service.input;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Timer;
import ru.namibios.bdofishbot.bot.command.Command;
import ru.namibios.bdofishbot.cli.Application;

import java.io.IOException;
import java.io.PrintWriter;

public class ArduinoService implements InputService{

    private static final Logger LOG = Logger.getLogger(ArduinoService.class);

    private static final String COMPLETE_COMMAND = "END";

    private SerialPort serialPort;

    public ArduinoService() {

        serialPort = SerialPort.getCommPort(Application.getInstance().COM_PORT());
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

        if (!serialPort.openPort()) {
            LOG.info("Port is closed. Check your port in settings or change the mode work");
            Application.closeBot(Application.CODE_CLOSE_COM_PORT);
        }

        LOG.info("Port is open..");
    }

    private boolean isComplete() throws IOException {

        Timer timer = new Timer();

        while (!timer.isOver(Application.getInstance().INPUT_TIMEOUT())) {

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
                LOG.error("Port is closed. Check you port in settings");
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

}