package ru.namibios.arduino.model.state;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import ru.namibios.arduino.model.status.Status;
import ru.namibios.arduino.model.status.StatusCut;
import ru.namibios.arduino.model.template.StatusCutTemplate;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusCutStateTest {

    @Mock
    private FishBot fishBot;

    @Mock
    private Status<StatusCutTemplate> statusCut;

    @InjectMocks
    private StatusCutState statusCutState;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void hz(){

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.GOOD);

        statusCutState.onStep();

        verify(statusCut).getNameTemplate();

    }

    @Test
    public void testForwardFilterLootState() {

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.PERFECT);

        statusCutState.onStep();

        verify(statusCut).getNameTemplate();

    }

    @Test
    public void testParseCaptchaState() {

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.GOOD);

        statusCutState.onStep();


    }

    @Test
    public void testBacktoStartState() {

        when(statusCut.getNameTemplate()).thenReturn(StatusCutTemplate.BAD);

        statusCutState.onStep();

    }

    @Test
    public void testOveflow() {

        int overflow = 500;

        when(statusCut.getNameTemplate()).thenReturn(null);

        for (int i = 0; i < overflow; i++) {
            statusCutState.onStep();
        }

        verify(statusCut, times(overflow)).getNameTemplate();

    }

}
