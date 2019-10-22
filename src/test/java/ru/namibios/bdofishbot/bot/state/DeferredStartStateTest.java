package ru.namibios.bdofishbot.bot.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.bdofishbot.bot.Timer;
import ru.namibios.bdofishbot.cli.Application;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeferredStartStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private Timer timer;

    @InjectMocks
    private DeferredStartState deferredStartState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStart() {

        Application.getInstance().setProperty("bot.autouse.start", "true, 0, Start");

        when(timer.isOver(anyLong())).thenReturn(true);

        deferredStartState.onStep();

        verify(fishBot).setState(isA(UseSlotState.class));
        verify(timer).isOver(anyLong());

    }

    @Test
    public void testStartInactive() {

        Application.getInstance().setProperty("bot.autouse.start", "false, 0, Start");
        when(timer.isOver(anyLong())).thenReturn(false);

        deferredStartState.onStep();

        verify(fishBot).setState(isA(UseSlotState.class));

    }

    @Test
    public void testWait() {

        Application.getInstance().setProperty("bot.autouse.start", "true, 0, Start");
        when(timer.isOver(anyLong())).thenReturn(false);

        deferredStartState.onStep();

        verify(timer).isOver(anyLong());
        verify(fishBot, never()).setState(isA(State.class));

    }
}