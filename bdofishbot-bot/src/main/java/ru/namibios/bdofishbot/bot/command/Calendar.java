package ru.namibios.bdofishbot.bot.command;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.PaletteParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.CalendarTemplate;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.awt.*;

public class Calendar implements Command {

    private final static Logger LOG = Logger.getLogger(Calendar.class);

    @Override
    public String getKey() {

        try {

            for (Rectangle rectangle : Application.getInstance().CALENDAR()) {
                Screen screen = new Screen(rectangle, true);

                PaletteParser parser = new PaletteParser(screen, CalendarTemplate.values());
                parser.parse(PaletteParser.CALENDAR_PALETTE);
                MatrixTemplate window = parser.getValue();
                if (window != null) {
                    LOG.info("Detected " + window);
                    return "Skip_calendar";
                }

            }

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

        return "";
    }
}
