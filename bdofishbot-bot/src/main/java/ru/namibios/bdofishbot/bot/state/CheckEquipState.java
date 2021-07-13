package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.Command;
import ru.namibios.bdofishbot.bot.command.WaitFish;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class CheckEquipState extends State {

    private static final Logger LOG = Logger.getLogger(CheckEquipState.class);

    private int cntActivityButton = 0;

    public CheckEquipState(FishBot fishBot) {
        super(fishBot);

        this.beforeStart = Application.getInstance().DELAY_BEFORE_CHECK_EQUIP();
        this.afterStart = Application.getInstance().DELAY_AFTER_CHECK_EQUIP();

        this.isInterrupt = true;
        this.maxStep = Application.getInstance().CHECK_EQUIP_ITERATION();

        LOG.info("Check equipment..");
    }

    @Override
    public void onInterrupt() {
        fishBot.setState(new WaitFishState(fishBot));
    }

    @Override
    public void onStep() {

        try {

            if (cntActivityButton > 0) {
                fishBot.setState(new ChangeRodState(fishBot));
            }

            Command command = new WaitFish(Application.getInstance().SPACE());
            if (!command.getKey().isEmpty()) {
                cntActivityButton++;
            }

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

    }

}