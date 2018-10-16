package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.status.StatusKapcha;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.awt.*;

public class StatusKapchaState extends State{

	private static final Logger LOG = Logger.getLogger(StatusKapchaState.class);

	private static final int COUNT_OVERFLOW = 150;
	
	private int step;

	private StatusKapcha statusKapcha;

	StatusKapchaState(FishBot fishBot) throws AWTException {
		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_STATUS_KAPCHA();
		this.step = 0;
		this.statusKapcha = new StatusKapcha();
	}

	@Override
	public void onOverflow() {
		if(step > COUNT_OVERFLOW){
			LOG.info("Status not identified. Go filter loot...");
			fishBot.setState(new FilterLootState(fishBot));
		}
	}

	@Override
	public void onInit() throws AWTException {
		statusKapcha.init();
	}

	@Override
	public void onStep() {
	
		try {

			onOverflow();
			onInit();

			StatusKapchaTemplate status = statusKapcha.getNameTemplate();
			
			if(status == null) {
				step++;
				return;
			}
			
			switch (status) {
				case SUCCESS: {
					LOG.info("Kapcha parsed success. Go filter loot...");
					fishBot.setState(new FilterLootState(fishBot));
					break;
				}
				
				case FAILURE: {
					LOG.info("Kapcha parsed failure. Back to start...");
					fishBot.setState(new StartFishState(fishBot));
					break;
				} 
			}
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));

			fishBot.setState(new FilterLootState(fishBot));

		}
	}
	
}