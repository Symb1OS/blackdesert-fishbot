package ru.namibios.bdofishbot.utils;

public final class DelayUtils{
	
	private DelayUtils() {}
	
	public static void delay(long value) {
		
		try {

			Thread.sleep(value);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}