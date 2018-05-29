package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.utils.PythonExec;

public class Kapcha implements Command{

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private Screen screen;
	private String key;
	private String filename;
	
	public Kapcha() throws AWTException  {
		this.screen = new Screen(Screen.KAPCHA);
		this.screen.clearNoise(Application.getInstance().CNT_KAPCHA());
		this.filename = screen.saveImage("kapcha");
		this.key = "";
	}
	
	public Kapcha(String file) throws IOException{
		this.screen = new Screen(file);
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public String getRezultKey() {
		return key;
	}

	@Override
	public String getKey(){
		
		try {
			
			key = PythonExec.exec(filename);
			key = key.replaceAll("0", "w").replaceAll("1", "s").replaceAll("2", "a").replaceAll("3", "d").replaceAll("4", "");
			
		}catch (IOException e) {
			logger.error(e);
		}
		
		return key.replaceAll("\"", "").replaceAll("\n", "");
		
	}

}