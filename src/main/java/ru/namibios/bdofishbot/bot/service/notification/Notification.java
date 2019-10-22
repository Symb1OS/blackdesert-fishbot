package ru.namibios.bdofishbot.bot.service.notification;

public abstract class Notification {
	
	protected String message; 
	
	private Notification nextNotification; 
	
	public Notification(String message) {
		this.message = message;
	}
	
	public void setNextNotification(Notification notification) {
		this.nextNotification = notification;
	}
	
	public void notifyUser() {
		
		send();
		
		if(nextNotification != null) {
			nextNotification.notifyUser();
		}
	}
	
	public abstract void send();

}