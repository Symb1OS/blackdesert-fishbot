package ru.namibios.arduino.model.state.service;

import com.fazecast.jSerialComm.SerialPort;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CommandSender {

    private SerialPort serialPort;

    private InputStream inputStream;
    private OutputStream outputStream;

    public boolean isOpen(){
        return serialPort.isOpen();
    }

    private boolean closePort() {
        return serialPort.closePort();
    }

    public boolean send(Command command){
        return Keyboard.send(command);
    }

    public CommandSender() {

      /*  serialPort = SerialPort.getCommPort(Application.getInstance().PORT());
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

        serialPort.openPort();

        inputStream = serialPort.getInputStream();
        outputStream = serialPort.getOutputStream();

        try {
            if (inputStream.available() != 0) {
                System.out.println("Port is not empty!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    public boolean isSend() throws IOException {

        while (true) {

            if (serialPort.isOpen()) {
                if (inputStream.available() > 0) {
                    int length = inputStream.available();
                    byte[] array = new byte[length];

                    int read = inputStream.read(array, 0, length);
                    System.out.println("Reading " + read + " bytes");

                    if (new String(array).equals("END")) {
                        return true;
                    }
                }

            } else {
                System.out.println("Port is closed");
                return  false;
            }
        }
    }

    public void sendNew(Command command) throws IOException {

        String message = command.getKey();
        if(!"".equals(message)){
            PrintWriter output = new PrintWriter(outputStream);
            output.println(message);
            output.flush();

            System.out.println("Send message[" + message.length() + "]: " + message);

        }

    }

    public static void main(String[] args) throws IOException {

        CommandSender commandSender = new CommandSender();

        if (commandSender.isOpen()) {
            System.out.println("Port is open");
            commandSender.sendNew(() -> "sadasdasdasdsawww");
            if (commandSender.isSend()) {
                System.out.println("Message sended");
            }

        } else {
            System.out.println("Port is closed");
        }
    }

}