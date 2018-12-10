package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.arduino.model.state.service.RodService;
import ru.namibios.arduino.model.state.service.input.EmulationService;
import ru.namibios.arduino.model.state.service.input.emulation.AWTRobot;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TestController implements ActionListener{

	private static final Logger LOG = Logger.getLogger(TestController.class);
	
	public TestController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	    new Thread(() -> {

            try {

                testRods();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }).start();


    }

    private static void printFact(){
	    LOG.info(String.format("Position [%s, %s]", (int) MouseInfo.getPointerInfo().getLocation().getLocation().getX(),
                (int) MouseInfo.getPointerInfo().getLocation().getLocation().getY()));
    }

    private static void testRods() throws IOException {
        LOG.info("Test rods..");

        EmulationService emulationService = new EmulationService(new AWTRobot());
        RodService rodService = new RodService(8);

        while (rodService.hasNext()) {

            DelayUtils.delay(2000);

            String next = rodService.getNext();
            LOG.info(next);
            emulationService.send(() -> next);

            printFact();

        }
    }

}