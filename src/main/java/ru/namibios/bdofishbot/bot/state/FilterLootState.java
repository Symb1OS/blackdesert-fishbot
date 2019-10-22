package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.FishLoot;
import ru.namibios.bdofishbot.config.Application;
import ru.namibios.bdofishbot.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class FilterLootState extends State{

	private static final Logger LOG = Logger.getLogger(FilterLootState.class);

	FilterLootState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_FILTER_LOOT();
		this.afterStart = Application.getInstance().DELAY_AFTER_FILTER_LOOT();
	}

	@Override
	public void onStep() {
		LOG.info("Check loot...");
		
		try {

			inputService.send(new FishLoot());
			fishBot.setState(new UseSlotState(fishBot));
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}