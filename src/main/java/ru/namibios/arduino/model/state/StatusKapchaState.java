package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.status.StatusKapcha;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;
import ru.namibios.arduino.utils.ExceptionUtils;

public class StatusKapchaState extends State{

	private static final Logger LOG = Logger.getLogger(StatusKapchaState.class);

	private StatusService<StatusKapchaTemplate> statusService;

	StatusKapchaState(FishBot fishBot) {

		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_STATUS_KAPCHA();

		this.statusService = new StatusService<>();

	}

	@Override
	public void onOverflow() {
		LOG.info("Status not identified. Go filter loot...");
		fishBot.setState(new FilterLootState(fishBot));
	}

	@Override
	public void onStep() {
	
		try {

			StatusKapchaTemplate status = statusService.getTemplate(new StatusKapcha());
			
			if(status == null) {
				ifBreak();

			}else{

				switch (status) {
					case SUCCESS: {
						LOG.info("Kapcha parsed success. Go filter loot...");
						fishBot.setState(new FilterLootState(fishBot));
						break;
					}

					case FAILURE: {
						LOG.info("Kapcha parsed failure. Back to start...");
						fishBot.setState(new StartFishState(fishBot));
						break;
					}
				}

			}

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));

			fishBot.setState(new FilterLootState(fishBot));

		}
	}
	
}