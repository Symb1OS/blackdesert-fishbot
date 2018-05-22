package ru.namibios.arduino.model.state;

import ru.namibios.arduino.utils.DelayUtils;

public abstract class State {
	
	public FishBot fishBot;
	
	protected long beforeStart;
	protected long afterStart;
	
	protected long timeStart;
	
	public State(FishBot fishBot) {
		this.fishBot = fishBot;
		this.timeStart = System.currentTimeMillis();
	}
	
	public State(FishBot fishBot, long beforeStart, long afterStart) {
		this.fishBot = fishBot;
		this.beforeStart = beforeStart;
		this.afterStart = afterStart;
	}
	
	public void start(){
		DelayUtils.delay(beforeStart);
		onStep();
		DelayUtils.delay(afterStart);
	}
	
	public abstract void onStep();
	
	public boolean checkTime(long period){
		long current = System.currentTimeMillis();
		long workTime = current - timeStart;
		
		return workTime > period;
	}

}