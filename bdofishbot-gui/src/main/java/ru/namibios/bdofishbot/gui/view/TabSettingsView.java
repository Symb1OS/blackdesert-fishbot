package ru.namibios.bdofishbot.gui.view;

import com.fazecast.jSerialComm.SerialPort;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.InputMode;
import ru.namibios.bdofishbot.cli.config.Message;
import ru.namibios.bdofishbot.cli.config.converter.UiThemeConverter;
import ru.namibios.bdofishbot.gui.CustomVerifier;
import ru.namibios.bdofishbot.gui.TimeMouseConverter;
import ru.namibios.bdofishbot.gui.UI;
import ru.namibios.bdofishbot.gui.components.CoordComponent;
import ru.namibios.bdofishbot.gui.components.DelayComponent;
import ru.namibios.bdofishbot.gui.components.SlotComponent;
import ru.namibios.bdofishbot.gui.components.TouchComponent;
import ru.namibios.bdofishbot.gui.controller.CancelController;
import ru.namibios.bdofishbot.gui.controller.ResetController;
import ru.namibios.bdofishbot.gui.controller.SaveController;
import ru.namibios.bdofishbot.utils.ExceptionUtils;
import ru.namibios.bdofishbot.utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.reflect.Method;
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
    private JCheckBox cbUsefull;
    private JCheckBox cbRed;
    private JCheckBox cbGold;
    private JCheckBox cb;
    private JCheckBox cbUnknown;
    private JCheckBox cbConfirm;
    private JPanel portContent;
    private JComboBox<String> cbPort;
    private JPanel task;
    private JPanel taskContent;
    private JCheckBox cbBeer;
    private JTextField beerKey;
    private JTextField beerPeriodFrom;
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
    private JPanel lootEight;
    private JPanel lootSeven;
    private JPanel lootSix;
    private JPanel lootFive;
    private JPanel lootFourContent;
    private JTextField tfLootFourX;
    private JTextField tfLootFourHeight;
    private JTextField tfLootFourY;
    private JTextField tfLootFourWidth;
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
    private JPanel addPane;
    private JLabel lInputDeay;
    private JLabel lParsingIdentity;
    private JLabel lCleanNoise;
    private JLabel lStatusCut;
    private JLabel lStatusCaptcha;
    private JLabel lRodX;
    private JLabel lRodY;
    private JLabel lRodDx;
    private JLabel lRodDy;
    private JLabel lIdeaDeferredStart;
    private JLabel lIdeaStopBot;
    private JLabel lIdeaExitGame;
    private JLabel lIdeaPause;
    private JLabel lIdeaInputDelay;
    private JLabel lIdeaParseIdentity;
    private JLabel lIdeaCleanNoise;
    private JLabel lIdeaStatusCut;
    private JLabel lIdeaStatusCaptcha;
    private JTextField tfDebufDayKey;
    private JTextField tfDebufNightKey;
    private JCheckBox cbDebufDay;
    private JCheckBox cbDebufNight;
    private JLabel lDebuf;
    private JCheckBox cbDebugDebuf;
    private JLabel lIdeaDebufDay;
    private JLabel lIdeaDebufNight;
    private JTextField beerPeriodTo;
    private JPanel debufContent;
    private JCheckBox cbBlue;
    private JCheckBox cbGreen;
    private JCheckBox cbGray;
    private JLabel lIdeaUnknownLoot;

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
        cbDebugDebuf.setSelected(Application.getInstance().DEBUG_DEBUF());
        cbUnknownLoot.setSelected(Application.getInstance().SAVE_UNKNOWN());
        cbUnsortLoot.setSelected(Application.getInstance().SAVE_UNSORT());
    }

    private void initDelay() {

        DelayComponent start = new DelayComponent(tfDelayStartBefore, tfDelayStartAfter, numericVerifier);
        start.init(Application.getInstance().DELAY_BEFORE_START(), Application.getInstance().DELAY_AFTER_START());

        DelayComponent waitfish = new DelayComponent(tfDelayWaitfishBefore, tfDelayWaitfishAfter, numericVerifier);
        waitfish.init(Application.getInstance().DELAY_BEFORE_WAIT_FISH(), Application.getInstance().DELAY_AFTER_WAIT_FISH());

        DelayComponent cut = new DelayComponent(tfDelayCutBefore, tfDelayCutAfter, numericVerifier);
        cut.init(Application.getInstance().DELAY_BEFORE_CUT_FISH(), Application.getInstance().DELAY_AFTER_CUT_FISH());

        DelayComponent statusCut = new DelayComponent(tfDelayStatusCutBefore, tfDelayStatusCutAfter, numericVerifier);
        statusCut.init(Application.getInstance().DELAY_BEFORE_STATUS_CUT(), Application.getInstance().DELAY_AFTER_STATUS_CUT());

        DelayComponent captcha = new DelayComponent(tfDelayCaptchaBefore, tfDelayCaptchaAfter, numericVerifier);
        captcha.init(Application.getInstance().DELAY_BEFORE_KAPCHA(), Application.getInstance().DELAY_AFTER_KAPCHA());

        DelayComponent statusCaptcha = new DelayComponent(tfDelayStatusCaptchaBefore, tfDelayStatusCaptchaAfter, numericVerifier);
        statusCaptcha.init(Application.getInstance().DELAY_BEFORE_STATUS_KAPCHA(), Application.getInstance().DELAY_AFTER_STATUS_KAPCHA());

        DelayComponent lootFilter = new DelayComponent(tfDelayLootFilterBefore, tfDelayLootFilterAfter, numericVerifier);
        lootFilter.init(Application.getInstance().DELAY_BEFORE_FILTER_LOOT(), Application.getInstance().DELAY_AFTER_FILTER_LOOT());

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

        Component[] components = debufContent.getComponents();
        for (Component component : components) {
            component.setEnabled(Application.getUser().isPremium());
        }

        lDebuf.setIcon(new ImageIcon(UI.SMALL_PREMIUM));
        lDebuf.setToolTipText(UIManager.getString("preference.premium.tooltip"));
        cbDebufDay.setSelected(Application.getInstance().SLOT_DEBUF_DESERT_DAY().isActive());
        tfDebufDayKey.setText(Application.getInstance().SLOT_DEBUF_DESERT_DAY().getKey());
        tfDebufDayKey.setInputVerifier(slotKeyVerifier);

        cbDebufNight.setSelected(Application.getInstance().SLOT_DEBUF_DESERT_NIGHT().isActive());
        tfDebufNightKey.setText(Application.getInstance().SLOT_DEBUF_DESERT_NIGHT().getKey());
        tfDebufNightKey.setInputVerifier(slotKeyVerifier);

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

    public JCheckBox getCbUsefull() {
        return cbUsefull;
    }

    public JCheckBox getCbRed() {
        return cbRed;
    }

    public JCheckBox getCbGold() {
        return cbGold;
    }

    public JCheckBox getCbBlue() {
        return cbBlue;
    }

    public JCheckBox getCbGreen() {
        return cbGreen;
    }

    public JCheckBox getCbGray() {
        return cbGray;
    }

    private void initAdd() {

        Application.LOCALES.keySet().forEach(s -> cbLanguage.addItem(s));
        cbLanguage.setSelectedItem(Application.getInstance().LANGUAGE());

        UiThemeConverter.THEMES.keySet().forEach(s -> cbTheme.addItem(s));
        cbTheme.setSelectedItem(UiThemeConverter.unconvert(Application.getInstance().THEME()));

        lIdeaInputDelay.setIcon(new ImageIcon(UI.IMG_IDEA));
        lIdeaParseIdentity.setIcon(new ImageIcon(UI.IMG_IDEA));
        lIdeaCleanNoise.setIcon(new ImageIcon(UI.IMG_IDEA));
        lIdeaStatusCut.setIcon(new ImageIcon(UI.IMG_IDEA));
        lIdeaStatusCaptcha.setIcon(new ImageIcon(UI.IMG_IDEA));
        lIdeaDebufDay.setIcon(new ImageIcon(UI.IMG_IDEA));
        lIdeaDebufNight.setIcon(new ImageIcon(UI.IMG_IDEA));

        lIdeaDebufDay.setToolTipText(String.format(UIManager.getString("preference.label.debuf.day.tooltip"), ImageUtils.toUrl(UI.IMG_DEBUF_DAY)));
        lIdeaDebufNight.setToolTipText(String.format(UIManager.getString("preference.label.debuf.night.tooltip"), ImageUtils.toUrl(UI.IMG_DEBUF_NIGHT)));

        cbSkipCalendar.setSelected(Application.getInstance().SKIP_CALENDAR());
        tfInputDelay.setText(String.valueOf(Application.getInstance().PRESS_KEY_DELAY()));
        tfParseCoef.setText(String.valueOf(Application.getInstance().COEF_IDENTITY()));
        tfCaptchaNoiseIteration.setText(String.valueOf(Application.getInstance().CNT_KAPCHA()));

        tfState.setText(String.valueOf(Application.getInstance().STATE_OVERFLOW()));
        tfCutState.setText(String.valueOf(Application.getInstance().STATE_CUT_OVERFLOW()));
        tfCapcthaState.setText(String.valueOf(Application.getInstance().STATE_STATUS_CAPTCHA_OVERFLOW()));

        TouchComponent lootSlotOne = new TouchComponent(tfLootSlotOneX, tfLootSlotOneY, numericVerifier);
        lootSlotOne.init(Application.getInstance().LOOT_TOUCH()[0]);

        TouchComponent lootSlotTwo = new TouchComponent(tfLootSlotTwoX, tfLootSlotTwoY, numericVerifier);
        lootSlotTwo.init(Application.getInstance().LOOT_TOUCH()[1]);

        TouchComponent lootSlotThree = new TouchComponent(tfLootSlotThreeX, tfLootSlotThreeY, numericVerifier);
        lootSlotThree.init(Application.getInstance().LOOT_TOUCH()[2]);

        TouchComponent lootSlotFour = new TouchComponent(tfLootSlotFourX, tfLootSlotFourY, numericVerifier);
        lootSlotFour.init(Application.getInstance().LOOT_TOUCH()[3]);

        TouchComponent lootSlotFive = new TouchComponent(tfLootSlotFiveX, tfLootSlotFiveY, numericVerifier);
        lootSlotFive.init(Application.getInstance().LOOT_TOUCH()[4]);

        TouchComponent lootSlotSix = new TouchComponent(tfLootSlotSixX, tfLootSlotSixY, numericVerifier);
        lootSlotSix.init(Application.getInstance().LOOT_TOUCH()[5]);

        TouchComponent lootSlotSeven = new TouchComponent(tfLootSlotSevenX, tfLootSlotSevenY, numericVerifier);
        lootSlotSeven.init(Application.getInstance().LOOT_TOUCH()[6]);

        TouchComponent lootSlotEight = new TouchComponent(tfLootSlotEightX, tfLootSlotEightY, numericVerifier);
        lootSlotEight.init(Application.getInstance().LOOT_TOUCH()[7]);

        lRodX.setIcon(new ImageIcon(UI.IMG_IDEA));
        lRodY.setIcon(new ImageIcon(UI.IMG_IDEA));
        lRodDx.setIcon(new ImageIcon(UI.IMG_IDEA));
        lRodDy.setIcon(new ImageIcon(UI.IMG_IDEA));

        tfRodX.setText(String.valueOf(Application.getInstance().ROD_START_X()));
        tfRodY.setText(String.valueOf(Application.getInstance().ROD_START_Y()));
        tfRodDX.setText(String.valueOf(Application.getInstance().ROD_DX()));
        tfRodDY.setText(String.valueOf(Application.getInstance().ROD_DY()));

        tfInputDelay.setInputVerifier(numericVerifier);
        tfCaptchaNoiseIteration.setInputVerifier(numericVerifier);
        tfState.setInputVerifier(numericVerifier);
        tfCutState.setInputVerifier(numericVerifier);
        tfCapcthaState.setInputVerifier(numericVerifier);

        tfRodX.setInputVerifier(numericVerifier);
        tfRodY.setInputVerifier(numericVerifier);
        tfRodDX.setInputVerifier(numericVerifier);
        tfRodDY.setInputVerifier(numericVerifier);

    }

    private void initCoord(){

        CoordComponent fullscreen = CoordComponent.Builder.config()
                .setComponentX(tfFullscreenX)
                .setComponentY(tfFullscreenY)
                .setComponentWidth(tfFullscreenWidth)
                .setComponentHeight(tfFullscreenHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().FULL_SCREEN())
                .build();

        CoordComponent space = CoordComponent.Builder.config()
                .setComponentX(tfSpaceX)
                .setComponentY(tfSpaceY)
                .setComponentWidth(tfSpaceWidth)
                .setComponentHeight(tfSpaceHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().SPACE())
                .build();

        CoordComponent line = CoordComponent.Builder.config()
                .setComponentX(tfLineX)
                .setComponentY(tfLineY)
                .setComponentWidth(tfLineWidth)
                .setComponentHeight(tfLineHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LINE())
                .build();

        CoordComponent subline = CoordComponent.Builder.config()
                .setComponentX(tfSubLineX)
                .setComponentY(tfSubLineY)
                .setComponentWidth(tfSubLineWidth)
                .setComponentHeight(tfSubLineHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().SUB_LINE())
                .build();

        CoordComponent statusCut = CoordComponent.Builder.config()
                .setComponentX(tfStatusCutX)
                .setComponentY(tfStatusCutY)
                .setComponentWidth(tfStatusCutWidth)
                .setComponentHeight(tfStatusCutHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().STATUS_CUT())
                .build();

        CoordComponent statusCaptcha = CoordComponent.Builder.config()
                .setComponentX(tfStatusCaptchaX)
                .setComponentY(tfStatusCaptchaY)
                .setComponentWidth(tfStatusCaptchaWidth)
                .setComponentHeight(tfStatusCaptchaHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().STATUS_CAPTCHA())
                .build();

        CoordComponent captcha = CoordComponent.Builder.config()
                .setComponentX(tfCaptchaX)
                .setComponentY(tfCaptchaY)
                .setComponentWidth(tfCaptchaWidth)
                .setComponentHeight(tfCaptchaHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().CAPTCHA())
                .build();

        CoordComponent lootOne = CoordComponent.Builder.config()
                .setComponentX(tfLootOneX)
                .setComponentY(tfLootOneY)
                .setComponentWidth(tfLootOneWidth)
                .setComponentHeight(tfLootOneHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[0])
                .build();

        CoordComponent lootTwo = CoordComponent.Builder.config()
                .setComponentX(tfLootTwoX)
                .setComponentY(tfLootTwoY)
                .setComponentWidth(tfLootTwoWidth)
                .setComponentHeight(tfLootTwoHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[1])
                .build();

        CoordComponent lootThree= CoordComponent.Builder.config()
                .setComponentX(tfLootThreeX)
                .setComponentY(tfLootThreeY)
                .setComponentWidth(tfLootThreeWidth)
                .setComponentHeight(tfLootThreeHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[2])
                .build();

        CoordComponent lootFour= CoordComponent.Builder.config()
                .setComponentX(tfLootFourX)
                .setComponentY(tfLootFourY)
                .setComponentWidth(tfLootFourWidth)
                .setComponentHeight(tfLootFourHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[3])
                .build();

        CoordComponent lootFive= CoordComponent.Builder.config()
                .setComponentX(tfLootFiveX)
                .setComponentY(tfLootFiveY)
                .setComponentWidth(tfLootFiveWidth)
                .setComponentHeight(tfLootFiveHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[4])
                .build();

        CoordComponent lootSix= CoordComponent.Builder.config()
                .setComponentX(tfLootSixX)
                .setComponentY(tfLootSixY)
                .setComponentWidth(tfLootSixWidth)
                .setComponentHeight(tfLootSixHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[5])
                .build();

        CoordComponent lootSeven= CoordComponent.Builder.config()
                .setComponentX(tfLootSevenX)
                .setComponentY(tfLootSevenY)
                .setComponentWidth(tfLootSevenWidth)
                .setComponentHeight(tfLootSevenHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[6])
                .build();

        CoordComponent lootEight= CoordComponent.Builder.config()
                .setComponentX(tfLootEightX)
                .setComponentY(tfLootEightY)
                .setComponentWidth(tfLootEightWidth)
                .setComponentHeight(tfLootEightHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().LOOT_SLOT_LIST()[7])
                .build();

        CoordComponent chat = CoordComponent.Builder.config()
                .setComponentX(tfChatX)
                .setComponentY(tfChatY)
                .setComponentWidth(tfChatWidth)
                .setComponentHeight(tfChatHeight)
                .setVerifier(numericVerifier)
                .setRectangle(Application.getInstance().CHAT())
                .build();
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
        tfRodChange.addMouseListener(new TimeMouseConverter(tfRodChange));

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

        SlotComponent.Builder.config()
                .setActiveComponent(cbFirstSlotActive)
                .setKeyComponent(tfFirstSlotKey, slotKeyVerifier)
                .setDelayComponent(tfFirstSlotDelay, delayPeriodVerifier)
                .setPeriodComponent(tfFirstSlotPeriod, delayPeriodVerifier)
                .setHotSlot(Application.getInstance().SLOT_ONE())
                .build();

        SlotComponent.Builder.config()
                .setActiveComponent(cbSecondSlotActive)
                .setKeyComponent(tfSecondSlotKey, slotKeyVerifier)
                .setDelayComponent(tfSecondSlotDelay, delayPeriodVerifier)
                .setPeriodComponent(tfSecondSlotPeriod, delayPeriodVerifier)
                .setHotSlot(Application.getInstance().SLOT_TWO())
                .build();

        SlotComponent.Builder.config()
                .setActiveComponent(cbThirdSlotActive)
                .setKeyComponent(tfThirdSlotKey, slotKeyVerifier)
                .setDelayComponent(tfThirdSlotDelay, delayPeriodVerifier)
                .setPeriodComponent(tfThirdSlotPeriod, delayPeriodVerifier)
                .setHotSlot(Application.getInstance().SLOT_THREE())
                .build();
    }

    private void initLootFilter() {
        cbRed.setSelected(Application.getInstance().RED_FRAME());
        cbGold.setSelected(Application.getInstance().GOLD_FRAME());
        cbBlue.setSelected(Application.getInstance().BLUE_FRAME());
        cbGreen.setSelected(Application.getInstance().GREEN_FRAME());
        cbGray.setSelected(Application.getInstance().GRAY_FRAME());

        cbUsefull.setSelected(Application.getInstance().USEFULL());
        cbConfirm.setSelected(Application.getInstance().CONFIRM());
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

        beerPeriodFrom.setText(String.valueOf(Application.getInstance().SLOT_BEER().getPeriod()));
        beerPeriodFrom.setInputVerifier(delayPeriodVerifier);
        beerPeriodFrom.addMouseListener(new TimeMouseConverter(beerPeriodFrom));

        beerPeriodTo.setText(String.valueOf(Application.getInstance().SLOT_BEER().getRandomPeriod()));
        beerPeriodTo.setInputVerifier(delayPeriodVerifier);
        beerPeriodTo.addMouseListener(new TimeMouseConverter(beerPeriodTo));
    }

    private void initAutoEnd() {

        lIdeaPause.setIcon(new ImageIcon(UI.IMG_IDEA));
        cbPause.setSelected(Application.getInstance().TASK_PAUSE().isActive());
        tfPauseDelayFrom.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getDelay()));
        tfPauseDelayFrom.addMouseListener(new TimeMouseConverter(tfPauseDelayFrom));
        tfPauseDelayTo.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getRandomDelay()));
        tfPauseDelayTo.addMouseListener(new TimeMouseConverter(tfPauseDelayTo));

        tfPauseDowntimeFrom.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getPauseFrom()));
        tfPauseDowntimeFrom.addMouseListener(new TimeMouseConverter(tfPauseDowntimeFrom));
        tfPauseDowntimeTo.setText(String.valueOf(Application.getInstance().TASK_PAUSE().getPauseTo()));
        tfPauseDowntimeTo.addMouseListener(new TimeMouseConverter(tfPauseDowntimeTo));

        lTimer.setIcon(new ImageIcon(UI.SMALL_PREMIUM));
        lTimer.setToolTipText(UIManager.getString("preference.premium.tooltip"));
        Component[] components = timerContent.getComponents();
        for (Component component : components) {
            component.setEnabled(Application.getUser().isPremium());
        }

        lIdeaDeferredStart.setIcon(new ImageIcon(UI.IMG_IDEA));
        cbDeferredStart.setSelected(Application.getInstance().TASK_START().isActive());
        tfDeferredStart.setText(String.valueOf(Application.getInstance().TASK_START().getDelay()));
        tfDeferredStart.addMouseListener(new TimeMouseConverter(tfDeferredStart));
        tfDeferredStart.setInputVerifier(delayPeriodVerifier);

        lIdeaStopBot.setIcon(new ImageIcon(UI.IMG_IDEA));
        cbStopBot.setSelected(Application.getInstance().TASK_STOP().isActive());
        cbStopBot.addItemListener(e -> {
            JCheckBox item = (JCheckBox) e.getItem();
            if (item.isSelected()) {
                cbExitGame.setSelected(false);
            }
        });

        tfStopBot.setText(String.valueOf(Application.getInstance().TASK_STOP().getDelay()));
        tfStopBot.addMouseListener(new TimeMouseConverter(tfStopBot));
        tfStopBot.setInputVerifier(delayPeriodVerifier);

        lIdeaExitGame.setIcon(new ImageIcon(UI.IMG_IDEA));
        cbExitGame.setSelected(Application.getInstance().TASK_EXIT_GAME().isActive());
        cbExitGame.addItemListener(e -> {
            JCheckBox item = (JCheckBox) e.getItem();
            if (item.isSelected()) {
                cbStopBot.setSelected(false);
            }
        });
        tfExitGame.setText(String.valueOf(Application.getInstance().TASK_EXIT_GAME().getDelay()));
        tfExitGame.addMouseListener(new TimeMouseConverter(tfExitGame));
        tfExitGame.setInputVerifier(delayPeriodVerifier);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TabSettingsView::new);

    }

    public JTextField getBeerPeriodTo() {
        return beerPeriodTo;
    }

    public JCheckBox getCbDebugDebuf() {
        return cbDebugDebuf;
    }

    public JTextField getTfDebufDayKey() {
        return tfDebufDayKey;
    }

    public JTextField getTfDebufNightKey() {
        return tfDebufNightKey;
    }

    public JCheckBox getCbDebufDay() {
        return cbDebufDay;
    }

    public JCheckBox getCbDebufNight() {
        return cbDebufNight;
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

    public JTextField getBeerPeriodFrom() {
        return beerPeriodFrom;
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
        generalTab.setLayout(new GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(this.$$$getMessageFromBundle$$$("locale", "preference.label.tab.general"), generalTab);
        final Spacer spacer1 = new Spacer();
        generalTab.add(spacer1, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        loot = new JPanel();
        loot.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(loot, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Loot = new JLabel();
        this.$$$loadLabelText$$$(Loot, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot"));
        loot.add(Loot, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        lootContent = new JPanel();
        lootContent.setLayout(new GridLayoutManager(1, 8, new Insets(5, 5, 5, 5), -1, -1));
        loot.add(lootContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbRed = new JCheckBox();
        this.$$$loadButtonText$$$(cbRed, this.$$$getMessageFromBundle$$$("locale", "preference.label.red"));
        lootContent.add(cbRed, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        lootContent.add(spacer2, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cbConfirm = new JCheckBox();
        this.$$$loadButtonText$$$(cbConfirm, this.$$$getMessageFromBundle$$$("locale", "preference.label.stack"));
        lootContent.add(cbConfirm, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbUsefull = new JCheckBox();
        this.$$$loadButtonText$$$(cbUsefull, this.$$$getMessageFromBundle$$$("locale", "preference.label.usefull"));
        lootContent.add(cbUsefull, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbBlue = new JCheckBox();
        this.$$$loadButtonText$$$(cbBlue, this.$$$getMessageFromBundle$$$("locale", "preference.label.blue"));
        lootContent.add(cbBlue, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbGold = new JCheckBox();
        this.$$$loadButtonText$$$(cbGold, this.$$$getMessageFromBundle$$$("locale", "preference.label.gold"));
        lootContent.add(cbGold, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbGreen = new JCheckBox();
        this.$$$loadButtonText$$$(cbGreen, this.$$$getMessageFromBundle$$$("locale", "preference.label.green"));
        lootContent.add(cbGreen, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbGray = new JCheckBox();
        this.$$$loadButtonText$$$(cbGray, this.$$$getMessageFromBundle$$$("locale", "preference.label.gray"));
        lootContent.add(cbGray, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portContent = new JPanel();
        portContent.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(portContent, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, this.$$$getMessageFromBundle$$$("locale", "preference.label.port"));
        portContent.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        portContent.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbPort = new JComboBox();
        panel1.add(cbPort, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        task = new JPanel();
        task.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(task, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lTask = new JLabel();
        this.$$$loadLabelText$$$(lTask, this.$$$getMessageFromBundle$$$("locale", "preference.label.autouse"));
        task.add(lTask, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        taskContent = new JPanel();
        taskContent.setLayout(new GridLayoutManager(1, 8, new Insets(5, 5, 5, 5), -1, -1));
        task.add(taskContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        taskContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbBeer = new JCheckBox();
        cbBeer.setHorizontalAlignment(10);
        cbBeer.setHorizontalTextPosition(11);
        this.$$$loadButtonText$$$(cbBeer, this.$$$getMessageFromBundle$$$("locale", "preference.label.beer"));
        taskContent.add(cbBeer, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(25, -1), null, 0, false));
        lBeerKey = new JLabel();
        this.$$$loadLabelText$$$(lBeerKey, this.$$$getMessageFromBundle$$$("locale", "preference.label.digital"));
        lBeerKey.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.beer_digit.tooltip"));
        taskContent.add(lBeerKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beerKey = new JTextField();
        taskContent.add(beerKey, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        lBeerPeriod = new JLabel();
        this.$$$loadLabelText$$$(lBeerPeriod, this.$$$getMessageFromBundle$$$("locale", "preference.label.period"));
        lBeerPeriod.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.beer_period.tooltip"));
        taskContent.add(lBeerPeriod, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beerPeriodFrom = new JTextField();
        taskContent.add(beerPeriodFrom, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbRepeatWork = new JCheckBox();
        this.$$$loadButtonText$$$(cbRepeatWork, this.$$$getMessageFromBundle$$$("locale", "preference.label.repeatslave"));
        cbRepeatWork.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.beer_repeat.tooltip"));
        taskContent.add(cbRepeatWork, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("-");
        taskContent.add(label2, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        beerPeriodTo = new JTextField();
        taskContent.add(beerPeriodTo, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        slots = new JPanel();
        slots.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(slots, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lSlots = new JLabel();
        this.$$$loadLabelText$$$(lSlots, this.$$$getMessageFromBundle$$$("locale", "preference.label.slot"));
        slots.add(lSlots, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        slotContent = new JPanel();
        slotContent.setLayout(new GridLayoutManager(3, 7, new Insets(5, 5, 5, 5), -1, -1));
        slots.add(slotContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        slotContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbFirstSlotActive = new JCheckBox();
        cbFirstSlotActive.setText("");
        slotContent.add(cbFirstSlotActive, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        this.$$$loadLabelText$$$(label3, this.$$$getMessageFromBundle$$$("locale", "preference.label.digital"));
        slotContent.add(label3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfFirstSlotKey = new JTextField();
        slotContent.add(tfFirstSlotKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        this.$$$loadLabelText$$$(label4, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        label4.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.slot.delay.tooltip"));
        slotContent.add(label4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfFirstSlotDelay = new JTextField();
        slotContent.add(tfFirstSlotDelay, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        this.$$$loadLabelText$$$(label5, this.$$$getMessageFromBundle$$$("locale", "preference.label.period"));
        label5.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.slot.period.tooltip"));
        slotContent.add(label5, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfFirstSlotPeriod = new JTextField();
        slotContent.add(tfFirstSlotPeriod, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbSecondSlotActive = new JCheckBox();
        cbSecondSlotActive.setText("");
        slotContent.add(cbSecondSlotActive, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        this.$$$loadLabelText$$$(label6, this.$$$getMessageFromBundle$$$("locale", "preference.label.digital"));
        slotContent.add(label6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfSecondSlotKey = new JTextField();
        slotContent.add(tfSecondSlotKey, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        this.$$$loadLabelText$$$(label7, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        label7.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.slot.delay.tooltip"));
        slotContent.add(label7, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfSecondSlotDelay = new JTextField();
        slotContent.add(tfSecondSlotDelay, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        this.$$$loadLabelText$$$(label8, this.$$$getMessageFromBundle$$$("locale", "preference.label.period"));
        label8.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.slot.period.tooltip"));
        slotContent.add(label8, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfSecondSlotPeriod = new JTextField();
        slotContent.add(tfSecondSlotPeriod, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbThirdSlotActive = new JCheckBox();
        cbThirdSlotActive.setText("");
        slotContent.add(cbThirdSlotActive, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        this.$$$loadLabelText$$$(label9, this.$$$getMessageFromBundle$$$("locale", "preference.label.digital"));
        slotContent.add(label9, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfThirdSlotKey = new JTextField();
        slotContent.add(tfThirdSlotKey, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        this.$$$loadLabelText$$$(label10, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        label10.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.slot.delay.tooltip"));
        slotContent.add(label10, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfThirdSlotDelay = new JTextField();
        slotContent.add(tfThirdSlotDelay, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label11 = new JLabel();
        this.$$$loadLabelText$$$(label11, this.$$$getMessageFromBundle$$$("locale", "preference.label.period"));
        label11.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.slot.period.tooltip"));
        slotContent.add(label11, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfThirdSlotPeriod = new JTextField();
        slotContent.add(tfThirdSlotPeriod, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        rod = new JPanel();
        rod.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(rod, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lRod = new JLabel();
        this.$$$loadLabelText$$$(lRod, this.$$$getMessageFromBundle$$$("locale", "preference.label.rod"));
        rod.add(lRod, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        rodContent = new JPanel();
        rodContent.setLayout(new GridLayoutManager(2, 2, new Insets(5, 5, 5, 5), -1, -1));
        rod.add(rodContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rodContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label12 = new JLabel();
        this.$$$loadLabelText$$$(label12, this.$$$getMessageFromBundle$$$("locale", "preference.label.rod.count"));
        rodContent.add(label12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfRodCount = new JTextField();
        rodContent.add(tfRodCount, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label13 = new JLabel();
        this.$$$loadLabelText$$$(label13, this.$$$getMessageFromBundle$$$("locale", "preference.label.waittime"));
        rodContent.add(label13, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfRodChange = new JTextField();
        rodContent.add(tfRodChange, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        notification = new JPanel();
        notification.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(notification, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        this.$$$loadLabelText$$$(label14, this.$$$getMessageFromBundle$$$("locale", "preference.label.notification"));
        notification.add(label14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        notificationContent = new JPanel();
        notificationContent.setLayout(new GridLayoutManager(1, 3, new Insets(5, 5, 5, 5), -1, -1));
        notification.add(notificationContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        notificationContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbTelegram = new JCheckBox();
        this.$$$loadButtonText$$$(cbTelegram, this.$$$getMessageFromBundle$$$("locale", "preference.label.telegram"));
        notificationContent.add(cbTelegram, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        this.$$$loadLabelText$$$(label15, this.$$$getMessageFromBundle$$$("locale", "preference.label.key"));
        notificationContent.add(label15, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfTelegramKey = new JTextField();
        notificationContent.add(tfTelegramKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        personalMessage = new JPanel();
        personalMessage.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(personalMessage, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        this.$$$loadLabelText$$$(label16, this.$$$getMessageFromBundle$$$("locale", "preference.label.wrotepm"));
        personalMessage.add(label16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        pmContent = new JPanel();
        pmContent.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        personalMessage.add(pmContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pmContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        rbAutoFish = new JRadioButton();
        this.$$$loadButtonText$$$(rbAutoFish, this.$$$getMessageFromBundle$$$("locale", "preference.label.autofish"));
        pmContent.add(rbAutoFish, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rbExitGame = new JRadioButton();
        this.$$$loadButtonText$$$(rbExitGame, this.$$$getMessageFromBundle$$$("locale", "preference.label.exitgame"));
        pmContent.add(rbExitGame, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rbNothing = new JRadioButton();
        rbNothing.setSelected(false);
        this.$$$loadButtonText$$$(rbNothing, this.$$$getMessageFromBundle$$$("locale", "preference.label.nothing"));
        pmContent.add(rbNothing, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        pmContent.add(spacer3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        timer = new JPanel();
        timer.setLayout(new GridLayoutManager(2, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(timer, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lTimer = new JLabel();
        this.$$$loadLabelText$$$(lTimer, this.$$$getMessageFromBundle$$$("locale", "preference.label.timer"));
        timer.add(lTimer, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        timerContent = new JPanel();
        timerContent.setLayout(new GridLayoutManager(3, 4, new Insets(5, 5, 5, 5), -1, -1));
        timer.add(timerContent, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        timerContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbStopBot = new JCheckBox();
        this.$$$loadButtonText$$$(cbStopBot, this.$$$getMessageFromBundle$$$("locale", "preference.label.autofish"));
        timerContent.add(cbStopBot, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        this.$$$loadLabelText$$$(label17, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        timerContent.add(label17, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStopBot = new JTextField();
        timerContent.add(tfStopBot, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbExitGame = new JCheckBox();
        this.$$$loadButtonText$$$(cbExitGame, this.$$$getMessageFromBundle$$$("locale", "preference.label.exitgame"));
        timerContent.add(cbExitGame, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        this.$$$loadLabelText$$$(label18, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        timerContent.add(label18, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfExitGame = new JTextField();
        timerContent.add(tfExitGame, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbDeferredStart = new JCheckBox();
        this.$$$loadButtonText$$$(cbDeferredStart, this.$$$getMessageFromBundle$$$("locale", "preference.label.auto_start"));
        timerContent.add(cbDeferredStart, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        this.$$$loadLabelText$$$(label19, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        timerContent.add(label19, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDeferredStart = new JTextField();
        timerContent.add(tfDeferredStart, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lIdeaDeferredStart = new JLabel();
        lIdeaDeferredStart.setText("");
        lIdeaDeferredStart.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "prefernce.label.auto_start.tooltip"));
        timerContent.add(lIdeaDeferredStart, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIdeaStopBot = new JLabel();
        lIdeaStopBot.setText("");
        lIdeaStopBot.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.stop_bot.tooltip"));
        timerContent.add(lIdeaStopBot, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIdeaExitGame = new JLabel();
        lIdeaExitGame.setText("");
        lIdeaExitGame.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.exitgame.tooltip"));
        timerContent.add(lIdeaExitGame, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pauseContent = new JPanel();
        pauseContent.setLayout(new GridLayoutManager(1, 10, new Insets(5, 5, 5, 5), -1, -1));
        timer.add(pauseContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pauseContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbPause = new JCheckBox();
        this.$$$loadButtonText$$$(cbPause, this.$$$getMessageFromBundle$$$("locale", "preference.label.pause"));
        pauseContent.add(cbPause, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        this.$$$loadLabelText$$$(label20, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        pauseContent.add(label20, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfPauseDelayTo = new JTextField();
        pauseContent.add(tfPauseDelayTo, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        tfPauseDelayFrom = new JTextField();
        pauseContent.add(tfPauseDelayFrom, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("-");
        pauseContent.add(label21, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfPauseDowntimeFrom = new JTextField();
        pauseContent.add(tfPauseDowntimeFrom, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        tfPauseDowntimeTo = new JTextField();
        pauseContent.add(tfPauseDowntimeTo, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        final JLabel label22 = new JLabel();
        this.$$$loadLabelText$$$(label22, this.$$$getMessageFromBundle$$$("locale", "preference.label.period"));
        pauseContent.add(label22, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("-");
        pauseContent.add(label23, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIdeaPause = new JLabel();
        lIdeaPause.setText("");
        lIdeaPause.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.pause.tooltip"));
        pauseContent.add(lIdeaPause, new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mode = new JPanel();
        mode.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(mode, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        this.$$$loadLabelText$$$(label24, this.$$$getMessageFromBundle$$$("locale", "preference.label.inputmode"));
        mode.add(label24, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        modeContent = new JPanel();
        modeContent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mode.add(modeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        modeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbMode = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        cbMode.setModel(defaultComboBoxModel1);
        modeContent.add(cbMode, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gameCrash = new JPanel();
        gameCrash.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(gameCrash, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label25 = new JLabel();
        this.$$$loadLabelText$$$(label25, this.$$$getMessageFromBundle$$$("locale", "preference.label.crash_client"));
        gameCrash.add(label25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        gameCrashContent = new JPanel();
        gameCrashContent.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        gameCrash.add(gameCrashContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gameCrashContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        rbCrashShutdownPc = new JRadioButton();
        this.$$$loadButtonText$$$(rbCrashShutdownPc, this.$$$getMessageFromBundle$$$("locale", "preference.label.shutdown_pc"));
        gameCrashContent.add(rbCrashShutdownPc, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rbCrashExitBot = new JRadioButton();
        this.$$$loadButtonText$$$(rbCrashExitBot, this.$$$getMessageFromBundle$$$("locale", "preference.label.close_bot"));
        gameCrashContent.add(rbCrashExitBot, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        gameCrashContent.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        rbCrashStopBot = new JRadioButton();
        this.$$$loadButtonText$$$(rbCrashStopBot, this.$$$getMessageFromBundle$$$("locale", "preference.label.stop_bot"));
        gameCrashContent.add(rbCrashStopBot, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        generalTab.add(panel2, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lDebuf = new JLabel();
        this.$$$loadLabelText$$$(lDebuf, this.$$$getMessageFromBundle$$$("locale", "preference.label.debuf"));
        panel2.add(lDebuf, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        debufContent = new JPanel();
        debufContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        panel2.add(debufContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        debufContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbDebufDay = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebufDay, this.$$$getMessageFromBundle$$$("locale", "preference.label.debuf.day"));
        debufContent.add(cbDebufDay, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label26 = new JLabel();
        this.$$$loadLabelText$$$(label26, this.$$$getMessageFromBundle$$$("locale", "preference.label.digital"));
        debufContent.add(label26, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfDebufDayKey = new JTextField();
        debufContent.add(tfDebufDayKey, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        cbDebufNight = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebufNight, this.$$$getMessageFromBundle$$$("locale", "preference.label.debuf.night"));
        debufContent.add(cbDebufNight, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label27 = new JLabel();
        this.$$$loadLabelText$$$(label27, this.$$$getMessageFromBundle$$$("locale", "preference.label.digital"));
        debufContent.add(label27, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
        tfDebufNightKey = new JTextField();
        debufContent.add(tfDebufNightKey, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, -1), null, 0, false));
        lIdeaDebufDay = new JLabel();
        lIdeaDebufDay.setText("");
        lIdeaDebufDay.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.debuf.tooltip"));
        debufContent.add(lIdeaDebufDay, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIdeaDebufNight = new JLabel();
        lIdeaDebufNight.setText("");
        lIdeaDebufNight.setToolTipText("");
        debufContent.add(lIdeaDebufNight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addTab = new JPanel();
        addTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(this.$$$getMessageFromBundle$$$("locale", "preference.label.tab.add"), addTab);
        final JScrollPane scrollPane1 = new JScrollPane();
        addTab.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addPane = new JPanel();
        addPane.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(addPane);
        language = new JPanel();
        language.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(language, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label28 = new JLabel();
        this.$$$loadLabelText$$$(label28, this.$$$getMessageFromBundle$$$("locale", "preference.language"));
        language.add(label28, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        localeContent = new JPanel();
        localeContent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        language.add(localeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        localeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbLanguage = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        cbLanguage.setModel(defaultComboBoxModel2);
        localeContent.add(cbLanguage, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        theme = new JPanel();
        theme.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(theme, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label29 = new JLabel();
        this.$$$loadLabelText$$$(label29, this.$$$getMessageFromBundle$$$("locale", "preference.label.theme"));
        theme.add(label29, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        themeContent = new JPanel();
        themeContent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        theme.add(themeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        themeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbTheme = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        cbTheme.setModel(defaultComboBoxModel3);
        themeContent.add(cbTheme, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        overflow = new JPanel();
        overflow.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(overflow, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label30 = new JLabel();
        this.$$$loadLabelText$$$(label30, this.$$$getMessageFromBundle$$$("locale", "preference.label.overflow"));
        overflow.add(label30, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        overflowContent = new JPanel();
        overflowContent.setLayout(new GridLayoutManager(3, 3, new Insets(5, 5, 5, 5), -1, -1));
        overflow.add(overflowContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        overflowContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label31 = new JLabel();
        this.$$$loadLabelText$$$(label31, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.general"));
        overflowContent.add(label31, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfState = new JTextField();
        overflowContent.add(tfState, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lStatusCut = new JLabel();
        this.$$$loadLabelText$$$(lStatusCut, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_cut"));
        overflowContent.add(lStatusCut, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfCutState = new JTextField();
        overflowContent.add(tfCutState, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lStatusCaptcha = new JLabel();
        this.$$$loadLabelText$$$(lStatusCaptcha, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_captcha"));
        lStatusCaptcha.setToolTipText("");
        overflowContent.add(lStatusCaptcha, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCapcthaState = new JTextField();
        overflowContent.add(tfCapcthaState, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lIdeaStatusCaptcha = new JLabel();
        lIdeaStatusCaptcha.setText("");
        lIdeaStatusCaptcha.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_captcha.tooltip"));
        overflowContent.add(lIdeaStatusCaptcha, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIdeaStatusCut = new JLabel();
        lIdeaStatusCut.setText("");
        lIdeaStatusCut.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_cut.tooltip"));
        overflowContent.add(lIdeaStatusCut, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lootTouch = new JPanel();
        lootTouch.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(lootTouch, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label32 = new JLabel();
        this.$$$loadLabelText$$$(label32, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot"));
        lootTouch.add(label32, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        lootTouchContent = new JPanel();
        lootTouchContent.setLayout(new GridLayoutManager(8, 5, new Insets(5, 5, 5, 5), -1, -1));
        lootTouch.add(lootTouchContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootTouchContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label33 = new JLabel();
        this.$$$loadLabelText$$$(label33, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot1"));
        label33.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot1.tooltip"));
        lootTouchContent.add(label33, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfLootSlotOneY = new JTextField();
        lootTouchContent.add(tfLootSlotOneY, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label34 = new JLabel();
        this.$$$loadLabelText$$$(label34, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot2"));
        label34.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot2.tooltip"));
        lootTouchContent.add(label34, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        tfLootSlotTwoY = new JTextField();
        lootTouchContent.add(tfLootSlotTwoY, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label35 = new JLabel();
        this.$$$loadLabelText$$$(label35, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot3"));
        label35.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot3.tooltip"));
        lootTouchContent.add(label35, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotThreeY = new JTextField();
        lootTouchContent.add(tfLootSlotThreeY, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotOneX = new JTextField();
        lootTouchContent.add(tfLootSlotOneX, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotTwoX = new JTextField();
        lootTouchContent.add(tfLootSlotTwoX, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotThreeX = new JTextField();
        lootTouchContent.add(tfLootSlotThreeX, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label36 = new JLabel();
        label36.setText("x:");
        lootTouchContent.add(label36, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label37 = new JLabel();
        label37.setText("x:");
        lootTouchContent.add(label37, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label38 = new JLabel();
        label38.setText("x:");
        lootTouchContent.add(label38, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label39 = new JLabel();
        label39.setText("y:");
        lootTouchContent.add(label39, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label40 = new JLabel();
        label40.setText("y:");
        lootTouchContent.add(label40, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label41 = new JLabel();
        label41.setText("y:");
        lootTouchContent.add(label41, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label42 = new JLabel();
        this.$$$loadLabelText$$$(label42, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot4"));
        label42.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot4.tooltip"));
        lootTouchContent.add(label42, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotFourY = new JTextField();
        lootTouchContent.add(tfLootSlotFourY, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotFourX = new JTextField();
        lootTouchContent.add(tfLootSlotFourX, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label43 = new JLabel();
        label43.setText("x:");
        lootTouchContent.add(label43, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label44 = new JLabel();
        label44.setText("y:");
        lootTouchContent.add(label44, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label45 = new JLabel();
        this.$$$loadLabelText$$$(label45, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot5"));
        label45.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot5.tooltip"));
        lootTouchContent.add(label45, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotFiveY = new JTextField();
        lootTouchContent.add(tfLootSlotFiveY, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotFiveX = new JTextField();
        lootTouchContent.add(tfLootSlotFiveX, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label46 = new JLabel();
        label46.setText("x:");
        lootTouchContent.add(label46, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label47 = new JLabel();
        label47.setText("y:");
        lootTouchContent.add(label47, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label48 = new JLabel();
        this.$$$loadLabelText$$$(label48, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot6"));
        label48.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot6.tooltip"));
        lootTouchContent.add(label48, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotSixY = new JTextField();
        lootTouchContent.add(tfLootSlotSixY, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotSixX = new JTextField();
        lootTouchContent.add(tfLootSlotSixX, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label49 = new JLabel();
        label49.setText("x:");
        lootTouchContent.add(label49, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label50 = new JLabel();
        label50.setText("y:");
        lootTouchContent.add(label50, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label51 = new JLabel();
        this.$$$loadLabelText$$$(label51, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot7"));
        label51.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot7.tooltip"));
        lootTouchContent.add(label51, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotSevenY = new JTextField();
        lootTouchContent.add(tfLootSlotSevenY, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotSevenX = new JTextField();
        lootTouchContent.add(tfLootSlotSevenX, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label52 = new JLabel();
        label52.setText("x:");
        lootTouchContent.add(label52, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label53 = new JLabel();
        label53.setText("y:");
        lootTouchContent.add(label53, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label54 = new JLabel();
        this.$$$loadLabelText$$$(label54, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot.slot8"));
        label54.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.loot8.tooltip"));
        lootTouchContent.add(label54, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSlotEightY = new JTextField();
        lootTouchContent.add(tfLootSlotEightY, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSlotEightX = new JTextField();
        lootTouchContent.add(tfLootSlotEightX, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label55 = new JLabel();
        label55.setText("x:");
        lootTouchContent.add(label55, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label56 = new JLabel();
        label56.setText("y:");
        lootTouchContent.add(label56, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rodTouch = new JPanel();
        rodTouch.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(rodTouch, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label57 = new JLabel();
        this.$$$loadLabelText$$$(label57, this.$$$getMessageFromBundle$$$("locale", "preference.label.rod"));
        rodTouch.add(label57, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        rodTouchContent = new JPanel();
        rodTouchContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        rodTouch.add(rodTouchContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rodTouchContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfRodDY = new JTextField();
        rodTouchContent.add(tfRodDY, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfRodX = new JTextField();
        rodTouchContent.add(tfRodX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lRodX = new JLabel();
        lRodX.setText("x:");
        lRodX.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.rod.x.tooltip"));
        rodTouchContent.add(lRodX, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lRodDy = new JLabel();
        lRodDy.setText("dy:");
        lRodDy.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.rod.dy.tooltip"));
        rodTouchContent.add(lRodDy, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lRodDx = new JLabel();
        lRodDx.setText("dx:");
        lRodDx.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.rod.dx.tooltip"));
        rodTouchContent.add(lRodDx, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lRodY = new JLabel();
        lRodY.setText("y:");
        lRodY.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.rod.y.tooltip"));
        rodTouchContent.add(lRodY, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfRodDX = new JTextField();
        rodTouchContent.add(tfRodDX, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfRodY = new JTextField();
        rodTouchContent.add(tfRodY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer5 = new Spacer();
        addPane.add(spacer5, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        state = new JPanel();
        state.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(state, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label58 = new JLabel();
        this.$$$loadLabelText$$$(label58, this.$$$getMessageFromBundle$$$("locale", "preference.label.states"));
        state.add(label58, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        stateContent = new JPanel();
        stateContent.setLayout(new GridLayoutManager(1, 2, new Insets(5, 5, 5, 5), -1, -1));
        state.add(stateContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stateContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cbSkipCalendar = new JCheckBox();
        this.$$$loadButtonText$$$(cbSkipCalendar, this.$$$getMessageFromBundle$$$("locale", "preference.label.skip_calendar"));
        stateContent.add(cbSkipCalendar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        stateContent.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        input = new JPanel();
        input.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(input, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label59 = new JLabel();
        this.$$$loadLabelText$$$(label59, this.$$$getMessageFromBundle$$$("locale", "preference.label.input"));
        input.add(label59, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        inputcontent = new JPanel();
        inputcontent.setLayout(new GridLayoutManager(1, 3, new Insets(5, 5, 5, 5), -1, -1));
        input.add(inputcontent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        inputcontent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lInputDeay = new JLabel();
        this.$$$loadLabelText$$$(lInputDeay, this.$$$getMessageFromBundle$$$("locale", "preference.label.delay"));
        inputcontent.add(lInputDeay, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfInputDelay = new JTextField();
        inputcontent.add(tfInputDelay, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lIdeaInputDelay = new JLabel();
        lIdeaInputDelay.setText("");
        lIdeaInputDelay.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.input.tooltip"));
        inputcontent.add(lIdeaInputDelay, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parsing = new JPanel();
        parsing.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        addPane.add(parsing, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label60 = new JLabel();
        this.$$$loadLabelText$$$(label60, this.$$$getMessageFromBundle$$$("locale", "preference.label.parsing"));
        parsing.add(label60, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        parsingContent = new JPanel();
        parsingContent.setLayout(new GridLayoutManager(2, 3, new Insets(5, 5, 5, 5), -1, -1));
        parsing.add(parsingContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        parsingContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lParsingIdentity = new JLabel();
        this.$$$loadLabelText$$$(lParsingIdentity, this.$$$getMessageFromBundle$$$("locale", "preference.label.parse.coef"));
        parsingContent.add(lParsingIdentity, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfParseCoef = new JTextField();
        parsingContent.add(tfParseCoef, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lCleanNoise = new JLabel();
        this.$$$loadLabelText$$$(lCleanNoise, this.$$$getMessageFromBundle$$$("locale", "preference.label.noise.iteration"));
        parsingContent.add(lCleanNoise, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaNoiseIteration = new JTextField();
        parsingContent.add(tfCaptchaNoiseIteration, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lIdeaCleanNoise = new JLabel();
        lIdeaCleanNoise.setText("");
        lIdeaCleanNoise.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.noise.iteration.tooltip"));
        parsingContent.add(lIdeaCleanNoise, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lIdeaParseIdentity = new JLabel();
        lIdeaParseIdentity.setText("");
        lIdeaParseIdentity.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.parse.coef.tooltip"));
        parsingContent.add(lIdeaParseIdentity, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        coordTab = new JPanel();
        coordTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(this.$$$getMessageFromBundle$$$("locale", "preference.label.tab.coord"), coordTab);
        final JScrollPane scrollPane2 = new JScrollPane();
        coordTab.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(561, 1112), null, 0, false));
        coordPane = new JPanel();
        coordPane.setLayout(new GridLayoutManager(16, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane2.setViewportView(coordPane);
        space = new JPanel();
        space.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(space, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label61 = new JLabel();
        this.$$$loadLabelText$$$(label61, this.$$$getMessageFromBundle$$$("locale", "preference.label.space"));
        space.add(label61, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        spaceContent = new JPanel();
        spaceContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        space.add(spaceContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        spaceContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label62 = new JLabel();
        label62.setText("x:");
        spaceContent.add(label62, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSpaceX = new JTextField();
        spaceContent.add(tfSpaceX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label63 = new JLabel();
        this.$$$loadLabelText$$$(label63, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        spaceContent.add(label63, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSpaceHeight = new JTextField();
        spaceContent.add(tfSpaceHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label64 = new JLabel();
        this.$$$loadLabelText$$$(label64, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        spaceContent.add(label64, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label65 = new JLabel();
        label65.setText("y:");
        spaceContent.add(label65, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSpaceY = new JTextField();
        spaceContent.add(tfSpaceY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfSpaceWidth = new JTextField();
        spaceContent.add(tfSpaceWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        line = new JPanel();
        line.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(line, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label66 = new JLabel();
        this.$$$loadLabelText$$$(label66, this.$$$getMessageFromBundle$$$("locale", "preference.label.line"));
        line.add(label66, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lineContent = new JPanel();
        lineContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        line.add(lineContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lineContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label67 = new JLabel();
        label67.setText("x:");
        lineContent.add(label67, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLineX = new JTextField();
        lineContent.add(tfLineX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label68 = new JLabel();
        this.$$$loadLabelText$$$(label68, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lineContent.add(label68, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLineHeight = new JTextField();
        lineContent.add(tfLineHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label69 = new JLabel();
        this.$$$loadLabelText$$$(label69, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lineContent.add(label69, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLineY = new JTextField();
        lineContent.add(tfLineY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLineWidth = new JTextField();
        lineContent.add(tfLineWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label70 = new JLabel();
        label70.setText("y:");
        lineContent.add(label70, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        subLine = new JPanel();
        subLine.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(subLine, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label71 = new JLabel();
        this.$$$loadLabelText$$$(label71, this.$$$getMessageFromBundle$$$("locale", "preference.label.subline"));
        subLine.add(label71, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        subLineContent = new JPanel();
        subLineContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        subLine.add(subLineContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        subLineContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label72 = new JLabel();
        label72.setText("x:");
        subLineContent.add(label72, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSubLineX = new JTextField();
        subLineContent.add(tfSubLineX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label73 = new JLabel();
        this.$$$loadLabelText$$$(label73, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        subLineContent.add(label73, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSubLineHeight = new JTextField();
        subLineContent.add(tfSubLineHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label74 = new JLabel();
        this.$$$loadLabelText$$$(label74, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        subLineContent.add(label74, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfSubLineY = new JTextField();
        subLineContent.add(tfSubLineY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfSubLineWidth = new JTextField();
        subLineContent.add(tfSubLineWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label75 = new JLabel();
        label75.setText("y:");
        subLineContent.add(label75, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statusCut = new JPanel();
        statusCut.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(statusCut, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label76 = new JLabel();
        this.$$$loadLabelText$$$(label76, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_cut"));
        statusCut.add(label76, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        statusCutContent = new JPanel();
        statusCutContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        statusCut.add(statusCutContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusCutContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label77 = new JLabel();
        label77.setText("x:");
        statusCutContent.add(label77, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCutX = new JTextField();
        statusCutContent.add(tfStatusCutX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label78 = new JLabel();
        this.$$$loadLabelText$$$(label78, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        statusCutContent.add(label78, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCutHeight = new JTextField();
        statusCutContent.add(tfStatusCutHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label79 = new JLabel();
        this.$$$loadLabelText$$$(label79, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        statusCutContent.add(label79, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label80 = new JLabel();
        label80.setText("y:");
        statusCutContent.add(label80, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCutWidth = new JTextField();
        statusCutContent.add(tfStatusCutWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfStatusCutY = new JTextField();
        statusCutContent.add(tfStatusCutY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        captcha = new JPanel();
        captcha.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(captcha, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label81 = new JLabel();
        this.$$$loadLabelText$$$(label81, this.$$$getMessageFromBundle$$$("locale", "preference.label.captcha"));
        captcha.add(label81, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        captchaContent = new JPanel();
        captchaContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        captcha.add(captchaContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        captchaContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label82 = new JLabel();
        label82.setText("x:");
        captchaContent.add(label82, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaX = new JTextField();
        captchaContent.add(tfCaptchaX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label83 = new JLabel();
        this.$$$loadLabelText$$$(label83, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        captchaContent.add(label83, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaHeight = new JTextField();
        captchaContent.add(tfCaptchaHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label84 = new JLabel();
        label84.setText("Длина");
        captchaContent.add(label84, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label85 = new JLabel();
        label85.setText("y:");
        captchaContent.add(label85, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCaptchaWidth = new JTextField();
        captchaContent.add(tfCaptchaWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfCaptchaY = new JTextField();
        captchaContent.add(tfCaptchaY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        statusCaptcha = new JPanel();
        statusCaptcha.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(statusCaptcha, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label86 = new JLabel();
        this.$$$loadLabelText$$$(label86, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_captcha"));
        statusCaptcha.add(label86, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        statusCaptchaContent = new JPanel();
        statusCaptchaContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        statusCaptcha.add(statusCaptchaContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusCaptchaContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label87 = new JLabel();
        label87.setText("x:");
        statusCaptchaContent.add(label87, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCaptchaX = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label88 = new JLabel();
        this.$$$loadLabelText$$$(label88, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        statusCaptchaContent.add(label88, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCaptchaHeight = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label89 = new JLabel();
        this.$$$loadLabelText$$$(label89, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        statusCaptchaContent.add(label89, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfStatusCaptchaWidth = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfStatusCaptchaY = new JTextField();
        statusCaptchaContent.add(tfStatusCaptchaY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label90 = new JLabel();
        label90.setText("y:");
        statusCaptchaContent.add(label90, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lootOne = new JPanel();
        lootOne.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootOne, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label91 = new JLabel();
        this.$$$loadLabelText$$$(label91, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot1"));
        lootOne.add(label91, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootOneContent = new JPanel();
        lootOneContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootOne.add(lootOneContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootOneContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label92 = new JLabel();
        label92.setText("x:");
        lootOneContent.add(label92, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootOneX = new JTextField();
        lootOneContent.add(tfLootOneX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label93 = new JLabel();
        this.$$$loadLabelText$$$(label93, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootOneContent.add(label93, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootOneHeight = new JTextField();
        lootOneContent.add(tfLootOneHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label94 = new JLabel();
        this.$$$loadLabelText$$$(label94, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootOneContent.add(label94, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label95 = new JLabel();
        label95.setText("y:");
        lootOneContent.add(label95, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootOneWidth = new JTextField();
        lootOneContent.add(tfLootOneWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootOneY = new JTextField();
        lootOneContent.add(tfLootOneY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootTwo = new JPanel();
        lootTwo.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootTwo, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label96 = new JLabel();
        this.$$$loadLabelText$$$(label96, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot2"));
        lootTwo.add(label96, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootTwoContent = new JPanel();
        lootTwoContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootTwo.add(lootTwoContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootTwoContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label97 = new JLabel();
        label97.setText("x:");
        lootTwoContent.add(label97, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootTwoX = new JTextField();
        lootTwoContent.add(tfLootTwoX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label98 = new JLabel();
        this.$$$loadLabelText$$$(label98, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootTwoContent.add(label98, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootTwoHeight = new JTextField();
        lootTwoContent.add(tfLootTwoHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label99 = new JLabel();
        this.$$$loadLabelText$$$(label99, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootTwoContent.add(label99, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label100 = new JLabel();
        label100.setText("y:");
        lootTwoContent.add(label100, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootTwoWidth = new JTextField();
        lootTwoContent.add(tfLootTwoWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootTwoY = new JTextField();
        lootTwoContent.add(tfLootTwoY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootThree = new JPanel();
        lootThree.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootThree, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label101 = new JLabel();
        this.$$$loadLabelText$$$(label101, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot3"));
        lootThree.add(label101, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootThreeContent = new JPanel();
        lootThreeContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootThree.add(lootThreeContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootThreeContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label102 = new JLabel();
        label102.setText("x:");
        lootThreeContent.add(label102, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootThreeX = new JTextField();
        lootThreeContent.add(tfLootThreeX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label103 = new JLabel();
        this.$$$loadLabelText$$$(label103, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootThreeContent.add(label103, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootThreeHeight = new JTextField();
        lootThreeContent.add(tfLootThreeHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label104 = new JLabel();
        this.$$$loadLabelText$$$(label104, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootThreeContent.add(label104, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label105 = new JLabel();
        label105.setText("y:");
        lootThreeContent.add(label105, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootThreeY = new JTextField();
        lootThreeContent.add(tfLootThreeY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootThreeWidth = new JTextField();
        lootThreeContent.add(tfLootThreeWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        chat = new JPanel();
        chat.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(chat, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label106 = new JLabel();
        this.$$$loadLabelText$$$(label106, this.$$$getMessageFromBundle$$$("locale", "preference.label.chat"));
        chat.add(label106, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        chatContent = new JPanel();
        chatContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        chat.add(chatContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chatContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label107 = new JLabel();
        label107.setText("x:");
        chatContent.add(label107, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatX = new JTextField();
        chatContent.add(tfChatX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label108 = new JLabel();
        this.$$$loadLabelText$$$(label108, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        chatContent.add(label108, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatHeight = new JTextField();
        chatContent.add(tfChatHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label109 = new JLabel();
        label109.setText("y:");
        chatContent.add(label109, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatY = new JTextField();
        chatContent.add(tfChatY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label110 = new JLabel();
        this.$$$loadLabelText$$$(label110, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        chatContent.add(label110, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfChatWidth = new JTextField();
        chatContent.add(tfChatWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fullscreen = new JPanel();
        fullscreen.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(fullscreen, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label111 = new JLabel();
        this.$$$loadLabelText$$$(label111, this.$$$getMessageFromBundle$$$("locale", "preference.label.fullscreen"));
        fullscreen.add(label111, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        fullscreenContent = new JPanel();
        fullscreenContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        fullscreen.add(fullscreenContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        fullscreenContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label112 = new JLabel();
        label112.setText("x:");
        fullscreenContent.add(label112, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFullscreenX = new JTextField();
        fullscreenContent.add(tfFullscreenX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label113 = new JLabel();
        this.$$$loadLabelText$$$(label113, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        fullscreenContent.add(label113, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFullscreenHeight = new JTextField();
        fullscreenContent.add(tfFullscreenHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label114 = new JLabel();
        this.$$$loadLabelText$$$(label114, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        fullscreenContent.add(label114, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label115 = new JLabel();
        label115.setText("y:");
        fullscreenContent.add(label115, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFullscreenWidth = new JTextField();
        tfFullscreenWidth.setText("");
        fullscreenContent.add(tfFullscreenWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfFullscreenY = new JTextField();
        fullscreenContent.add(tfFullscreenY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootFour = new JPanel();
        lootFour.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootFour, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label116 = new JLabel();
        this.$$$loadLabelText$$$(label116, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot4"));
        lootFour.add(label116, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootFourContent = new JPanel();
        lootFourContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootFour.add(lootFourContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootFourContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label117 = new JLabel();
        label117.setText("x:");
        lootFourContent.add(label117, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFourX = new JTextField();
        lootFourContent.add(tfLootFourX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label118 = new JLabel();
        this.$$$loadLabelText$$$(label118, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootFourContent.add(label118, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFourHeight = new JTextField();
        lootFourContent.add(tfLootFourHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label119 = new JLabel();
        this.$$$loadLabelText$$$(label119, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootFourContent.add(label119, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label120 = new JLabel();
        label120.setText("y:");
        lootFourContent.add(label120, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFourY = new JTextField();
        lootFourContent.add(tfLootFourY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootFourWidth = new JTextField();
        lootFourContent.add(tfLootFourWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootEight = new JPanel();
        lootEight.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootEight, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label121 = new JLabel();
        this.$$$loadLabelText$$$(label121, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot8"));
        lootEight.add(label121, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootEightContent = new JPanel();
        lootEightContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootEight.add(lootEightContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootEightContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label122 = new JLabel();
        label122.setText("x:");
        lootEightContent.add(label122, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootEightX = new JTextField();
        lootEightContent.add(tfLootEightX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label123 = new JLabel();
        this.$$$loadLabelText$$$(label123, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootEightContent.add(label123, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootEightHeight = new JTextField();
        lootEightContent.add(tfLootEightHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label124 = new JLabel();
        this.$$$loadLabelText$$$(label124, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootEightContent.add(label124, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label125 = new JLabel();
        label125.setText("y:");
        lootEightContent.add(label125, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootEightY = new JTextField();
        lootEightContent.add(tfLootEightY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootEightWidth = new JTextField();
        lootEightContent.add(tfLootEightWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootSeven = new JPanel();
        lootSeven.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootSeven, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label126 = new JLabel();
        this.$$$loadLabelText$$$(label126, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot7"));
        lootSeven.add(label126, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootSevenContent = new JPanel();
        lootSevenContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootSeven.add(lootSevenContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootSevenContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label127 = new JLabel();
        label127.setText("x:");
        lootSevenContent.add(label127, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSevenX = new JTextField();
        lootSevenContent.add(tfLootSevenX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label128 = new JLabel();
        this.$$$loadLabelText$$$(label128, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootSevenContent.add(label128, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSevenHeight = new JTextField();
        lootSevenContent.add(tfLootSevenHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label129 = new JLabel();
        this.$$$loadLabelText$$$(label129, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootSevenContent.add(label129, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label130 = new JLabel();
        label130.setText("y:");
        lootSevenContent.add(label130, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSevenY = new JTextField();
        lootSevenContent.add(tfLootSevenY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSevenWidth = new JTextField();
        lootSevenContent.add(tfLootSevenWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootSix = new JPanel();
        lootSix.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootSix, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label131 = new JLabel();
        this.$$$loadLabelText$$$(label131, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot6"));
        lootSix.add(label131, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootSixContent = new JPanel();
        lootSixContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootSix.add(lootSixContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootSixContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label132 = new JLabel();
        label132.setText("x:");
        lootSixContent.add(label132, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSixX = new JTextField();
        lootSixContent.add(tfLootSixX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label133 = new JLabel();
        this.$$$loadLabelText$$$(label133, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootSixContent.add(label133, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSixHeight = new JTextField();
        lootSixContent.add(tfLootSixHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label134 = new JLabel();
        this.$$$loadLabelText$$$(label134, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootSixContent.add(label134, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label135 = new JLabel();
        label135.setText("y:");
        lootSixContent.add(label135, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootSixY = new JTextField();
        lootSixContent.add(tfLootSixY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootSixWidth = new JTextField();
        lootSixContent.add(tfLootSixWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lootFive = new JPanel();
        lootFive.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        coordPane.add(lootFive, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label136 = new JLabel();
        this.$$$loadLabelText$$$(label136, this.$$$getMessageFromBundle$$$("locale", "preference.label.loot5"));
        lootFive.add(label136, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, -1), null, 0, false));
        lootFiveContent = new JPanel();
        lootFiveContent.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        lootFive.add(lootFiveContent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lootFiveContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label137 = new JLabel();
        label137.setText("x:");
        lootFiveContent.add(label137, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFiveX = new JTextField();
        lootFiveContent.add(tfLootFiveX, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label138 = new JLabel();
        this.$$$loadLabelText$$$(label138, this.$$$getMessageFromBundle$$$("locale", "preference.label.height"));
        lootFiveContent.add(label138, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFiveHeight = new JTextField();
        lootFiveContent.add(tfLootFiveHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label139 = new JLabel();
        this.$$$loadLabelText$$$(label139, this.$$$getMessageFromBundle$$$("locale", "preference.label.width"));
        lootFiveContent.add(label139, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label140 = new JLabel();
        label140.setText("y:");
        lootFiveContent.add(label140, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfLootFiveY = new JTextField();
        lootFiveContent.add(tfLootFiveY, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfLootFiveWidth = new JTextField();
        lootFiveContent.add(tfLootFiveWidth, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        delayTab = new JPanel();
        delayTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(this.$$$getMessageFromBundle$$$("locale", "preference.label.tab.delay"), delayTab);
        delay = new JPanel();
        delay.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        delayTab.add(delay, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayContent = new JPanel();
        delayContent.setLayout(new GridLayoutManager(7, 2, new Insets(5, 5, 5, 5), -1, -1));
        delay.add(delayContent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label141 = new JLabel();
        this.$$$loadLabelText$$$(label141, this.$$$getMessageFromBundle$$$("locale", "preference.label.start"));
        delayContent.add(label141, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label142 = new JLabel();
        this.$$$loadLabelText$$$(label142, this.$$$getMessageFromBundle$$$("locale", "preference.label.waitfish"));
        delayContent.add(label142, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label143 = new JLabel();
        this.$$$loadLabelText$$$(label143, this.$$$getMessageFromBundle$$$("locale", "preference.label.cut"));
        delayContent.add(label143, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label144 = new JLabel();
        this.$$$loadLabelText$$$(label144, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_cut"));
        delayContent.add(label144, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label145 = new JLabel();
        this.$$$loadLabelText$$$(label145, this.$$$getMessageFromBundle$$$("locale", "preference.label.captcha"));
        delayContent.add(label145, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label146 = new JLabel();
        this.$$$loadLabelText$$$(label146, this.$$$getMessageFromBundle$$$("locale", "preference.label.state.status_captcha"));
        delayContent.add(label146, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        final JLabel label147 = new JLabel();
        this.$$$loadLabelText$$$(label147, this.$$$getMessageFromBundle$$$("locale", "preference.label.lootfilter"));
        delayContent.add(label147, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, -1), null, 0, false));
        delayStart = new JPanel();
        delayStart.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayStart, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayStart.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label148 = new JLabel();
        this.$$$loadLabelText$$$(label148, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label148.setToolTipText("Задержка перед задачей на старт.");
        delayStart.add(label148, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDelayStartBefore = new JTextField();
        delayStart.add(tfDelayStartBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayStartAfter = new JTextField();
        delayStart.add(tfDelayStartAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label149 = new JLabel();
        this.$$$loadLabelText$$$(label149, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label149.setToolTipText("Задержка после задачи на старт.");
        delayStart.add(label149, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayWaitFish = new JPanel();
        delayWaitFish.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayWaitFish, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayWaitFish.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label150 = new JLabel();
        this.$$$loadLabelText$$$(label150, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label150.setToolTipText("Задержка перед задачей по проверке готовы ли мы подсекать рыбу.");
        delayWaitFish.add(label150, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDelayWaitfishBefore = new JTextField();
        delayWaitFish.add(tfDelayWaitfishBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayWaitfishAfter = new JTextField();
        delayWaitFish.add(tfDelayWaitfishAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label151 = new JLabel();
        this.$$$loadLabelText$$$(label151, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label151.setToolTipText("Задержка после задачи по проверке готовы ли мы подсекать рыбу.");
        delayWaitFish.add(label151, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayCut = new JPanel();
        delayCut.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayCut, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayCut.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label152 = new JLabel();
        this.$$$loadLabelText$$$(label152, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label152.setToolTipText("Задержка перед подсечкой.");
        delayCut.add(label152, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDelayCutBefore = new JTextField();
        delayCut.add(tfDelayCutBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayCutAfter = new JTextField();
        delayCut.add(tfDelayCutAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label153 = new JLabel();
        this.$$$loadLabelText$$$(label153, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label153.setToolTipText("Задержка после подсечки.");
        delayCut.add(label153, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayStatusCut = new JPanel();
        delayStatusCut.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayStatusCut, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayStatusCut.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfDelayStatusCutBefore = new JTextField();
        delayStatusCut.add(tfDelayStatusCutBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayStatusCutAfter = new JTextField();
        delayStatusCut.add(tfDelayStatusCutAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label154 = new JLabel();
        this.$$$loadLabelText$$$(label154, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label154.setToolTipText("Задержка перед определением статуса подсечки.");
        delayStatusCut.add(label154, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label155 = new JLabel();
        this.$$$loadLabelText$$$(label155, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label155.setToolTipText("Задержка после определения статуса подсечки.");
        delayStatusCut.add(label155, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayCaptcha = new JPanel();
        delayCaptcha.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayCaptcha, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayCaptcha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfDelayCaptchaBefore = new JTextField();
        delayCaptcha.add(tfDelayCaptchaBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayCaptchaAfter = new JTextField();
        delayCaptcha.add(tfDelayCaptchaAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label156 = new JLabel();
        this.$$$loadLabelText$$$(label156, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label156.setToolTipText("Задержка перед парсингом капчи.");
        delayCaptcha.add(label156, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label157 = new JLabel();
        this.$$$loadLabelText$$$(label157, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label157.setToolTipText("Задержка после парсинга и ввода капчи.");
        delayCaptcha.add(label157, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayStatusCaptcha = new JPanel();
        delayStatusCaptcha.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayStatusCaptcha, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayStatusCaptcha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfDelayStatusCaptchaBefore = new JTextField();
        delayStatusCaptcha.add(tfDelayStatusCaptchaBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayStatusCaptchaAfter = new JTextField();
        delayStatusCaptcha.add(tfDelayStatusCaptchaAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label158 = new JLabel();
        this.$$$loadLabelText$$$(label158, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label158.setToolTipText("Задержка перед определением статуса капчи.");
        delayStatusCaptcha.add(label158, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label159 = new JLabel();
        this.$$$loadLabelText$$$(label159, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label159.setToolTipText("Задержка после определения статуса капчи.");
        delayStatusCaptcha.add(label159, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        delayLooFilter = new JPanel();
        delayLooFilter.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        delayContent.add(delayLooFilter, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        delayLooFilter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfDelayLootFilterBefore = new JTextField();
        delayLooFilter.add(tfDelayLootFilterBefore, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDelayLootFilterAfter = new JTextField();
        delayLooFilter.add(tfDelayLootFilterAfter, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label160 = new JLabel();
        this.$$$loadLabelText$$$(label160, this.$$$getMessageFromBundle$$$("locale", "preference.label.before"));
        label160.setToolTipText("Задержка перед запуском задачи по фильтрации лута.");
        delayLooFilter.add(label160, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label161 = new JLabel();
        this.$$$loadLabelText$$$(label161, this.$$$getMessageFromBundle$$$("locale", "preference.label.after"));
        label161.setToolTipText("Задержка после фильтрации лута.");
        delayLooFilter.add(label161, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        delayTab.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        debugTab = new JPanel();
        debugTab.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabPane.addTab(this.$$$getMessageFromBundle$$$("locale", "preference.label.tab.debug"), debugTab);
        debug = new JPanel();
        debug.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        debugTab.add(debug, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        debugContent = new JPanel();
        debugContent.setLayout(new GridLayoutManager(10, 1, new Insets(5, 5, 5, 5), -1, -1));
        debug.add(debugContent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cbDebugWaitFish = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugWaitFish, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.waitfish"));
        debugContent.add(cbDebugWaitFish, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugStatusCaptcha = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugStatusCaptcha, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.status_captcha"));
        debugContent.add(cbDebugStatusCaptcha, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugStatusCut = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugStatusCut, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.status_cut"));
        debugContent.add(cbDebugStatusCut, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugLine = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugLine, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.line"));
        debugContent.add(cbDebugLine, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugCaptcha = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugCaptcha, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.captcha"));
        debugContent.add(cbDebugCaptcha, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugLootFilter = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugLootFilter, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.filter"));
        debugContent.add(cbDebugLootFilter, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugPersonalMessage = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugPersonalMessage, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.pm"));
        debugContent.add(cbDebugPersonalMessage, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbUnknownLoot = new JCheckBox();
        this.$$$loadButtonText$$$(cbUnknownLoot, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.unknown_loot"));
        cbUnknownLoot.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.unknown_loot.tooltip"));
        debugContent.add(cbUnknownLoot, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbUnsortLoot = new JCheckBox();
        this.$$$loadButtonText$$$(cbUnsortLoot, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.unsort_loot"));
        cbUnsortLoot.setToolTipText(this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.unsort_loot.tooltip"));
        debugContent.add(cbUnsortLoot, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbDebugDebuf = new JCheckBox();
        this.$$$loadButtonText$$$(cbDebugDebuf, this.$$$getMessageFromBundle$$$("locale", "preference.label.debug.debuf"));
        debugContent.add(cbDebugDebuf, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        debugTab.add(spacer8, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttons = new JPanel();
        buttons.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        content.add(buttons, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnReset = new JButton();
        this.$$$loadButtonText$$$(btnReset, this.$$$getMessageFromBundle$$$("locale", "preference.label.reset"));
        buttons.add(btnReset, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCancel = new JButton();
        this.$$$loadButtonText$$$(btnCancel, this.$$$getMessageFromBundle$$$("locale", "preference.button.cancel"));
        buttons.add(btnCancel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSave = new JButton();
        this.$$$loadButtonText$$$(btnSave, this.$$$getMessageFromBundle$$$("locale", "preference.button.save"));
        buttons.add(btnSave, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        buttons.add(spacer9, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    private static Method $$$cachedGetBundleMethod$$$ = null;

    private String $$$getMessageFromBundle$$$(String path, String key) {
        ResourceBundle bundle;
        try {
            Class<?> thisClass = this.getClass();
            if ($$$cachedGetBundleMethod$$$ == null) {
                Class<?> dynamicBundleClass = thisClass.getClassLoader().loadClass("com.intellij.DynamicBundle");
                $$$cachedGetBundleMethod$$$ = dynamicBundleClass.getMethod("getBundle", String.class, Class.class);
            }
            bundle = (ResourceBundle) $$$cachedGetBundleMethod$$$.invoke(null, path, thisClass);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(path);
        }
        return bundle.getString(key);
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
