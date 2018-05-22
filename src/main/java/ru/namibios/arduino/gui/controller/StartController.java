package ru.namibios.arduino.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;

import ru.namibios.arduino.Transfer;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.utils.Http;
import ru.namibios.arduino.utils.JSON;

public class StartController implements ActionListener {
	
	private Logger logger = Logger.getLogger(StartController.class);

	private RootView view;

	private Transfer threadTransfer;
	
	public StartController(Transfer threadTransfer, RootView view) {
		this.view = view;
		this.threadTransfer = threadTransfer;
	}
	
	private Map<String, Object> getAuthMap() {
		String hash = Application.getInstance().HASH();
		Map<String, Object> map = new HashMap<>();
		try{
			Http http = new Http();
			map = JSON.getInstance().readValue(http.auth(hash), new TypeReference<Map<String, Object>>(){});
		}catch (Exception e) {
			logger.error("Exception " + e);
		}
		return map;
	}
	
	private void startThread() {
		
		boolean isInit = threadTransfer != null ;
		boolean isRunned = isInit && threadTransfer.getFishBot().isRunned();
		if( !isInit || !isRunned ){
			threadTransfer = new Transfer();
			threadTransfer.start();
		}else{
			showMessageDialog(Message.ALREADY_WORK);
		} 
		
	}
	
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(view, message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> map= getAuthMap();
		boolean status = (boolean) map.get("status");
		String message = (String) map.get("message");
		
		if (status) {
			startThread();
		}else {
			showMessageDialog(message);
		}
		
	}
	
}