package ru.namibios.arduino.utils;

public class DelayUtils{
	
	final void DateUtils() {}
	
	public static void delay(long value) {
		
		try {
			Thread.sleep(value);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}