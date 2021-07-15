package ru.namibios.bdofishbot.bot.command;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.MarkTemplate;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class Mark implements Command {

    private final static Logger LOG = Logger.getLogger(Mark.class);

    @Override
    public String getKey() {

        try {

            Screen screen = new Screen(Application.getInstance().MARK());
            ImageParser parser = new ImageParser(screen, MarkTemplate.values());
            parser.parse(Screen.WHITE);

            MatrixTemplate nameTemplate = parser.getNameTemplate();
            if (nameTemplate != null) {
                return nameTemplate.toString();
            }

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

        return ShortCommand.IGNORE.getKey();
    }

}