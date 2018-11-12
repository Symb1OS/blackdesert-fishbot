package ru.namibios.arduino;

import org.apache.log4j.Logger;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.model.state.service.input.ArduinoService;
import ru.namibios.arduino.utils.DelayUtils;

public class ArduinoStarter extends AbstractStarter {

    private final static Logger LOG = Logger.getLogger(ArduinoStarter.class);

    ArduinoStarter(FishBot fishBot) {
        super(fishBot);
    }

    @Override
    public void run() {

        LOG.info("Init arduino starter");

        ArduinoService inputService = (ArduinoService) fishBot.getInputService();

        inputService.openPort();
        DelayUtils.delay(3000);

        if(!inputService.isOpen()) {
            LOG.info("Port is closed. Check you port in settings");
            fishBot.setRunned(false);
            return;
        }

        LOG.info("Port is open...");

        while (fishBot.isRunned()) fishBot.getState().process();

        inputService.closePort();

        LOG.info("Port closed...");
        LOG.info("Thread stop.");

        if(fishBot.isRestart()) restart();
    }
}