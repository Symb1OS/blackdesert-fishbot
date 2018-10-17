package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.Kapcha;
import ru.namibios.arduino.utils.ExceptionUtils;

public class KapchaState extends State {

	private static final Logger LOG = Logger.getLogger(KapchaState.class);

	KapchaState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_KAPCHA();
	}
	
	@Override
	public void onStep() {
	
		LOG.info("Parsing kapcha...");
		
		try{

			if(commandSender.send(new Kapcha())){
				LOG.info("Captcha send to input. Go to check status...");
				fishBot.setState(new StatusKapchaState(fishBot));
			}
			else {
				LOG.info("Captcha is not recognized. Return to start...");
				fishBot.setState(new StartFishState(fishBot));
			}
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}
	
}