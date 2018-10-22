package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.command.Kapcha;
import ru.namibios.arduino.model.state.service.CommandSender;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class KapchaStateTest {

    @Mock
    private CommandSender commandSender;

    @Mock
    private FishBot fishBot;

    @InjectMocks
    private KapchaState kapchaState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendToInput() throws IOException {

        Mockito.when(commandSender.send(any(Kapcha.class))).thenReturn(true);

        kapchaState.onStep();

        Mockito.verify(commandSender).send(isA(Kapcha.class));
        Mockito.verify(fishBot).setState(isA(StatusKapchaState.class));

    }

    @Test
    public void testReturnToStart() throws IOException {

        Mockito.when(commandSender.send(any(Kapcha.class))).thenReturn(false);

        kapchaState.onStep();

        Mockito.verify(commandSender).send(isA(Kapcha.class));
        Mockito.verify(fishBot).setState(isA(StartFishState.class));

    }
}