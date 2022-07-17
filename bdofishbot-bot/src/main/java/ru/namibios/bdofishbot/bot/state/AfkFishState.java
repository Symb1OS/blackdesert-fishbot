package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.Command;
import ru.namibios.bdofishbot.bot.command.WaitFish;
import ru.namibios.bdofishbot.bot.service.SlotService;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class AfkFishState extends State {

    private static final Logger LOG = Logger.getLogger(AfkFishState.class);

    private final SlotService slotService;

    public AfkFishState(FishBot fishBot) {
        super(fishBot);

        this.beforeStart = Application.getInstance().DELAY_BEFORE_AFK_FISH();
        this.afterStart = Application.getInstance().DELAY_AFTER_AFK_FISH();

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

            Command command = new WaitFish(Application.getInstance().SPACE());
            if (command.getKey().isEmpty()) {
                timer.reset();
            }

            if (timer.isOver(Application.getInstance().TIME_CHANGE_AFK_FISH_ROD())) {
                 fishBot.setState(new ChangeRodState(fishBot));
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

    }
}
