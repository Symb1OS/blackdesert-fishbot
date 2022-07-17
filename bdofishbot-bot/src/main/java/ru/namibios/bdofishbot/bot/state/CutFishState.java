package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.Line;
import ru.namibios.bdofishbot.bot.service.StatsService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class CutFishState extends State {

	private static final Logger LOG = Logger.getLogger(CutFishState.class);
	private final StatsService statsService;

	CutFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_CUT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_CUT_FISH();
		this.statsService = fishBot.getStatsService();

		statsService.update(this.getClass());
		statsService.initCutFishStart();
	}

	@Override
	public void onStep() {
		
		try{

			if(inputService.send(new Line())) {
				LOG.info("Cut the fish...");
				fishBot.setState(new StatusCutState(fishBot));

			} else if (timer.isOver(Application.getInstance().TIME_CHANGE_ROD())) {
				LOG.info("Waiting time for fish is out..");
				fishBot.setState(new ChangeRodState(fishBot));
			}

			statsService.initCutFishEnd();
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
		}
		
	}

}