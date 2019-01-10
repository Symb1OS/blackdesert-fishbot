package ru.namibios.arduino.model.bot;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.bot.service.SlotService;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.utils.ExceptionUtils;

public class UseSlotState extends State {

	private static final Logger LOG = Logger.getLogger(UseSlotState.class);

	private SlotService slotService;

	public UseSlotState(FishBot fishBot) {
		super(fishBot);
		this.beforeStart = 0;
		this.afterStart = 1000;

		slotService = fishBot.getSlotService();
	}

	private void stop(){
		LOG.info("Task: auto stop complete");
		fishBot.setRunned(false);
		fishBot.notifyUser(Message.AUTO_STOP);
	}

	private void exit(){
		LOG.info("Task: auto exit");
		fishBot.setRunned(false);
		fishBot.notifyUser(Message.EXIT_GAME);
	}

	@Override
	public boolean onPremium() {
		if (!Application.getUser().isPremium()) {
			LOG.info("Use slots available only for premium user");
			fishBot.setState(new StartFishState(fishBot));
			return false;
		}

		return true;
	}

	@Override
	public void onStep() {

	    LOG.info("Check slots..");

	    try {

			if (slotService.isReady()) {
				LOG.info("Slot ready.. Use");
				fishBot.call();

				String key = slotService.getKey();
				if (key.startsWith(ShortCommand.STOP.getKey())) {
					stop();
				} else if (key.startsWith(ShortCommand.EXIT.getKey())) {
					exit();
					inputService.send(() -> key);
				} else {
					inputService.send(() -> key);
				}

			}

			fishBot.setState(new StartFishState(fishBot));

		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));

			fishBot.setState(new StartFishState(fishBot));
		}

	}

}