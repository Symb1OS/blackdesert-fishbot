package ru.namibios.bdofishbot.gui.controller;


import ru.namibios.bdofishbot.cli.Bot;
import ru.namibios.bdofishbot.gui.view.StatsGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsController implements ActionListener {

    private Bot bot;

    public StatsController(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StatsGui statsGui = new StatsGui(bot);
    }

}
