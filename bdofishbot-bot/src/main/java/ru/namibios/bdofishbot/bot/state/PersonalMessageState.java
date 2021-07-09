package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.PersonalMessage;
import ru.namibios.bdofishbot.bot.command.ShortCommand;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

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

				LOG.info("Chat activity detected.");
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
					inputService.send(ShortCommand.EXIT);
				}
				
				fishBot.setPmDetected(true);
			}
			
			fishBot.setState(new CheckEquipState(fishBot));
		
		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
		}

	}
}