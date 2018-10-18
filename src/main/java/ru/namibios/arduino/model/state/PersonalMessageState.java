package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.PersonalMessage;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.awt.*;

public class PersonalMessageState extends State {

	private static final Logger LOG = Logger.getLogger(PersonalMessage.class);

	private PersonalMessage pm;

	PersonalMessageState(FishBot fishBot) throws AWTException {
		super(fishBot);
		this.beforeStart = 0;
		this.afterStart = 0;


		pm = new PersonalMessage(Application.getInstance().PM_COEF());
	}

	@Override
	public void onStep() {
		
		try {

			if(pm.isDetected() && !fishBot.isPmDetected()) {
				
				LOG.info("Reseived a private message. Send telegram notification.");
				fishBot.notifyUser(Message.RECEIVED_PRIVATE_MESSAGE);
				
				if (Application.getInstance().PM_AUTOFISH()) {
					LOG.info("Received a private message. Switch to autofish...");
					fishBot.notifyUser(Message.TURN_AUTOFISH);
					fishBot.setRunned(false);
				}
				else if (Application.getInstance().PM_EXIT_GAME()) {
					LOG.info("Received a private message. Exit game...");
					fishBot.notifyUser(Message.EXIT_GAME);
					fishBot.setRunned(false);
					commandSender.send(ShortCommand.EXIT);
				}
				
				fishBot.setPmDetected(true);
			}
			
			fishBot.setState(new WaitFishState(fishBot));
		
		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

	}
}