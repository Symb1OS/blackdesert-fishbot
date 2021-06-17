package ru.namibios.bdofishbot.bot.command;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.CloseTemplate;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

public class LootWindow implements Command {

    private final static Logger LOG = Logger.getLogger(Calendar.class);

    @Override
    public String getKey() {

        try {

            Screen close = new Screen(Application.getInstance().LOOT_WINDOW_CLOSE(), true);
            ImageParser parser = new ImageParser(close, CloseTemplate.values());
            parser.parse(Screen.GRAY);

            MatrixTemplate value = parser.getNameTemplate();
            if (value == null) {
                LOG.info("Incorrect loot window position.. Image saved resources/debug. Reset settings to default..");

                Screen lootWindow = new Screen(Application.getInstance().LOOT_WINDOW());
                Screen full = new Screen(Application.getInstance().FULL_SCREEN(), false);

                full.saveDebugImage();
                close.saveDebugImage();
                lootWindow.saveDebugImage();
            }

            return value != null ? value.toString() : "";

        } catch (Exception e) {
            LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
            LOG.error(ExceptionUtils.getString(e));
        }

        return "";
    }

}