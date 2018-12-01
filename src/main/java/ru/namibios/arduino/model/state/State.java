package ru.namibios.arduino.model.state;

import ru.namibios.arduino.model.Timer;
import ru.namibios.arduino.model.state.service.input.InputService;
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

	private static final int OVERFLOW = 10;
	private int step = 0;

	void ifBreak() {
		step++;
		if(step >= OVERFLOW){
			onOverflow();
		}
	}

	public void process(){
		DelayUtils.delay(beforeStart);
		onStep();
		DelayUtils.delay(afterStart);
	}

	public void onOverflow() {

	}

	public abstract void onStep();
	
	public FishBot getFishBot() {
		return fishBot;
	}
}