package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.service.HttpService;
import ru.namibios.bdofishbot.bot.service.StatusService;
import ru.namibios.bdofishbot.bot.status.StatusCaptcha;
import ru.namibios.bdofishbot.bot.template.StatusCaptchaTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class StatusCaptchaState extends State{

	private static final Logger LOG = Logger.getLogger(StatusCaptchaState.class);

	private HttpService httpService = new HttpService();
	private StatusService<StatusCaptchaTemplate> statusService;

	private String filename;

	StatusCaptchaState(FishBot fishBot, String name) {

		super(fishBot);
		this.beforeStart = Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_STATUS_KAPCHA();
		this.overflow = Application.getInstance().STATE_STATUS_CAPTCHA_OVERFLOW();

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

			StatusCaptchaTemplate status = statusService.getTemplate(new StatusCaptcha());
			
			if(status == null) {
				overflow();

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
						httpService.markFail(filename);
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