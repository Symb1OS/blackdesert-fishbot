package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.state.service.StatusService;
import ru.namibios.arduino.model.status.Status;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusCaptchaStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private StatusService statusService;

    @InjectMocks
    private StatusCaptchaState statusCaptchaState;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess() {

        Mockito.when(statusService.getTemplate(any(Status.class))).thenReturn(StatusKapchaTemplate.SUCCESS);

        statusCaptchaState.onStep();

        Mockito.verify(statusService).getTemplate(isA(Status.class));
        Mockito.verify(fishBot).setState(isA(FilterLootState.class));

    }

    @Test
    public void testFailure() {

        Mockito.when(statusService.getTemplate(any(Status.class))).thenReturn(StatusKapchaTemplate.FAILED);

        statusCaptchaState.onStep();

        Mockito.verify(statusService).getTemplate(isA(Status.class));
        Mockito.verify(fishBot).setState(isA(StartFishState.class));
    }

    @Test
    public void testOverflow() {

        int overflow = 51;

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