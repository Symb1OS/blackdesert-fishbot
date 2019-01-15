package ru.namibios.arduino.gui.controller;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.Launcher;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResetController implements ActionListener {

    private static final Logger LOG = Logger.getLogger(Launcher.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            LOG.info("delete application.properties..");
            Files.delete(Paths.get("resources/application.properties"));

        } catch (IOException ioe) {
            LOG.info("application.properties not found.");
        }

        try {

            Application.restart();

        } catch (IOException ioe) {
            LOG.error(ExceptionUtils.getString(ioe));
        }

    }
}
