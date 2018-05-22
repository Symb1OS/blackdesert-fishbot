package ru.namibios.arduino.config;

import java.io.File;
import java.io.FileOutputStream;

import org.aeonbits.owner.ConfigFactory;

import com.fazecast.jSerialComm.SerialPort;

public class Application {

	public static final String PROPERTY_FILE_NAME = "resources/application.properties";
	
	private static ApplicationConfig config;
	
	private static SerialPort serialPort;
	
	private Application() {}
	
	public static ApplicationConfig getInstance() {
		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
			
			serialPort = SerialPort.getCommPort(config.PORT().trim());
			serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		}
		
		return config;
	}
	
	public static SerialPort getPhysicalPort(){
		if(config == null){
			getInstance();
		}
		return serialPort;
	}
	
	public static void record() {
		try {
			Application.config.store(new FileOutputStream(new File(PROPERTY_FILE_NAME)), "");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}