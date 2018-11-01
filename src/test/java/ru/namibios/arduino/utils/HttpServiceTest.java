package ru.namibios.arduino.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.state.service.HttpService;

import java.io.IOException;

public class HttpServiceTest {

    private HttpService http;

    @Before
    public void setUp() throws Exception {
        http = new HttpService();
    }

    @Test
    public void parseByteCaptcha() throws IOException {

        Screen screen = new Screen(Path.TEST_RESOURCES + "parsing/captcha/0303244444.jpg");
        String key = http.parseByteCaptcha(System.getProperty("user.name"), ImageUtils.imageToBytes(screen.getScreenShot()));
        Assert.assertEquals("wdwda", key);

    }


}