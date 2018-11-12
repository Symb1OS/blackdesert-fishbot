package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Timer;
import ru.namibios.arduino.model.command.WaitFish;
import ru.namibios.arduino.model.state.service.input.InputService;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class WaitFishStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private Timer timer;

    @Mock
    private InputService inputService;

    @InjectMocks
    private WaitFishState waitFishState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBite() throws IOException {

        Mockito.when(inputService.send(any(WaitFish.class))).thenReturn(true);

        waitFishState.onStep();

        Mockito.verify(inputService).send(isA(WaitFish.class));
        Mockito.verify(fishBot).setState(isA(CutFishState.class));

    }

    @Test
    public void testChangeRod() throws IOException {

        Mockito.when(inputService.send(any(WaitFish.class))).thenReturn(false);
        Mockito.when(timer.isOver(Application.getInstance().TIME_CHANGE_ROD())).thenReturn(true);

        waitFishState.onStep();

        Mockito.verify(inputService).send(isA(WaitFish.class));
        Mockito.verify(timer).isOver(Application.getInstance().TIME_CHANGE_ROD());
        Mockito.verify(fishBot).setState(isA(ChangeRodState.class));

    }
}