package ru.namibios.arduino;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.state.FishBot;

public class Transfer extends Thread{ 
	
	private final static Logger LOG = Logger.getLogger(Transfer.class);

	private FishBot fishBot;
	
	public Transfer() {
		this.fishBot = new FishBot();
	}

	public Transfer(FishBot fishBot) {
		this.fishBot = fishBot;
	}
	
	public FishBot getFishBot() {
		return fishBot;
	}
	
	@Override
	public void run() {
		
		LOG.info("Start...");

        AbstractStarter starter;

        switch (Application.getInstance().INPUT_MODE()) {
            case ARDUINO: starter = new ArduinoStarter(fishBot); break;
            case ROBOT  : starter = new EmulationStarter(fishBot); break;

            default: throw new IllegalArgumentException("Unknown starter. Check input mode in settings");
        }

        starter.run();

	}

}