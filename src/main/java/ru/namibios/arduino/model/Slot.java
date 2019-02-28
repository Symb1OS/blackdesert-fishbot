package ru.namibios.arduino.model;

import ru.namibios.arduino.model.command.Command;

public class Slot implements Command{

    private String command;

	private boolean isActive;
	private String hotKey;

	private Timer timer;

	private HotSlot hotSlot;

	public Slot(HotSlot hotSlot) {
		this.hotSlot = hotSlot;

        this.command = hotSlot.getCommand();
        this.isActive = hotSlot.isActive();
        this.hotKey = hotSlot.getKey();

        this.timer = new Timer(hotSlot.getDelayWithRandom(), hotSlot.getPeriodWithRandom());

	}

	public Slot(String command, boolean isActive, String hotKey, long delay, long period) {
        this.command = command;
		this.isActive = isActive;
		this.hotKey = hotKey;

		this.timer = new Timer(delay, period);
	}

	@Override
	public String getKey() {
		timer.resetTimeLastRunWithUpdate(hotSlot.getPeriodWithRandom());
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

	public boolean isActive() {
		return isActive;
	}

	public String info(){
    	long minute = 1000 * 60;
		long ready = Math.abs(timer.getReadyTime());

		return ready < minute
				? String.format("Next %s in %s second", toCommand(), ready / 1000)
				: String.format("Next %s in %s minute", toCommand(), ready / 1000 / 60);
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