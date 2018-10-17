package ru.namibios.arduino.model.state;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.namibios.arduino.model.Touch;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Keyboard.class)
public class ChangeRodStateTest {

    @Mock
    private FishBot fishBot;

    @InjectMocks
    private ChangeRodState changeRodState;

    @Test
    public void testLockedRods() {

        when(fishBot.hasNextRod()).thenReturn(false);

        changeRodState.onStep();

        verify(fishBot).notifyUser(anyString());
        verify(fishBot).setRunned(false);

    }

    @Captor
    ArgumentCaptor<Command> commandCaptor;

    @Test
    public void testFreeFishRods() {

        Touch touch = new Touch(1, 1);

        when(fishBot.hasNextRod()).thenReturn(true);
        when(fishBot.getNextRod()).thenReturn(touch);

        PowerMockito.mockStatic(Keyboard.class);

        changeRodState.onStep();

        PowerMockito.verifyStatic(Keyboard.class);
        Keyboard.send(commandCaptor.capture());

        assertEquals(new Touch(1,1).toCommandRod(), commandCaptor.getValue().getKey());

        verify(fishBot).setState(any(StartFishState.class));
        verify(fishBot).restart();

    }
}