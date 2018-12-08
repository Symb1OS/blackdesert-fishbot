package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.state.service.HttpService;
import ru.namibios.arduino.model.state.service.StatusService;
import ru.namibios.arduino.model.status.StatusKapcha;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;
import ru.namibios.arduino.utils.ExceptionUtils;

public class StatusCaptchaState extends State{

	private static final Logger LOG = Logger.getLogger(StatusCaptchaState.class);

	private HttpService httpService = new HttpService();
	private StatusService<StatusKapchaTemplate> statusService;

	private String filename;

	StatusCaptchaState(FishBot fishBot, String name) {

		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_STATUS_KAPCHA();

		this.filename = name;

		this.statusService = new StatusService<>();

        LOG.info("Check status parsing captcha");
	}

    public void setFilename(String filename) {
        this.filename = filename;
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
						LOG.info("Captcha parsed success. Go filter loot...");
						fishBot.setState(new FilterLootState(fishBot));
						break;
					}

					case FAILED: {
						LOG.info("Captcha parsed failure. Back to start...");
						fishBot.setState(new StartFishState(fishBot));
						httpService.markFail(filename, StatusKapchaTemplate.FAILED.name());
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