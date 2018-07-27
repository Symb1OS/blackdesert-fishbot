package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

public class UseSlotState extends State{

	private static final Logger LOG = Logger.getLogger(UseSlotState.class);
	
	public UseSlotState(FishBot fishBot) {
		super(fishBot);
		this.beforeStart = 0;
		this.afterStart = 1000;
	}

	@Override
	public void onStep() {
		
		if(fishBot.getSlot().isNeedUse()) {
			LOG.info("Run auto use slot...");
			Command hotKey = () -> fishBot.getSlot().getKey();
			Keyboard.send(hotKey);
		}
		
		fishBot.setState(new StartFishState(fishBot));
		
	}
	
}