package ru.namibios.bdofishbot.bot.state;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Slot;
import ru.namibios.bdofishbot.bot.Timer;
import ru.namibios.bdofishbot.bot.service.*;
import ru.namibios.bdofishbot.bot.service.input.ArduinoService;
import ru.namibios.bdofishbot.bot.service.input.EmulationService;
import ru.namibios.bdofishbot.bot.service.input.InputService;
import ru.namibios.bdofishbot.bot.service.input.emulation.AWTRobot;
import ru.namibios.bdofishbot.bot.service.notification.Notification;
import ru.namibios.bdofishbot.bot.service.notification.TelegramNotification;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FishBot {

    private static final Logger LOG = Logger.getLogger(FishBot.class);

	private State state;

    private boolean isRunned;
	
	private boolean isRestart;
	
	private boolean isPmDetected;

	private RodService rodService;

	private SlotService slotService;

	private InputService inputService;

	private HttpService httpService;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

	private PauseService pauseService;

	private StatsService statsService;

	private Timer timer;

    public FishBot(boolean start) {

        List<Slot> slots = Arrays.asList(
                new Slot(Application.getInstance().SLOT_ONE()),
                new Slot(Application.getInstance().SLOT_TWO()),
                new Slot(Application.getInstance().SLOT_THREE()),
                new Slot(Application.getInstance().SLOT_BEER()),
				new Slot(Application.getInstance().TASK_STOP()),
				new Slot(Application.getInstance().TASK_EXIT_GAME())
        );

		this.httpService = new HttpService();

        this.slotService = new SlotService(slots);

        this.rodService = new RodService(Application.getInstance().COUNT_ROD());

		switch (Application.getInstance().INPUT_MODE()) {
			case ARDUINO:
				this.inputService = new ArduinoService();
				break;
			case ROBOT:
				this.inputService = new EmulationService(new AWTRobot());
				break;
			default:
				LOG.error("Unknown input mode. Check settings.");
				throw new IllegalArgumentException("Unknown input mode. Check settings.");
		}

		this.isRunned = start;
		this.isPmDetected = false;

		this.state = new UseSlotState(this);

		this.timer = new Timer();
		this.pauseService = new PauseService(Application.getInstance().TASK_PAUSE());
		this.statsService = new StatsService();

	}

	public void restart(){
		setRestart(true);
		setRunned(false);
	}

	public void call(){

    	executorService.submit(() -> {

    		try {

				httpService.call(state.getClass().getName());

			} catch (IOException e) {
				LOG.error(ExceptionUtils.getString(e));
			}

		});

	}

	public long getUptime(){
		return timer.getUptime();
	}

	public String getFormatUptime(String format){
		long uptime = timer.getUptime();
		return DurationFormatUtils.formatDuration(uptime, format);
	}

	public void notifyUser(String message){
			
		if(Application.getInstance().TELEGRAM()) {
            LOG.info("Send telegram notification.");
			Notification telegram = new TelegramNotification(message);
			telegram.notifyUser();
		}
			
	}

	public void stopExecutors(){
		try {
			LOG.debug("attempt to shutdown executor");
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			LOG.error("tasks interrupted");
		}
		finally {
			if (!executorService.isTerminated()) {
				LOG.debug("cancel non-finished tasks");
			}
			LOG.debug("shutdown finished");
		}
	}

	public void saveStats() {
    	statsService.endWork();
    	statsService.export();
	}

	public PauseService getPauseService() {
		return pauseService;
	}

	public HttpService getHttpService() {
		return httpService;
	}

	public InputService getInputService() {
		return inputService;
	}

	public void setRestart(boolean isRestart) {
		this.isRestart = isRestart;
	}
	
	public boolean isRestart() {
		return isRestart;
	}

	public State getState() {
		return state;
	}

	public void setState(State command) {
		this.state = command;
	}
	
	public boolean isRunned() {
		return isRunned;
	}

	public void setRunned(boolean isRunned) {
		this.isRunned = isRunned;
	}

	public boolean isPmDetected() {
		return isPmDetected;
	}

	public void setPmDetected(boolean isPmDetected) {
		this.isPmDetected = isPmDetected;
	}

    public RodService getRodService() {
        return rodService;
    }

    public SlotService getSlotService() {
        return slotService;
    }

	public StatsService getStatsService() {
		return statsService;
	}
}