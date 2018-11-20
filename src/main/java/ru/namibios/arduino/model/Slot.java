package ru.namibios.arduino.model;

import ru.namibios.arduino.model.command.Command;

public class Slot implements Command{

    private String command;

	private boolean isActive;
	private String hotKey;

	private Timer timer;

    public Slot(HotSlot hotSlot) {
        this.command = hotSlot.getCommand();
        this.isActive = hotSlot.isActive();
        this.hotKey = hotSlot.getKey();

        this.timer = new Timer(hotSlot.getDelay(), hotSlot.getPeriod());
    }

	public Slot(String command, boolean isActive, String hotKey, long delay, long period) {
        this.command = command;
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
		return command + "[" + hotKey + "]";
	}

	public boolean isNeedUse() {
		return isActive && timer.hasReady();
	}

    @Override
    public String toString() {
        return "Slot{" +
                "command='" + command + '\'' +
                ", isActive=" + isActive +
                ", hotKey='" + hotKey + '\'' +
                ", timer=" + timer +
                '}';
    }

}