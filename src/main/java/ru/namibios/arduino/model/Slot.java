package ru.namibios.arduino.model;

import ru.namibios.arduino.model.command.Command;

public class Slot implements Command{
	
	private boolean isActive;
	private String hotKey;

	private Timer timer;

	public Slot(boolean isActive, String hotKey, long period) {
		this.isActive = isActive;
		this.hotKey = hotKey;

		this.timer = new Timer(0, period);
	}

	public Slot(boolean isActive, String hotKey, long delay, long period) {
		this.isActive = isActive;
		this.hotKey = hotKey;

		this.timer = new Timer(delay, period);
	}

	@Override
	public String getKey() {
		timer.resetTimeLastRun();
		return toCommand();
	}

	public long getReadyTime() {
		return timer.getReadyTime();
	}

	private String toCommand(){
		return "Slot[" + hotKey + "]";
	}

	public boolean isNeedUse() {
		return isActive && timer.hasReady();
	}

	@Override
	public String toString() {
		return "Slot{" +
				"isActive=" + isActive +
				", hotKey='" + hotKey + '\'' +
				", timer=" + timer +
				'}';
	}

}