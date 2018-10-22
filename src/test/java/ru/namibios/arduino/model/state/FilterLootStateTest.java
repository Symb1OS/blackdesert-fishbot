package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.model.state.service.CommandSender;

import java.io.IOException;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilterLootStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private CommandSender commandSender;

    @InjectMocks
    private FilterLootState filterLootState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilter() throws IOException {

        filterLootState.onStep();

        verify(commandSender).send(isA(FishLoot.class));
        verify(fishBot).setState(isA(UseSlotState.class));
    }

    @Test
    public void testException() throws IOException {

        doThrow(NullPointerException.class).when(commandSender).send(isA(FishLoot.class));

        filterLootState.onStep();

        verify(fishBot).setState(isA(StartFishState.class));
    }
}