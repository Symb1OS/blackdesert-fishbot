package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

public class StartFishState extends State{

	private static final Logger logger= Logger.getLogger(StartFishState.class);
	
	public StartFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_START();
		this.afterStart = Application.getInstance().DELAY_AFTER_START();
	}

	@Override
	public void onStep() {
		logger.info("Start Fish...");
		
		Command command = () -> Keyboard.Keys.SPACE;
		boolean isSend = Keyboard.send(command);
		
		if(isSend) fishBot.setState(new PersonalMessageState(fishBot));
		
	}
}