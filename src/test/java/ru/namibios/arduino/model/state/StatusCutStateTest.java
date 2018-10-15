package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.status.StatusCut;
import ru.namibios.arduino.model.template.StatusCutTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusCutStateTest {

    @Spy
    private FishBot fishBot;

    @Mock
    private StatusCut statusCut;

    @InjectMocks
    private StatusCutState statusCutState;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testPerfect() {

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.PERFECT);

        statusCutState.onStep();

        verify(statusCut).getNameTemplate();

        assertEquals(FilterLootState.class, statusCutState.getFishBot().getState().getClass());

    }

    @Test
    public void testGood() {

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.GOOD);

        statusCutState.onStep();

        verify(statusCut).getNameTemplate();

        assertEquals(KapchaState.class, statusCutState.getFishBot().getState().getClass());

    }

    @Test
    public void testBad() {

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.BAD);

        statusCutState.onStep();

        verify(statusCut).getNameTemplate();

        assertEquals(StartFishState.class, statusCutState.getFishBot().getState().getClass());

    }

    @Test
    public void testOverflow() {

        int overflow = 500;

        when(statusCut.getNameTemplate()).thenReturn(null);

        for (int i = 0; i < overflow; i++) {
            statusCutState.onStep();
        }

        verify(statusCut, times(overflow)).getNameTemplate();

        assertEquals(FilterLootState.class, statusCutState.getFishBot().getState().getClass());

    }

    @Test
    public void throwException(){

        when(statusCut.getNameTemplate()).thenThrow(new NullPointerException());

        statusCutState.onStep();

        assertEquals(KapchaState.class, statusCutState.getFishBot().getState().getClass());

    }

}