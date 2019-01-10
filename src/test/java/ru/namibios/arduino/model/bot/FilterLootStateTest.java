package ru.namibios.arduino.model.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.bot.service.input.InputService;
import ru.namibios.arduino.model.command.FishLoot;

import java.io.IOException;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilterLootStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private InputService inputService;

    @InjectMocks
    private FilterLootState filterLootState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilter() throws IOException {

        filterLootState.onStep();

        verify(inputService).send(isA(FishLoot.class));
        verify(fishBot).setState(isA(UseSlotState.class));
    }

    @Test
    public void testException() throws IOException {

        doThrow(NullPointerException.class).when(inputService).send(isA(FishLoot.class));

        filterLootState.onStep();

        verify(fishBot).setState(isA(StartFishState.class));
    }
}