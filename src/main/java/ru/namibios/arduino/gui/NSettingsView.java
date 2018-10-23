package ru.namibios.arduino.gui;

import javax.swing.*;
import java.awt.event.*;

public class NSettingsView extends JDialog {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JPanel settings;
    private JPanel portContent;
    private JComboBox cbPort;
    private JLabel Loot;
    private JPanel loot;
    private JPanel lootContent;
    private JCheckBox cbFish;
    private JCheckBox cbEvent;
    private JCheckBox cbRock;
    private JCheckBox cbKey;
    private JRadioButton cbUnknow;
    private JPanel task;
    private JPanel taskContent;
    private JCheckBox cbBeer;
    private JCheckBox cbMinigame;
    private JPanel slots;
    private JCheckBox cvFirstSlotActive;
    private JTextField tfFirstSlotKey;
    private JTextField tfFirstSlotDelay;
    private JTextField tfFirstSlotPeriod;
    private JPanel slotContent;
    private JCheckBox cbSecondSlotActive;
    private JTextField tfSecondSlotKey;
    private JTextField tfSecondSlotDelay;
    private JTextField tfSecondSlotPeriod;
    private JCheckBox cbThirdSlotActive;
    private JTextField tfThirdSlotKey;
    private JTextField tfThirdSlotDelay;
    private JTextField tfThirdSlotPeriod;
    private JTextField tfRodCount;
    private JTextField tfRodChange;
    private JPanel notification;
    private JCheckBox cbTelegram;
    private JTextField textField1;
    private JPanel notificationContent;
    private JPanel delayContent;
    private JTextField tfBeforeStart;
    private JTextField tfAfterStart;
    private JPanel rod;
    private JPanel personalMessage;
    private JPanel delay;
    private JTextField tfBeforeWait;
    private JTextField tfAfterWait;
    private JTextField tfBeforeChangeRod;
    private JTextField tfAfterChangeRod;
    private JTextField tfBeforeCaptcha;
    private JTextField tfAfterCaptcha;
    private JTextField tfBeforeFilterLoot;
    private JTextField tfAfterFilterLoot;
    private JPanel settingsPanel;
    private JPanel buttonPanel;
    private JRadioButton rbAutoFish;
    private JRadioButton rbExitGame;
    private JRadioButton rbNothing;
    private JPanel pmContent;

    public NSettingsView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSave);

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        NSettingsView dialog = new NSettingsView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
