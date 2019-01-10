package ru.namibios.arduino.model.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.model.bot.service.input.InputService;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.PersonalMessage;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonalMessageStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private PersonalMessage pm;

    @Mock
    private InputService inputService;

    @InjectMocks
    private PersonalMessageState personalMessageState;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDetectedAutoFish() {

        Application.getInstance().setProperty("bot.pm.event.autofish","true");
        Application.getInstance().setProperty("bot.pm.event.exitgame","false");

        when(pm.isDetected()).thenReturn(true);
        when(fishBot.isPmDetected()).thenReturn(false);

        personalMessageState.onStep();

        verify(fishBot).notifyUser(Message.RECEIVED_PRIVATE_MESSAGE);
        verify(fishBot).notifyUser(Message.TURN_AUTOFISH);
        verify(fishBot).setRunned(false);

        verify(fishBot).setPmDetected(true);
        verify(fishBot).setState(isA(WaitFishState.class));

    }

    @Test
    public void testDetectedExitGame() throws IOException {

        Application.getInstance().setProperty("bot.pm.event.autofish","false");
        Application.getInstance().setProperty("bot.pm.event.exitgame","true");

        when(pm.isDetected()).thenReturn(true);
        when(fishBot.isPmDetected()).thenReturn(false);

        personalMessageState.onStep();

        verify(fishBot).notifyUser(Message.RECEIVED_PRIVATE_MESSAGE);
        verify(fishBot).notifyUser(Message.EXIT_GAME);
        verify(fishBot).setRunned(false);
        verify(inputService).send(any(Command.class));

        verify(fishBot).setPmDetected(true);
        verify(fishBot).setState(isA(WaitFishState.class));
    }
}