package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Slot;
import ru.namibios.arduino.model.state.service.CommandSender;
import ru.namibios.arduino.model.state.service.RodService;
import ru.namibios.arduino.model.state.service.SlotService;
import ru.namibios.arduino.notification.Notification;
import ru.namibios.arduino.notification.TelegramNotification;

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

	private CommandSender commandSender;

	public FishBot() {

        List<Slot> slots = Arrays.asList(
                new Slot(Application.getInstance().SLOT_ONE().isActive(),
                        Application.getInstance().SLOT_ONE().getKey(),
                        Application.getInstance().SLOT_ONE().getDelay(),
                        Application.getInstance().SLOT_ONE().getPeriod()),

                new Slot(Application.getInstance().SLOT_TWO().isActive(),
                        Application.getInstance().SLOT_TWO().getKey(),
                        Application.getInstance().SLOT_TWO().getDelay(),
                        Application.getInstance().SLOT_TWO().getPeriod()),

                new Slot(Application.getInstance().SLOT_THREE().isActive(),
                        Application.getInstance().SLOT_THREE().getKey(),
                        Application.getInstance().SLOT_THREE().getDelay(),
                        Application.getInstance().SLOT_THREE().getPeriod())
        );

        this.slotService = new SlotService(slots);

        this.rodService = new RodService(Application.getInstance().COUNT_ROD());

        this.commandSender = new CommandSender();

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
            LOG.info("Send telegram notification.");
			Notification telegram = new TelegramNotification(message);
			telegram.notifyUser();
		}
			
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

    public CommandSender getCommandSender() {
        return commandSender;
    }

    public SlotService getSlotService() {
        return slotService;
    }

}