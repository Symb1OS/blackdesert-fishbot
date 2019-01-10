package ru.namibios.arduino;

import com.sun.jna.platform.win32.WinDef;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.config.Mode;
import ru.namibios.arduino.model.bot.FishBot;
import ru.namibios.arduino.model.bot.SlotTaskModeState;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.WinAPI;

public class Transfer extends Thread{ 
	
	private final static Logger LOG = Logger.getLogger(Transfer.class);

	private final Mode mode;

	private FishBot fishBot;
	
	public Transfer(Mode mode) {
		this.mode = mode;
	}

	public FishBot getFishBot() {
		return fishBot;
	}

	private void restart(){
		LOG.info("Need Restart. Restarted after 10 second...");
		DelayUtils.delay(10000);
		fishBot.setRunned(true);
		fishBot.setRestart(false);
		process();
	}

	private void process(){

		LOG.info("Start...");

		WinDef.HWND windowGame = WinAPI.findWindow("BLACK DESERT");
		if (windowGame == null) {
			LOG.info("The game is not running");
			System.exit(1);
		}

		if (fishBot == null) {
			fishBot = new FishBot(true);
		}

		DelayUtils.delay(1000);
		WinAPI.activateWindow(windowGame);

		DelayUtils.delay(3000);

		switch (mode) {
			case FISHING:
				LOG.info("Bot started on FISHING mode..");
				break;
			case TASK_SLOT:
				LOG.info("Bot started on TASK/SLOT mode..");
				fishBot.setState(new SlotTaskModeState(fishBot));
				break;
			default:
				LOG.info("Undefined mode.. Started FISHING mode by default..");
		}

		while (fishBot.isRunned()) fishBot.getState().process();

		LOG.info("Thread stop.");

		if(fishBot.isRestart()) restart();

	}

	@Override
	public void run() {
		
		process();

		LOG.info("End work.");
		fishBot.notifyUser(Message.END_WORK);

	}

}