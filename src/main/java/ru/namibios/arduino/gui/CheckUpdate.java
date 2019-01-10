package ru.namibios.arduino.gui;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.view.RootView;
import ru.namibios.arduino.model.bot.service.HttpService;
import ru.namibios.arduino.utils.ExecUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckUpdate extends WindowAdapter{

    private static final Logger LOG = Logger.getLogger(CheckUpdate.class);

    private static final String LATEST_RELEASE_URL = "https://github.com/Symb1OS/blackdesert-fishbot/releases/latest";

    private RootView rootView;

    public CheckUpdate(RootView rootView) {
        this.rootView = rootView;
    }

    private boolean checkUpdate() {

        try {

            HttpService httpService = new HttpService();

            String lastReleaseTag = httpService.getLastReleaseTag().replaceAll("\\D", "");
            String curVersion = Application.getUser().getVersion().replaceAll("\\D", "");

            LOG.debug("Current version: " + curVersion);
            LOG.debug("Last available version: " + lastReleaseTag);

            if (!curVersion.isEmpty() && Integer.valueOf(curVersion) < Integer.valueOf(lastReleaseTag))
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        new CheckUpdateTask().start();
    }

    private final class CheckUpdateTask extends Thread {

        @Override
        public void run() {

            boolean oldVersion = checkUpdate();
            if (oldVersion) {
                int code = JOptionPane.showConfirmDialog(rootView, "You want download new version?", "Available update", JOptionPane.YES_NO_OPTION);
                if (code == JOptionPane.YES_OPTION) {
                    ExecUtils.openUri(LATEST_RELEASE_URL);
                }
            }

        }
    }

}
