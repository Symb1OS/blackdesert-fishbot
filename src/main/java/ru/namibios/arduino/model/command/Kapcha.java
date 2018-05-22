package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.utils.Http;
import ru.namibios.arduino.utils.JSON;

public class Kapcha implements Command{

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private static final int NEURAL_NETWORK = 0;
	private static final int ALGORITHM = 1;

	private Screen screen;
	private String key;
	
	public Kapcha() throws AWTException  {
		this.screen = new Screen(Screen.KAPCHA);
		this.screen.clearNoise(Application.getInstance().CNT_KAPCHA());
		this.screen.saveImage("kapcha");
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
			
			ImageParser imageParser = new ImageParser(screen);
			imageParser.parse(Screen.GRAY);
			
			Http http = new Http();
			
			switch (Application.getInstance().PARSE_VARIABLE()) {
				case NEURAL_NETWORK:
					key = http.parseByteKapcha(Application.getInstance().HASH(), screen.toByteArray());
					break;
					
				case ALGORITHM:
					key = http.parseKapcha(Application.getInstance().HASH(), JSON.getInstance().writeValueAsString(imageParser.getImageMatrix()));
					break;
	
				default:
					throw new IllegalArgumentException(String.format("Strategy is not defined. Strategy = %s", Application.getInstance().PARSE_VARIABLE()));
			}
			
		} catch (IOException e){
			logger.error("Exception: " + e); 
		} 
		return key.replaceAll("\"", "").replaceAll("\n", "");
	}

}