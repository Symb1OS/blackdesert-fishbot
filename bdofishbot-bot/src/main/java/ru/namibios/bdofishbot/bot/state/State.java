package ru.namibios.bdofishbot.bot.state;

import com.sun.jna.platform.win32.WinDef;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Timer;
import ru.namibios.bdofishbot.bot.service.input.InputService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.DelayUtils;
import ru.namibios.bdofishbot.utils.WinAPI;

public abstract class State {

    private static final Logger LOG = Logger.getLogger(State.class);

    FishBot fishBot;
	
	long beforeStart;
	long afterStart;

	InputService inputService;

	Timer timer;

	boolean isInterrupt;

	public State(FishBot fishBot) {
		this.fishBot = fishBot;
		this.timer = new Timer();
		this.inputService = fishBot.getInputService();
	}
	
	public State(FishBot fishBot, long beforeStart, long afterStart) {
		this(fishBot);
		this.beforeStart = beforeStart;
		this.afterStart = afterStart;
	}

	int maxStep = Application.getInstance().STATE_OVERFLOW();
	private int step = 0;

	private void interrupt() {
		step++;
		if(step >= maxStep){
			onInterrupt();
		}
	}

	private boolean isActiveClient(){

		WinDef.HWND windowGame = WinAPI.findWindow(Application.getInstance().GAME_TITLE());
		if (windowGame == null) {

			LOG.info("Game client crash");

			if (Application.getInstance().CRASH_EXIT_BOT()) {

				fishBot.notifyUser(Message.GAME_CLIENT_CRASH_CLOSE_BOT);
				Application.closeBot(Application.CODE_GAME_CLIENT_CRASH);
			}

			if (Application.getInstance().CRASH_STOP_BOT()) {
				LOG.info("Stop bot..");

				fishBot.notifyUser(Message.GAME_CLIENT_CRASH_STOP_BOT);
				fishBot.setRunned(false);
			}

			if (Application.getInstance().CRASH_SHUTDOWN_PC()) {
				fishBot.notifyUser(Message.GAME_CLIENT_CRASH_SHUTDOWN_PC);
				Application.shutdownPc();
			}

		}

		return true;
	}

	public void process(){

		if (isActiveClient()) {

			DelayUtils.delay(beforeStart);
			if (onPremium()) onStep();
			DelayUtils.delay(afterStart);

			if (isInterrupt) {
				interrupt();
			}

		}

	}

	public boolean onPremium(){
		return true;
	}

	public void onInterrupt() {

	}

	public abstract void onStep();
	
	public FishBot getFishBot() {
		return fishBot;
	}
}