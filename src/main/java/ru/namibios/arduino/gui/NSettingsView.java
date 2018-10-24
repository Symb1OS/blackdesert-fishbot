package ru.namibios.arduino.gui;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;

public class NSettingsView extends JDialog {

    private static final Logger LOG = Logger.getLogger(NSettingsView.class);

    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JPanel settings;
    private JPanel portContent;
    private JComboBox<String> cbPort;
    private JLabel Loot;
    private JPanel loot;
    private JPanel lootContent;
    private JCheckBox cbFish;
    private JCheckBox cbEvent;
    private JCheckBox cbRock;
    private JCheckBox cbKey;
    private JCheckBox cbUnknown;
    private JPanel task;
    private JPanel taskContent;
    private JCheckBox cbBeer;
    private JCheckBox cbMinigame;
    private JPanel slots;
    private JCheckBox cbFirstSlotActive;
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
    private JTextField tfTelegramKey;
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


    private void init(){

        SerialPort[] portNames = SerialPort.getCommPorts();
        for(int i = 0; i < portNames.length; i++){
            try{
                cbPort.addItem(new String(portNames[i].getDescriptivePortName().getBytes("ISO-8859-1"), "Cp1251"));

            }catch (Exception e) {
                LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
                LOG.error(ExceptionUtils.getString(e));
            }
        }

        int count = cbPort.getItemCount();
        for (int index = 0; index < count; index++) {
            if(cbPort.getItemAt(index).contains(Application.getInstance().PORT())){
                cbPort.setSelectedItem(cbPort.getItemAt(index));
            }
        }

        initLootFilter();
        initTask();
        initSlots();
        initRod();
        initPm();
        initNotification();
        initDelay();

    }

    private void initPm() {
        rbAutoFish.setSelected(Application.getInstance().PM_AUTOFISH());
        rbExitGame.setSelected(Application.getInstance().PM_EXIT_GAME());
        rbNothing.setSelected(Application.getInstance().PM_NOTHING());

        ButtonGroup pmGroup = new ButtonGroup();
        pmGroup.add(rbAutoFish);
        pmGroup.add(rbExitGame);
        pmGroup.add(rbNothing);
    }

    private void initRod() {
        tfRodChange.setText(String.valueOf(Application.getInstance().TIME_CHANGE_ROD()));
        tfRodCount.setText(String.valueOf(Application.getInstance().COUNT_ROD()));
    }

    private void initNotification() {
        cbTelegram.setSelected(Application.getInstance().TELEGRAM());
        tfTelegramKey.setText(Application.getInstance().TELEGRAM_KEY());
    }

    private void initDelay() {
        tfBeforeStart.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_START()));
        tfAfterStart.setText(String.valueOf(Application.getInstance().DELAY_AFTER_START()));

        tfBeforeWait.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_WAIT_FISH()));
        tfAfterWait.setText(String.valueOf(Application.getInstance().DELAY_AFTER_WAIT_FISH()));

        tfBeforeChangeRod.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_CHANGE_ROD()));
        tfAfterChangeRod.setText(String.valueOf(Application.getInstance().DELAY_AFTER_CHANGE_ROD()));

        tfBeforeCaptcha.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_KAPCHA()));
        tfAfterCaptcha.setText(String.valueOf(Application.getInstance().DELAY_AFTER_KAPCHA()));

        tfBeforeFilterLoot.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_FILTER_LOOT()));
        tfAfterFilterLoot.setText(String.valueOf(Application.getInstance().DELAY_AFTER_FILTER_LOOT()));

    }

    private void initSlots() {
        cbFirstSlotActive.setSelected(Application.getInstance().SLOT_ONE().isActive());
        tfFirstSlotKey.setText(Application.getInstance().SLOT_ONE().getKey());
        tfFirstSlotDelay.setText(String.valueOf(Application.getInstance().SLOT_ONE().getDelay()));
        tfFirstSlotPeriod.setText(String.valueOf(Application.getInstance().SLOT_ONE().getPeriod()));

        cbSecondSlotActive.setSelected(Application.getInstance().SLOT_TWO().isActive());
        tfSecondSlotKey.setText(Application.getInstance().SLOT_TWO().getKey());
        tfSecondSlotDelay.setText(String.valueOf(Application.getInstance().SLOT_TWO().getDelay()));
        tfSecondSlotPeriod.setText(String.valueOf(Application.getInstance().SLOT_TWO().getPeriod()));

        cbThirdSlotActive.setSelected(Application.getInstance().SLOT_THREE().isActive());
        tfThirdSlotKey.setText(Application.getInstance().SLOT_THREE().getKey());
        tfThirdSlotDelay.setText(String.valueOf(Application.getInstance().SLOT_THREE().getDelay()));
        tfThirdSlotPeriod.setText(String.valueOf(Application.getInstance().SLOT_THREE().getPeriod()));

    }

    private void initLootFilter() {
        cbFish.setSelected(Application.getInstance().FISH());
        cbKey.setSelected(Application.getInstance().KEY());
        cbEvent.setSelected(Application.getInstance().EVENT());
        cbRock.setSelected(Application.getInstance().ROCK());
        cbUnknown.setSelected(Application.getInstance().TAKE_UNKNOWN());
    }

    private void initTask() {
        cbBeer.setSelected(Application.getInstance().BEER());
        cbMinigame.setSelected(Application.getInstance().MINIGAME());
    }

    public NSettingsView() {
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonSave);

        init();

        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        NSettingsView dialog = new NSettingsView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}