package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.Touch;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.state.service.CommandSender;
import ru.namibios.arduino.model.state.service.RodService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangeRodStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private CommandSender commandSender;

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
        when(commandSender.send(any(Command.class))).thenReturn(true);

        changeRodState.onStep();

        verify(rodService).hasNext();
        verify(rodService).getNext();

        verify(commandSender).send(commandCaptor.capture());

        assertEquals(new Touch(1,1).toCommandRod(), commandCaptor.getValue().getKey());

        verify(fishBot).setState(isA(StartFishState.class));
        verify(fishBot).restart();

    }
}