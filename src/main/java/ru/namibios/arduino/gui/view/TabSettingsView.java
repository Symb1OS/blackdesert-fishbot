package ru.namibios.arduino.gui.view;

import com.fazecast.jSerialComm.SerialPort;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.InputMode;
import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.config.converter.UiThemeConverter;
import ru.namibios.arduino.gui.CustomVerifier;
import ru.namibios.arduino.gui.Launcher;
import ru.namibios.arduino.gui.UI;
import ru.namibios.arduino.gui.controller.CancelController;
import ru.namibios.arduino.gui.controller.ResetController;
import ru.namibios.arduino.gui.controller.SaveController;
import ru.namibios.arduino.utils.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class TabSettingsView extends JDialog {
    private JPanel content;
    private JTabbedPane tabPane;
    private JPanel generalTab;
    private JPanel addTab;
    private JPanel coordTab;
    private JPanel debugTab;
    private JPanel loot;
    private JLabel Loot;
    private JPanel lootContent;
    private JCheckBox cbFish;
    private JCheckBox cbEvent;
    private JCheckBox cbRock;
    private JCheckBox cbKey;
    private JCheckBox cbUnknown;
    private JCheckBox cbConfirm;
    private JPanel portContent;
    private JComboBox<String> cbPort;
    private JPanel task;
    private JPanel taskContent;
    private JCheckBox cbBeer;
    private JTextField beerKey;
    private JTextField beerPeriod;
    private JCheckBox cbRepeatWork;
    private JPanel slots;
    private JLabel lSlots;
    private JPanel slotContent;
    private JCheckBox cbFirstSlotActive;
    private JTextField tfFirstSlotKey;
    private JTextField tfFirstSlotDelay;
    private JTextField tfFirstSlotPeriod;
    private JCheckBox cbSecondSlotActive;
    private JTextField tfSecondSlotKey;
    private JTextField tfSecondSlotDelay;
    private JTextField tfSecondSlotPeriod;
    private JCheckBox cbThirdSlotActive;
    private JTextField tfThirdSlotKey;
    private JTextField tfThirdSlotDelay;
    private JTextField tfThirdSlotPeriod;
    private JPanel rod;
    private JLabel lRod;
    private JTextField tfRodCount;
    private JTextField tfRodChange;
    private JPanel notification;
    private JPanel notificationContent;
    private JCheckBox cbTelegram;
    private JTextField tfTelegramKey;
    private JPanel personalMessage;
    private JPanel pmContent;
    private JRadioButton rbAutoFish;
    private JRadioButton rbExitGame;
    private JRadioButton rbNothing;
    private JCheckBox cbStopBot;
    private JTextField tfStopBot;
    private JCheckBox cbExitGame;
    private JTextField tfExitGame;
    private JPanel timer;
    private JPanel timerContent;
    private JPanel mode;
    private JPanel modeContent;
    private JComboBox cbMode;
    private JPanel language;
    private JPanel localeContent;
    private JComboBox cbLanguage;
    private JPanel theme;
    private JPanel themeContent;
    private JComboBox cbTheme;
    private JTextField tfLootSlotOneX;
    private JTextField tfLootSlotTwoX;
    private JTextField tfLootSlotThreeX;
    private JCheckBox cbDebugPersonalMessage;
    private JTextField tfInputDelay;
    private JButton btnCancel;
    private JButton btnSave;
    private JCheckBox cbUnknownLoot;
    private JCheckBox cbUnsortLoot;
    private JPanel overflow;
    private JPanel lootTouch;
    private JPanel rodTouch;
    private JPanel state;
    private JPanel input;
    private JPanel parsing;
    private JPanel overflowContent;
    private JTextField tfState;
    private JTextField tfCutState;
    private JTextField tfCapcthaState;
    private JCheckBox cbSkipCalendar;
    private JPanel stateContent;
    private JPanel inputcontent;
    private JPanel parsingContent;
    private JTextField tfParseCoef;
    private JTextField tfCaptchaNoiseIteration;
    private JPanel lootTouchContent;
    private JTextField tfLootSlotOneY;
    private JTextField tfLootSlotTwoY;
    private JTextField tfLootSlotThreeY;
    private JPanel rodTouchContent;
    private JTextField tfRodY;
    private JTextField tfRodDY;
    private JTextField tfRodX;
    private JTextField tfRodDX;
    private JPanel delayTab;
    private JPanel delay;
    private JPanel delayContent;
    private JPanel debug;
    private JPanel debugContent;
    private JCheckBox cbDebugWaitFish;
    private JCheckBox cbDebugStatusCaptcha;
    private JCheckBox cbDebugStatusCut;
    private JCheckBox cbDebugLine;
    private JCheckBox cbDebugCaptcha;
    private JCheckBox cbDebugLootFilter;
    private JPanel delayStart;
    private JPanel delayWaitFish;
    private JPanel delayCut;
    private JPanel delayStatusCut;
    private JPanel delayCaptcha;
    private JPanel delayStatusCaptcha;
    private JPanel delayLooFilter;
    private JPanel coordPane;
    private JPanel space;
    private JPanel spaceContent;
    private JTextField tfSpaceX;
    private JTextField tfSpaceY;
    private JTextField tfSpaceWidth;
    private JTextField tfSpaceHeight;
    private JPanel line;
    private JPanel lineContent;
    private JTextField tfLineX;
    private JTextField tfLineY;
    private JTextField tfLineWidth;
    private JTextField tfLineHeight;
    private JPanel subLine;
    private JPanel subLineContent;
    private JTextField tfSubLineX;
    private JTextField tfSubLineY;
    private JTextField tfSubLineWidth;
    private JTextField tfSubLineHeight;
    private JPanel statusCut;
    private JPanel statusCutContent;
    private JTextField tfStatusCutX;
    private JTextField tfStatusCutY;
    private JTextField tfStatusCutWidth;
    private JTextField tfStatusCutHeight;
    private JPanel captcha;
    private JPanel captchaContent;
    private JTextField tfCaptchaX;
    private JTextField tfCaptchaY;
    private JTextField tfCaptchaWidth;
    private JTextField tfCaptchaHeight;
    private JPanel statusCaptcha;
    private JPanel statusCaptchaContent;
    private JTextField tfStatusCaptchaX;
    private JTextField tfStatusCaptchaY;
    private JTextField tfStatusCaptchaWidth;
    private JTextField tfStatusCaptchaHeight;
    private JPanel lootOne;
    private JPanel lootOneContent;
    private JTextField tfLootOneX;
    private JTextField tfLootOneY;
    private JTextField tfLootOneWidth;
    private JTextField tfLootOneHeight;
    private JPanel lootTwo;
    private JPanel lootTwoContent;
    private JTextField tfLootTwoX;
    private JTextField tfLootTwoY;
    private JTextField tfLootTwoWidth;
    private JTextField tfLootTwoHeight;
    private JPanel lootThree;
    private JPanel lootThreeContent;
    private JTextField tfLootThreeX;
    private JTextField tfLootThreeY;
    private JTextField tfLootThreeWidth;
    private JTextField tfLootThreeHeight;
    private JPanel chat;
    private JPanel chatContent;
    private JTextField tfChatX;
    private JTextField tfChatY;
    private JTextField tfChatWidth;
    private JTextField tfChatHeight;
    private JPanel fullscreen;
    private JPanel fullscreenContent;
    private JTextField tfFullscreenX;
    private JTextField tfFullscreenY;
    private JTextField tfFullscreenWidth;
    private JTextField tfFullscreenHeight;
    private JTextField tfDelayStartBefore;
    private JTextField tfDelayStartAfter;
    private JTextField tfDelayWaitfishBefore;
    private JTextField tfDelayWaitfishAfter;
    private JTextField tfDelayCutBefore;
    private JTextField tfDelayCutAfter;
    private JTextField tfDelayStatusCutBefore;
    private JTextField tfDelayStatusCutAfter;
    private JTextField tfDelayCaptchaBefore;
    private JTextField tfDelayCaptchaAfter;
    private JTextField tfDelayStatusCaptchaBefore;
    private JTextField tfDelayStatusCaptchaAfter;
    private JTextField tfDelayLootFilterBefore;
    private JTextField tfDelayLootFilterAfter;
    private JButton btnReset;
    private JPanel buttons;
    private JLabel lTask;
    private JPanel rodContent;
    private JLabel lTimer;
    private JLabel lBeerKey;
    private JLabel lBeerPeriod;
    private JCheckBox cbDeferredStart;
    private JTextField tfDeferredStart;
    private JPanel gameCrash;
    private JPanel gameCrashContent;
    private JRadioButton rbCrashShutdownPc;
    private JRadioButton rbCrashExitBot;
    private JRadioButton rbCrashStopBot;
    private JCheckBox cbPause;
    private JTextField tfPauseDelayFrom;
    private JTextField tfPauseDelayTo;
    private JTextField tfPauseDowntimeFrom;
    private JTextField tfPauseDowntimeTo;
    private JPanel pauseContent;
    private JPanel lootFour;
    private JPanel lootNine;
    private JPanel lootEight;
    private JPanel lootSeven;
    private JPanel lootSix;
    private JPanel lootFive;
    private JPanel lootFourContent;
    private JTextField tfLootFourX;
    private JTextField tfLootFourHeight;
    private JTextField tfLootFourY;
    private JTextField tfLootFourWidth;
    private JPanel lootNineContent;
    private JTextField tfLootNineX;
    private JTextField tfLootNineHeight;
    private JTextField tfLootNineY;
    private JTextField tfLootNineWidth;
    private JPanel lootEightContent;
    private JTextField tfLootEightX;
    private JTextField tfLootEightHeight;
    private JTextField tfLootEightY;
    private JTextField tfLootEightWidth;
    private JPanel lootSevenContent;
    private JPanel lootSixContent;
    private JPanel lootFiveContent;
    private JTextField tfLootFiveX;
    private JTextField tfLootFiveHeight;
    private JTextField tfLootFiveY;
    private JTextField tfLootFiveWidth;
    private JTextField tfLootSixX;
    private JTextField tfLootSixHeight;
    private JTextField tfLootSixY;
    private JTextField tfLootSixWidth;
    private JTextField tfLootSevenX;
    private JTextField tfLootSevenHeight;
    private JTextField tfLootSevenY;
    private JTextField tfLootSevenWidth;
    private JTextField tfLootSlotFourX;
    private JTextField tfLootSlotFourY;
    private JTextField tfLootSlotFiveX;
    private JTextField tfLootSlotFiveY;
    private JTextField tfLootSlotSixX;
    private JTextField tfLootSlotSixY;
    private JTextField tfLootSlotSevenX;
    private JTextField tfLootSlotSevenY;
    private JTextField tfLootSlotEightX;
    private JTextField tfLootSlotEightY;
    private JTextField tfLootSlotNineX;
    private JTextField tfLootSlotNineY;

    private CustomVerifier numericVerifier;
    private CustomVerifier slotKeyVerifier;
    private CustomVerifier delayPeriodVerifier;
    private CustomVerifier delayVerifier;
    private CustomVerifier rodCountVerifier;

    private static final Logger LOG = Logger.getLogger(TabSettingsView.class);

    private static final String REGEX_DELAY_OR_PERIOD = "[0-9]+[m|s]{0,1}";
    private static final String REGEX_DELAY = "[0-9]+";
    private static final String REGEX_SLOT = "[a-z0-9-=]{1}";
    private static final String REGEX_ROD_COUNT = "[0-9]|[1][0-6]";

    public TabSettingsView(){

        setContentPane(content);

        getRootPane().setDefaultButton(btnSave);

        btnSave.setIcon(new ImageIcon(UI.IMG_SAVE));
        btnSave.addActionListener(new SaveController(this));

        btnCancel.setIcon(new ImageIcon(UI.IMG_CLOSE));
        btnCancel.addActionListener(new CancelController(this));

        btnReset.setIcon(new ImageIcon(UI.IMG_RESET));
        btnReset.addActionListener(new ResetController());

        Image im = new ImageIcon(UI.IMG_SETTINGS).getImage();
        setIconImage(im);

        initGeneral();
        initAdd();
        initCoord();
        initDelay();
        initDebug();

        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setModal(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack();

    }

    private void initDebug() {
        cbDebugWaitFish.setSelected(Application.getInstance().DEBUG_WAITFISH());
        cbDebugStatusCaptcha.setSelected(Application.getInstance().DEBUG_STATUS_CAPTCHA());
        cbDebugStatusCut.setSelected(Application.getInstance().DEBUG_STATUS_CUT());
        cbDebugLine.setSelected(Application.getInstance().DEBUG_SUBLINE());
        cbDebugCaptcha.setSelected(Application.getInstance().DEBUG_CAPTCHA());
        cbDebugPersonalMessage.setSelected(Application.getInstance().DEBUG_PM_MESSAGE());
        cbDebugLootFilter.setSelected(Application.getInstance().DEBUG_FILTER_LOOT());
        cbUnknownLoot.setSelected(Application.getInstance().SAVE_UNKNOWN());
        cbUnsortLoot.setSelected(Application.getInstance().SAVE_UNSORT());
    }

    private void initDelay() {

        tfDelayStartBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_START()));
        tfDelayStartAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_START()));

        tfDelayWaitfishBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_WAIT_FISH()));
        tfDelayWaitfishAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_WAIT_FISH()));

        tfDelayCutBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_CUT_FISH()));
        tfDelayCutAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_CUT_FISH()));

        tfDelayStatusCutBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_STATUS_CUT()));
        tfDelayStatusCutAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_STATUS_CUT()));

        tfDelayCaptchaBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_KAPCHA()));
        tfDelayCaptchaAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_KAPCHA()));

        tfDelayStatusCaptchaBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA()));
        tfDelayStatusCaptchaAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_STATUS_KAPCHA()));

        tfDelayLootFilterBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_FILTER_LOOT()));
        tfDelayLootFilterAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_FILTER_LOOT()));

        tfDelayStartBefore.setInputVerifier(numericVerifier);
        tfDelayStartAfter.setInputVerifier(numericVerifier);
        tfDelayWaitfishBefore.setInputVerifier(numericVerifier);
        tfDelayWaitfishAfter.setInputVerifier(numericVerifier);
        tfDelayCutBefore.setInputVerifier(numericVerifier);
        tfDelayCutAfter.setInputVerifier(numericVerifier);
        tfDelayStatusCutBefore.setInputVerifier(numericVerifier);
        tfDelayStatusCutAfter.setInputVerifier(numericVerifier);
        tfDelayCaptchaBefore.setInputVerifier(numericVerifier);
        tfDelayCaptchaAfter.setInputVerifier(numericVerifier);
        tfDelayStatusCaptchaBefore.setInputVerifier(numericVerifier);
        tfDelayStatusCaptchaAfter.setInputVerifier(numericVerifier);
        tfDelayLootFilterBefore.setInputVerifier(numericVerifier);
        tfDelayLootFilterAfter.setInputVerifier(numericVerifier);

    }

    private void initGeneral() {

        cbMode.addItem(InputMode.ROBOT);
        cbMode.addItem(InputMode.ARDUINO);
        cbMode.setSelectedItem(Application.getInstance().INPUT_MODE());
        cbMode.addItemListener(e -> {
            cbPort.setEnabled(e.getItem() == InputMode.ARDUINO);
        });


        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            try {
                cbPort.addItem(new String(portNames[i].getDescriptivePortName().getBytes(StandardCharsets.ISO_8859_1), "Cp1251"));

            } catch (Exception e) {
                LOG.info(String.format(Message.LOG_FORMAT_ERROR, e));
                LOG.error(ExceptionUtils.getString(e));
            }
        }

        int count = cbPort.getItemCount();
        for (int index = 0; index < count; index++) {
            if (cbPort.getItemAt(index).contains(Application.getInstance().COM_PORT())) {
                cbPort.setSelectedItem(cbPort.getItemAt(index));
            }
        }

        cbPort.setEnabled(cbMode.getSelectedItem() == InputMode.ARDUINO);

        slotKeyVerifier = new CustomVerifier(UIManager.getString("err.format.slotkey"), REGEX_SLOT);
        delayPeriodVerifier = new CustomVerifier(UIManager.getString("err.format.slotdelayperiod"), REGEX_DELAY_OR_PERIOD);
        delayVerifier = new CustomVerifier(UIManager.getString("err.format.delay"), REGEX_DELAY);
        numericVerifier = new CustomVerifier(UIManager.getString("err.format.numeric"), REGEX_DELAY);
        rodCountVerifier = new CustomVerifier(UIManager.getString("err.format.rodcount"), REGEX_ROD_COUNT);

        initLootFilter();
        initTask();
        initSlots();
        initRod();
        initPm();
        initNotification();
        initAutoEnd();
        initCrashGame();

    }

    private void initCrashGame() {

        rbCrashExitBot.setSelected(Application.getInstance().CRASH_EXIT_BOT());
        rbCrashStopBot.setSelected(Application.getInstance().CRASH_STOP_BOT());
        rbCrashShutdownPc.setSelected(Application.getInstance().CRASH_SHUTDOWN_PC());

        ButtonGroup pmGroup = new ButtonGroup();
        pmGroup.add(rbCrashExitBot);
        pmGroup.add(rbCrashStopBot);
        pmGroup.add(rbCrashShutdownPc);
    }

    private void initAdd(){
        Launcher.LOCALES.keySet().forEach(s -> cbLanguage.addItem(s));
        cbLanguage.setSelectedItem(Application.getInstance().LANGUAGE());

        UiThemeConverter.THEMES.keySet().forEach(s -> cbTheme.addItem(s));
        cbTheme.setSelectedItem(UiThemeConverter.unconvert(Application.getInstance().THEME()));

        cbSkipCalendar.setSelected(Application.getInstance().SKIP_CALENDAR());
        tfInputDelay.setText(String.valueOf(Application.getInstance().PRESS_KEY_DELAY()));
        tfParseCoef.setText(String.valueOf(Application.getInstance().COEF_IDENTITY()));
        tfCaptchaNoiseIteration.setText(String.valueOf(Application.getInstance().CNT_KAPCHA()));

        tfState.setText(String.valueOf(Application.getInstance().STATE_OVERFLOW()));
        tfCutState.setText(String.valueOf(Application.getInstance().STATE_CUT_OVERFLOW()));
        tfCapcthaState.setText(String.valueOf(Application.getInstance().STATE_STATUS_CAPTCHA_OVERFLOW()));

        tfLootSlotOneX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[0].getX()));
        tfLootSlotOneY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[0].getY()));

        tfLootSlotTwoX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[1].getX()));
        tfLootSlotTwoY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[1].getY()));

        tfLootSlotThreeX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[2].getX()));
        tfLootSlotThreeY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[2].getY()));

        tfLootSlotFourX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[3].getX()));
        tfLootSlotFourY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[3].getY()));

        tfLootSlotFiveX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[4].getX()));
        tfLootSlotFiveY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[4].getY()));

        tfLootSlotSixX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[5].getX()));
        tfLootSlotSixY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[5].getY()));

        tfLootSlotSevenX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[6].getX()));
        tfLootSlotSevenY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[6].getY()));

        tfLootSlotEightX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[7].getX()));
        tfLootSlotEightY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[7].getY()));

        tfLootSlotNineX.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[8].getX()));
        tfLootSlotNineY.setText(String.valueOf(Application.getInstance().LOOT_TOUCH()[8].getY()));

        tfRodX.setText(String.valueOf(Application.getInstance().ROD_START_X()));
        tfRodY.setText(String.valueOf(Application.getInstance().ROD_START_Y()));
        tfRodDX.setText(String.valueOf(Application.getInstance().ROD_DX()));
        tfRodDY.setText(String.valueOf(Application.getInstance().ROD_DY()));

        tfInputDelay.setInputVerifier(numericVerifier);
        tfCaptchaNoiseIteration.setInputVerifier(numericVerifier);
        tfState.setInputVerifier(numericVerifier);
        tfCutState.setInputVerifier(numericVerifier);
        tfCapcthaState.setInputVerifier(numericVerifier);

        tfLootSlotOneX.setInputVerifier(numericVerifier);
        tfLootSlotOneY.setInputVerifier(numericVerifier);
        tfLootSlotTwoX.setInputVerifier(numericVerifier);
        tfLootSlotTwoY.setInputVerifier(numericVerifier);
        tfLootSlotThreeX.setInputVerifier(numericVerifier);
        tfLootSlotThreeY.setInputVerifier(numericVerifier);
        tfLootSlotFourX.setInputVerifier(numericVerifier);
        tfLootSlotFourY.setInputVerifier(numericVerifier);
        tfLootSlotFiveX.setInputVerifier(numericVerifier);
        tfLootSlotFiveY.setInputVerifier(numericVerifier);
        tfLootSlotSixX.setInputVerifier(numericVerifier);
        tfLootSlotSixY.setInputVerifier(numericVerifier);
        tfLootSlotSevenX.setInputVerifier(numericVerifier);
        tfLootSlotSevenY.setInputVerifier(numericVerifier);
        tfLootSlotEightX.setInputVerifier(numericVerifier);
        tfLootSlotEightY.setInputVerifier(numericVerifier);
        tfLootSlotNineX.setInputVerifier(numericVerifier);
        tfLootSlotNineY.setInputVerifier(numericVerifier);

        tfRodX.setInputVerifier(numericVerifier);
        tfRodY.setInputVerifier(numericVerifier);
        tfRodDX.setInputVerifier(numericVerifier);
        tfRodDY.setInputVerifier(numericVerifier);

    }

    private void initCoord(){

        tfFullscreenX.setText(String.valueOf(Application.getInstance().FULL_SCREEN().x));
        tfFullscreenY.setText(String.valueOf(Application.getInstance().FULL_SCREEN().y));
        tfFullscreenWidth.setText(String.valueOf(Application.getInstance().FULL_SCREEN().width));
        tfFullscreenHeight.setText(String.valueOf(Application.getInstance().FULL_SCREEN().height));

        tfFullscreenX.setInputVerifier(numericVerifier);
        tfFullscreenY.setInputVerifier(numericVerifier);
        tfFullscreenWidth.setInputVerifier(numericVerifier);
        tfFullscreenHeight.setInputVerifier(numericVerifier);

        tfSpaceX.setText(String.valueOf(Application.getInstance().SPACE().x));
        tfSpaceY.setText(String.valueOf(Application.getInstance().SPACE().y));
        tfSpaceWidth.setText(String.valueOf(Application.getInstance().SPACE().width));
        tfSpaceHeight.setText(String.valueOf(Application.getInstance().SPACE().height));

        tfSpaceX.setInputVerifier(numericVerifier);
        tfSpaceY.setInputVerifier(numericVerifier);
        tfSpaceWidth.setInputVerifier(numericVerifier);
        tfSpaceHeight.setInputVerifier(numericVerifier);

        tfLineX.setText(String.valueOf(Application.getInstance().LINE().x));
        tfLineY.setText(String.valueOf(Application.getInstance().LINE().y));
        tfLineWidth.setText(String.valueOf(Application.getInstance().LINE().width));
        tfLineHeight.setText(String.valueOf(Application.getInstance().LINE().height));

        tfLineX.setInputVerifier(numericVerifier);
        tfLineY.setInputVerifier(numericVerifier);
        tfLineWidth.setInputVerifier(numericVerifier);
        tfLineHeight.setInputVerifier(numericVerifier);

        tfSubLineX.setText(String.valueOf(Application.getInstance().SUB_LINE().x));
        tfSubLineY.setText(String.valueOf(Application.getInstance().SUB_LINE().y));
        tfSubLineWidth.setText(String.valueOf(Application.getInstance().SUB_LINE().width));
        tfSubLineHeight.setText(String.valueOf(Application.getInstance().SUB_LINE().height));

        tfSubLineX.setInputVerifier(numericVerifier);
        tfSubLineY.setInputVerifier(numericVerifier);
        tfSubLineWidth.setInputVerifier(numericVerifier);
        tfSubLineHeight.setInputVerifier(numericVerifier);

        tfStatusCutX.setText(String.valueOf(Application.getInstance().STATUS_CUT().x));
        tfStatusCutY.setText(String.valueOf(Application.getInstance().STATUS_CUT().y));
        tfStatusCutWidth.setText(String.valueOf(Application.getInstance().STATUS_CUT().width));
        tfStatusCutHeight.setText(String.valueOf(Application.getInstance().STATUS_CUT().height));

        tfStatusCutX.setInputVerifier(numericVerifier);
        tfStatusCutY.setInputVerifier(numericVerifier);
        tfStatusCutWidth.setInputVerifier(numericVerifier);
        tfStatusCutHeight.setInputVerifier(numericVerifier);

        tfStatusCaptchaX.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().x));
        tfStatusCaptchaY.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().y));
        tfStatusCaptchaWidth.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().width));
        tfStatusCaptchaHeight.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().height));

        tfStatusCaptchaX.setInputVerifier(numericVerifier);
        tfStatusCaptchaY.setInputVerifier(numericVerifier);
        tfStatusCaptchaWidth.setInputVerifier(numericVerifier);
        tfStatusCaptchaHeight.setInputVerifier(numericVerifier);

        tfCaptchaX.setText(String.valueOf(Application.getInstance().CAPTCHA().x));
        tfCaptchaY.setText(String.valueOf(Application.getInstance().CAPTCHA().y));
        tfCaptchaWidth.setText(String.valueOf(Application.getInstance().CAPTCHA().width));
        tfCaptchaHeight.setText(String.valueOf(Application.getInstance().CAPTCHA().height));

        tfCaptchaX.setInputVerifier(numericVerifier);
        tfCaptchaY.setInputVerifier(numericVerifier);
        tfCaptchaWidth.setInputVerifier(numericVerifier);
        tfCaptchaHeight.setInputVerifier(numericVerifier);

        tfStatusCaptchaX.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().x));
        tfStatusCaptchaY.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().y));
        tfStatusCaptchaWidth.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().width));
        tfStatusCaptchaHeight.setText(String.valueOf(Application.getInstance().STATUS_CAPTCHA().height));

        tfStatusCaptchaX.setInputVerifier(numericVerifier);
        tfStatusCaptchaY.setInputVerifier(numericVerifier);
        tfStatusCaptchaWidth.setInputVerifier(numericVerifier);
        tfStatusCaptchaHeight.setInputVerifier(numericVerifier);

        tfLootOneX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[0].x));
        tfLootOneY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[0].y));
        tfLootOneWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[0].width));
        tfLootOneHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[0].height));

        tfLootOneX.setInputVerifier(numericVerifier);
        tfLootOneY.setInputVerifier(numericVerifier);
        tfLootOneWidth.setInputVerifier(numericVerifier);
        tfLootOneHeight.setInputVerifier(numericVerifier);

        tfLootTwoX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[1].x));
        tfLootTwoY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[1].y));
        tfLootTwoWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[1].width));
        tfLootTwoHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[1].height));

        tfLootTwoX.setInputVerifier(numericVerifier);
        tfLootTwoY.setInputVerifier(numericVerifier);
        tfLootTwoWidth.setInputVerifier(numericVerifier);
        tfLootTwoHeight.setInputVerifier(numericVerifier);

        tfLootThreeX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[2].x));
        tfLootThreeY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[2].y));
        tfLootThreeWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[2].width));
        tfLootThreeHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[2].height));

        tfLootThreeX.setInputVerifier(numericVerifier);
        tfLootThreeY.setInputVerifier(numericVerifier);
        tfLootThreeWidth.setInputVerifier(numericVerifier);
        tfLootThreeHeight.setInputVerifier(numericVerifier);

        tfLootFourX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[3].x));
        tfLootFourY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[3].y));
        tfLootFourWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[3].width));
        tfLootFourHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[3].height));

        tfLootFourX.setInputVerifier(numericVerifier);
        tfLootFourY.setInputVerifier(numericVerifier);
        tfLootFourWidth.setInputVerifier(numericVerifier);
        tfLootFourHeight.setInputVerifier(numericVerifier);

        tfLootFiveX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[4].x));
        tfLootFiveY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[4].y));
        tfLootFiveWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[4].width));
        tfLootFiveHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[4].height));

        tfLootFiveX.setInputVerifier(numericVerifier);
        tfLootFiveY.setInputVerifier(numericVerifier);
        tfLootFiveWidth.setInputVerifier(numericVerifier);
        tfLootFiveHeight.setInputVerifier(numericVerifier);

        tfLootSixX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[5].x));
        tfLootSixY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[5].y));
        tfLootSixWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[5].width));
        tfLootSixHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[5].height));

        tfLootSixX.setInputVerifier(numericVerifier);
        tfLootSixY.setInputVerifier(numericVerifier);
        tfLootSixWidth.setInputVerifier(numericVerifier);
        tfLootSixHeight.setInputVerifier(numericVerifier);

        tfLootSevenX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[6].x));
        tfLootSevenY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[6].y));
        tfLootSevenWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[6].width));
        tfLootSevenHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[6].height));

        tfLootSevenX.setInputVerifier(numericVerifier);
        tfLootSevenY.setInputVerifier(numericVerifier);
        tfLootSevenWidth.setInputVerifier(numericVerifier);
        tfLootSevenHeight.setInputVerifier(numericVerifier);

        tfLootEightX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[7].x));
        tfLootEightY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[7].y));
        tfLootEightWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[7].width));
        tfLootEightHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[7].height));

        tfLootEightX.setInputVerifier(numericVerifier);
        tfLootEightY.setInputVerifier(numericVerifier);
        tfLootEightWidth.setInputVerifier(numericVerifier);
        tfLootEightHeight.setInputVerifier(numericVerifier);

        tfLootNineX.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[8].x));
        tfLootNineY.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[8].y));
        tfLootNineWidth.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[8].width));
        tfLootNineHeight.setText(String.valueOf(Application.getInstance().LOOT_SLOT_LIST()[8].height));

        tfLootNineX.setInputVerifier(numericVerifier);
        tfLootNineY.setInputVerifier(numericVerifier);
        tfLootNineWidth.setInputVerifier(numericVerifier);
        tfLootNineHeight.setInputVerifier(numericVerifier);

        tfChatX.setText(String.valueOf(Application.getInstance().CHAT().x));
        tfChatY.setText(String.valueOf(Application.getInstance().CHAT().y));
        tfChatWidth.setText(String.valueOf(Application.getInstance().CHAT().width));
        tfChatHeight.setText(String.valueOf(Application.getInstance().CHAT().height));

        tfChatX.setInputVerifier(numericVerifier);
        tfChatY.setInputVerifier(numericVerifier);
        tfChatWidth.setInputVerifier(numericVerifier);
        tfChatHeight.setInputVerifier(numericVerifier);

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

        Component[] components = rodContent.getComponents();
        for (Component component : components) {
            component.setEnabled(Application.getUser().isPremium());
        }

        lRod.setIcon(new ImageIcon(UI.SMALL_PREMIUM));
        lRod.setToolTipText(UIManager.getString("preference.premium.tooltip"));

        tfRodChange.setText(String.valueOf(Application.getInstance().TIME_CHANGE_ROD()));
        tfRodChange.setInputVerifier(delayVerifier);

        tfRodCount.setText(String.valueOf(Application.getInstance().COUNT_ROD()));
        tfRodCount.setInputVerifier(rodCountVerifier);

    }

    private void initNotification() {
        cbTelegram.setSelected(Application.getInstance().TELEGRAM());
        tfTelegramKey.setText(Application.getInstance().TELEGRAM_KEY());
    }

    private void initSlots() {

        lSlots.setIcon(new ImageIcon(UI.SMALL_PREMIUM));
        lSlots.setToolTipText(UIManager.getString("preference.premium.tooltip"));

        Component[] components = slotContent.getComponents();
        for (Component component : components) {
            component.setEnabled(Application.getUser().isPremium());
        }

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
        cbConfirm.setSelected(Application.getInstance().CONFIRM());
        cbRock.setSelected(Application.getInstance().ROCK());
        cbUnknown.setSelected(Application.getInstance().TAKE_UNKNOWN());
    }

    private void initTask() {

        lTask.setIcon(new ImageIcon(UI.SMALL_PREMIUM));
        lTask.setToolTipText(UIManager.getString("preference.premium.tooltip"));

        Component[] components = taskContent.getComponents();
        for (Component component : components) {
            component.setEnabled(Application.getUser().isPremium());
        }

        cbBeer.setSelected(Application.getInstance().SLOT_BEER().isActive());
        cbRepeatWork.setSelected(Application.getInstance().BEER_TOUCHS()[2].isActive());
        cbRepeatWork.setToolTipText(UIManager.getString("preference.label.beer_repeat.tooltip"));

        lBeerKey.setToolTipText(UIManager.getString("preference.label.beer_key.tooltip"));
        beerKey.setText(Application.getInstance().SLOT_BEER().getKey());
        beerKey.setInputVerifier(slotKeyVerifier);

        lBeerPeriod.setToolTipText(UIManager.getString("preference.label.beer_period.tooltip"));
        beerPeriod.setText(String.valueOf(Application.getInstance().SLOT_BEER().getPeriod()));
        beerPeriod.setInputVerifier(delayPeriodVerifier);

    }

    private void initAutoEnd() {

        cbPause.setSelected(Application.getInstance().TASK_PAUSE().isActive());
        tfPauseDelayFrom.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getDelay()));
        tfPauseDelayTo.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getRandomDelay()));

        tfPauseDowntimeFrom.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getPauseFrom()));
        tfPauseDowntimeTo.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getPauseTo()));

        lTimer.setIcon(new ImageIcon(UI.SMALL_PREMIUM));
        lTimer.setToolTipText(UIManager.getString("preference.premium.tooltip"));
        Component[] components = timerContent.getComponents();
        for (Component component : components) {
            component.setEnabled(Application.getUser().isPremium());
        }

        cbDeferredStart.setSelected(Application.getInstance().TASK_START().isActive());
        tfDeferredStart.setText(String.valueOf(Application.getInstance().TASK_START().getDelay()));
        tfDeferredStart.setInputVerifier(delayPeriodVerifier);

        cbStopBot.setSelected(Application.getInstance().TASK_STOP().isActive());
        cbStopBot.addItemListener(e -> {
            JCheckBox item = (JCheckBox) e.getItem();
            if (item.isSelected()) {
                cbExitGame.setSelected(false);
            }
        });

        tfStopBot.setText(String.valueOf(Application.getInstance().TASK_STOP().getDelay()));
        tfStopBot.setInputVerifier(delayPeriodVerifier);

        cbExitGame.setSelected(Application.getInstance().TASK_EXIT_GAME().isActive());
        cbExitGame.addItemListener(e -> {
            JCheckBox item = (JCheckBox) e.getItem();
            if (item.isSelected()) {
                cbStopBot.setSelected(false);
            }
        });
        tfExitGame.setText(String.valueOf(Application.getInstance().TASK_EXIT_GAME().getDelay()));
        tfExitGame.setInputVerifier(delayPeriodVerifier);

    }

    public static void main(String[] args) {

//        UIManager.getDefaults().addResourceBundle("locale");

        SwingUtilities.invokeLater(TabSettingsView::new);

    }


    public JTextField getTfLootSlotFourX() {
        return tfLootSlotFourX;
    }

    public JTextField getTfLootSlotFourY() {
        return tfLootSlotFourY;
    }

    public JTextField getTfLootSlotFiveX() {
        return tfLootSlotFiveX;
    }

    public JTextField getTfLootSlotFiveY() {
        return tfLootSlotFiveY;
    }

    public JTextField getTfLootSlotSixX() {
        return tfLootSlotSixX;
    }

    public JTextField getTfLootSlotSixY() {
        return tfLootSlotSixY;
    }

    public JTextField getTfLootSlotSevenX() {
        return tfLootSlotSevenX;
    }

    public JTextField getTfLootSlotSevenY() {
        return tfLootSlotSevenY;
    }

    public JTextField getTfLootSlotEightX() {
        return tfLootSlotEightX;
    }

    public JTextField getTfLootSlotEightY() {
        return tfLootSlotEightY;
    }

    public JTextField getTfLootSlotNineX() {
        return tfLootSlotNineX;
    }

    public JTextField getTfLootSlotNineY() {
        return tfLootSlotNineY;
    }

    public JTextField getTfLootFourX() {
        return tfLootFourX;
    }

    public JTextField getTfLootFourHeight() {
        return tfLootFourHeight;
    }

    public JTextField getTfLootFourY() {
        return tfLootFourY;
    }

    public JTextField getTfLootFourWidth() {
        return tfLootFourWidth;
    }

    public JTextField getTfLootNineX() {
        return tfLootNineX;
    }

    public JTextField getTfLootNineHeight() {
        return tfLootNineHeight;
    }

    public JTextField getTfLootNineY() {
        return tfLootNineY;
    }

    public JTextField getTfLootNineWidth() {
        return tfLootNineWidth;
    }

    public JTextField getTfLootEightX() {
        return tfLootEightX;
    }

    public JTextField getTfLootEightHeight() {
        return tfLootEightHeight;
    }

    public JTextField getTfLootEightY() {
        return tfLootEightY;
    }

    public JTextField getTfLootEightWidth() {
        return tfLootEightWidth;
    }

    public JTextField getTfLootFiveX() {
        return tfLootFiveX;
    }

    public JTextField getTfLootFiveHeight() {
        return tfLootFiveHeight;
    }

    public JTextField getTfLootFiveY() {
        return tfLootFiveY;
    }

    public JTextField getTfLootFiveWidth() {
        return tfLootFiveWidth;
    }

    public JTextField getTfLootSixX() {
        return tfLootSixX;
    }

    public JTextField getTfLootSixHeight() {
        return tfLootSixHeight;
    }

    public JTextField getTfLootSixY() {
        return tfLootSixY;
    }

    public JTextField getTfLootSixWidth() {
        return tfLootSixWidth;
    }

    public JTextField getTfLootSevenX() {
        return tfLootSevenX;
    }

    public JTextField getTfLootSevenHeight() {
        return tfLootSevenHeight;
    }

    public JTextField getTfLootSevenY() {
        return tfLootSevenY;
    }

    public JTextField getTfLootSevenWidth() {
        return tfLootSevenWidth;
    }

    public JCheckBox getCbPause() {
        return cbPause;
    }

    public JTextField getTfPauseDelayFrom() {
        return tfPauseDelayFrom;
    }

    public JTextField getTfPauseDelayTo() {
        return tfPauseDelayTo;
    }

    public JTextField getTfPauseDowntimeFrom() {
        return tfPauseDowntimeFrom;
    }

    public JTextField getTfPauseDowntimeTo() {
        return tfPauseDowntimeTo;
    }

    public JRadioButton getRbCrashShutdownPc() {
        return rbCrashShutdownPc;
    }

    public JRadioButton getRbCrashExitBot() {
        return rbCrashExitBot;
    }

    public JRadioButton getRbCrashStopBot() {
        return rbCrashStopBot;
    }

    public JCheckBox getCbDeferredStart() {
        return cbDeferredStart;
    }

    public JTextField getTfDeferredStart() {
        return tfDeferredStart;
    }

    public JPanel getContent() {
        return content;
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    public JPanel getGeneralTab() {
        return generalTab;
    }

    public JPanel getAddTab() {
        return addTab;
    }

    public JPanel getCoordTab() {
        return coordTab;
    }

    public JPanel getDebugTab() {
        return debugTab;
    }

    public JPanel getLoot() {
        return loot;
    }

    public JPanel getLootContent() {
        return lootContent;
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

    public JCheckBox getCbConfirm() {
        return cbConfirm;
    }

    public JPanel getPortContent() {
        return portContent;
    }

    public JComboBox<String> getCbPort() {
        return cbPort;
    }

    public JPanel getTask() {
        return task;
    }

    public JPanel getTaskContent() {
        return taskContent;
    }

    public JCheckBox getCbBeer() {
        return cbBeer;
    }

    public JTextField getBeerKey() {
        return beerKey;
    }

    public JTextField getBeerPeriod() {
        return beerPeriod;
    }

    public JCheckBox getCbRepeatWork() {
        return cbRepeatWork;
    }

    public JPanel getSlots() {
        return slots;
    }

    public JLabel getlSlots() {
        return lSlots;
    }

    public JPanel getSlotContent() {
        return slotContent;
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

    public JPanel getRod() {
        return rod;
    }

    public JLabel getlRod() {
        return lRod;
    }

    public JTextField getTfRodCount() {
        return tfRodCount;
    }

    public JTextField getTfRodChange() {
        return tfRodChange;
    }

    public JPanel getNotification() {
        return notification;
    }

    public JPanel getNotificationContent() {
        return notificationContent;
    }

    public JCheckBox getCbTelegram() {
        return cbTelegram;
    }

    public JTextField getTfTelegramKey() {
        return tfTelegramKey;
    }

    public JPanel getPersonalMessage() {
        return personalMessage;
    }

    public JPanel getPmContent() {
        return pmContent;
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

    public JCheckBox getCbStopBot() {
        return cbStopBot;
    }

    public JTextField getTfStopBot() {
        return tfStopBot;
    }

    public JCheckBox getCbExitGame() {
        return cbExitGame;
    }

    public JTextField getTfExitGame() {
        return tfExitGame;
    }

    public JPanel getTimer() {
        return timer;
    }

    public JPanel getTimerContent() {
        return timerContent;
    }

    public JPanel getMode() {
        return mode;
    }

    public JPanel getModeContent() {
        return modeContent;
    }

    public JComboBox getCbMode() {
        return cbMode;
    }

    public JPanel getLanguage() {
        return language;
    }

    public JPanel getLocaleContent() {
        return localeContent;
    }

    public JComboBox getCbLanguage() {
        return cbLanguage;
    }

    public JPanel getTheme() {
        return theme;
    }

    public JPanel getThemeContent() {
        return themeContent;
    }

    public JComboBox getCbTheme() {
        return cbTheme;
    }

    public JTextField getTfLootSlotOneX() {
        return tfLootSlotOneX;
    }

    public JTextField getTfLootSlotTwoX() {
        return tfLootSlotTwoX;
    }

    public JTextField getTfLootSlotThreeX() {
        return tfLootSlotThreeX;
    }

    public JCheckBox getCbDebugPersonalMessage() {
        return cbDebugPersonalMessage;
    }

    public JTextField getTfInputDelay() {
        return tfInputDelay;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JCheckBox getCbUnknownLoot() {
        return cbUnknownLoot;
    }

    public JCheckBox getCbUnsortLoot() {
        return cbUnsortLoot;
    }

    public JPanel getOverflow() {
        return overflow;
    }

    public JPanel getLootTouch() {
        return lootTouch;
    }

    public JPanel getRodTouch() {
        return rodTouch;
    }

    public JPanel getState() {
        return state;
    }

    public JPanel getInput() {
        return input;
    }

    public JPanel getParsing() {
        return parsing;
    }

    public JPanel getOverflowContent() {
        return overflowContent;
    }

    public JTextField getTfState() {
        return tfState;
    }

    public JTextField getTfCutState() {
        return tfCutState;
    }

    public JTextField getTfCapcthaState() {
        return tfCapcthaState;
    }

    public JCheckBox getCbSkipCalendar() {
        return cbSkipCalendar;
    }

    public JPanel getStateContent() {
        return stateContent;
    }

    public JPanel getInputcontent() {
        return inputcontent;
    }

    public JPanel getParsingContent() {
        return parsingContent;
    }

    public JTextField getTfParseCoef() {
        return tfParseCoef;
    }

    public JTextField getTfCaptchaNoiseIteration() {
        return tfCaptchaNoiseIteration;
    }

    public JPanel getLootTouchContent() {
        return lootTouchContent;
    }

    public JTextField getTfLootSlotOneY() {
        return tfLootSlotOneY;
    }

    public JTextField getTfLootSlotTwoY() {
        return tfLootSlotTwoY;
    }

    public JTextField getTfLootSlotThreeY() {
        return tfLootSlotThreeY;
    }

    public JPanel getRodTouchContent() {
        return rodTouchContent;
    }

    public JTextField getTfRodY() {
        return tfRodY;
    }

    public JTextField getTfRodDY() {
        return tfRodDY;
    }

    public JTextField getTfRodX() {
        return tfRodX;
    }

    public JTextField getTfRodDX() {
        return tfRodDX;
    }

    public JPanel getDelayTab() {
        return delayTab;
    }

    public JPanel getDelay() {
        return delay;
    }

    public JPanel getDelayContent() {
        return delayContent;
    }

    public JPanel getDebug() {
        return debug;
    }

    public JPanel getDebugContent() {
        return debugContent;
    }

    public JCheckBox getCbDebugWaitFish() {
        return cbDebugWaitFish;
    }

    public JCheckBox getCbDebugStatusCaptcha() {
        return cbDebugStatusCaptcha;
    }

    public JCheckBox getCbDebugStatusCut() {
        return cbDebugStatusCut;
    }

    public JCheckBox getCbDebugLine() {
        return cbDebugLine;
    }

    public JCheckBox getCbDebugCaptcha() {
        return cbDebugCaptcha;
    }

    public JCheckBox getCbDebugLootFilter() {
        return cbDebugLootFilter;
    }

    public JPanel getDelayStart() {
        return delayStart;
    }

    public JPanel getDelayWaitFish() {
        return delayWaitFish;
    }

    public JPanel getDelayCut() {
        return delayCut;
    }

    public JPanel getDelayStatusCut() {
        return delayStatusCut;
    }

    public JPanel getDelayCaptcha() {
        return delayCaptcha;
    }

    public JPanel getDelayStatusCaptcha() {
        return delayStatusCaptcha;
    }

    public JPanel getDelayLooFilter() {
        return delayLooFilter;
    }

    public JPanel getCoordPane() {
        return coordPane;
    }

    public JPanel getSpace() {
        return space;
    }

    public JPanel getSpaceContent() {
        return spaceContent;
    }

    public JTextField getTfSpaceX() {
        return tfSpaceX;
    }

    public JTextField getTfSpaceY() {
        return tfSpaceY;
    }

    public JTextField getTfSpaceWidth() {
        return tfSpaceWidth;
    }

    public JTextField getTfSpaceHeight() {
        return tfSpaceHeight;
    }

    public JPanel getLine() {
        return line;
    }

    public JPanel getLineContent() {
        return lineContent;
    }

    public JTextField getTfLineX() {
        return tfLineX;
    }

    public JTextField getTfLineY() {
        return tfLineY;
    }

    public JTextField getTfLineWidth() {
        return tfLineWidth;
    }

    public JTextField getTfLineHeight() {
        return tfLineHeight;
    }

    public JPanel getSubLine() {
        return subLine;
    }

    public JPanel getSubLineContent() {
        return subLineContent;
    }

    public JTextField getTfSubLineX() {
        return tfSubLineX;
    }

    public JTextField getTfSubLineY() {
        return tfSubLineY;
    }

    public JTextField getTfSubLineWidth() {
        return tfSubLineWidth;
    }

    public JTextField getTfSubLineHeight() {
        return tfSubLineHeight;
    }

    public JPanel getStatusCut() {
        return statusCut;
    }

    public JPanel getStatusCutContent() {
        return statusCutContent;
    }

    public JTextField getTfStatusCutX() {
        return tfStatusCutX;
    }

    public JTextField getTfStatusCutY() {
        return tfStatusCutY;
    }

    public JTextField getTfStatusCutWidth() {
        return tfStatusCutWidth;
    }

    public JTextField getTfStatusCutHeight() {
        return tfStatusCutHeight;
    }

    public JPanel getCaptcha() {
        return captcha;
    }

    public JPanel getCaptchaContent() {
        return captchaContent;
    }

    public JTextField getTfCaptchaX() {
        return tfCaptchaX;
    }

    public JTextField getTfCaptchaY() {
        return tfCaptchaY;
    }

    public JTextField getTfCaptchaWidth() {
        return tfCaptchaWidth;
    }

    public JTextField getTfCaptchaHeight() {
        return tfCaptchaHeight;
    }

    public JPanel getStatusCaptcha() {
        return statusCaptcha;
    }

    public JPanel getStatusCaptchaContent() {
        return statusCaptchaContent;
    }

    public JTextField getTfStatusCaptchaX() {
        return tfStatusCaptchaX;
    }

    public JTextField getTfStatusCaptchaY() {
        return tfStatusCaptchaY;
    }

    public JTextField getTfStatusCaptchaWidth() {
        return tfStatusCaptchaWidth;
    }

    public JTextField getTfStatusCaptchaHeight() {
        return tfStatusCaptchaHeight;
    }

    public JPanel getLootOne() {
        return lootOne;
    }

    public JPanel getLootOneContent() {
        return lootOneContent;
    }

    public JTextField getTfLootOneX() {
        return tfLootOneX;
    }

    public JTextField getTfLootOneY() {
        return tfLootOneY;
    }

    public JTextField getTfLootOneWidth() {
        return tfLootOneWidth;
    }

    public JTextField getTfLootOneHeight() {
        return tfLootOneHeight;
    }

    public JPanel getLootTwo() {
        return lootTwo;
    }

    public JPanel getLootTwoContent() {
        return lootTwoContent;
    }

    public JTextField getTfLootTwoX() {
        return tfLootTwoX;
    }

    public JTextField getTfLootTwoY() {
        return tfLootTwoY;
    }

    public JTextField getTfLootTwoWidth() {
        return tfLootTwoWidth;
    }

    public JTextField getTfLootTwoHeight() {
        return tfLootTwoHeight;
    }

    public JPanel getLootThree() {
        return lootThree;
    }

    public JPanel getLootThreeContent() {
        return lootThreeContent;
    }

    public JTextField getTfLootThreeX() {
        return tfLootThreeX;
    }

    public JTextField getTfLootThreeY() {
        return tfLootThreeY;
    }

    public JTextField getTfLootThreeWidth() {
        return tfLootThreeWidth;
    }

    public JTextField getTfLootThreeHeight() {
        return tfLootThreeHeight;
    }

    public JPanel getChat() {
        return chat;
    }

    public JPanel getChatContent() {
        return chatContent;
    }

    public JTextField getTfChatX() {
        return tfChatX;
    }

    public JTextField getTfChatY() {
        return tfChatY;
    }

    public JTextField getTfChatWidth() {
        return tfChatWidth;
    }

    public JTextField getTfChatHeight() {
        return tfChatHeight;
    }

    public JPanel getFullscreen() {
        return fullscreen;
    }

    public JPanel getFullscreenContent() {
        return fullscreenContent;
    }

    public JTextField getTfFullscreenX() {
        return tfFullscreenX;
    }

    public JTextField getTfFullscreenY() {
        return tfFullscreenY;
    }

    public JTextField getTfFullscreenWidth() {
        return tfFullscreenWidth;
    }

    public JTextField getTfFullscreenHeight() {
        return tfFullscreenHeight;
    }

    public JTextField getTfDelayStartBefore() {
        return tfDelayStartBefore;
    }

    public JTextField getTfDelayStartAfter() {
        return tfDelayStartAfter;
    }

    public JTextField getTfDelayWaitfishBefore() {
        return tfDelayWaitfishBefore;
    }

    public JTextField getTfDelayWaitfishAfter() {
        return tfDelayWaitfishAfter;
    }

    public JTextField getTfDelayCutBefore() {
        return tfDelayCutBefore;
    }

    public JTextField getTfDelayCutAfter() {
        return tfDelayCutAfter;
    }

    public JTextField getTfDelayStatusCutBefore() {
        return tfDelayStatusCutBefore;
    }

    public JTextField getTfDelayStatusCutAfter() {
        return tfDelayStatusCutAfter;
    }

    public JTextField getTfDelayCaptchaBefore() {
        return tfDelayCaptchaBefore;
    }

    public JTextField getTfDelayCaptchaAfter() {
        return tfDelayCaptchaAfter;
    }

    public JTextField getTfDelayStatusCaptchaBefore() {
        return tfDelayStatusCaptchaBefore;
    }

    public JTextField getTfDelayStatusCaptchaAfter() {
        return tfDelayStatusCaptchaAfter;
    }

    public JTextField getTfDelayLootFilterBefore() {
        return tfDelayLootFilterBefore;
    }

    public JTextField getTfDelayLootFilterAfter() {
        return tfDelayLootFilterAfter;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public JPanel getButtons() {
        return buttons;
    }

    public CustomVerifier getSlotKeyVerifier() {
        return slotKeyVerifier;
    }

    public CustomVerifier getDelayPeriodVerifier() {
        return delayPeriodVerifier;
    }

    public CustomVerifier getDelayVerifier() {
        return delayVerifier;
    }

    public CustomVerifier getRodCountVerifier() {
        return rodCountVerifier;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        content = new JPanel();
        content.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        tabPane = new JTabbedPane();
        content.add(tabPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        generalTab = new JPanel();
        generalTab.setLayout(new GridLayoutManager(11, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(ResourceBundle.getBundle("locale").getString("preference.label.tab.general"), generalTab);
        final Spacer spacer1 = new Spacer();
        generalTab.add(spacer1, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        loot = new JPanel();
        loot.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(loot, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Loot = new JLabel();
        this.$$$loadLabelText$$$(Loot, ResourceBundle.getBundle("locale").getString("preference.label.loot"));
        loot.add(Loot, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        lootContent = new JPanel();
        lootContent.setLayout(new GridLayoutManager(1, 7, new Insets(5, 5, 5, 5), -1, -1));
        loot.add(lootContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbFish = new JCheckBox();
        this.$$$loadButtonText$$$(cbFish, ResourceBundle.getBundle("locale").getString("preference.label.fish"));
        lootContent.add(cbFish, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbEvent = new JCheckBox();
        this.$$$loadButtonText$$$(cbEvent, ResourceBundle.getBundle("locale").getString("preference.label.event"));
        lootContent.add(cbEvent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbRock = new JCheckBox();
        this.$$$loadButtonText$$$(cbRock, ResourceBundle.getBundle("locale").getString("preference.label.rock"));
        lootContent.add(cbRock, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbKey = new JCheckBox();
        this.$$$loadButtonText$$$(cbKey, ResourceBundle.getBundle("locale").getString("preference.label.keys"));
        lootContent.add(cbKey, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbUnknown = new JCheckBox();
        this.$$$loadButtonText$$$(cbUnknown, ResourceBundle.getBundle("locale").getString("preference.label.unknow"));
        lootContent.add(cbUnknown, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        lootContent.add(spacer2, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cbConfirm = new JCheckBox();
        this.$$$loadButtonText$$$(cbConfirm, ResourceBundle.getBundle("locale").getString("preference.label.stack"));
        lootContent.add(cbConfirm, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portContent = new JPanel();
        portContent.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(portContent, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("locale").getString("preference.label.port"));
        portContent.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        portContent.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbPort = new JComboBox();
        panel1.add(cbPort, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        task = new JPanel();
        task.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(task, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lTask = new JLabel();
        this.$$$loadLabelText$$$(lTask, ResourceBundle.getBundle("locale").getString("preference.label.autouse"));
        task.add(lTask, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        taskContent = new JPanel();
        taskContent.setLayout(new GridLayoutManager(1, 6, new Insets(5, 5, 5, 5), -1, -1));
        task.add(taskContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        taskContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbBeer = new JCheckBox();
        cbBeer.setHorizontalAlignment(10);
        cbBeer.setHorizontalTextPosition(11);
        this.$$$loadButtonText$$$(cbBeer, ResourceBundle.getBundle("locale").getString("preference.label.beer"));
        taskContent.add(cbBeer, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, -1), null, 0, false));
        lBeerKey = new JLabel();
        this.$$$loadLabelText$$$(lBeerKey, ResourceBundle.getBundle("locale").getString("preference.label.digital"));
        lBeerKey.setToolTipText("Слот в котором располагается пиво/еда для рабочих");
        taskContent.add(lBeerKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beerKey = new JTextField();
        taskContent.add(beerKey, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        lBeerPeriod = new JLabel();
        this.$$$loadLabelText$$$(lBeerPeriod, ResourceBundle.getBundle("locale").getString("preference.label.period"));
        lBeerPeriod.setToolTipText("Как часто кормить рабочих");
        taskContent.add(lBeerPeriod, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beerPeriod = new JTextField();
        taskContent.add(beerPeriod, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbRepeatWork = new JCheckBox();
        this.$$$loadButtonText$$$(cbRepeatWork, ResourceBundle.getBundle("locale").getString("preference.label.repeatslave"));
        cbRepeatWork.setToolTipText("Необходимо ли прожимать кнопку \"Повтор работ\"");
        taskContent.add(cbRepeatWork, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slots = new JPanel();
        slots.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(slots, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lSlots = new JLabel();
        this.$$$loadLabelText$$$(lSlots, ResourceBundle.getBundle("locale").getString("preference.label.slot"));
        slots.add(lSlots, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        slotContent = new JPanel();
        slotContent.setLayout(new GridLayoutManager(3, 7, new Insets(5, 5, 5, 5), -1, -1));
        slots.add(slotContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        slotContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbFirstSlotActive = new JCheckBox();
        cbFirstSlotActive.setText("");
        slotContent.add(cbFirstSlotActive, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        this.$$$loadLabelText$$$(label2, ResourceBundle.getBundle("locale").getString("preference.label.digital"));
        slotContent.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfFirstSlotKey = new JTextField();
        slotContent.add(tfFirstSlotKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        this.$$$loadLabelText$$$(label3, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        label3.setToolTipText("Задержка перед первым вызовом");
        slotContent.add(label3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfFirstSlotDelay = new JTextField();
        slotContent.add(tfFirstSlotDelay, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        this.$$$loadLabelText$$$(label4, ResourceBundle.getBundle("locale").getString("preference.label.period"));
        label4.setToolTipText("С какой периодичностью прожимать клавишу");
        slotContent.add(label4, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfFirstSlotPeriod = new JTextField();
        slotContent.add(tfFirstSlotPeriod, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbSecondSlotActive = new JCheckBox();
        cbSecondSlotActive.setText("");
        slotContent.add(cbSecondSlotActive, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        this.$$$loadLabelText$$$(label5, ResourceBundle.getBundle("locale").getString("preference.label.digital"));
        slotContent.add(label5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfSecondSlotKey = new JTextField();
        slotContent.add(tfSecondSlotKey, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        this.$$$loadLabelText$$$(label6, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        label6.setToolTipText("Задержка перед первым вызовом");
        slotContent.add(label6, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfSecondSlotDelay = new JTextField();
        slotContent.add(tfSecondSlotDelay, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        this.$$$loadLabelText$$$(label7, ResourceBundle.getBundle("locale").getString("preference.label.period"));
        label7.setToolTipText("С какой периодичностью прожимать клавишу");
        slotContent.add(label7, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfSecondSlotPeriod = new JTextField();
        slotContent.add(tfSecondSlotPeriod, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbThirdSlotActive = new JCheckBox();
        cbThirdSlotActive.setText("");
        slotContent.add(cbThirdSlotActive, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        this.$$$loadLabelText$$$(label8, ResourceBundle.getBundle("locale").getString("preference.label.digital"));
        slotContent.add(label8, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfThirdSlotKey = new JTextField();
        slotContent.add(tfThirdSlotKey, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        this.$$$loadLabelText$$$(label9, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        label9.setToolTipText("Задержка перед первым вызовом");
        slotContent.add(label9, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfThirdSlotDelay = new JTextField();
        slotContent.add(tfThirdSlotDelay, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        this.$$$loadLabelText$$$(label10, ResourceBundle.getBundle("locale").getString("preference.label.period"));
        label10.setToolTipText("С какой периодичностью прожимать клавишу");
        slotContent.add(label10, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfThirdSlotPeriod = new JTextField();
        slotContent.add(tfThirdSlotPeriod, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        rod = new JPanel();
        rod.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(rod, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lRod = new JLabel();
        this.$$$loadLabelText$$$(lRod, ResourceBundle.getBundle("locale").getString("preference.label.rod"));
        rod.add(lRod, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        rodContent = new JPanel();
        rodContent.setLayout(new GridLayoutManager(2, 2, new Insets(5, 5, 5, 5), -1, -1));
        rod.add(rodContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rodContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label11 = new JLabel();
        this.$$$loadLabelText$$$(label11, ResourceBundle.getBundle("locale").getString("preference.label.rod.count"));
        rodContent.add(label11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfRodCount = new JTextField();
        rodContent.add(tfRodCount, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label12 = new JLabel();
        this.$$$loadLabelText$$$(label12, ResourceBundle.getBundle("locale").getString("preference.label.waittime"));
        rodContent.add(label12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfRodChange = new JTextField();
        rodContent.add(tfRodChange, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        notification = new JPanel();
        notification.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(notification, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        this.$$$loadLabelText$$$(label13, ResourceBundle.getBundle("locale").getString("preference.label.notification"));
        notification.add(label13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        notificationContent = new JPanel();
        notificationContent.setLayout(new GridLayoutManager(1, 3, new Insets(5, 5, 5, 5), -1, -1));
        notification.add(notificationContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        notificationContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbTelegram = new JCheckBox();
        this.$$$loadButtonText$$$(cbTelegram, ResourceBundle.getBundle("locale").getString("preference.label.telegram"));
        notificationContent.add(cbTelegram, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        this.$$$loadLabelText$$$(label14, ResourceBundle.getBundle("locale").getString("preference.label.key"));
        notificationContent.add(label14, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfTelegramKey = new JTextField();
        notificationContent.add(tfTelegramKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        personalMessage = new JPanel();
        personalMessage.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(personalMessage, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        this.$$$loadLabelText$$$(label15, ResourceBundle.getBundle("locale").getString("preference.label.wrotepm"));
        personalMessage.add(label15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        pmContent = new JPanel();
        pmContent.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        personalMessage.add(pmContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pmContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        rbAutoFish = new JRadioButton();
        this.$$$loadButtonText$$$(rbAutoFish, ResourceBundle.getBundle("locale").getString("preference.label.autofish"));
        pmContent.add(rbAutoFish, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rbExitGame = new JRadioButton();
        this.$$$loadButtonText$$$(rbExitGame, ResourceBundle.getBundle("locale").getString("preference.label.exitgame"));
        pmContent.add(rbExitGame, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rbNothing = new JRadioButton();
        rbNothing.setSelected(false);
        this.$$$loadButtonText$$$(rbNothing, ResourceBundle.getBundle("locale").getString("preference.label.nothing"));
        pmContent.add(rbNothing, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        pmContent.add(spacer3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        timer = new JPanel();
        timer.setLayout(new GridLayoutManager(2, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(timer, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lTimer = new JLabel();
        this.$$$loadLabelText$$$(lTimer, ResourceBundle.getBundle("locale").getString("preference.label.timer"));
        timer.add(lTimer, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        timerContent = new JPanel();
        timerContent.setLayout(new GridLayoutManager(3, 3, new Insets(5, 5, 5, 5), -1, -1));
        timer.add(timerContent, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        timerContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbStopBot = new JCheckBox();
        this.$$$loadButtonText$$$(cbStopBot, ResourceBundle.getBundle("locale").getString("preference.label.autofish"));
        cbStopBot.setToolTipText("Спустя установленное время бот остановит работу. Рыбалка в игре продолжится в афк режиме.");
        timerContent.add(cbStopBot, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        this.$$$loadLabelText$$$(label16, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        timerContent.add(label16, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStopBot = new JTextField();
        timerContent.add(tfStopBot, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbExitGame = new JCheckBox();
        this.$$$loadButtonText$$$(cbExitGame, ResourceBundle.getBundle("locale").getString("preference.label.exitgame"));
        cbExitGame.setToolTipText("Спустя установленное время бот выйдет из игры");
        timerContent.add(cbExitGame, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        this.$$$loadLabelText$$$(label17, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        timerContent.add(label17, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfExitGame = new JTextField();
        timerContent.add(tfExitGame, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbDeferredStart = new JCheckBox();
        this.$$$loadButtonText$$$(cbDeferredStart, ResourceBundle.getBundle("locale").getString("preference.label.auto_start"));
        cbDeferredStart.setToolTipText(ResourceBundle.getBundle("locale").getString("prefernce.label.auto_start.tooltip"));
        timerContent.add(cbDeferredStart, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        this.$$$loadLabelText$$$(label18, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        timerContent.add(label18, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDeferredStart = new JTextField();
        timerContent.add(tfDeferredStart, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pauseContent = new JPanel();
        pauseContent.setLayout(new GridLayoutManager(1, 9, new Insets(5, 5, 5, 5), -1, -1));
        timer.add(pauseContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pauseContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbPause = new JCheckBox();
        cbPause.setText("Пауза");
        cbPause.setToolTipText("<html>\nСпустя установленное время бот будет останавливаться, ждать определённый промежуток времени, после чего продолжать работу.\n<br>\n<table border=\"1\">\n   <tr>\n    <th>Наименование</th> <th>Значение</th> <th>Описание</th>\n   </tr>\n   <tr><td>Задержка</td><td>20m-40m</td><td>Спустя 20-40 минут бот встанет на паузу.</td></tr>\n   <tr><td>Период</td><td>10m-15m</td><td>Бот простоит на паузе 10-15 минут, после чего продолжит работу.</td></tr>\n</html>");
        pauseContent.add(cbPause, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Задержка");
        label19.setToolTipText("");
        pauseContent.add(label19, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfPauseDelayTo = new JTextField();
        pauseContent.add(tfPauseDelayTo, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        tfPauseDelayFrom = new JTextField();
        pauseContent.add(tfPauseDelayFrom, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("-");
        pauseContent.add(label20, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfPauseDowntimeFrom = new JTextField();
        pauseContent.add(tfPauseDowntimeFrom, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        tfPauseDowntimeTo = new JTextField();
        pauseContent.add(tfPauseDowntimeTo, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Период");
        label21.setToolTipText("");
        pauseContent.add(label21, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("-");
        pauseContent.add(label22, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mode = new JPanel();
        mode.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(mode, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        this.$$$loadLabelText$$$(label23, ResourceBundle.getBundle("locale").getString("preference.label.inputmode"));
        mode.add(label23, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        modeContent = new JPanel();
        modeContent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mode.add(modeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        modeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbMode = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        cbMode.setModel(defaultComboBoxModel1);
        modeContent.add(cbMode, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gameCrash = new JPanel();
        gameCrash.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(gameCrash, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        this.$$$loadLabelText$$$(label24, ResourceBundle.getBundle("locale").getString("preference.label.crash_client"));
        gameCrash.add(label24, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        gameCrashContent = new JPanel();
        gameCrashContent.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        gameCrash.add(gameCrashContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gameCrashContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        rbCrashShutdownPc = new JRadioButton();
        this.$$$loadButtonText$$$(rbCrashShutdownPc, ResourceBundle.getBundle("locale").getString("preference.label.shutdown_pc"));
        gameCrashContent.add(rbCrashShutdownPc, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rbCrashExitBot = new JRadioButton();
        this.$$$loadButtonText$$$(rbCrashExitBot, ResourceBundle.getBundle("locale").getString("preference.label.close_bot"));
        gameCrashContent.add(rbCrashExitBot, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        gameCrashContent.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        rbCrashStopBot = new JRadioButton();
        this.$$$loadButtonText$$$(rbCrashStopBot, ResourceBundle.getBundle("locale").getString("preference.label.stop_bot"));
        gameCrashContent.add(rbCrashStopBot, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addTab = new JPanel();
        addTab.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(ResourceBundle.getBundle("locale").getString("preference.label.tab.add"), addTab);
        final Spacer spacer5 = new Spacer();
        addTab.add(spacer5, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        language = new JPanel();
        language.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(language, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label25 = new JLabel();
        this.$$$loadLabelText$$$(label25, ResourceBundle.getBundle("locale").getString("preference.language"));
        language.add(label25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        localeContent = new JPanel();
        localeContent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        language.add(localeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        localeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbLanguage = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        cbLanguage.setModel(defaultComboBoxModel2);
        localeContent.add(cbLanguage, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        theme = new JPanel();
        theme.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(theme, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label26 = new JLabel();
        this.$$$loadLabelText$$$(label26, ResourceBundle.getBundle("locale").getString("preference.label.theme"));
        theme.add(label26, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        themeContent = new JPanel();
        themeContent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        theme.add(themeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        themeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbTheme = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        cbTheme.setModel(defaultComboBoxModel3);
        themeContent.add(cbTheme, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        overflow = new JPanel();
        overflow.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(overflow, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label27 = new JLabel();
        this.$$$loadLabelText$$$(label27, ResourceBundle.getBundle("locale").getString("preference.label.overflow"));
        overflow.add(label27, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        overflowContent = new JPanel();
        overflowContent.setLayout(new GridLayoutManager(3, 2, new Insets(5, 5, 5, 5), -1, -1));
        overflow.add(overflowContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        overflowContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label28 = new JLabel();
        this.$$$loadLabelText$$$(label28, ResourceBundle.getBundle("locale").getString("preference.label.state.general"));
        overflowContent.add(label28, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfState = new JTextField();
        overflowContent.add(tfState, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label29 = new JLabel();
        this.$$$loadLabelText$$$(label29, ResourceBundle.getBundle("locale").getString("preference.label.state.status_cut"));
        label29.setToolTipText("Максимальное количество итераций для определения статуса подсечки.");
        overflowContent.add(label29, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfCutState = new JTextField();
        overflowContent.add(tfCutState, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label30 = new JLabel();
        this.$$$loadLabelText$$$(label30, ResourceBundle.getBundle("locale").getString("preference.label.state.status_captcha"));
        label30.setToolTipText("Максимальное количество итераций для определения статуска капчи. (выполнение может занимать продолжительное время на слабых ПК)");
        overflowContent.add(label30, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCapcthaState = new JTextField();
        overflowContent.add(tfCapcthaState, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootTouch = new JPanel();
        lootTouch.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(lootTouch, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label31 = new JLabel();
        this.$$$loadLabelText$$$(label31, ResourceBundle.getBundle("locale").getString("preference.label.loot"));
        lootTouch.add(label31, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        lootTouchContent = new JPanel();
        lootTouchContent.setLayout(new GridLayoutManager(9, 5, new Insets(5, 5, 5, 5), -1, -1));
        lootTouch.add(lootTouchContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootTouchContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label32 = new JLabel();
        this.$$$loadLabelText$$$(label32, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot1"));
        label32.setToolTipText("Координата 1 ячейки лута.");
        lootTouchContent.add(label32, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfLootSlotOneY = new JTextField();
        lootTouchContent.add(tfLootSlotOneY, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label33 = new JLabel();
        this.$$$loadLabelText$$$(label33, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot2"));
        label33.setToolTipText("Координата 2 ячейки лута.");
        lootTouchContent.add(label33, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfLootSlotTwoY = new JTextField();
        lootTouchContent.add(tfLootSlotTwoY, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label34 = new JLabel();
        this.$$$loadLabelText$$$(label34, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot3"));
        label34.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label34, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotThreeY = new JTextField();
        lootTouchContent.add(tfLootSlotThreeY, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotOneX = new JTextField();
        lootTouchContent.add(tfLootSlotOneX, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotTwoX = new JTextField();
        lootTouchContent.add(tfLootSlotTwoX, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotThreeX = new JTextField();
        lootTouchContent.add(tfLootSlotThreeX, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label35 = new JLabel();
        label35.setText("x:");
        lootTouchContent.add(label35, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label36 = new JLabel();
        label36.setText("x:");
        lootTouchContent.add(label36, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label37 = new JLabel();
        label37.setText("x:");
        lootTouchContent.add(label37, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label38 = new JLabel();
        label38.setText("y:");
        lootTouchContent.add(label38, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label39 = new JLabel();
        label39.setText("y:");
        lootTouchContent.add(label39, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label40 = new JLabel();
        label40.setText("y:");
        lootTouchContent.add(label40, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label41 = new JLabel();
        this.$$$loadLabelText$$$(label41, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot4"));
        label41.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label41, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotFourY = new JTextField();
        lootTouchContent.add(tfLootSlotFourY, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotFourX = new JTextField();
        lootTouchContent.add(tfLootSlotFourX, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label42 = new JLabel();
        label42.setText("x:");
        lootTouchContent.add(label42, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label43 = new JLabel();
        label43.setText("y:");
        lootTouchContent.add(label43, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label44 = new JLabel();
        this.$$$loadLabelText$$$(label44, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot5"));
        label44.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label44, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotFiveY = new JTextField();
        lootTouchContent.add(tfLootSlotFiveY, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotFiveX = new JTextField();
        lootTouchContent.add(tfLootSlotFiveX, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label45 = new JLabel();
        label45.setText("x:");
        lootTouchContent.add(label45, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label46 = new JLabel();
        label46.setText("y:");
        lootTouchContent.add(label46, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label47 = new JLabel();
        this.$$$loadLabelText$$$(label47, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot6"));
        label47.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label47, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotSixY = new JTextField();
        lootTouchContent.add(tfLootSlotSixY, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotSixX = new JTextField();
        lootTouchContent.add(tfLootSlotSixX, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label48 = new JLabel();
        label48.setText("x:");
        lootTouchContent.add(label48, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label49 = new JLabel();
        label49.setText("y:");
        lootTouchContent.add(label49, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label50 = new JLabel();
        this.$$$loadLabelText$$$(label50, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot7"));
        label50.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label50, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotSevenY = new JTextField();
        lootTouchContent.add(tfLootSlotSevenY, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotSevenX = new JTextField();
        lootTouchContent.add(tfLootSlotSevenX, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label51 = new JLabel();
        label51.setText("x:");
        lootTouchContent.add(label51, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label52 = new JLabel();
        label52.setText("y:");
        lootTouchContent.add(label52, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label53 = new JLabel();
        this.$$$loadLabelText$$$(label53, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot8"));
        label53.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label53, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotEightY = new JTextField();
        lootTouchContent.add(tfLootSlotEightY, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotEightX = new JTextField();
        lootTouchContent.add(tfLootSlotEightX, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label54 = new JLabel();
        label54.setText("x:");
        lootTouchContent.add(label54, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label55 = new JLabel();
        label55.setText("y:");
        lootTouchContent.add(label55, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label56 = new JLabel();
        this.$$$loadLabelText$$$(label56, ResourceBundle.getBundle("locale").getString("preference.label.loot.slot9"));
        label56.setToolTipText("Координата 3 ячейки лута.");
        lootTouchContent.add(label56, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotNineY = new JTextField();
        lootTouchContent.add(tfLootSlotNineY, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotNineX = new JTextField();
        lootTouchContent.add(tfLootSlotNineX, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label57 = new JLabel();
        label57.setText("x:");
        lootTouchContent.add(label57, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label58 = new JLabel();
        label58.setText("y:");
        lootTouchContent.add(label58, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rodTouch = new JPanel();
        rodTouch.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(rodTouch, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label59 = new JLabel();
        this.$$$loadLabelText$$$(label59, ResourceBundle.getBundle("locale").getString("preference.label.rod"));
        rodTouch.add(label59, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        rodTouchContent = new JPanel();
        rodTouchContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        rodTouch.add(rodTouchContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rodTouchContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tfRodDY = new JTextField();
        rodTouchContent.add(tfRodDY, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfRodX = new JTextField();
        rodTouchContent.add(tfRodX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label60 = new JLabel();
        label60.setText("x:");
        label60.setToolTipText("Координата верхнего-левого слота инвентаря по осе абсцисс.");
        rodTouchContent.add(label60, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label61 = new JLabel();
        label61.setText("dy:");
        label61.setToolTipText("Смещение по y.");
        rodTouchContent.add(label61, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label62 = new JLabel();
        label62.setText("dx:");
        label62.setToolTipText("Смещение по x.");
        rodTouchContent.add(label62, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label63 = new JLabel();
        label63.setText("y:");
        label63.setToolTipText("Координата верхнего-левого слота инвентаря по осе ординат.");
        rodTouchContent.add(label63, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfRodDX = new JTextField();
        rodTouchContent.add(tfRodDX, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfRodY = new JTextField();
        rodTouchContent.add(tfRodY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        state = new JPanel();
        state.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(state, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label64 = new JLabel();
        this.$$$loadLabelText$$$(label64, ResourceBundle.getBundle("locale").getString("preference.label.states"));
        state.add(label64, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        stateContent = new JPanel();
        stateContent.setLayout(new GridLayoutManager(1, 2, new Insets(5, 5, 5, 5), -1, -1));
        state.add(stateContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stateContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        cbSkipCalendar = new JCheckBox();
        this.$$$loadButtonText$$$(cbSkipCalendar, ResourceBundle.getBundle("locale").getString("preference.label.skip_calendar"));
        stateContent.add(cbSkipCalendar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        stateContent.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        input = new JPanel();
        input.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(input, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label65 = new JLabel();
        this.$$$loadLabelText$$$(label65, ResourceBundle.getBundle("locale").getString("preference.label.input"));
        input.add(label65, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        inputcontent = new JPanel();
        inputcontent.setLayout(new GridLayoutManager(1, 2, new Insets(5, 5, 5, 5), -1, -1));
        input.add(inputcontent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        inputcontent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label66 = new JLabel();
        this.$$$loadLabelText$$$(label66, ResourceBundle.getBundle("locale").getString("preference.label.delay"));
        label66.setToolTipText("Если бот не успевает вводить длинные капчи можно немного уменьшить значение, чтобы ускорить ввод. Желательно не ставить очень низкие(нечеловеческие) значения");
        inputcontent.add(label66, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfInputDelay = new JTextField();
        inputcontent.add(tfInputDelay, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        parsing = new JPanel();
        parsing.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addTab.add(parsing, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label67 = new JLabel();
        this.$$$loadLabelText$$$(label67, ResourceBundle.getBundle("locale").getString("preference.label.parsing"));
        parsing.add(label67, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        parsingContent = new JPanel();
        parsingContent.setLayout(new GridLayoutManager(2, 2, new Insets(5, 5, 5, 5), -1, -1));
        parsing.add(parsingContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        parsingContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label68 = new JLabel();
        this.$$$loadLabelText$$$(label68, ResourceBundle.getBundle("locale").getString("preference.label.parse.coef"));
        label68.setToolTipText("<html>\nМинимальный коэффициент при котором картинка будет опознана.<br>\nЕсли какой-то лут не распознается, можно немного уменьшить значение, но не стоит уменьшать слишком сильно.\n</html>");
        parsingContent.add(label68, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfParseCoef = new JTextField();
        parsingContent.add(tfParseCoef, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label69 = new JLabel();
        this.$$$loadLabelText$$$(label69, ResourceBundle.getBundle("locale").getString("preference.label.noise.iteration"));
        label69.setToolTipText("<html> Количество итераций для очистки капчи от шума. Чем выше параметр, тем позже бот начнет ввод капчи. <br> Если слабый процессор и бот полностью не успевает вводить капчу можно немного понизить данный параметр. </html>");
        parsingContent.add(label69, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaNoiseIteration = new JTextField();
        parsingContent.add(tfCaptchaNoiseIteration, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        coordTab = new JPanel();
        coordTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(ResourceBundle.getBundle("locale").getString("preference.label.tab.coord"), coordTab);
        final JScrollPane scrollPane1 = new JScrollPane();
        coordTab.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(561, 1112), null, 0, false));
        coordPane = new JPanel();
        coordPane.setLayout(new GridLayoutManager(17, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(coordPane);
        space = new JPanel();
        space.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(space, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label70 = new JLabel();
        this.$$$loadLabelText$$$(label70, ResourceBundle.getBundle("locale").getString("preference.label.space"));
        space.add(label70, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        spaceContent = new JPanel();
        spaceContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        space.add(spaceContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        spaceContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label71 = new JLabel();
        label71.setText("x:");
        spaceContent.add(label71, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSpaceX = new JTextField();
        spaceContent.add(tfSpaceX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label72 = new JLabel();
        this.$$$loadLabelText$$$(label72, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        spaceContent.add(label72, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSpaceHeight = new JTextField();
        spaceContent.add(tfSpaceHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label73 = new JLabel();
        this.$$$loadLabelText$$$(label73, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        spaceContent.add(label73, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label74 = new JLabel();
        label74.setText("y:");
        spaceContent.add(label74, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSpaceY = new JTextField();
        spaceContent.add(tfSpaceY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfSpaceWidth = new JTextField();
        spaceContent.add(tfSpaceWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        line = new JPanel();
        line.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(line, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label75 = new JLabel();
        this.$$$loadLabelText$$$(label75, ResourceBundle.getBundle("locale").getString("preference.label.line"));
        line.add(label75, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lineContent = new JPanel();
        lineContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        line.add(lineContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lineContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label76 = new JLabel();
        label76.setText("x:");
        lineContent.add(label76, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLineX = new JTextField();
        lineContent.add(tfLineX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label77 = new JLabel();
        this.$$$loadLabelText$$$(label77, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lineContent.add(label77, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLineHeight = new JTextField();
        lineContent.add(tfLineHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label78 = new JLabel();
        this.$$$loadLabelText$$$(label78, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lineContent.add(label78, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLineY = new JTextField();
        lineContent.add(tfLineY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLineWidth = new JTextField();
        lineContent.add(tfLineWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label79 = new JLabel();
        label79.setText("y:");
        lineContent.add(label79, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        subLine = new JPanel();
        subLine.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(subLine, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label80 = new JLabel();
        this.$$$loadLabelText$$$(label80, ResourceBundle.getBundle("locale").getString("preference.label.subline"));
        subLine.add(label80, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        subLineContent = new JPanel();
        subLineContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        subLine.add(subLineContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        subLineContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label81 = new JLabel();
        label81.setText("x:");
        subLineContent.add(label81, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSubLineX = new JTextField();
        subLineContent.add(tfSubLineX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label82 = new JLabel();
        this.$$$loadLabelText$$$(label82, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        subLineContent.add(label82, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSubLineHeight = new JTextField();
        subLineContent.add(tfSubLineHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label83 = new JLabel();
        this.$$$loadLabelText$$$(label83, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        subLineContent.add(label83, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSubLineY = new JTextField();
        subLineContent.add(tfSubLineY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfSubLineWidth = new JTextField();
        subLineContent.add(tfSubLineWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label84 = new JLabel();
        label84.setText("y:");
        subLineContent.add(label84, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statusCut = new JPanel();
        statusCut.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(statusCut, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label85 = new JLabel();
        this.$$$loadLabelText$$$(label85, ResourceBundle.getBundle("locale").getString("preference.label.state.status_cut"));
        statusCut.add(label85, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        statusCutContent = new JPanel();
        statusCutContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        statusCut.add(statusCutContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusCutContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label86 = new JLabel();
        label86.setText("x:");
        statusCutContent.add(label86, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCutX = new JTextField();
        statusCutContent.add(tfStatusCutX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label87 = new JLabel();
        this.$$$loadLabelText$$$(label87, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        statusCutContent.add(label87, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCutHeight = new JTextField();
        statusCutContent.add(tfStatusCutHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label88 = new JLabel();
        this.$$$loadLabelText$$$(label88, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        statusCutContent.add(label88, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label89 = new JLabel();
        label89.setText("y:");
        statusCutContent.add(label89, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCutWidth = new JTextField();
        statusCutContent.add(tfStatusCutWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfStatusCutY = new JTextField();
        statusCutContent.add(tfStatusCutY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        captcha = new JPanel();
        captcha.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(captcha, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label90 = new JLabel();
        this.$$$loadLabelText$$$(label90, ResourceBundle.getBundle("locale").getString("preference.label.captcha"));
        captcha.add(label90, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        captchaContent = new JPanel();
        captchaContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        captcha.add(captchaContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        captchaContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label91 = new JLabel();
        label91.setText("x:");
        captchaContent.add(label91, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaX = new JTextField();
        captchaContent.add(tfCaptchaX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label92 = new JLabel();
        this.$$$loadLabelText$$$(label92, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        captchaContent.add(label92, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaHeight = new JTextField();
        captchaContent.add(tfCaptchaHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label93 = new JLabel();
        label93.setText("Длина");
        captchaContent.add(label93, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label94 = new JLabel();
        label94.setText("y:");
        captchaContent.add(label94, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaWidth = new JTextField();
        captchaContent.add(tfCaptchaWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfCaptchaY = new JTextField();
        captchaContent.add(tfCaptchaY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        statusCaptcha = new JPanel();
        statusCaptcha.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(statusCaptcha, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label95 = new JLabel();
        this.$$$loadLabelText$$$(label95, ResourceBundle.getBundle("locale").getString("preference.label.state.status_captcha"));
        statusCaptcha.add(label95, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        statusCaptchaContent = new JPanel();
        statusCaptchaContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        statusCaptcha.add(statusCaptchaContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusCaptchaContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label96 = new JLabel();
        label96.setText("x:");
        statusCaptchaContent.add(label96, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCaptchaX = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label97 = new JLabel();
        this.$$$loadLabelText$$$(label97, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        statusCaptchaContent.add(label97, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCaptchaHeight = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label98 = new JLabel();
        this.$$$loadLabelText$$$(label98, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        statusCaptchaContent.add(label98, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCaptchaWidth = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfStatusCaptchaY = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label99 = new JLabel();
        label99.setText("y:");
        statusCaptchaContent.add(label99, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lootOne = new JPanel();
        lootOne.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootOne, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label100 = new JLabel();
        this.$$$loadLabelText$$$(label100, ResourceBundle.getBundle("locale").getString("preference.label.loot1"));
        lootOne.add(label100, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootOneContent = new JPanel();
        lootOneContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootOne.add(lootOneContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootOneContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label101 = new JLabel();
        label101.setText("x:");
        lootOneContent.add(label101, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootOneX = new JTextField();
        lootOneContent.add(tfLootOneX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label102 = new JLabel();
        this.$$$loadLabelText$$$(label102, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootOneContent.add(label102, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootOneHeight = new JTextField();
        lootOneContent.add(tfLootOneHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label103 = new JLabel();
        this.$$$loadLabelText$$$(label103, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootOneContent.add(label103, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label104 = new JLabel();
        label104.setText("y:");
        lootOneContent.add(label104, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootOneWidth = new JTextField();
        lootOneContent.add(tfLootOneWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootOneY = new JTextField();
        lootOneContent.add(tfLootOneY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootTwo = new JPanel();
        lootTwo.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootTwo, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label105 = new JLabel();
        this.$$$loadLabelText$$$(label105, ResourceBundle.getBundle("locale").getString("preference.label.loot2"));
        lootTwo.add(label105, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootTwoContent = new JPanel();
        lootTwoContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootTwo.add(lootTwoContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootTwoContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label106 = new JLabel();
        label106.setText("x:");
        lootTwoContent.add(label106, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootTwoX = new JTextField();
        lootTwoContent.add(tfLootTwoX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label107 = new JLabel();
        this.$$$loadLabelText$$$(label107, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootTwoContent.add(label107, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootTwoHeight = new JTextField();
        lootTwoContent.add(tfLootTwoHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label108 = new JLabel();
        this.$$$loadLabelText$$$(label108, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootTwoContent.add(label108, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label109 = new JLabel();
        label109.setText("y:");
        lootTwoContent.add(label109, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootTwoWidth = new JTextField();
        lootTwoContent.add(tfLootTwoWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootTwoY = new JTextField();
        lootTwoContent.add(tfLootTwoY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootThree = new JPanel();
        lootThree.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootThree, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label110 = new JLabel();
        this.$$$loadLabelText$$$(label110, ResourceBundle.getBundle("locale").getString("preference.label.loot3"));
        lootThree.add(label110, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootThreeContent = new JPanel();
        lootThreeContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootThree.add(lootThreeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootThreeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label111 = new JLabel();
        label111.setText("x:");
        lootThreeContent.add(label111, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootThreeX = new JTextField();
        lootThreeContent.add(tfLootThreeX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label112 = new JLabel();
        this.$$$loadLabelText$$$(label112, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootThreeContent.add(label112, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootThreeHeight = new JTextField();
        lootThreeContent.add(tfLootThreeHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label113 = new JLabel();
        this.$$$loadLabelText$$$(label113, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootThreeContent.add(label113, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label114 = new JLabel();
        label114.setText("y:");
        lootThreeContent.add(label114, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootThreeY = new JTextField();
        lootThreeContent.add(tfLootThreeY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootThreeWidth = new JTextField();
        lootThreeContent.add(tfLootThreeWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        chat = new JPanel();
        chat.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(chat, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label115 = new JLabel();
        this.$$$loadLabelText$$$(label115, ResourceBundle.getBundle("locale").getString("preference.label.chat"));
        chat.add(label115, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        chatContent = new JPanel();
        chatContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        chat.add(chatContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chatContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label116 = new JLabel();
        label116.setText("x:");
        chatContent.add(label116, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatX = new JTextField();
        chatContent.add(tfChatX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label117 = new JLabel();
        this.$$$loadLabelText$$$(label117, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        chatContent.add(label117, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatHeight = new JTextField();
        chatContent.add(tfChatHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label118 = new JLabel();
        label118.setText("y:");
        chatContent.add(label118, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatY = new JTextField();
        chatContent.add(tfChatY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label119 = new JLabel();
        this.$$$loadLabelText$$$(label119, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        chatContent.add(label119, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatWidth = new JTextField();
        chatContent.add(tfChatWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fullscreen = new JPanel();
        fullscreen.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(fullscreen, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label120 = new JLabel();
        this.$$$loadLabelText$$$(label120, ResourceBundle.getBundle("locale").getString("preference.label.fullscreen"));
        fullscreen.add(label120, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        fullscreenContent = new JPanel();
        fullscreenContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        fullscreen.add(fullscreenContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        fullscreenContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label121 = new JLabel();
        label121.setText("x:");
        fullscreenContent.add(label121, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFullscreenX = new JTextField();
        fullscreenContent.add(tfFullscreenX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label122 = new JLabel();
        this.$$$loadLabelText$$$(label122, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        fullscreenContent.add(label122, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFullscreenHeight = new JTextField();
        fullscreenContent.add(tfFullscreenHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label123 = new JLabel();
        this.$$$loadLabelText$$$(label123, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        fullscreenContent.add(label123, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label124 = new JLabel();
        label124.setText("y:");
        fullscreenContent.add(label124, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFullscreenWidth = new JTextField();
        tfFullscreenWidth.setText("");
        fullscreenContent.add(tfFullscreenWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfFullscreenY = new JTextField();
        fullscreenContent.add(tfFullscreenY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootFour = new JPanel();
        lootFour.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootFour, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label125 = new JLabel();
        this.$$$loadLabelText$$$(label125, ResourceBundle.getBundle("locale").getString("preference.label.loot4"));
        lootFour.add(label125, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootFourContent = new JPanel();
        lootFourContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootFour.add(lootFourContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootFourContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label126 = new JLabel();
        label126.setText("x:");
        lootFourContent.add(label126, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFourX = new JTextField();
        lootFourContent.add(tfLootFourX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label127 = new JLabel();
        this.$$$loadLabelText$$$(label127, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootFourContent.add(label127, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFourHeight = new JTextField();
        lootFourContent.add(tfLootFourHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label128 = new JLabel();
        this.$$$loadLabelText$$$(label128, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootFourContent.add(label128, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label129 = new JLabel();
        label129.setText("y:");
        lootFourContent.add(label129, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFourY = new JTextField();
        lootFourContent.add(tfLootFourY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootFourWidth = new JTextField();
        lootFourContent.add(tfLootFourWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootNine = new JPanel();
        lootNine.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootNine, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label130 = new JLabel();
        this.$$$loadLabelText$$$(label130, ResourceBundle.getBundle("locale").getString("preference.label.loot9"));
        lootNine.add(label130, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootNineContent = new JPanel();
        lootNineContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootNine.add(lootNineContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootNineContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label131 = new JLabel();
        label131.setText("x:");
        lootNineContent.add(label131, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootNineX = new JTextField();
        lootNineContent.add(tfLootNineX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label132 = new JLabel();
        this.$$$loadLabelText$$$(label132, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootNineContent.add(label132, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootNineHeight = new JTextField();
        lootNineContent.add(tfLootNineHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label133 = new JLabel();
        this.$$$loadLabelText$$$(label133, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootNineContent.add(label133, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label134 = new JLabel();
        label134.setText("y:");
        lootNineContent.add(label134, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootNineY = new JTextField();
        lootNineContent.add(tfLootNineY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootNineWidth = new JTextField();
        lootNineContent.add(tfLootNineWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootEight = new JPanel();
        lootEight.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootEight, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label135 = new JLabel();
        this.$$$loadLabelText$$$(label135, ResourceBundle.getBundle("locale").getString("preference.label.loot8"));
        lootEight.add(label135, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootEightContent = new JPanel();
        lootEightContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootEight.add(lootEightContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootEightContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label136 = new JLabel();
        label136.setText("x:");
        lootEightContent.add(label136, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootEightX = new JTextField();
        lootEightContent.add(tfLootEightX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label137 = new JLabel();
        this.$$$loadLabelText$$$(label137, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootEightContent.add(label137, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootEightHeight = new JTextField();
        lootEightContent.add(tfLootEightHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label138 = new JLabel();
        this.$$$loadLabelText$$$(label138, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootEightContent.add(label138, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label139 = new JLabel();
        label139.setText("y:");
        lootEightContent.add(label139, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootEightY = new JTextField();
        lootEightContent.add(tfLootEightY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootEightWidth = new JTextField();
        lootEightContent.add(tfLootEightWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootSeven = new JPanel();
        lootSeven.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootSeven, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label140 = new JLabel();
        this.$$$loadLabelText$$$(label140, ResourceBundle.getBundle("locale").getString("preference.label.loot7"));
        lootSeven.add(label140, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootSevenContent = new JPanel();
        lootSevenContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootSeven.add(lootSevenContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootSevenContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label141 = new JLabel();
        label141.setText("x:");
        lootSevenContent.add(label141, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSevenX = new JTextField();
        lootSevenContent.add(tfLootSevenX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label142 = new JLabel();
        this.$$$loadLabelText$$$(label142, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootSevenContent.add(label142, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSevenHeight = new JTextField();
        lootSevenContent.add(tfLootSevenHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label143 = new JLabel();
        this.$$$loadLabelText$$$(label143, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootSevenContent.add(label143, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label144 = new JLabel();
        label144.setText("y:");
        lootSevenContent.add(label144, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSevenY = new JTextField();
        lootSevenContent.add(tfLootSevenY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSevenWidth = new JTextField();
        lootSevenContent.add(tfLootSevenWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootSix = new JPanel();
        lootSix.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootSix, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label145 = new JLabel();
        this.$$$loadLabelText$$$(label145, ResourceBundle.getBundle("locale").getString("preference.label.loot6"));
        lootSix.add(label145, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootSixContent = new JPanel();
        lootSixContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootSix.add(lootSixContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootSixContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label146 = new JLabel();
        label146.setText("x:");
        lootSixContent.add(label146, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSixX = new JTextField();
        lootSixContent.add(tfLootSixX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label147 = new JLabel();
        this.$$$loadLabelText$$$(label147, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootSixContent.add(label147, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSixHeight = new JTextField();
        lootSixContent.add(tfLootSixHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label148 = new JLabel();
        this.$$$loadLabelText$$$(label148, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootSixContent.add(label148, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label149 = new JLabel();
        label149.setText("y:");
        lootSixContent.add(label149, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSixY = new JTextField();
        lootSixContent.add(tfLootSixY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSixWidth = new JTextField();
        lootSixContent.add(tfLootSixWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootFive = new JPanel();
        lootFive.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootFive, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label150 = new JLabel();
        this.$$$loadLabelText$$$(label150, ResourceBundle.getBundle("locale").getString("preference.label.loot5"));
        lootFive.add(label150, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootFiveContent = new JPanel();
        lootFiveContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootFive.add(lootFiveContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootFiveContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label151 = new JLabel();
        label151.setText("x:");
        lootFiveContent.add(label151, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFiveX = new JTextField();
        lootFiveContent.add(tfLootFiveX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label152 = new JLabel();
        this.$$$loadLabelText$$$(label152, ResourceBundle.getBundle("locale").getString("preference.label.height"));
        lootFiveContent.add(label152, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFiveHeight = new JTextField();
        lootFiveContent.add(tfLootFiveHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label153 = new JLabel();
        this.$$$loadLabelText$$$(label153, ResourceBundle.getBundle("locale").getString("preference.label.width"));
        lootFiveContent.add(label153, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label154 = new JLabel();
        label154.setText("y:");
        lootFiveContent.add(label154, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFiveY = new JTextField();
        lootFiveContent.add(tfLootFiveY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootFiveWidth = new JTextField();
        lootFiveContent.add(tfLootFiveWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        delayTab = new JPanel();
        delayTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(ResourceBundle.getBundle("locale").getString("preference.label.tab.delay"), delayTab);
        delay = new JPanel();
        delay.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        delayTab.add(delay, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayContent = new JPanel();
        delayContent.setLayout(new GridLayoutManager(7, 2, new Insets(5, 5, 5, 5), -1, -1));
        delay.add(delayContent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label155 = new JLabel();
        this.$$$loadLabelText$$$(label155, ResourceBundle.getBundle("locale").getString("preference.label.start"));
        delayContent.add(label155, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label156 = new JLabel();
        this.$$$loadLabelText$$$(label156, ResourceBundle.getBundle("locale").getString("preference.label.waitfish"));
        delayContent.add(label156, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label157 = new JLabel();
        this.$$$loadLabelText$$$(label157, ResourceBundle.getBundle("locale").getString("preference.label.cut"));
        delayContent.add(label157, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label158 = new JLabel();
        this.$$$loadLabelText$$$(label158, ResourceBundle.getBundle("locale").getString("preference.label.state.status_cut"));
        delayContent.add(label158, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label159 = new JLabel();
        this.$$$loadLabelText$$$(label159, ResourceBundle.getBundle("locale").getString("preference.label.captcha"));
        delayContent.add(label159, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label160 = new JLabel();
        this.$$$loadLabelText$$$(label160, ResourceBundle.getBundle("locale").getString("preference.label.state.status_captcha"));
        delayContent.add(label160, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label161 = new JLabel();
        this.$$$loadLabelText$$$(label161, ResourceBundle.getBundle("locale").getString("preference.label.lootfilter"));
        delayContent.add(label161, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        delayStart = new JPanel();
        delayStart.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayStart, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayStart.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label162 = new JLabel();
        this.$$$loadLabelText$$$(label162, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label162.setToolTipText("Задержка перед задачей на старт.");
        delayStart.add(label162, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDelayStartBefore = new JTextField();
        delayStart.add(tfDelayStartBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayStartAfter = new JTextField();
        delayStart.add(tfDelayStartAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label163 = new JLabel();
        this.$$$loadLabelText$$$(label163, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label163.setToolTipText("Задержка после задачи на старт.");
        delayStart.add(label163, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayWaitFish = new JPanel();
        delayWaitFish.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayWaitFish, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayWaitFish.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label164 = new JLabel();
        this.$$$loadLabelText$$$(label164, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label164.setToolTipText("Задержка перед задачей по проверке готовы ли мы подсекать рыбу.");
        delayWaitFish.add(label164, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDelayWaitfishBefore = new JTextField();
        delayWaitFish.add(tfDelayWaitfishBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayWaitfishAfter = new JTextField();
        delayWaitFish.add(tfDelayWaitfishAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label165 = new JLabel();
        this.$$$loadLabelText$$$(label165, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label165.setToolTipText("Задержка после задачи по проверке готовы ли мы подсекать рыбу.");
        delayWaitFish.add(label165, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayCut = new JPanel();
        delayCut.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayCut, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayCut.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        final JLabel label166 = new JLabel();
        this.$$$loadLabelText$$$(label166, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label166.setToolTipText("Задержка перед подсечкой.");
        delayCut.add(label166, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDelayCutBefore = new JTextField();
        delayCut.add(tfDelayCutBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayCutAfter = new JTextField();
        delayCut.add(tfDelayCutAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label167 = new JLabel();
        this.$$$loadLabelText$$$(label167, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label167.setToolTipText("Задержка после подсечки.");
        delayCut.add(label167, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayStatusCut = new JPanel();
        delayStatusCut.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayStatusCut, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayStatusCut.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tfDelayStatusCutBefore = new JTextField();
        delayStatusCut.add(tfDelayStatusCutBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayStatusCutAfter = new JTextField();
        delayStatusCut.add(tfDelayStatusCutAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label168 = new JLabel();
        this.$$$loadLabelText$$$(label168, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label168.setToolTipText("Задержка перед определением статуса подсечки.");
        delayStatusCut.add(label168, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label169 = new JLabel();
        this.$$$loadLabelText$$$(label169, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label169.setToolTipText("Задержка после определения статуса подсечки.");
        delayStatusCut.add(label169, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayCaptcha = new JPanel();
        delayCaptcha.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayCaptcha, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayCaptcha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tfDelayCaptchaBefore = new JTextField();
        delayCaptcha.add(tfDelayCaptchaBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayCaptchaAfter = new JTextField();
        delayCaptcha.add(tfDelayCaptchaAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label170 = new JLabel();
        this.$$$loadLabelText$$$(label170, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label170.setToolTipText("Задержка перед парсингом капчи.");
        delayCaptcha.add(label170, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label171 = new JLabel();
        this.$$$loadLabelText$$$(label171, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label171.setToolTipText("Задержка после парсинга и ввода капчи.");
        delayCaptcha.add(label171, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayStatusCaptcha = new JPanel();
        delayStatusCaptcha.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayStatusCaptcha, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayStatusCaptcha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tfDelayStatusCaptchaBefore = new JTextField();
        delayStatusCaptcha.add(tfDelayStatusCaptchaBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayStatusCaptchaAfter = new JTextField();
        delayStatusCaptcha.add(tfDelayStatusCaptchaAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label172 = new JLabel();
        this.$$$loadLabelText$$$(label172, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label172.setToolTipText("Задержка перед определением статуса капчи.");
        delayStatusCaptcha.add(label172, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label173 = new JLabel();
        this.$$$loadLabelText$$$(label173, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label173.setToolTipText("Задержка после определения статуса капчи.");
        delayStatusCaptcha.add(label173, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayLooFilter = new JPanel();
        delayLooFilter.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayLooFilter, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayLooFilter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        tfDelayLootFilterBefore = new JTextField();
        delayLooFilter.add(tfDelayLootFilterBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayLootFilterAfter = new JTextField();
        delayLooFilter.add(tfDelayLootFilterAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label174 = new JLabel();
        this.$$$loadLabelText$$$(label174, ResourceBundle.getBundle("locale").getString("preference.label.before"));
        label174.setToolTipText("Задержка перед запуском задачи по фильтрации лута.");
        delayLooFilter.add(label174, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label175 = new JLabel();
        this.$$$loadLabelText$$$(label175, ResourceBundle.getBundle("locale").getString("preference.label.after"));
        label175.setToolTipText("Задержка после фильтрации лута.");
        delayLooFilter.add(label175, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        delayTab.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        debugTab = new JPanel();
        debugTab.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(ResourceBundle.getBundle("locale").getString("preference.label.tab.debug"), debugTab);
        debug = new JPanel();
        debug.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        debugTab.add(debug, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        debugContent = new JPanel();
        debugContent.setLayout(new GridLayoutManager(9, 1, new Insets(5, 5, 5, 5), -1, -1));
        debug.add(debugContent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cbDebugWaitFish = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugWaitFish, ResourceBundle.getBundle("locale").getString("preference.label.debug.waitfish"));
        debugContent.add(cbDebugWaitFish, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugStatusCaptcha = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugStatusCaptcha, ResourceBundle.getBundle("locale").getString("preference.label.debug.status_captcha"));
        debugContent.add(cbDebugStatusCaptcha, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugStatusCut = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugStatusCut, ResourceBundle.getBundle("locale").getString("preference.label.debug.status_cut"));
        debugContent.add(cbDebugStatusCut, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugLine = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugLine, ResourceBundle.getBundle("locale").getString("preference.label.debug.line"));
        debugContent.add(cbDebugLine, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugCaptcha = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugCaptcha, ResourceBundle.getBundle("locale").getString("preference.label.debug.captcha"));
        debugContent.add(cbDebugCaptcha, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugLootFilter = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugLootFilter, ResourceBundle.getBundle("locale").getString("preference.label.debug.filter"));
        debugContent.add(cbDebugLootFilter, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugPersonalMessage = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugPersonalMessage, ResourceBundle.getBundle("locale").getString("preference.label.debug.pm"));
        debugContent.add(cbDebugPersonalMessage, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbUnknownLoot = new JCheckBox();
        this.$$$loadButtonText$$$(cbUnknownLoot, ResourceBundle.getBundle("locale").getString("preference.label.debug.unknown_loot"));
        cbUnknownLoot.setToolTipText("Сохранять ли неопознанный лут в папку loot/unknown");
        debugContent.add(cbUnknownLoot, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbUnsortLoot = new JCheckBox();
        this.$$$loadButtonText$$$(cbUnsortLoot, ResourceBundle.getBundle("locale").getString("preference.label.debug.unsort_loot"));
        cbUnsortLoot.setToolTipText("Сохранять ли весь лут в папку, независимо от того опознан он или нет.");
        debugContent.add(cbUnsortLoot, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        debugTab.add(spacer8, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttons = new JPanel();
        buttons.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        content.add(buttons, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnReset = new JButton();
        this.$$$loadButtonText$$$(btnReset, ResourceBundle.getBundle("locale").getString("preference.label.reset"));
        buttons.add(btnReset, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCancel = new JButton();
        this.$$$loadButtonText$$$(btnCancel, ResourceBundle.getBundle("locale").getString("preference.button.cancel"));
        buttons.add(btnCancel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSave = new JButton();
        this.$$$loadButtonText$$$(btnSave, ResourceBundle.getBundle("locale").getString("preference.button.save"));
        buttons.add(btnSave, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        buttons.add(spacer9, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return content;
    }
}
