package ru.namibios.arduino.model.state;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Rod;
import ru.namibios.arduino.model.Slot;
import ru.namibios.arduino.notification.Notification;
import ru.namibios.arduino.notification.TelegramNotification;

public class FishBot {

	private State state;
	
	private Rod rod;
	
	private Slot slot;
	
	private boolean isRunned;
	
	private boolean isRestart;
	
	private boolean isPmDetected;
	
	public FishBot() {
		this.rod = new Rod(Application.getInstance().COUNT_ROD());
		this.slot = new Slot(Application.getInstance().FIRST_SLOT(), Application.getInstance().FIRST_KEY_NUMBER(), Application.getInstance().FIRST_SLOT_USE_DELAY());
		
		isRunned = true;
		isPmDetected = false;
		state = new UseSlotState(this);
	}
	
	void restart(){
		setRestart(true);
		setRunned(false);
	}
	
	void notifyUser(String message){
			
		if(Application.getInstance().TELEGRAM()) {
			Notification telegram = new TelegramNotification(message);
			telegram.notifyUser();
		}
			
	}
	
	public Slot getSlot() {
		return slot;
	}

	public void setRestart(boolean isRestart) {
		this.isRestart = isRestart;
	}
	
	public boolean isRestart() {
		return isRestart;
	}

	public State getState() {
		return state;
	}

	public void setState(State command) {
		this.state = command;
	}
	
	public boolean isRunned() {
		return isRunned;
	}

	public void setRunned(boolean isRunned) {
		this.isRunned = isRunned;
	}

	public Rod getRod() {
		return rod;
	}

	public void setRod(Rod rod) {
		this.rod = rod;
	}

	public boolean isPmDetected() {
		return isPmDetected;
	}

	public void setPmDetected(boolean isPmDetected) {
		this.isPmDetected = isPmDetected;
	}

}