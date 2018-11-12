package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.command.Captcha;
import ru.namibios.arduino.model.state.service.input.InputService;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaStateTest {

    @Mock
    private InputService inputService;

    @Mock
    private FishBot fishBot;

    @InjectMocks
    private CaptchaState captchaState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendToInput() throws IOException {

        Mockito.when(inputService.send(any(Captcha.class))).thenReturn(true);

        captchaState.onStep();

        Mockito.verify(inputService).send(isA(Captcha.class));
        Mockito.verify(fishBot).setState(isA(StatusCaptchaState.class));

    }

    @Test
    public void testReturnToStart() throws IOException {

        Mockito.when(inputService.send(any(Captcha.class))).thenReturn(false);

        captchaState.onStep();

        Mockito.verify(inputService).send(isA(Captcha.class));
        Mockito.verify(fishBot).setState(isA(StartFishState.class));

    }
}