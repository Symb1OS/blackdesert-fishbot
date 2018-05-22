package ru.namibios.arduino.notification;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.utils.Http;

public class TelegramNotification extends Notification {

	public TelegramNotification(String message) {
		super(message);
	}

	@Override
	public void send() {
		try {
			
			Http http = new Http();
			http.sendTelegram(Application.getInstance().TELEGRAM_KEY(), message);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}