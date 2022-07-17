package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.service.HttpService;
import ru.namibios.bdofishbot.bot.service.StatsService;
import ru.namibios.bdofishbot.bot.service.StatusService;
import ru.namibios.bdofishbot.bot.status.StatusCaptcha;
import ru.namibios.bdofishbot.bot.template.StatusCaptchaTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.util.Date;

public class StatusCaptchaState extends State{

	private static final Logger LOG = Logger.getLogger(StatusCaptchaState.class);

	private final long startTime;

	private HttpService httpService = new HttpService();
	private StatusService<StatusCaptchaTemplate> statusService;

	private String filename;
	private final StatsService statsService;

	StatusCaptchaState(FishBot fishBot, String name) {

		super(fishBot);

		this.startTime = new Date().getTime();

		this.beforeStart = Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_STATUS_KAPCHA();

		this.filename = name;

		this.statusService = new StatusService<>();

		statsService = fishBot.getStatsService();
		statsService.update(this.getClass());
		statsService.initStatusCaptchaStart();

		LOG.info("Check status parsing captcha");
	}

    public void setFilename(String filename) {
        this.filename = filename;
    }

	@Override
	public void onStep() {

		try {

			StatusCaptchaTemplate status = statusService.getTemplate(new StatusCaptcha());

			if (timer.isOver(Application.getInstance().STATE_STATUS_CAPTCHA_MAX_TIME())) {
				LOG.info("Captcha parsed success. Go filter loot...");
				fishBot.setState(new FilterLootState(fishBot));
				statsService.okCaptcha();

			} else if (status == StatusCaptchaTemplate.FAILED){
				LOG.info("Captcha parsed failure. Back to start...");
				fishBot.setState(new StartFishState(fishBot));
				httpService.markFail(filename);
				statsService.failCaptcha();
			}

			statsService.initStatusCaptchaEnd();

		}catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));

			fishBot.setState(new FilterLootState(fishBot));

		}
	}
	
}