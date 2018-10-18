package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.Keyboard;

public class StartFishState extends State{

	private static final Logger LOG = Logger.getLogger(StartFishState.class);
	
	StartFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_START();
		this.afterStart = Application.getInstance().DELAY_AFTER_START();
	}

	@Override
	public void onStep() {
		LOG.info("Start Fish...");

		try {

			if(commandSender.send(() -> Keyboard.Keys.SPACE)){
				fishBot.setState(new PersonalMessageState(fishBot));
			}

		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

	}
}