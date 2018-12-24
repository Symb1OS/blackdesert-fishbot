package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.command.Captcha;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.state.service.input.InputService;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaStateTest {

    @Mock
    private InputService inputService;

    @Mock
    private FishBot fishBot;

    @Mock
    private Captcha captcha;

    @InjectMocks
    private CaptchaState captchaState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendToInput() throws IOException {


        when(captcha.getKey()).thenReturn("wasd");
        when(inputService.send(any(Captcha.class))).thenReturn(true);

        captchaState.onStep();

        verify(captcha).getKey();
        verify(inputService).send(isA(Command.class));
        verify(fishBot).setState(isA(StatusCaptchaState.class));

    }

    @Test
    public void testReturnToStart() throws IOException {

        when(captcha.getKey()).thenReturn("");
        when(inputService.send(any(Captcha.class))).thenReturn(false);

        captchaState.onStep();

        verify(captcha).getKey();
        verify(inputService).send(isA(Command.class));
        verify(fishBot).setState(isA(StartFishState.class));

    }

    @Test
    public void testEndWork() throws IOException {

        when(captcha.getKey()).thenReturn("Client need update");

        captchaState.onStep();

        verify(captcha).getKey();
    }
}