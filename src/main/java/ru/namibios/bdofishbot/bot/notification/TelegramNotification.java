package ru.namibios.bdofishbot.bot.notification;

import ru.namibios.bdofishbot.bot.state.service.HttpService;
import ru.namibios.bdofishbot.config.Application;

public class TelegramNotification extends Notification {

	public TelegramNotification(String message) {
		super(message);
	}

	@Override
	public void send() {
		try {
			
			HttpService http = new HttpService();
			http.sendTelegramMessage(Application.getInstance().TELEGRAM_KEY(), message);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}