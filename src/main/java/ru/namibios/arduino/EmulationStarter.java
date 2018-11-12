package ru.namibios.arduino;

import org.apache.log4j.Logger;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.utils.DelayUtils;

public class EmulationStarter extends AbstractStarter {

    private final static Logger LOG = Logger.getLogger(EmulationStarter.class);

    EmulationStarter(FishBot fishBot) {
        super(fishBot);
    }

    @Override
    public void run() {

        LOG.info("Init robot starter");

        DelayUtils.delay(3000);

        while (fishBot.isRunned()) fishBot.getState().process();

        LOG.info("Thread stop.");

        if(fishBot.isRestart()) restart();
    }
}
