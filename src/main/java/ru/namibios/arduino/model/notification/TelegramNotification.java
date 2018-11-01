package ru.namibios.arduino.model.notification;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.state.service.HttpService;

public class TelegramNotification extends Notification {

	public TelegramNotification(String message) {
		super(message);
	}

	@Override
	public void send() {
		try {
			
			HttpService http = new HttpService();
			http.sendTelegram(Application.getInstance().TELEGRAM_KEY(), message);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}