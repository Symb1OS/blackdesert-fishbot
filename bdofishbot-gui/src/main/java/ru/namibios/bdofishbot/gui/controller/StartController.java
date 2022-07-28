package ru.namibios.bdofishbot.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.state.FishBot;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.Bot;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.gui.view.RootView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StartController implements ActionListener {

    private static final Logger LOG = Logger.getLogger(StartController.class);

	private RootView view;

    private Executor executor = Executors.newSingleThreadExecutor();

	private Bot bot;
    private StatsController statsController;

    public StartController(RootView view, Bot bot, StatsController statsController) {
		this.view = view;
        this.bot = bot;

        this.statsController = statsController;
    }

	private void showMessageDialog(String message) {
        LOG.info(message);
		JOptionPane.showMessageDialog(view, message);
	}

	private void start(){

        if (Application.getUser().isBlocked()){
            showMessageDialog(Message.USER_IS_BLOCKED);
            return;
        }

        boolean isInit = bot != null;
        if (isInit) {

            FishBot fishBot = bot.getFishBot();

            if (fishBot != null) {
                if (!fishBot.isRunned()) {
                    bot = new Bot();
                    executor.execute(bot);

                } else {
                    showMessageDialog(Message.ALREADY_WORK);
                }

            } else {
                executor.execute(bot);
            }

            statsController.setBot(bot);
            enablePreference(false);
        }
    }

    private void stop(){
        enablePreference(true);
        if(bot.getFishBot() != null){
            bot.getFishBot().setRunned(false);
            bot.getFishBot().stopExecutors();
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