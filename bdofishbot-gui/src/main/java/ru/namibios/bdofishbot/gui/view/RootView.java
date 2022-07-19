package ru.namibios.bdofishbot.gui.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.*;
import ru.namibios.bdofishbot.gui.CheckUpdate;
import ru.namibios.bdofishbot.gui.Info;
import ru.namibios.bdofishbot.gui.MousePointer;
import ru.namibios.bdofishbot.gui.controller.*;
import ru.namibios.bdofishbot.utils.AppUtils;
import ru.namibios.bdofishbot.utils.ExceptionUtils;
import ru.namibios.bdofishbot.utils.ExecUtils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static ru.namibios.bdofishbot.gui.UI.*;

public class RootView extends JFrame {

    private static final Logger LOG = Logger.getLogger(RootView.class);

    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonStop;
    private JTextArea taLog;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JLabel mouseXY;
    private JButton buttonTest;
    private JLabel premiumLabel;
    private JComboBox<Mode> cbWorkMode;
    private JLabel lMode;
    private final JMenuItem preference;

    public RootView() {

        setTitle(AppUtils.getVersion());

        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setLocation(Application.getInstance().UI_WINDOW_ROOT_X(), Application.getInstance().UI_WINDOW_ROOT_Y());
        setSize(new Dimension(520, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image im = new ImageIcon(Path.ROOT_ICON).getImage();
        setIconImage(im);

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        TextAreaAppender appender = new TextAreaAppender(taLog);
        appender.setLayout(new PatternLayout("[%d{dd.MM.yyyy HH:mm:ss}] - %m%n"));
        LogManager.getRootLogger().addAppender(appender);

        lMode.setIcon(new ImageIcon(SMALL_PREMIUM));
        lMode.setText(UIManager.getString("rootview.label.mode"));
        lMode.setToolTipText(UIManager.getString("preference.premium.tooltip"));

        cbWorkMode.setEnabled(Application.getUser().isPremium());
        cbWorkMode.setToolTipText(UIManager.getString("rootview.mode.tooltip"));
        cbWorkMode.addItemListener(e -> Application.getInstance().setProperty("bot.mode", e.getItem().toString()));

        Arrays.stream(Mode.values()).forEach(mode -> cbWorkMode.addItem(mode));

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu(UIManager.getString("rootview.menu.file"));

        preference = new JMenuItem(UIManager.getString("rootview.menu.file.preference"));
        preference.setIcon(new ImageIcon(IMG_SETTINGS));
        preference.addActionListener(new SettingsController());

        file.add(preference);

        file.addSeparator();

        JMenuItem exit = new JMenuItem(UIManager.getString("rootview.menu.file.close"));
        exit.setIcon(new ImageIcon(IMG_CLOSE));
        exit.addActionListener((e) -> Application.closeBot(Application.CODE_OK));
        file.add(exit);

        JMenu premium = new JMenu(UIManager.getString("rootview.menu.premium"));

        JMenuItem premiumItem = new JMenuItem(UIManager.getString("rootview.menu.premium.info"));
        premiumItem.setIcon(new ImageIcon(SMALL_PREMIUM));
        premiumItem.addActionListener(new PremiumController());
        premium.add(premiumItem);

        JMenuItem payInfo = new JMenuItem(UIManager.getString("rootview.menu.premium.payinfo"));
        payInfo.setIcon(new ImageIcon(SMALL_PAYMENT));
        payInfo.addActionListener((e) -> ExecUtils.openUri(String.format(UIManager.getString("rootview.menu.premium.payinfo.url"), Application.getInstance().URL_SERVER_HTTPS())));
        premium.add(payInfo);

        JMenu help = new JMenu(UIManager.getString("rootview.menu.help"));

        JMenuItem wiki = new JMenuItem(UIManager.getString("rootview.menu.help.wiki"));
        wiki.setIcon(new ImageIcon(IMG_WIKI));
        wiki.addActionListener((e) -> ExecUtils.openUri(Message.URI_WIKI));
        help.add(wiki);

        JMenuItem feedback = new JMenuItem(UIManager.getString("rootview.menu.help.feedback"));
        feedback.setIcon(new ImageIcon(IMG_FEEDBACK));
        feedback.addActionListener((e) -> ExecUtils.openUri(Message.URI_REPORT_PROBLEM));
        help.add(feedback);

        menuBar.add(file);
        menuBar.add(premium);
        menuBar.add(help);

        setJMenuBar(menuBar);

        if (Application.getUser().isPremium()) {
            LOG.debug("Premium user");
            premiumLabel.setIcon(new ImageIcon(IMG_PREMIUM));
        }

        StartController startStopController = new StartController(this);

        buttonStart.addActionListener(startStopController);
        buttonStart.setIcon(new ImageIcon(IMG_PLAY));

        buttonStop.addActionListener(startStopController);
        buttonStop.setIcon(new ImageIcon(IMG_STOP));

        buttonTest.addActionListener(new TestController());
        buttonTest.setVisible(false);

        addWindowListener(new CheckUpdate(this));
        addWindowListener(new Info(this));

        try {

            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new HotKeyController(buttonStart, buttonStop));
            GlobalScreen.addNativeMouseMotionListener(new MousePointer(mouseXY));

        } catch (NativeHookException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

        if (Application.getInstance().TELEGRAM()) {
            WebSocketClient client = new StandardWebSocketClient();
            WebSocketStompClient stompClient = new WebSocketStompClient(client);

            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            StompSessionHandler sessionHandler = new TelegramHandler(buttonStart, buttonStop);
            stompClient.connect(Application.getInstance().URL_WS(), sessionHandler);
        }

        ToolTipManager.sharedInstance().setDismissDelay(1000 * 100);
        ToolTipManager.sharedInstance().setInitialDelay(100);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                buttonStop.doClick();
                Application.closeBot(Application.CODE_CLOSE_GUI);
            }
        });

        setVisible(true);
        setResizable(false);

    }

    public JMenuItem getPreference() {
        return preference;
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(buttonPanel, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonStart = new JButton();
        this.$$$loadButtonText$$$(buttonStart, this.$$$getMessageFromBundle$$$("locale", "rootview.button.start"));
        buttonStart.setToolTipText("Hotkey : \"Home\"");
        buttonPanel.add(buttonStart, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonStop = new JButton();
        this.$$$loadButtonText$$$(buttonStop, this.$$$getMessageFromBundle$$$("locale", "rootview.button.stop"));
        buttonStop.setToolTipText("Hotkey : \"End\"");
        buttonPanel.add(buttonStop, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTest = new JButton();
        buttonTest.setText("Тест");
        buttonPanel.add(buttonTest, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mouseXY = new JLabel();
        mouseXY.setEnabled(false);
        mouseXY.setText("Point");
        panel1.add(mouseXY, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        premiumLabel = new JLabel();
        premiumLabel.setText("");
        panel1.add(premiumLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbWorkMode = new JComboBox();
        panel1.add(cbWorkMode, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lMode = new JLabel();
        lMode.setText("Режим:");
        panel1.add(lMode, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel2.add(scrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        taLog = new JTextArea();
        taLog.setBackground(new Color(-16777216));
        taLog.setEditable(false);
        Font taLogFont = this.$$$getFont$$$("DejaVu Sans", -1, 11, taLog.getFont());
        if (taLogFont != null) taLog.setFont(taLogFont);
        taLog.setForeground(new Color(-16711936));
        scrollPane.setViewportView(taLog);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
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
        return contentPane;
    }
}