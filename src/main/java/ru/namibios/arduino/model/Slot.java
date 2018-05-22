package ru.namibios.arduino.model;

import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.WorkTime;

public class Slot implements Command{
	
	private boolean isActive;
	private String hotKey;
	private long useDelay;
	
	private WorkTime workTime;

	public Slot(boolean isActive, String hotKey, long useDelay) {
		this.isActive = isActive;
		this.hotKey = hotKey;
		this.useDelay = useDelay;
		
		workTime = new WorkTime();
	}
	
	@Override
	public String getKey() {
		workTime.reloadStart();
		return "Slot[" + hotKey + "]";
	}
	
	public boolean isNeedUse() {
		return isActive && (workTime.getTime() > useDelay);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getHotKey() {
		return hotKey;
	}

	public void setHotKey(String hotKey) {
		this.hotKey = hotKey;
	}

	public long getUseDelay() {
		return useDelay;
	}

	public void setUseDelay(long useDelay) {
		this.useDelay = useDelay;
	}

	public WorkTime getWorkTime() {
		return workTime;
	}

	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}

	@Override
	public String toString() {
		return "Slot [isActive=" + isActive + ", hotKey=" + hotKey + ", useDelay=" + useDelay + ", workTime=" + workTime
				+ "]";
	}
}