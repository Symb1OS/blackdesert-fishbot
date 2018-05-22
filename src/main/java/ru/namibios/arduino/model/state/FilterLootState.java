package ru.namibios.arduino.model.state;


import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.utils.Keyboard;

public class FilterLootState extends State{

	private static final Logger logger = Logger.getLogger(FilterLootState.class);

	public FilterLootState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_FILTER_LOOT();
		this.afterStart = Application.getInstance().DELAY_AFTER_FILTER_LOOT();
	}

	@Override
	public void onStep() {
		logger.info("Check loot...");
		
		try {
			
			Screen screen = new Screen(Screen.FULL_SCREEN, false);
			screen.upload();
			
			FishLoot filter = new FishLoot();
			Keyboard.send(filter);
			
			fishBot.setState(new UseSlotState(fishBot));
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}