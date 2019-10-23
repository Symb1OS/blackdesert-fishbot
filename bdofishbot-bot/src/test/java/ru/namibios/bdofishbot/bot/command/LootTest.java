package ru.namibios.bdofishbot.bot.command;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.cli.Application;

import java.io.File;
import java.io.IOException;

public class LootTest {

    private final static Logger LOG = Logger.getLogger(LootTest.class);

    @Before
    public void init() {
        Application.getInstance();
        Application.getInstance().setProperty("bot.loot.rock", "true");
        Application.getInstance().setProperty("bot.loot.key",  "true");
        Application.getInstance().setProperty("bot.loot.event","true");
        Application.getInstance().setProperty("bot.loot.fish", "true");

    }

    private void testCategory(String dir, String name) throws IOException {

        LOG.debug("Directory: " + dir);

        File categoryDir = new File(dir);
        for (File file : categoryDir.listFiles()) {
            Screen screen = new Screen(file.getPath());

            ImageParser parser = new ImageParser(screen, Loot.values());
            parser.parse(Screen.GRAY);

            String number = parser.getNumber();
            Assert.assertEquals(name, number);

        }
    }

    @Test
    public void testCorrectlyParse() throws IOException {

        testCategory("resources/loot/ok/scala", Loot.SCALA.name());
        testCategory("resources/loot/ok/event", Loot.EVENT.name());
        testCategory("resources/loot/ok/fish", Loot.FISH.name());
        testCategory("resources/loot/ok/key", Loot.KEY.name());
        testCategory("resources/loot/trash", Loot.TRASH.name());

    }

}