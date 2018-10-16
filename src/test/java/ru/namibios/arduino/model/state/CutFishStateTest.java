package ru.namibios.arduino.model.state;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Keyboard.class)
public class CutFishStateTest {

    @Mock
    private FishBot fishBot;

    @InjectMocks
    private CutFishState cutFishState;

    @Test
    public void testCutFish() {

        PowerMockito.mockStatic(Keyboard.class);

        Mockito.when(Keyboard.send(any(Command.class))).thenReturn(true);

        cutFishState.onStep();

        PowerMockito.verifyStatic(Keyboard.class);
        Keyboard.send(any(Command.class));

        verify(fishBot).setState(any(StatusCutState.class));

    }

}