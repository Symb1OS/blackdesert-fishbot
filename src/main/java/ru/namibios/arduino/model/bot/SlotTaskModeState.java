package ru.namibios.arduino.model.bot;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.bot.service.SlotService;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.util.concurrent.Executors;

public class SlotTaskModeState extends State {

    private static final Logger LOG = Logger.getLogger(UseSlotState.class);

    private SlotService slotService;

    public SlotTaskModeState(FishBot fishBot) {
        super(fishBot);

        slotService = fishBot.getSlotService();
    }

    @Override
    public boolean onPremium() {
        if (!Application.getUser().isPremium()) {
            LOG.info("Use slots/tasks available only for premium user");
            return false;
        }

        return true;
    }

    @Override
    public void onStep() {

        try {

            if (slotService.isReady()) {
                LOG.info("Slot ready.. Use");

                Executors.newSingleThreadExecutor().submit(() -> fishBot.call());

                String key = slotService.getKey();
                inputService.send(() -> key);
            }

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

    }
}