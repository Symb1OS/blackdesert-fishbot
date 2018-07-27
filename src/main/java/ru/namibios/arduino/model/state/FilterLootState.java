package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.Keyboard;

public class FilterLootState extends State{

	private static final Logger LOG = Logger.getLogger(FilterLootState.class);

	public FilterLootState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_FILTER_LOOT();
		this.afterStart = Application.getInstance().DELAY_AFTER_FILTER_LOOT();
	}

	@Override
	public void onStep() {
		LOG.info("Check loot...");
		
		try {
			
			FishLoot filter = new FishLoot();
			Keyboard.send(filter);
			
			fishBot.setState(new UseSlotState(fishBot));
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}