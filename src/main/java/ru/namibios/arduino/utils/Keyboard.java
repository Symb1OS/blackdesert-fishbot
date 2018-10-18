package ru.namibios.arduino.utils;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.Command;

import java.io.PrintWriter;

public final class Keyboard {
	
	private final static Logger LOG = Logger.getLogger(Keyboard.class);
	
	private Keyboard() {}
	
	public final class Keys {

		public static final String TAKE = "r";
		public static final String IGNORE = "";

	}

	public static boolean send(Command command) {
		String message = command.getKey().trim();
		boolean status = false;
		if(!"".equals(message)){
			PrintWriter output = new PrintWriter(Application.getPhysicalPort().getOutputStream());
			output.println(message);
			output.flush();
			
			LOG.info("Sended message[" + message.length() + "]: " + message);
			status = true;
		}
		
		return status;
	}
	
}