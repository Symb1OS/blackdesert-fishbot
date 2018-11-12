package ru.namibios.arduino;

import org.apache.log4j.Logger;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.utils.DelayUtils;

public abstract class AbstractStarter {

    private final static Logger LOG = Logger.getLogger(AbstractStarter.class);

    FishBot fishBot;

    AbstractStarter(FishBot fishBot) {
        this.fishBot = fishBot;
    }

    void restart(){
        LOG.info("Need Restart. Restarted after 15 second...");
        DelayUtils.delay(15000);
        fishBot.setRunned(true);
        fishBot.setRestart(false);
        run();
    }

    abstract void run();
}
