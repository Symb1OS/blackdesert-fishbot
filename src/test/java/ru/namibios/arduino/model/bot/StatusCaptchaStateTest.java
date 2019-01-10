package ru.namibios.arduino.model.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.bot.service.HttpService;
import ru.namibios.arduino.model.bot.service.StatusService;
import ru.namibios.arduino.model.status.Status;
import ru.namibios.arduino.model.template.StatusCaptchaTemplate;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusCaptchaStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private StatusService statusService;

    @Mock
    private HttpService httpService;

    @InjectMocks
    private StatusCaptchaState statusCaptchaState;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess() {

        when(statusService.getTemplate(any(Status.class))).thenReturn(StatusCaptchaTemplate.SUCCESS);

        statusCaptchaState.onStep();

        verify(statusService).getTemplate(isA(Status.class));
        verify(fishBot).setState(isA(FilterLootState.class));

    }

    @Test
    public void testFailure() throws IOException {

        String name = "file";

        when(statusService.getTemplate(any(Status.class))).thenReturn(StatusCaptchaTemplate.FAILED);

        statusCaptchaState.setFilename(name);
        statusCaptchaState.onStep();

        verify(statusService).getTemplate(isA(Status.class));
        verify(fishBot).setState(isA(StartFishState.class));
        verify(httpService).markFail(name);

    }

    @Test
    public void testOverflow() {

        int overflow = Application.getInstance().STATE_STATUS_CAPTCHA_OVERFLOW() + 1;

        when(statusService.getTemplate(any(Status.class))).thenReturn(null);

        for (int i = 0; i < overflow; i++) {
            statusCaptchaState.onStep();
        }

        verify(statusService, times(overflow)).getTemplate(isA(Status.class));
        verify(fishBot, atLeastOnce()).setState(isA(FilterLootState.class));
    }

    @Test
    public void testThrowException(){

        when(statusService.getTemplate(any(Status.class))).thenThrow(new NullPointerException("Test Exception"));

        statusCaptchaState.onStep();

        verify(fishBot).setState(isA(FilterLootState.class));
    }

}