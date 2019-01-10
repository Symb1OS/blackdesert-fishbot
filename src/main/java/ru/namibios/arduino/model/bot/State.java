package ru.namibios.arduino.model.bot;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Timer;
import ru.namibios.arduino.model.bot.service.input.InputService;
import ru.namibios.arduino.utils.DelayUtils;

public abstract class State {
	
	FishBot fishBot;
	
	long beforeStart;
	long afterStart;

	InputService inputService;

	Timer timer;

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

	int overflow = Application.getInstance().STATE_OVERFLOW();
	private int step = 0;

	void ifBreak() {
		step++;
		if(step >= overflow){
			onOverflow();
		}
	}

	public void process(){
		DelayUtils.delay(beforeStart);
		if (onPremium()) onStep();
		DelayUtils.delay(afterStart);
	}

	public boolean onPremium(){
		return true;
	}

	public void onOverflow() {

	}

	public abstract void onStep();
	
	public FishBot getFishBot() {
		return fishBot;
	}
}