package ru.namibios.arduino.utils;

public class WorkTime{
	
	private long start;
	private long current;
	
	public WorkTime() {
		start = 0;
	}
	
	public long getTime() {
		current = System.currentTimeMillis();
		return current - start;
	}
	
	public void reloadStart() {
		start = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "WorkTime [start=" + start + ", current=" + current + "]";
	}
	
}
