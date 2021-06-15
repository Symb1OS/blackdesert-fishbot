package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.command.Calendar;
import ru.namibios.bdofishbot.bot.command.ShortCommand;
import ru.namibios.bdofishbot.bot.command.WaitFish;
import ru.namibios.bdofishbot.bot.service.HttpService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;
import ru.namibios.bdofishbot.utils.ImageUtils;

import java.io.IOException;

public class WaitFishState extends State {

	private static final Logger LOG = Logger.getLogger(WaitFishState.class);

	private final HttpService httpService;

	WaitFishState(FishBot fishBot) {
		super(fishBot);

		this.beforeStart = Application.getInstance().DELAY_BEFORE_WAIT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_WAIT_FISH();

		this.httpService = fishBot.getHttpService();

	}

	private void runSideTask(){
		boolean isSideTask = SideTaskContainer.getInstance().size() > 0;
		if (!isSideTask) {
			return;
		}

		SideTaskContainer.Task task;
		while ((task = SideTaskContainer.getInstance().poll()) != null) {

			try {

				LOG.info("Side task: " + task);

				switch (task) {

					case UPTIME:
						httpService.sendTelegramMessage(Application.getInstance().TELEGRAM_KEY(), fishBot.getFormatUptime("HH:mm:ss"));
						break;

					case SKIP_CALENDAR:
						inputService.send(ShortCommand.SKIP_CALENDAR);
						break;

					case INVENTORY:
						inputService.send(ShortCommand.INVENTORY);
						httpService.sendTelegramPhoto(Application.getInstance().TELEGRAM_KEY(), ImageUtils.imageToBytes(Screen.getScreen(Application.getInstance().INVENTORY())));
						inputService.send(ShortCommand.INVENTORY);
						break;

					default:

				}

			} catch (IOException e) {
				LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
				LOG.error(ExceptionUtils.getString(e));
			}

		}
	}

	@Override
	public void onStep() {
		LOG.info("Wait fish..");

		try {

			runSideTask();

			if (Application.getInstance().SKIP_CALENDAR()) {
				inputService.send(new Calendar());
			}

			if (inputService.send(new WaitFish())) {
				LOG.info("Fish detected..");
				fishBot.setState(new CutFishState(fishBot));

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