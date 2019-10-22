package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Slot;
import ru.namibios.bdofishbot.bot.command.DebufStatus;
import ru.namibios.bdofishbot.bot.service.input.InputService;
import ru.namibios.bdofishbot.bot.template.Debuf;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.awt.*;

public class DebufState extends State {

    private static final Logger LOG = Logger.getLogger(DebufState.class);

    public DebufState(FishBot fishBot) {
        super(fishBot);

        this.beforeStart = 0;
        this.afterStart = 0;

        this.inputService = fishBot.getInputService();

        try {

            this.debufStatus = new DebufStatus();

        } catch (AWTException e) {
            ExceptionUtils.getString(e);
        }

    }

    private InputService inputService;

    private DebufStatus debufStatus;

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

            String key = debufStatus.getKey();
            if (!key.isEmpty()) {

                Debuf debuf = Debuf.valueOf(key);
                LOG.debug("Detected debuff " + debuf);

                Slot slot;
                switch (debuf) {
                    case DAY:
                        slot = new Slot(Application.getInstance().SLOT_DEBUF_DESERT_DAY());
                        break;

                    case NIGHT:
                        slot = new Slot(Application.getInstance().SLOT_DEBUF_DESERT_NIGHT());
                        break;

                    default:
                        throw new IllegalArgumentException("Undefined value " + debuf);
                }

                fishBot.call();
                inputService.send(slot);
            }

        } catch (Exception e) {
            LOG.error(ExceptionUtils.getString(e));
        }

        fishBot.setState(new SlotTaskModeState(fishBot));

    }

}