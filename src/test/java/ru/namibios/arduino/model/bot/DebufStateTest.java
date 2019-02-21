package ru.namibios.arduino.model.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.bot.service.input.InputService;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.DebufStatus;

import java.io.IOException;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class DebufStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private InputService inputService;

    @Mock
    private DebufStatus debufStatus;

    @InjectMocks
    private DebufState debufState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDebufDay() throws IOException {

        when(debufStatus.getKey()).thenReturn("DAY");

        debufState.onStep();

        verify(debufStatus).getKey();
        verify(inputService).send(isA(Command.class));
        verify(fishBot).call();

    }

    @Test
    public void testDebufNight() throws IOException {

        when(debufStatus.getKey()).thenReturn("NIGHT");

        debufState.onStep();

        verify(debufStatus).getKey();
        verify(inputService).send(isA(Command.class));
        verify(fishBot).call();

    }

    @Test
    public void testEmpty() throws IOException {

        when(debufStatus.getKey()).thenReturn("");

        debufState.onStep();

        verify(debufStatus).getKey();
        verifyNoMoreInteractions(debufStatus, inputService);

    }
}