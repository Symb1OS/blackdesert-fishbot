package ru.namibios.arduino.model.bot;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Slot;
import ru.namibios.arduino.model.bot.service.input.InputService;
import ru.namibios.arduino.model.command.DebufStatus;
import ru.namibios.arduino.model.template.Debuf;
import ru.namibios.arduino.utils.ExceptionUtils;

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
                if (Application.getInstance().DEBUG_DEBUF()) {
                    LOG.debug("Detected debuff " + debuf);
                }

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