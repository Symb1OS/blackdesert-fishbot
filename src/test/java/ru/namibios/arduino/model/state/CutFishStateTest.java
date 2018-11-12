package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.command.Line;
import ru.namibios.arduino.model.state.service.input.InputService;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CutFishStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private InputService inputService;

    @InjectMocks
    private CutFishState cutFishState;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCutFish() throws IOException {

        when(inputService.send(any(Line.class))).thenReturn(true);

        cutFishState.onStep();

        verify(inputService).send(isA(Line.class));
        verify(fishBot).setState(isA(StatusCutState.class));

    }

}