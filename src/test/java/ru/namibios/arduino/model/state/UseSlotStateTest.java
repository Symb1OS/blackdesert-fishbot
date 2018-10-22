package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.state.service.CommandSender;
import ru.namibios.arduino.model.state.service.SlotService;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseSlotStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private CommandSender commandSender;

    @Mock
    private SlotService slotService;

    @InjectMocks
    private UseSlotState useSlotState;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUseSlot() throws IOException {

        when(slotService.isReady()).thenReturn(true);
        when(slotService.getKey()).thenReturn("1");
        when(commandSender.send(any(Command.class))).thenReturn(true);

        useSlotState.onStep();

        verify(slotService).isReady();
        verify(slotService).getKey();
        verify(commandSender).send(any(Command.class));
        verify(fishBot).setState(isA(StartFishState.class));

    }

}