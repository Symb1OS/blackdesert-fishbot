package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.FishLoot;
import ru.namibios.bdofishbot.bot.command.LootWindow;
import ru.namibios.bdofishbot.bot.service.StatsService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class FilterLootState extends State{

	private static final Logger LOG = Logger.getLogger(FilterLootState.class);
	private final StatsService statsService;

	FilterLootState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_FILTER_LOOT();
		this.afterStart = Application.getInstance().DELAY_AFTER_FILTER_LOOT();
		this.statsService = fishBot.getStatsService();

		statsService.update(this.getClass());
	}

	@Override
	public void onStep() {
		LOG.info("Check loot...");
		
		try {

			if (new LootWindow().getKey().isEmpty()) {
				fishBot.setState(new StartFishState(fishBot));
			} else {
				inputService.send(new FishLoot());
				fishBot.setState(new UseSlotState(fishBot));
			}

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}