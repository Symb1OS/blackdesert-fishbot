package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Slot;
import ru.namibios.arduino.model.notification.Notification;
import ru.namibios.arduino.model.notification.TelegramNotification;
import ru.namibios.arduino.model.state.service.RodService;
import ru.namibios.arduino.model.state.service.SlotService;
import ru.namibios.arduino.model.state.service.input.ArduinoService;
import ru.namibios.arduino.model.state.service.input.EmulationService;
import ru.namibios.arduino.model.state.service.input.InputService;
import ru.namibios.arduino.model.state.service.input.emulation.AWTRobot;

import java.util.Arrays;
import java.util.List;

public class FishBot {

    private static final Logger LOG = Logger.getLogger(FishBot.class);

    private State state;

    private boolean isRunned;
	
	private boolean isRestart;
	
	private boolean isPmDetected;

	private RodService rodService;

	private SlotService slotService;

	private InputService inputService;

    public FishBot(boolean start) {

        List<Slot> slots = Arrays.asList(
                new Slot(Application.getInstance().SLOT_ONE()),
                new Slot(Application.getInstance().SLOT_TWO()),
                new Slot(Application.getInstance().SLOT_THREE()),
                new Slot(Application.getInstance().SLOT_BEER()),
				new Slot(Application.getInstance().TASK_STOP()),
				new Slot(Application.getInstance().TASK_EXIT_GAME())
        );

        this.slotService = new SlotService(slots);

        this.rodService = new RodService(Application.getInstance().COUNT_ROD());

		switch (Application.getInstance().INPUT_MODE()) {
			case ARDUINO:
				this.inputService = new ArduinoService();
				break;
			case ROBOT:
				this.inputService = new EmulationService(new AWTRobot());
				break;
			default:
				throw new IllegalArgumentException("Unknown input mode. Check settings.");
		}

		this.isRunned = start;
		this.isPmDetected = false;

		this.state = new UseSlotState(this);
	}
	
	void restart(){
		setRestart(true);
		setRunned(false);
	}
	
	public void notifyUser(String message){
			
		if(Application.getInstance().TELEGRAM()) {
            LOG.info("Send telegram notification.");
			Notification telegram = new TelegramNotification(message);
			telegram.notifyUser();
		}
			
	}

	public InputService getInputService() {
		return inputService;
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

	public boolean isPmDetected() {
		return isPmDetected;
	}

	public void setPmDetected(boolean isPmDetected) {
		this.isPmDetected = isPmDetected;
	}

    public RodService getRodService() {
        return rodService;
    }

    public SlotService getSlotService() {
        return slotService;
    }

}