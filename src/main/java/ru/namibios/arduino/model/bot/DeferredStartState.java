package ru.namibios.arduino.model.bot;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.TimeUtils;

public class DeferredStartState extends State {

    private static final Logger LOG = Logger.getLogger(DeferredStartState.class);

    public DeferredStartState(FishBot fishBot) {
        super(fishBot);

        LOG.info("Bot starting over " + TimeUtils.getStringTime(Application.getInstance().TASK_START().getDelay()));
    }

    @Override
    public void onStep() {

        if (!Application.getInstance().TASK_START().isActive() || timer.isOver(Application.getInstance().TASK_START().getDelay())) {
            fishBot.setState(new UseSlotState(fishBot));
            return;
        }

        DelayUtils.delay(1000);
    }
}
