package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.service.RodService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class ChangeRodState extends State{

	private static final Logger LOG = Logger.getLogger(ChangeRodState.class);

	private RodService rodService;

	ChangeRodState(FishBot fishBot) {
		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_CHANGE_ROD();
		this.afterStart = Application.getInstance().DELAY_AFTER_CHANGE_ROD();

		this.rodService = fishBot.getRodService();

	}

	@Override
	public boolean onPremium() {
		if (!Application.getUser().isPremium()) {
			LOG.info("Change rod available only for premium user");
			fishBot.setRunned(false);
			return false;
		}

		return true;
	}

	@Override
	public void onStep() {

		LOG.info("Start change rod...");

		try {

			LOG.info("Checking availability fishing rod..");
			if (rodService.hasNext()) {

				LOG.info("New fishing rod found. Use..");
				LOG.info("Available: " + rodService.getCountAvailableRods() + "/" + RodService.MAX_RODS);

				fishBot.call();
				fishBot.notifyUser("Started change fishing rod. Available: " + rodService.getCountAvailableRods() + "/" + Application.getInstance().COUNT_ROD());

				String nextFree = rodService.getNext();
				inputService.send( () -> nextFree);

				switch (Application.getInstance().MODE()) {
					case FISHING:
						fishBot.setState(new StartFishState(fishBot));
						break;
					case AFK_FISH:
						fishBot.setState(new AfkFishState(fishBot));
						break;
					default:
						LOG.info("Undefined mode..");
				}

				fishBot.restart();

			}else {
				LOG.info("Free fishing rods are locked. Finish work.");
				fishBot.notifyUser(Message.OUT_RODS);
				fishBot.setRunned(false);
			}

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

	}
	
}