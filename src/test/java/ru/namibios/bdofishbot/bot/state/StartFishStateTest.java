package ru.namibios.bdofishbot.bot.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.bdofishbot.bot.command.Command;
import ru.namibios.bdofishbot.bot.service.PauseService;
import ru.namibios.bdofishbot.bot.service.input.InputService;
import ru.namibios.bdofishbot.cli.Application;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class StartFishStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private InputService inputService;

    @Mock
    private PauseService pauseService;

    @InjectMocks
    private StartFishState startFishState;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testPause() throws IOException {

        Application.getInstance().setProperty("bot.state.skip_calendar", "false");

        Mockito.when(pauseService.isReady()).thenReturn(true);
        Mockito.when(inputService.send(any(Command.class))).thenReturn(true);

        startFishState.onStep();

        Mockito.verify(inputService).send(any(Command.class));
        Mockito.verify(fishBot).setState(isA(PersonalMessageState.class));
        Mockito.verify(pauseService).isReady();
        Mockito.verify(pauseService).rest();

    }

    @Test
    public void testStart() throws IOException {

        Application.getInstance().setProperty("bot.state.skip_calendar", "true");

        Mockito.when(inputService.send(any(Command.class))).thenReturn(true);

        startFishState.onStep();

        Mockito.verify(inputService, Mockito.times(2)).send(any(Command.class));
        Mockito.verify(fishBot).setState(isA(PersonalMessageState.class));

    }

    @Test
    public void testWithoutSkipCalendar() throws IOException {

        Application.getInstance().setProperty("bot.state.skip_calendar", "false");

        Mockito.when(inputService.send(any(Command.class))).thenReturn(true);

        startFishState.onStep();

        Mockito.verify(inputService).send(any(Command.class));
        Mockito.verify(fishBot).setState(isA(PersonalMessageState.class));

    }
}