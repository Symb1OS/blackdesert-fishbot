package ru.namibios.bdofishbot.bot.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.bdofishbot.bot.Touch;
import ru.namibios.bdofishbot.bot.command.Command;
import ru.namibios.bdofishbot.bot.service.RodService;
import ru.namibios.bdofishbot.bot.service.input.InputService;
import ru.namibios.bdofishbot.cli.config.Message;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangeRodStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private InputService inputService;

    @Mock
    private RodService rodService;

    @InjectMocks
    private ChangeRodState changeRodState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLockedRods() {

        when(rodService.hasNext()).thenReturn(false);

        changeRodState.onStep();

        verify(fishBot).notifyUser(Message.OUT_RODS);
        verify(fishBot).setRunned(false);

    }

    @Captor
    private ArgumentCaptor<Command> commandCaptor;

    @Test
    public void testFreeFishRods() throws IOException {

        Touch touch = new Touch(1, 1);

        when(rodService.hasNext()).thenReturn(true);

        when(rodService.getNext()).thenReturn(touch.toCommandRod());
        when(inputService.send(any(Command.class))).thenReturn(true);

        changeRodState.onStep();

        verify(rodService).hasNext();
        verify(rodService).getNext();

        verify(inputService).send(commandCaptor.capture());

        assertEquals(new Touch(1,1).toCommandRod(), commandCaptor.getValue().getKey());

        verify(fishBot).call();
        verify(fishBot).setState(isA(StartFishState.class));
        verify(fishBot).restart();

    }
}