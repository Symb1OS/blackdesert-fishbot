package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.Line;
import ru.namibios.arduino.utils.ExceptionUtils;

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

			if(inputService.send(new Line())) {
				LOG.info("Cut the fish...");
				fishBot.setState(new StatusCutState(fishBot));

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