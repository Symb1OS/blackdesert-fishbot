package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.WaitFish;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.Keyboard;

import java.awt.*;

public class WaitFishState extends State {
	
	private static final Logger LOG = Logger.getLogger(WaitFishState.class);

	private WaitFish waitFish;

	WaitFishState(FishBot fishBot) throws AWTException {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_WAIT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_WAIT_FISH();

		this.waitFish = new WaitFish();

	}

	@Override
	public void onInit() throws AWTException {
		waitFish.init();

	}

	@Override
	public void onStep() {
		LOG.info("Wait fish..");
		
		try {

			onInit();

			boolean isFishBite = Keyboard.send(waitFish);
			
			if(isFishBite) fishBot.setState(new CutFishState(fishBot));
			
			if(checkTime(Application.getInstance().TIME_CHANGE_ROD())){
				fishBot.setState(new ChangeRodState(fishBot));
			} 
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}
	}
	
}