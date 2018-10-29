package ru.namibios.arduino.gui.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import ru.namibios.arduino.Transfer;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.config.TextAreaAppender;
import ru.namibios.arduino.gui.UI;
import ru.namibios.arduino.gui.controller.SettingsController;
import ru.namibios.arduino.gui.controller.StartController;
import ru.namibios.arduino.gui.controller.StopController;
import ru.namibios.arduino.utils.ExecUtils;

import javax.swing.*;
import java.awt.*;

public class RootView extends JFrame {

    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonStop;
    private JTextArea taLog;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;

    public RootView() {

        Transfer transfer = null;

        setTitle("Fish bot");
        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setLocation(0, 400);
        setSize(new Dimension(520, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image im = new ImageIcon(Path.ROOT_ICON).getImage();
        setIconImage(im);

        getRootPane().setDefaultButton(buttonStart);

        TextAreaAppender appender = new TextAreaAppender(taLog);
        appender.setLayout(new PatternLayout("[%d{dd.MM.yyyy HH:mm:ss}] - %m%n"));
        LogManager.getRootLogger().addAppender(appender);

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu(UIManager.getString("rootview.menu.file"));

        JMenuItem preference = new JMenuItem(UIManager.getString("rootview.menu.file.preference"));
        preference.setIcon(new ImageIcon(UI.IMG_SETTINGS));
        preference.addActionListener(new SettingsController());
        file.add(preference);

        file.addSeparator();

        JMenuItem exit = new JMenuItem(UIManager.getString("rootview.menu.file.close"));
        exit.setIcon(new ImageIcon(UI.IMG_CLOSE));
        exit.addActionListener((e) -> System.exit(1));
        file.add(exit);

        JMenu help = new JMenu(UIManager.getString("rootview.menu.help"));

        JMenuItem wiki = new JMenuItem(UIManager.getString("rootview.menu.help.wiki"));
        wiki.setIcon(new ImageIcon(UI.IMG_WIKI));
        wiki.addActionListener((e) -> ExecUtils.openUri(Message.URI_WIKI));
        help.add(wiki);

        JMenuItem feedback = new JMenuItem(UIManager.getString("rootview.menu.help.feedback"));
        feedback.setIcon(new ImageIcon(UI.IMG_FEEDBACK));
        feedback.addActionListener((e) -> ExecUtils.openUri(Message.URI_REPORT_PROBLEM));
        help.add(feedback);

        menuBar.add(file);
        menuBar.add(help);

        setJMenuBar(menuBar);

        buttonStart.addActionListener(new StartController(transfer, this));
        buttonStart.setIcon(new ImageIcon(UI.IMG_PLAY));

        buttonStop.addActionListener(new StopController(transfer));
        buttonStop.setIcon(new ImageIcon(UI.IMG_STOP));

        setVisible(true);
    }

}