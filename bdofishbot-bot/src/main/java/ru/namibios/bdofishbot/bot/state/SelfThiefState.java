package ru.namibios.bdofishbot.bot.state;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.command.Mark;
import ru.namibios.bdofishbot.bot.command.ShortCommand;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class SelfThiefState extends State {

    private static final Logger LOG = Logger.getLogger(SelfThiefState.class);

    public SelfThiefState(FishBot fishBot) {
        super(fishBot);

        this.beforeStart = Application.getInstance().DELAY_SELF_THIEF_BEFORE();
        this.afterStart = Application.getInstance().DELAY_SELF_THIEF_AFTER();
    }

    @Override
    public void onStep() {

        try{

            if (Application.getInstance().MARK_SELT_THIEF()) {
                LOG.info("Check mark self thief");
                Mark mark = new Mark();
                if (mark.getKey().isEmpty()) {
                    inputService.send(ShortCommand.MARK);
                }
            }

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

        fishBot.setState(new CheckEquipState(fishBot));

    }

}