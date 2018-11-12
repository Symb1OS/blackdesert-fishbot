package ru.namibios.arduino;

import com.sun.jna.platform.win32.WinDef;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.WinAPI;

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

		WinDef.HWND windowGame = WinAPI.findWindow("Black Desert");
		if (windowGame == null) {
			LOG.info("The game is not running");
			return;
		}

		DelayUtils.delay(2000);
		WinAPI.activateWindow(windowGame);

		AbstractStarter starter;

        switch (Application.getInstance().INPUT_MODE()) {
            case ARDUINO: starter = new ArduinoStarter(fishBot); break;
            case ROBOT  : starter = new EmulationStarter(fishBot); break;

            default: throw new IllegalArgumentException("Unknown starter. Check input mode in settings");
        }

        starter.run();

	}

}