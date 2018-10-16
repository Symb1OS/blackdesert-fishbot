package ru.namibios.arduino.model.state;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.utils.Keyboard;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Keyboard.class)
@Ignore
public class FilterLootStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private FishLoot filter;

    @InjectMocks
    private FilterLootState filterLootState;

    @Test
    public void testFilter() {
        PowerMockito.mockStatic(Keyboard.class);

        Mockito.when(Keyboard.send(any(Command.class))).thenReturn(true);

        filterLootState.onStep();

        PowerMockito.verifyStatic(Keyboard.class);
        Keyboard.send(any(Command.class));

        verify(fishBot).setState(any(UseSlotState.class));
    }

    @Test
    public void testException() {
        PowerMockito.mockStatic(Keyboard.class);

        PowerMockito.doThrow(new Exception("Some exception")).when(Keyboard.class);

        filterLootState.onStep();

        verify(fishBot).setState(any(StartFishState.class));
    }
}