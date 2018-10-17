package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.status.StatusCut;
import ru.namibios.arduino.model.template.StatusCutTemplate;
import ru.namibios.arduino.utils.ExceptionUtils;

public class StatusCutState extends State{

	private static final Logger LOG = Logger.getLogger(StatusCutState.class);

	private StatusService<StatusCutTemplate> statusService;

	StatusCutState(FishBot fishBot) {

		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_STATUS_CUT();
		this.afterStart = Application.getInstance().DELAY_AFTER_STATUS_CUT();

		this.statusService = new StatusService<>();
	}

	@Override
	public void onOverflow() {
		LOG.info("Status not identified... Go to FilterLoot..");
		fishBot.setState(new FilterLootState(fishBot));
	}

	@Override
	public void onStep() {
		
		try{

			StatusCutTemplate status = statusService.getTemplate(new StatusCut());
			if (status == null) {
				ifBreak();

			} else {

				switch ( status ) {
					case PERFECT:{
						LOG.info("PERFECT. Go filter loot..");
						fishBot.setState(new FilterLootState(fishBot));
						break;
					}
					case GOOD: {
						LOG.info("GOOD. Go parse captcha");
						fishBot.setState(new KapchaState(fishBot));
						break;
					}
					case BAD: {
						LOG.info("BAD. Back to start...");
						fishBot.setState(new StartFishState(fishBot));
						break;
					}
				}

			}

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new KapchaState(fishBot));
		}
		
	}

}