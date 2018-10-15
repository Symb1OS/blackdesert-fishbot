package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.Line;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.Keyboard;

public class CutFishState extends State {

	private static final Logger LOG = Logger.getLogger(CutFishState.class);

	CutFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_CUT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_CUT_FISH();
	}

	@Override
	public void onStep() {
		
		try{
			
			Command line = new Line(); 
			boolean isBlueZone = Keyboard.send(line);
			
			if(isBlueZone) {
				LOG.info("Cut the fish...");
				fishBot.setState(new StatusCutState(fishBot));
			}
			
		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
		}
		
	}

}