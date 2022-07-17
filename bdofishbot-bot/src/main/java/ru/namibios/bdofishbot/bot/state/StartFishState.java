package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.Calendar;
import ru.namibios.bdofishbot.bot.command.ShortCommand;
import ru.namibios.bdofishbot.bot.service.PauseService;
import ru.namibios.bdofishbot.bot.service.StatsService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class StartFishState extends State {

	private static final Logger LOG = Logger.getLogger(StartFishState.class);

	private PauseService pauseService;
	private final StatsService statsService;

	public StartFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_START();
		this.afterStart = Application.getInstance().DELAY_AFTER_START();
		this.pauseService = fishBot.getPauseService();
		this.statsService = fishBot.getStatsService();

		statsService.update(this.getClass());

	}

	@Override
	public void onStep() {
		LOG.info("Start Fish...");

		try {

			if (pauseService.isReady()) {
				pauseService.rest();
			}

			if (Application.getInstance().SKIP_CALENDAR()) {
				inputService.send(new Calendar());
			}

			if(inputService.send(ShortCommand.SPACE)){
				switch (Application.getInstance().MODE()) {
					case FISHING:
						fishBot.setState(new PersonalMessageState(fishBot));
						break;
					case AFK_FISH:
						fishBot.setState(new AfkFishState(fishBot));
				}
			}

		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

	}
}