package ru.namibios.arduino.model.state;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Rod;
import ru.namibios.arduino.model.Slot;
import ru.namibios.arduino.model.Touch;
import ru.namibios.arduino.notification.Notification;
import ru.namibios.arduino.notification.TelegramNotification;

import java.util.Arrays;
import java.util.List;

public class FishBot {

    private State state;
	
	private Rod rod;
	
	private List<Slot> slots;

	private boolean isRunned;
	
	private boolean isRestart;
	
	private boolean isPmDetected;

	public FishBot() {

		this.rod = new Rod(Application.getInstance().COUNT_ROD());

        this.slots = Arrays.asList(
        		new Slot(Application.getInstance().FIRST_SLOT(),
						Application.getInstance().FIRST_KEY_NUMBER(),
						Application.getInstance().FIRST_SLOT_USE_DELAY(),
						Application.getInstance().FIRST_SLOT_USE_PERIOD()),

				new Slot(Application.getInstance().SECOND_SLOT(),
						Application.getInstance().SECOND_KEY_NUMBER(),
						Application.getInstance().SECOND_SLOT_USE_DELAY(),
						Application.getInstance().SECOND_SLOT_USE_PERIOD()),

				new Slot(Application.getInstance().THIRD_SLOT(),
						Application.getInstance().THIRD_KEY_NUMBER(),
						Application.getInstance().THIRD_SLOT_USE_DELAY(),
						Application.getInstance().THIRD_SLOT_USE_PERIOD())
				);

		this.isRunned = true;
		this.isPmDetected = false;
		this.state = new UseSlotState(this);
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

	public boolean hasNextRod(){
		return rod.hasNext();
	}

	public Touch getNextRod(){
		return rod.getNext();
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

	public List<Slot> getSlots() {
		return slots;
	}
}