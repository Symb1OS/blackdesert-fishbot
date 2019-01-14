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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StartController implements ActionListener {

    private static final Logger LOG = Logger.getLogger(StartController.class);

	private RootView view;

    private Executor executor = Executors.newSingleThreadExecutor();

	private Transfer transfer;

	public StartController(RootView view) {
		this.view = view;
        this.transfer = new Transfer();

	}

	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(view, message);
	}

	private void start(){

        if (Application.getUser().isBlocked()){
            LOG.info(Message.USER_IS_BLOCKED);
            showMessageDialog(Message.USER_IS_BLOCKED);
            return;
        }

        boolean isInit = transfer != null;
        if (isInit) {

            FishBot fishBot = transfer.getFishBot();

            if (fishBot != null) {
                if (!fishBot.isRunned()) {
                    transfer = new Transfer();
                    executor.execute(transfer);

                } else {
                    showMessageDialog(Message.ALREADY_WORK);
                }

            } else {
                executor.execute(transfer);
            }

            enablePreference(false);
        }
    }

    private void stop(){
        enablePreference(true);
        if(transfer != null){
            transfer.getFishBot().setRunned(false);
            transfer.getFishBot().stopExecutors();
        }

    }

	@Override
	public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(UIManager.getString("rootview.button.start"))) {
            start();
        }

        if (e.getActionCommand().equals(UIManager.getString("rootview.button.stop"))) {
            stop();
        }

	}

    private void enablePreference(boolean state) {
        JMenuItem preference = view.getPreference();
        preference.setEnabled(state);
    }
}