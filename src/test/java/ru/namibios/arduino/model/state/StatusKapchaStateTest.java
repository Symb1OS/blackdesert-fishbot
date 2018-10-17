package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.status.Status;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusKapchaStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private StatusService statusService;

    @InjectMocks
    private StatusKapchaState statusKapchaState;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess() {

        Mockito.when(statusService.getTemplate(any(Status.class))).thenReturn(StatusKapchaTemplate.SUCCESS);

        statusKapchaState.onStep();

        Mockito.verify(statusService).getTemplate(isA(Status.class));
        Mockito.verify(fishBot).setState(isA(FilterLootState.class));

    }

    @Test
    public void testFailure() {

        Mockito.when(statusService.getTemplate(any(Status.class))).thenReturn(StatusKapchaTemplate.FAILURE);

        statusKapchaState.onStep();

        Mockito.verify(statusService).getTemplate(isA(Status.class));
        Mockito.verify(fishBot).setState(isA(StartFishState.class));
    }

    @Test
    public void testOverflow() {

        int overflow = 500;

        when(statusService.getTemplate(any(Status.class))).thenReturn(null);

        for (int i = 0; i < overflow; i++) {
            statusKapchaState.onStep();
        }

        verify(statusService, times(overflow)).getTemplate(isA(Status.class));
        verify(fishBot, atLeastOnce()).setState(isA(FilterLootState.class));
    }

    @Test
    public void testThrowException(){

        when(statusService.getTemplate(any(Status.class))).thenThrow(new NullPointerException());

        statusKapchaState.onStep();

        verify(fishBot).setState(isA(FilterLootState.class));
    }

}