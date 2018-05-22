package ru.namibios.arduino;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.utils.DelayUtils;

public class Transfer extends Thread{ 
	
	final static Logger logger = Logger.getLogger(Transfer.class);

	private FishBot fishBot;
	
	public Transfer() {
		this.fishBot = new FishBot();
		new ActionClientSocket();
	}
	
	public FishBot getFishBot() {
		return fishBot;
	}
	
	public void restart(){
		logger.info("Need Restart. Restarted after 15 second...");
		DelayUtils.delay(15000);
		fishBot.setRunned(true);
		fishBot.setRestart(false);
		run();
	}

	@Override
	public void run() {
		
		logger.info("Start...");

		Application.getPhysicalPort().openPort();
		DelayUtils.delay(3000);
		
		if(!Application.getPhysicalPort().isOpen()) {
			logger.info("Port is closed. Check you port in settings");
			return;
		} 
		
		logger.info("Port is open...");
		
		while (fishBot.isRunned()) fishBot.getState().start();
		
		Application.getPhysicalPort().closePort();
		
		logger.info("Port closed...");
		logger.info("Thread stop.");
		
		if(fishBot.isRestart()) restart();

	}
	
}