package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.Calendar;
import ru.namibios.bdofishbot.bot.service.SlotService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class SlotTaskModeState extends State {

    private static final Logger LOG = Logger.getLogger(UseSlotState.class);

    private SlotService slotService;

    public SlotTaskModeState(FishBot fishBot) {
        super(fishBot);

        this.slotService = fishBot.getSlotService();
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

            if (Application.getInstance().SKIP_CALENDAR()) {
                inputService.send(new Calendar());
            }

            if (!slotService.isActiveTasks()
                    && !Application.getInstance().SLOT_DEBUF_DESERT_DAY().isActive()
                    && !Application.getInstance().SLOT_DEBUF_DESERT_NIGHT().isActive()){
                LOG.info("No active task.");
                fishBot.setRunned(false);
            }

            if (slotService.isReady()) {
                LOG.info("Slot ready.. Use");

                fishBot.call();

                String key = slotService.getKey();
                inputService.send(() -> key);
            }

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

        if (Application.getInstance().SLOT_DEBUF_DESERT_DAY().isActive() || Application.getInstance().SLOT_DEBUF_DESERT_NIGHT().isActive()) {
            fishBot.setState(new DebufState(fishBot));
        }

    }
}