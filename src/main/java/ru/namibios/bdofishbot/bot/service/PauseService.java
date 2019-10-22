package ru.namibios.bdofishbot.bot.service;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.HotSlot;
import ru.namibios.bdofishbot.bot.Timer;
import ru.namibios.bdofishbot.utils.DelayUtils;

public class PauseService {

    private static final Logger LOG = Logger.getLogger(PauseService.class);

    private Timer timer;

    private HotSlot hotSlot;

    public PauseService(HotSlot hotSlot) {
        this.hotSlot = hotSlot;
        this.timer = new Timer();
    }

    public void rest(){

        long periodWithRandom = hotSlot.getPeriodWithRandom();
        LOG.info("Paused work on " + format(periodWithRandom));

        DelayUtils.delay(periodWithRandom);

        timer.reset();

        LOG.info("Continue work.. Next pause over " + format(hotSlot.getDelay()) + " - " + format(hotSlot.getRandomDelay()));
    }

    private String format(long time){
        return DurationFormatUtils.formatDuration(time, "HH:mm:ss.S");
    }

    public boolean isReady() {
        return hotSlot.isActive() && timer.isOver(hotSlot.getDelayWithRandom());
    }

}