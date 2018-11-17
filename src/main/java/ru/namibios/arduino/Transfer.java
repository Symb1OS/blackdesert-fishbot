package ru.namibios.arduino;

import com.sun.jna.platform.win32.WinDef;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.WinAPI;

public class Transfer extends Thread{ 
	
	private final static Logger LOG = Logger.getLogger(Transfer.class);

	private FishBot fishBot;
	
	public Transfer() {}

	public Transfer(FishBot fishBot) {
		this.fishBot = fishBot;
	}
	
	public FishBot getFishBot() {
		return fishBot;
	}

	private void restart(){
		LOG.info("Need Restart. Restarted after 10 second...");
		DelayUtils.delay(10000);
		fishBot.setRunned(true);
		fishBot.setRestart(false);
		run();
	}

	@Override
	public void run() {
		
		LOG.info("Start...");

		WinDef.HWND windowGame = WinAPI.findWindow("BLACK DESERT");
		if (windowGame == null) {
			LOG.info("The game is not running");
			return;
		}

		if (fishBot == null) {
			fishBot = new FishBot(true);
		}

		DelayUtils.delay(1000);
		WinAPI.activateWindow(windowGame);

		DelayUtils.delay(3000);

		while (fishBot.isRunned()) fishBot.getState().process();

		LOG.info("Thread stop.");

		if(fishBot.isRestart()) restart();

		fishBot.notifyUser(Message.END_WORK);

	}

}