package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.Kapcha;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.Keyboard;

public class KapchaState extends State {

	private static final Logger LOG = Logger.getLogger(KapchaState.class);

	public KapchaState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_KAPCHA();
	}
	
	@Override
	public void onStep() {
	
		LOG.info("Parsing kapcha...");
		
		try{
			
			Kapcha kapcha = new Kapcha();
			boolean isSendToInput = Keyboard.send(kapcha);
			
			if(isSendToInput){
				LOG.info("Kapcha send to input. Go to check status...");
				fishBot.setState(new StatusKapchaState(fishBot));
			}
			else {
				LOG.info("Kapcha is not recognized. Return to start...");
				fishBot.setState(new StartFishState(fishBot));
			}
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}
	
}