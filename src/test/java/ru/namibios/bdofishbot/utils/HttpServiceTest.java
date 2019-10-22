package ru.namibios.bdofishbot.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.bot.state.service.HttpService;
import ru.namibios.bdofishbot.config.Path;

import java.io.IOException;
import java.util.UUID;

public class HttpServiceTest {

    private HttpService http;

    @Before
    public void setUp() throws Exception {
        http = new HttpService();
    }

    @Test
    public void parseByteCaptchaByName() throws IOException {

        Screen screen = new Screen(Path.TEST_RESOURCES + "parsing/captcha/0303244444.jpg");
        String key = http.parseByteCaptcha(UUID.randomUUID() + "_test", ImageUtils.imageToBytes(screen.getScreenShot()));
        Assert.assertEquals("wdwda", key);

    }

}