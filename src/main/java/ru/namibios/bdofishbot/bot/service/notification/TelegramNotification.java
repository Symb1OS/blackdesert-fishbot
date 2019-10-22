package ru.namibios.bdofishbot.bot.service.notification;

import ru.namibios.bdofishbot.bot.service.HttpService;
import ru.namibios.bdofishbot.cli.Application;

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