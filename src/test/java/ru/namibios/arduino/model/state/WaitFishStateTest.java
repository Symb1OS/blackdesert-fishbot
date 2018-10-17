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
import ru.namibios.arduino.model.TimeService;
import ru.namibios.arduino.model.command.WaitFish;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class WaitFishStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private TimeService timeService;

    @Mock
    private CommandSender commandSender;

    @InjectMocks
    private WaitFishState waitFishState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBite() {

        Mockito.when(commandSender.send(any(WaitFish.class))).thenReturn(true);

        waitFishState.onStep();

        Mockito.verify(commandSender).send(isA(WaitFish.class));
        Mockito.verify(fishBot).setState(isA(CutFishState.class));

    }

    @Test
    public void testChangeRod() {

        Mockito.when(commandSender.send(any(WaitFish.class))).thenReturn(false);
        Mockito.when(timeService.isOver(Application.getInstance().TIME_CHANGE_ROD())).thenReturn(true);

        waitFishState.onStep();

        Mockito.verify(commandSender).send(isA(WaitFish.class));
        Mockito.verify(timeService).isOver(Application.getInstance().TIME_CHANGE_ROD());
        Mockito.verify(fishBot).setState(isA(ChangeRodState.class));

    }
}