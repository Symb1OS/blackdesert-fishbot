package ru.namibios.arduino.model.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.bot.service.SlotService;
import ru.namibios.arduino.model.bot.service.input.InputService;
import ru.namibios.arduino.model.command.Command;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SlotTaskModeStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private InputService inputService;

    @Mock
    private SlotService slotService;

    @InjectMocks
    private SlotTaskModeState slotTaskModeState;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSlotReady() throws IOException {

        when(slotService.isActiveTasks()).thenReturn(true);
        when(slotService.isReady()).thenReturn(true);
        when(slotService.getKey()).thenReturn("1");
        when(inputService.send(any(Command.class))).thenReturn(true);

        slotTaskModeState.onStep();

        verify(slotService).isReady();
        verify(fishBot).call();
        verify(slotService).getKey();
        verify(inputService).send(any(Command.class));

    }

    @Test
    public void testNoActiveSlot() {

        when(slotService.isActiveTasks()).thenReturn(false);

        slotTaskModeState.onStep();

        verify(fishBot).setRunned(false);

    }

}