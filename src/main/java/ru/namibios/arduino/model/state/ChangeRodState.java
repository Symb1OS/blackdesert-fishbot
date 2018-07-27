package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.Touch;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

public class ChangeRodState extends State{

	private static final Logger LOG = Logger.getLogger(ChangeRodState.class);
	
	public ChangeRodState(FishBot fishBot) {
		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_CHANGE_ROD();
		this.afterStart = Application.getInstance().DELAY_AFTER_CHANGE_ROD();
	}

	@Override
	public void onStep() {

		LOG.info("Start change rod...");
		
		LOG.info("Check new rod..");
		if(fishBot.getRod().hasNext()){
			LOG.info("New fishing rod found. Use..");
			Touch touch = fishBot.getRod().getNext();
			Command command  = () -> "Rod" + touch;
			Keyboard.send(command);
			
			fishBot.setState(new StartFishState(fishBot));
			fishBot.restart();
			
		}else{
			LOG.info("Free fishing rods are locked. Finish work.");
			fishBot.notifyUser(Message.OUT_RODS);
			fishBot.setRunned(false);
		}
	}
	
}