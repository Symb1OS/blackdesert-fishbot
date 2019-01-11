package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.arduino.Transfer;
import ru.namibios.arduino.gui.view.RootView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopController implements ActionListener{

	private static final Logger LOG = Logger.getLogger(StopController.class);

	private Transfer threadTransfer;
	private RootView rootView;

	public StopController(Transfer threadTransfer, RootView rootView) {
		this.threadTransfer = threadTransfer;
		this.rootView = rootView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

        activePreference();

        if(threadTransfer != null)
			threadTransfer.getFishBot().setRunned(false);
	}

    private void activePreference() {
        JMenuItem preference = rootView.getPreference();
        preference.setEnabled(true);
    }

}