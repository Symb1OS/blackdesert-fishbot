package ru.namibios.arduino.model.bot;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.command.Captcha;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.util.Date;

public class CaptchaState extends State {

    private static final Logger LOG = Logger.getLogger(CaptchaState.class);

    private final String name;
    private Command captcha;

    CaptchaState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_KAPCHA();
		this.afterStart = Application.getInstance().DELAY_AFTER_KAPCHA();

        this.name = String.valueOf(new Date().getTime()) + "_" + Application.getUser().getHash();
        this.captcha = new Captcha(name);

    }

	@Override
	public void onStep() {
	
		LOG.info("Parsing captcha...");
		
		try {

            String key = captcha.getKey();

            if (!key.matches(Captcha.REGEX)) {
                LOG.error(key);
                fishBot.setRunned(false);
                return;
            }

            if (inputService.send(() -> key)){
				LOG.info("Captcha send to input. Go to check status...");
				fishBot.setState(new StatusCaptchaState(fishBot, name));
			}
			else {
				LOG.info("Captcha is not recognized. Return to start...");
				fishBot.setState(new StartFishState(fishBot));
			}

		} catch (Exception e) {
			LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
			LOG.error(ExceptionUtils.getString(e));
			
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}