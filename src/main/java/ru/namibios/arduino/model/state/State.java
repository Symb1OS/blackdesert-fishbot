package ru.namibios.arduino.model.state;

import ru.namibios.arduino.model.TimeService;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;

public abstract class State {
	
	public FishBot fishBot;
	
	protected long beforeStart;
	protected long afterStart;

	protected TimeService timeService;
	protected CommandSender commandSender;

	public State(FishBot fishBot) {
		this.fishBot = fishBot;
		this.timeService = new TimeService();
	}
	
	public State(FishBot fishBot, long beforeStart, long afterStart) {
		this.fishBot = fishBot;
		this.beforeStart = beforeStart;
		this.afterStart = afterStart;
		this.timeService = new TimeService();
	}

	private static final int OVERFLOW = 300;
	private int step = 0;

	protected void ifBreak() {
		step++;
		if(step >= OVERFLOW){
			onOverflow();
		}
	}

	public void start(){
		DelayUtils.delay(beforeStart);
		onStep();
		DelayUtils.delay(afterStart);
	}

	public void onOverflow() {

	}

	public void onInit() throws AWTException {

	}

	public abstract void onStep();
	
	public FishBot getFishBot() {
		return fishBot;
	}
}