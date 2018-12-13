package ru.namibios.arduino.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.state.service.HttpService;

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