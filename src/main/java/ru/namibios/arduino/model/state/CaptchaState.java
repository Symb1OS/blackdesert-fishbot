package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.Captcha;
import ru.namibios.arduino.utils.ExceptionUtils;

public class CaptchaState extends State {

	private static final Logger LOG = Logger.getLogger(CaptchaState.class);

	CaptchaState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_KAPCHA();
	}
	
	@Override
	public void onStep() {
	
		LOG.info("Parsing captcha...");
		
		try{

			if(inputService.send(new Captcha())){
				LOG.info("Captcha send to input. Go to check status...");
				fishBot.setState(new StatusCaptchaState(fishBot));
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