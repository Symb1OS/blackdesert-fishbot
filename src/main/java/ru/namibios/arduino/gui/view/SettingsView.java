package ru.namibios.arduino.gui.view;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.gui.CustomVerifier;
import ru.namibios.arduino.gui.UI;
import ru.namibios.arduino.gui.controller.CancelController;
import ru.namibios.arduino.gui.controller.SaveController;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;

public class SettingsView extends JDialog {

    private static final Logger LOG = Logger.getLogger(SettingsView.class);

    private static final String REGEX_DELAY_OR_PERIOD = "[0-9]+[m|s]{0,1}";
    private static final String REGEX_DELAY = "[0-9]+";
    private static final String REGEX_SLOT = "[a-z0-9-=]{1}";
    private static final String REGEX_ROD_COUNT = "[0-8]{1}";

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

    private CustomVerifier slotKeyVerifier;
    private CustomVerifier delayPeriodVerifier;
    private CustomVerifier delayVerifier;
    private CustomVerifier rodCountVerifier;


    private void init(){

        SerialPort[] portNames = SerialPort.getCommPorts();
        for(int i = 0; i < portNames.length; i++){
            try{
                cbPort.addItem(new String(portNames[i].getDescriptivePortName().getBytes(StandardCharsets.ISO_8859_1), "Cp1251"));

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

        slotKeyVerifier = new CustomVerifier(UIManager.getString("err.format.slotkey"), REGEX_SLOT);
        delayPeriodVerifier = new CustomVerifier(UIManager.getString("err.format.slotdelayperiod"), REGEX_DELAY_OR_PERIOD);
        delayVerifier = new CustomVerifier(UIManager.getString("err.format.delay"), REGEX_DELAY);
        rodCountVerifier = new CustomVerifier(UIManager.getString("err.format.rodcount"), REGEX_ROD_COUNT);

        initLootFilter();
        initTask();
        initSlots();
        initRod();
        initPm();
        initNotification();
        initDelay();

        Image im = new ImageIcon(UI.IMG_SETTINGS).getImage();
        setIconImage(im);

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
        tfRodChange.setInputVerifier(delayVerifier);

        tfRodCount.setText(String.valueOf(Application.getInstance().COUNT_ROD()));
        tfRodCount.setInputVerifier(rodCountVerifier);
    }

    private void initNotification() {
        cbTelegram.setSelected(Application.getInstance().TELEGRAM());
        tfTelegramKey.setText(Application.getInstance().TELEGRAM_KEY());
    }

    private void initDelay() {

        tfBeforeStart.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_START()));
        tfBeforeStart.setInputVerifier(delayVerifier);

        tfAfterStart.setText(String.valueOf(Application.getInstance().DELAY_AFTER_START()));
        tfAfterStart.setInputVerifier(delayVerifier);

        tfBeforeWait.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_WAIT_FISH()));
        tfBeforeWait.setInputVerifier(delayVerifier);

        tfAfterWait.setText(String.valueOf(Application.getInstance().DELAY_AFTER_WAIT_FISH()));
        tfAfterWait.setInputVerifier(delayVerifier);

        tfBeforeChangeRod.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_CHANGE_ROD()));
        tfBeforeChangeRod.setInputVerifier(delayVerifier);

        tfAfterChangeRod.setText(String.valueOf(Application.getInstance().DELAY_AFTER_CHANGE_ROD()));
        tfAfterChangeRod.setInputVerifier(delayVerifier);

        tfBeforeCaptcha.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_KAPCHA()));
        tfBeforeCaptcha.setInputVerifier(delayVerifier);

        tfAfterCaptcha.setText(String.valueOf(Application.getInstance().DELAY_AFTER_KAPCHA()));
        tfAfterCaptcha.setInputVerifier(delayVerifier);

        tfBeforeFilterLoot.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_FILTER_LOOT()));
        tfBeforeFilterLoot.setInputVerifier(delayVerifier);

        tfAfterFilterLoot.setText(String.valueOf(Application.getInstance().DELAY_AFTER_FILTER_LOOT()));
        tfAfterFilterLoot.setInputVerifier(delayVerifier);

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

        tfFirstSlotKey.setInputVerifier(slotKeyVerifier);
        tfFirstSlotDelay.setInputVerifier(delayPeriodVerifier);
        tfFirstSlotPeriod.setInputVerifier(delayPeriodVerifier);

        tfSecondSlotKey.setInputVerifier(slotKeyVerifier);
        tfSecondSlotDelay.setInputVerifier(delayPeriodVerifier);
        tfSecondSlotPeriod.setInputVerifier(delayPeriodVerifier);

        tfThirdSlotKey.setInputVerifier(slotKeyVerifier);
        tfThirdSlotDelay.setInputVerifier(delayPeriodVerifier);
        tfThirdSlotPeriod.setInputVerifier(delayPeriodVerifier);

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

    public SettingsView() {
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonSave);

        init();

        buttonSave.setIcon(new ImageIcon(UI.IMG_SAVE));
        buttonSave.addActionListener(new SaveController(this));

        buttonCancel.setIcon(new ImageIcon(UI.IMG_CLOSE));
        buttonCancel.addActionListener(new CancelController(this));

        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public JComboBox<String> getCbPort() {
        return cbPort;
    }

    public JLabel getLoot() {
        return Loot;
    }

    public JCheckBox getCbFish() {
        return cbFish;
    }

    public JCheckBox getCbEvent() {
        return cbEvent;
    }

    public JCheckBox getCbRock() {
        return cbRock;
    }

    public JCheckBox getCbKey() {
        return cbKey;
    }

    public JCheckBox getCbUnknown() {
        return cbUnknown;
    }

    public JCheckBox getCbBeer() {
        return cbBeer;
    }

    public JCheckBox getCbMinigame() {
        return cbMinigame;
    }

    public JCheckBox getCbFirstSlotActive() {
        return cbFirstSlotActive;
    }

    public JTextField getTfFirstSlotKey() {
        return tfFirstSlotKey;
    }

    public JTextField getTfFirstSlotDelay() {
        return tfFirstSlotDelay;
    }

    public JTextField getTfFirstSlotPeriod() {
        return tfFirstSlotPeriod;
    }

    public JCheckBox getCbSecondSlotActive() {
        return cbSecondSlotActive;
    }

    public JTextField getTfSecondSlotKey() {
        return tfSecondSlotKey;
    }

    public JTextField getTfSecondSlotDelay() {
        return tfSecondSlotDelay;
    }

    public JTextField getTfSecondSlotPeriod() {
        return tfSecondSlotPeriod;
    }

    public JCheckBox getCbThirdSlotActive() {
        return cbThirdSlotActive;
    }

    public JTextField getTfThirdSlotKey() {
        return tfThirdSlotKey;
    }

    public JTextField getTfThirdSlotDelay() {
        return tfThirdSlotDelay;
    }

    public JTextField getTfThirdSlotPeriod() {
        return tfThirdSlotPeriod;
    }

    public JTextField getTfRodCount() {
        return tfRodCount;
    }

    public JTextField getTfRodChange() {
        return tfRodChange;
    }

    public JCheckBox getCbTelegram() {
        return cbTelegram;
    }

    public JTextField getTfTelegramKey() {
        return tfTelegramKey;
    }

    public JPanel getNotificationContent() {
        return notificationContent;
    }

    public JTextField getTfBeforeStart() {
        return tfBeforeStart;
    }

    public JTextField getTfAfterStart() {
        return tfAfterStart;
    }

    public JPanel getPersonalMessage() {
        return personalMessage;
    }

    public JTextField getTfBeforeWait() {
        return tfBeforeWait;
    }

    public JTextField getTfAfterWait() {
        return tfAfterWait;
    }

    public JTextField getTfBeforeChangeRod() {
        return tfBeforeChangeRod;
    }

    public JTextField getTfAfterChangeRod() {
        return tfAfterChangeRod;
    }

    public JTextField getTfBeforeCaptcha() {
        return tfBeforeCaptcha;
    }

    public JTextField getTfAfterCaptcha() {
        return tfAfterCaptcha;
    }

    public JTextField getTfBeforeFilterLoot() {
        return tfBeforeFilterLoot;
    }

    public JTextField getTfAfterFilterLoot() {
        return tfAfterFilterLoot;
    }

    public JRadioButton getRbAutoFish() {
        return rbAutoFish;
    }

    public JRadioButton getRbExitGame() {
        return rbExitGame;
    }

    public JRadioButton getRbNothing() {
        return rbNothing;
    }

    public static void main(String[] args) {

        UIManager.getDefaults().addResourceBundle("locale");

        SettingsView dialog = new SettingsView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}