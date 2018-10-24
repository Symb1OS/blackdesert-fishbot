package ru.namibios.arduino.config.converter;

import com.fazecast.jSerialComm.SerialPort;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class SerialPortConverter implements Converter<SerialPort>{

	@Override
	public SerialPort convert(Method method, String text) {
		SerialPort serialPort = SerialPort.getCommPort(text.trim());
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		return serialPort;
	}

}