package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.state.service.RodService;
import ru.namibios.arduino.utils.ExceptionUtils;

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
	public void onStep() {

		LOG.info("Start change rod...");

		try {

			LOG.info("Checking availability fishing rod..");
			if (rodService.hasNext()) {

				LOG.info("New fishing rod found. Use..");

				String nextFree = rodService.getNext();
				inputService.send( () -> nextFree);

				fishBot.setState(new StartFishState(fishBot));
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