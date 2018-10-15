package ru.namibios.arduino.model.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.status.StatusKapcha;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusKapchaStateTest {

    @Spy
    private FishBot fishBot;

    @Mock
    private StatusKapcha statusKapcha;

    @InjectMocks
    private StatusKapchaState statusKapchaState;


    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess() {

        Mockito.when(statusKapcha.getNameTemplate()).thenReturn(StatusKapchaTemplate.SUCCESS);

        statusKapchaState.onStep();

        Mockito.verify(statusKapcha).getNameTemplate();

        assertEquals(FilterLootState.class, statusKapchaState.getFishBot().getState().getClass());
    }

    @Test
    public void testFailure() {
        Mockito.when(statusKapcha.getNameTemplate()).thenReturn(StatusKapchaTemplate.FAILURE);

        statusKapchaState.onStep();

        Mockito.verify(statusKapcha).getNameTemplate();

        assertEquals(StartFishState.class, statusKapchaState.getFishBot().getState().getClass());
    }

    @Test
    public void testOverflow() {

        int overflow = 400;

        when(statusKapcha.getNameTemplate()).thenReturn(null);

        for (int i = 0; i < overflow; i++) {
            statusKapchaState.onStep();
        }

        verify(statusKapcha, times(overflow)).getNameTemplate();

        assertEquals(FilterLootState.class, statusKapchaState.getFishBot().getState().getClass());
    }

    @Test
    public void throwException(){

        when(statusKapcha.getNameTemplate()).thenThrow(new NullPointerException());

        statusKapchaState.onStep();

        assertEquals(FilterLootState.class, statusKapchaState.getFishBot().getState().getClass());

    }

}
