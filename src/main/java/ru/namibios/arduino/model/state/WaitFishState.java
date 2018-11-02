package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.model.command.WaitFish;
import ru.namibios.arduino.utils.ExceptionUtils;

public class WaitFishState extends State {
	
	private static final Logger LOG = Logger.getLogger(WaitFishState.class);

	WaitFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_WAIT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_WAIT_FISH();

	}

	@Override
	public void onStep() {
		LOG.info("Wait fish..");
		
		try {

			if (commandSender.send(new WaitFish())) {
				LOG.info("Fish detected..");
				fishBot.setState(new CutFishState(fishBot));

			} else if (timer.isOver(Application.getInstance().TIME_CALENDAR_SKIP())) {
				LOG.info("Try skip calendar..");
				commandSender.send(ShortCommand.SKIP_CALENDAR);

			} else if (timer.isOver(Application.getInstance().TIME_CHANGE_ROD())) {
				LOG.info("Waiting time for fish is out..");
				fishBot.setState(new ChangeRodState(fishBot));
			}

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}
	}
	
}