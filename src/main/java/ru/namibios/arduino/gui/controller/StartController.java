package ru.namibios.arduino.gui.controller;

import ru.namibios.arduino.Transfer;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.NRootView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {
	
	private NRootView view;

	private Transfer threadTransfer;
	
	public StartController(Transfer threadTransfer, NRootView view) {
		this.view = view;
		this.threadTransfer = threadTransfer;
	}
	
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(view, message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isInit = threadTransfer != null ;
		boolean isRunned = isInit && threadTransfer.getFishBot().isRunned();
		if( !isInit){
			threadTransfer = new Transfer();
			threadTransfer.start();

        } else if (!isRunned) {
		    threadTransfer.getFishBot().setRunned(true);
            threadTransfer = new Transfer(threadTransfer.getFishBot());
            threadTransfer.start();

        } else {
            showMessageDialog(Message.ALREADY_WORK);
        }
		
	}
	
}