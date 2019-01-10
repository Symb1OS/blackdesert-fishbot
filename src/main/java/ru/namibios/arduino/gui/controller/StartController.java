package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.arduino.Transfer;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.model.bot.FishBot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private static final Logger LOG = Logger.getLogger(StartController.class);

	private RootView view;

	private Transfer threadTransfer;
	
	public StartController(Transfer threadTransfer, RootView view) {
		this.view = view;
		this.threadTransfer = threadTransfer;
	}
	
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(view, message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isInit = threadTransfer != null ;

		if (Application.getUser().isBlocked()){
            LOG.info(Message.USER_IS_BLOCKED);
            showMessageDialog(Message.USER_IS_BLOCKED);
            return;
        }

        if (isInit) {

            FishBot fishBot = threadTransfer.getFishBot();

            if (fishBot != null) {

               if (!fishBot.isRunned()) {
                   threadTransfer = new Transfer();
                   threadTransfer.start();

                } else {
                   showMessageDialog(Message.ALREADY_WORK);
                }

            } else {
                threadTransfer.start();
            }

        }
	}
}