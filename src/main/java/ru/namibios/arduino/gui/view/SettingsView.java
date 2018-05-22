package ru.namibios.arduino.gui.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.controller.CancelController;
import ru.namibios.arduino.gui.controller.SaveController;

public class SettingsView extends JFrame{
	
	private Logger logger = Logger.getLogger(SettingsView.class);

	private static final long serialVersionUID = 1L;
	
	private JTextField tHash;
	private JComboBox<String> tPort;
	private JTextField tStartDelayAfter;
	private JTextField tStartDelayBefore;
	private JTextField tWaitDelayAfter;
	private JTextField tWaitDelayBefore;
	private JTextField tRodDelayAfter;
	private JTextField tRodDelayBefore;
	private JTextField tKapchaDelayBefore;
	private JTextField tKapchaDelayAfter;
	private JTextField tFilterDelayAfter;
	private JTextField tFilterDelayBefore;
	
	private JCheckBox cbFish;
	private JCheckBox cbRock;
	private JCheckBox cbEvent;
	private JCheckBox cbKey;
	private JCheckBox cbUnknown;
	
	private JCheckBox cbAutoOne;
	private JTextField tHotKeyOne;
	private JTextField tKeyTimerOne;
	
	private JCheckBox cbMinigame;
	private JCheckBox cbBeer;
	private JTextField tCountRod;
	private JTextField tTimeChangeRod;
	
	private JCheckBox cbTelegram;
	private JTextField tTelegramKey;

	private JRadioButton rbAutoFish;

	private JRadioButton rbExitGame;

	private JRadioButton rbNothing;	

	public SettingsView() {
		
		this.setTitle(UIManager.getString("preference.label"));
		this.setSize(new Dimension(520, 550));
		this.setLocationRelativeTo(null);  
	    this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[] {30, 0, 0, 0, 52, 0, 0, 0, 0, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lHash = new JLabel(UIManager.getString("preference.label.key"));
		lHash.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lHash = new GridBagConstraints();
		gbc_lHash.insets = new Insets(0, 0, 5, 5);
		gbc_lHash.anchor = GridBagConstraints.WEST;
		gbc_lHash.gridx = 0;
		gbc_lHash.gridy = 0;
		getContentPane().add(lHash, gbc_lHash);
		
		tHash = new JTextField();
		tHash.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tHash = new GridBagConstraints();
		gbc_tHash.anchor = GridBagConstraints.WEST;
		gbc_tHash.insets = new Insets(0, 0, 5, 0);
		gbc_tHash.fill = GridBagConstraints.HORIZONTAL;
		gbc_tHash.gridx = 1;
		gbc_tHash.gridy = 0;
		getContentPane().add(tHash, gbc_tHash);
		tHash.setColumns(10);
		
		JLabel lPort = new JLabel(UIManager.getString(UIManager.getString("preference.label.port")));
		GridBagConstraints gbc_lPort = new GridBagConstraints();
		gbc_lPort.anchor = GridBagConstraints.WEST;
		gbc_lPort.insets = new Insets(0, 0, 5, 5);
		gbc_lPort.gridx = 0;
		gbc_lPort.gridy = 1;
		getContentPane().add(lPort, gbc_lPort);
		
		tPort = new JComboBox<String>();
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++){
			try{
				tPort.addItem(new String(portNames[i].getDescriptivePortName().getBytes("ISO-8859-1"), "Cp1251"));
			}catch (Exception e) {
				logger.error("Exception " + e);
			}
		}

		GridBagConstraints gbc_tPort = new GridBagConstraints();
		gbc_tPort.anchor = GridBagConstraints.WEST;
		gbc_tPort.insets = new Insets(0, 0, 5, 0);
		gbc_tPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_tPort.gridx = 1;
		gbc_tPort.gridy = 1;
		getContentPane().add(tPort, gbc_tPort);
		
		JLabel lLoot = new JLabel(UIManager.getString("preference.label.loot"));
		GridBagConstraints gbc_lLoot = new GridBagConstraints();
		gbc_lLoot.anchor = GridBagConstraints.NORTHWEST;
		gbc_lLoot.insets = new Insets(0, 0, 5, 5);
		gbc_lLoot.gridx = 0;
		gbc_lLoot.gridy = 3;
		getContentPane().add(lLoot, gbc_lLoot);
		
		JPanel cbPanel = new JPanel();
		cbPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_cbPanel = new GridBagConstraints();
		gbc_cbPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cbPanel.fill = GridBagConstraints.BOTH;
		gbc_cbPanel.gridx = 1;
		gbc_cbPanel.gridy = 3;
		getContentPane().add(cbPanel, gbc_cbPanel);
		cbPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		cbFish = new JCheckBox(UIManager.getString("preference.label.fish"));
		cbPanel.add(cbFish);
		
		cbRock = new JCheckBox(UIManager.getString("preference.label.rock"));
		cbPanel.add(cbRock);
		
		cbEvent = new JCheckBox(UIManager.getString("preference.label.event"));
		cbPanel.add(cbEvent);
		
		cbKey = new JCheckBox(UIManager.getString("preference.label.keys"));
		cbPanel.add(cbKey);
		
		cbUnknown = new JCheckBox(UIManager.getString("preference.label.unknow"));
		cbPanel.add(cbUnknown);
		
		JLabel label_1 = new JLabel(UIManager.getString("preference.label.autouse"));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 4;
		getContentPane().add(label_1, gbc_label_1);
		
		JPanel autoUsePanel = new JPanel();
		autoUsePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_autoUsePanel = (FlowLayout) autoUsePanel.getLayout();
		fl_autoUsePanel.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_autoUsePanel = new GridBagConstraints();
		gbc_autoUsePanel.insets = new Insets(0, 0, 5, 0);
		gbc_autoUsePanel.fill = GridBagConstraints.BOTH;
		gbc_autoUsePanel.gridx = 1;
		gbc_autoUsePanel.gridy = 4;
		getContentPane().add(autoUsePanel, gbc_autoUsePanel);
		
		cbBeer = new JCheckBox(UIManager.getString("preference.label.beer"));
		autoUsePanel.add(cbBeer);
		
		cbMinigame = new JCheckBox(UIManager.getString("preference.label.minigame"));
		autoUsePanel.add(cbMinigame);
		
		JLabel lblNewLabel_2 = new JLabel(UIManager.getString("preference.label.slot"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JPanel slotPanel = new JPanel();
		slotPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_slotPanel = new GridBagConstraints();
		gbc_slotPanel.insets = new Insets(0, 0, 5, 0);
		gbc_slotPanel.fill = GridBagConstraints.BOTH;
		gbc_slotPanel.gridx = 1;
		gbc_slotPanel.gridy = 5;
		getContentPane().add(slotPanel, gbc_slotPanel);
		slotPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel slotOnePanel = new JPanel();
		slotPanel.add(slotOnePanel);
		slotOnePanel.setLayout(null);
		
		cbAutoOne = new JCheckBox("1:");
		cbAutoOne.setBounds(8, 0, 45, 23);
		slotOnePanel.add(cbAutoOne);
		
		JLabel lHotKeyOne = new JLabel(UIManager.getString("preference.label.digital"));
		lHotKeyOne.setBounds(67, 4, 83, 15);
		slotOnePanel.add(lHotKeyOne);
		
		tHotKeyOne = new JTextField();
		tHotKeyOne.setBounds(150, 2, 60, 19);
		slotOnePanel.add(tHotKeyOne);
		tHotKeyOne.setColumns(10);
		
		JLabel lKeyTimerOne = new JLabel(UIManager.getString("preference.label.timer"));
		lKeyTimerOne.setBounds(218, 4, 70, 15);
		slotOnePanel.add(lKeyTimerOne);
		
		tKeyTimerOne = new JTextField();
		tKeyTimerOne.setBounds(290, 2, 91, 19);
		slotOnePanel.add(tKeyTimerOne);
		tKeyTimerOne.setColumns(10);
		
		JLabel label = new JLabel(UIManager.getString("preference.label.rod"));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 6;
		getContentPane().add(label, gbc_label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel(UIManager.getString("preference.label.rod.count"));
		panel.add(lblNewLabel_4);
		
		tCountRod = new JTextField();
		tCountRod.setText("8");
		panel.add(tCountRod);
		tCountRod.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(UIManager.getString("preference.label.waittime"));
		lblNewLabel.setToolTipText(UIManager.getString("preference.label.waittime.tooltip"));
		panel.add(lblNewLabel);
		
		tTimeChangeRod = new JTextField();
		panel.add(tTimeChangeRod);
		tTimeChangeRod.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(UIManager.getString("preference.label.notification"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 7;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel notificationPanel = new JPanel();
		notificationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_notificationPanel = new GridBagConstraints();
		gbc_notificationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_notificationPanel.fill = GridBagConstraints.BOTH;
		gbc_notificationPanel.gridx = 1;
		gbc_notificationPanel.gridy = 7;
		getContentPane().add(notificationPanel, gbc_notificationPanel);
		notificationPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel telegramPanel = new JPanel();
		notificationPanel.add(telegramPanel);
		GridBagLayout gbl_telegramPanel = new GridBagLayout();
		gbl_telegramPanel.columnWidths = new int[] {0, 0, 0};
		gbl_telegramPanel.rowHeights = new int[]{0, 0};
		gbl_telegramPanel.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_telegramPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		telegramPanel.setLayout(gbl_telegramPanel);
		
		cbTelegram = new JCheckBox(UIManager.getString("preference.label.telegram"));
		GridBagConstraints gbc_cbTelegram = new GridBagConstraints();
		gbc_cbTelegram.anchor = GridBagConstraints.WEST;
		gbc_cbTelegram.insets = new Insets(0, 0, 0, 5);
		gbc_cbTelegram.gridx = 0;
		gbc_cbTelegram.gridy = 0;
		telegramPanel.add(cbTelegram, gbc_cbTelegram);
		
		JLabel lTelegram = new JLabel(UIManager.getString("preference.label.key"));
		GridBagConstraints gbc_lTelegram = new GridBagConstraints();
		gbc_lTelegram.insets = new Insets(0, 0, 0, 5);
		gbc_lTelegram.anchor = GridBagConstraints.EAST;
		gbc_lTelegram.gridx = 1;
		gbc_lTelegram.gridy = 0;
		telegramPanel.add(lTelegram, gbc_lTelegram);
		
		tTelegramKey = new JTextField();
		GridBagConstraints gbc_tTelegramKey = new GridBagConstraints();
		gbc_tTelegramKey.anchor = GridBagConstraints.EAST;
		gbc_tTelegramKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_tTelegramKey.gridx = 2;
		gbc_tTelegramKey.gridy = 0;
		telegramPanel.add(tTelegramKey, gbc_tTelegramKey);
		tTelegramKey.setColumns(10);
		
		JLabel label_2 = new JLabel(UIManager.getString("preference.label.wrotepm"));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 8;
		getContentPane().add(label_2, gbc_label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 8;
		getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		rbAutoFish = new JRadioButton(UIManager.getString("preference.label.autofish"));
		panel_1.add(rbAutoFish);
		
		rbExitGame = new JRadioButton(UIManager.getString("preference.label.exitgame"));
		panel_1.add(rbExitGame);
		
		rbNothing = new JRadioButton(UIManager.getString("preference.label.nothing"));
		panel_1.add(rbNothing);
		
		ButtonGroup pmGroup = new ButtonGroup();
		pmGroup.add(rbAutoFish);
		pmGroup.add(rbExitGame);
		pmGroup.add(rbNothing);
		
		JLabel lDelay = new JLabel(UIManager.getString("preference.label.delay"));
		lDelay.setHorizontalAlignment(SwingConstants.LEFT);
		lDelay.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lDelay = new GridBagConstraints();
		gbc_lDelay.anchor = GridBagConstraints.NORTHWEST;
		gbc_lDelay.insets = new Insets(0, 0, 5, 5);
		gbc_lDelay.gridx = 0;
		gbc_lDelay.gridy = 9;
		getContentPane().add(lDelay, gbc_lDelay);
		
		JPanel delayPanel = new JPanel();
		delayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_delayPanel = new GridBagConstraints();
		gbc_delayPanel.insets = new Insets(0, 0, 5, 0);
		gbc_delayPanel.fill = GridBagConstraints.BOTH;
		gbc_delayPanel.gridx = 1;
		gbc_delayPanel.gridy = 9;
		getContentPane().add(delayPanel, gbc_delayPanel);
		delayPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel startPanel = new JPanel();
		delayPanel.add(startPanel);
		GridBagLayout gbl_startPanel = new GridBagLayout();
		gbl_startPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_startPanel.rowHeights = new int[]{0, 0};
		gbl_startPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0};
		gbl_startPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		startPanel.setLayout(gbl_startPanel);
		
		JLabel lDelayStart = new JLabel(UIManager.getString("preference.label.start"));
		GridBagConstraints gbc_lDelayStart = new GridBagConstraints();
		gbc_lDelayStart.gridwidth = 3;
		gbc_lDelayStart.anchor = GridBagConstraints.WEST;
		gbc_lDelayStart.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayStart.gridx = 0;
		gbc_lDelayStart.gridy = 0;
		startPanel.add(lDelayStart, gbc_lDelayStart);
		
		tStartDelayBefore = new JTextField();
		GridBagConstraints gbc_tStartDelayBefore = new GridBagConstraints();
		gbc_tStartDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tStartDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tStartDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tStartDelayBefore.gridx = 4;
		gbc_tStartDelayBefore.gridy = 0;
		startPanel.add(tStartDelayBefore, gbc_tStartDelayBefore);
		tStartDelayBefore.setColumns(10);
		
		tStartDelayAfter = new JTextField();
		tStartDelayAfter.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tStartDelayAfter = new GridBagConstraints();
		gbc_tStartDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tStartDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tStartDelayAfter.gridx = 5;
		gbc_tStartDelayAfter.gridy = 0;
		startPanel.add(tStartDelayAfter, gbc_tStartDelayAfter);
		tStartDelayAfter.setColumns(10);
		
		JPanel waitPanel = new JPanel();
		delayPanel.add(waitPanel);
		GridBagLayout gbl_waitPanel = new GridBagLayout();
		gbl_waitPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_waitPanel.rowHeights = new int[] {0, 0};
		gbl_waitPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_waitPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		waitPanel.setLayout(gbl_waitPanel);
		
		JLabel lDelayWait = new JLabel(UIManager.getString("preference.label.wait"));
		GridBagConstraints gbc_lDelayWait = new GridBagConstraints();
		gbc_lDelayWait.gridwidth = 3;
		gbc_lDelayWait.anchor = GridBagConstraints.WEST;
		gbc_lDelayWait.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayWait.gridx = 0;
		gbc_lDelayWait.gridy = 0;
		waitPanel.add(lDelayWait, gbc_lDelayWait);
		
		tWaitDelayBefore = new JTextField();
		GridBagConstraints gbc_tWaitDelayBefore = new GridBagConstraints();
		gbc_tWaitDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tWaitDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tWaitDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tWaitDelayBefore.gridx = 3;
		gbc_tWaitDelayBefore.gridy = 0;
		waitPanel.add(tWaitDelayBefore, gbc_tWaitDelayBefore);
		tWaitDelayBefore.setColumns(10);
		
		tWaitDelayAfter = new JTextField();
		GridBagConstraints gbc_tWaitDelayAfter = new GridBagConstraints();
		gbc_tWaitDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tWaitDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tWaitDelayAfter.gridx = 4;
		gbc_tWaitDelayAfter.gridy = 0;
		waitPanel.add(tWaitDelayAfter, gbc_tWaitDelayAfter);
		tWaitDelayAfter.setColumns(10);
		
		JPanel cutPanel = new JPanel();
		delayPanel.add(cutPanel);
		GridBagLayout gbl_cutPanel = new GridBagLayout();
		gbl_cutPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_cutPanel.rowHeights = new int[]{0, 0};
		gbl_cutPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0};
		gbl_cutPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		cutPanel.setLayout(gbl_cutPanel);
		
		JLabel lDelayChangeRod = new JLabel(UIManager.getString("preference.label.changerod"));
		GridBagConstraints gbc_lDelayChangeRod = new GridBagConstraints();
		gbc_lDelayChangeRod.gridwidth = 3;
		gbc_lDelayChangeRod.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayChangeRod.anchor = GridBagConstraints.WEST;
		gbc_lDelayChangeRod.gridx = 0;
		gbc_lDelayChangeRod.gridy = 0;
		cutPanel.add(lDelayChangeRod, gbc_lDelayChangeRod);
		
		tRodDelayBefore = new JTextField();
		GridBagConstraints gbc_tRodDelayBefore = new GridBagConstraints();
		gbc_tRodDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tRodDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tRodDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tRodDelayBefore.gridx = 4;
		gbc_tRodDelayBefore.gridy = 0;
		cutPanel.add(tRodDelayBefore, gbc_tRodDelayBefore);
		tRodDelayBefore.setColumns(10);
		
		tRodDelayAfter = new JTextField();
		GridBagConstraints gbc_tRodDelayAfter = new GridBagConstraints();
		gbc_tRodDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tRodDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tRodDelayAfter.gridx = 5;
		gbc_tRodDelayAfter.gridy = 0;
		cutPanel.add(tRodDelayAfter, gbc_tRodDelayAfter);
		tRodDelayAfter.setColumns(10);
		
		JPanel kapchaPanel = new JPanel();
		delayPanel.add(kapchaPanel);
		GridBagLayout gbl_kapchaPanel = new GridBagLayout();
		gbl_kapchaPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_kapchaPanel.rowHeights = new int[]{0, 0};
		gbl_kapchaPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0};
		gbl_kapchaPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		kapchaPanel.setLayout(gbl_kapchaPanel);
		
		JLabel lDelayKapcha = new JLabel(UIManager.getString("preference.label.captcha"));
		GridBagConstraints gbc_lDelayKapcha = new GridBagConstraints();
		gbc_lDelayKapcha.anchor = GridBagConstraints.WEST;
		gbc_lDelayKapcha.gridwidth = 3;
		gbc_lDelayKapcha.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayKapcha.gridx = 0;
		gbc_lDelayKapcha.gridy = 0;
		kapchaPanel.add(lDelayKapcha, gbc_lDelayKapcha);
		
		tKapchaDelayAfter = new JTextField();
		GridBagConstraints gbc_tKapchaDelayAfter = new GridBagConstraints();
		gbc_tKapchaDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tKapchaDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tKapchaDelayAfter.gridx = 5;
		gbc_tKapchaDelayAfter.gridy = 0;
		kapchaPanel.add(tKapchaDelayAfter, gbc_tKapchaDelayAfter);
		tKapchaDelayAfter.setColumns(10);
		
		tKapchaDelayBefore = new JTextField();
		GridBagConstraints gbc_tKapchaDelayBefore = new GridBagConstraints();
		gbc_tKapchaDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tKapchaDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tKapchaDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tKapchaDelayBefore.gridx = 4;
		gbc_tKapchaDelayBefore.gridy = 0;
		kapchaPanel.add(tKapchaDelayBefore, gbc_tKapchaDelayBefore);
		tKapchaDelayBefore.setColumns(10);
		
		JPanel filterPanel = new JPanel();
		delayPanel.add(filterPanel);
		GridBagLayout gbl_filterPanel = new GridBagLayout();
		gbl_filterPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_filterPanel.rowHeights = new int[]{0, 0};
		gbl_filterPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_filterPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		filterPanel.setLayout(gbl_filterPanel);
		
		JLabel lDelayFilter = new JLabel(UIManager.getString("preference.label.filter"));
		GridBagConstraints gbc_lDelayFilter = new GridBagConstraints();
		gbc_lDelayFilter.anchor = GridBagConstraints.WEST;
		gbc_lDelayFilter.gridwidth = 3;
		gbc_lDelayFilter.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayFilter.gridx = 0;
		gbc_lDelayFilter.gridy = 0;
		filterPanel.add(lDelayFilter, gbc_lDelayFilter);
		
		tFilterDelayBefore = new JTextField();
		GridBagConstraints gbc_tFilterDelayBefore = new GridBagConstraints();
		gbc_tFilterDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tFilterDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tFilterDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFilterDelayBefore.gridx = 4;
		gbc_tFilterDelayBefore.gridy = 0;
		filterPanel.add(tFilterDelayBefore, gbc_tFilterDelayBefore);
		tFilterDelayBefore.setColumns(10);
		
		tFilterDelayAfter = new JTextField();
		GridBagConstraints gbc_tFilterDelayAfter = new GridBagConstraints();
		gbc_tFilterDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tFilterDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFilterDelayAfter.gridx = 5;
		gbc_tFilterDelayAfter.gridy = 0;
		filterPanel.add(tFilterDelayAfter, gbc_tFilterDelayAfter);
		tFilterDelayAfter.setColumns(10);
		
		JPanel bPanel = new JPanel();
		GridBagConstraints gbc_bPanel = new GridBagConstraints();
		gbc_bPanel.anchor = GridBagConstraints.EAST;
		gbc_bPanel.fill = GridBagConstraints.VERTICAL;
		gbc_bPanel.gridx = 1;
		gbc_bPanel.gridy = 10;
		getContentPane().add(bPanel, gbc_bPanel);
		
		JButton bSave = new JButton(UIManager.getString("preference.button.save"));
		bSave.addActionListener(new SaveController(this));
		bPanel.add(bSave);
		
		JButton bCancel = new JButton(UIManager.getString("preference.button.cancel"));
		bCancel.addActionListener(new CancelController(this));
		bPanel.add(bCancel);
		
		Application.getInstance();
		
		tHash.setText(UIManager.getString("preference.label.key.value"));
		tHash.setEditable(false);
		
		int count = tPort.getItemCount();
		for (int index = 0; index < count; index++) {
			if(tPort.getItemAt(index).contains(Application.getInstance().PORT())){
				tPort.setSelectedItem(tPort.getItemAt(index));
			};
		}
		
		cbFish.setSelected(Application.getInstance().FISH());
		cbKey.setSelected(Application.getInstance().KEY());
		cbEvent.setSelected(Application.getInstance().EVENT());
		cbRock.setSelected(Application.getInstance().ROCK());
		cbUnknown.setSelected(Application.getInstance().TAKE_UNKNOWN());
		
		cbBeer.setSelected(Application.getInstance().BEER());
		cbMinigame.setSelected(Application.getInstance().MINIGAME());
		
		cbAutoOne.setSelected(Application.getInstance().FIRST_SLOT());
		tHotKeyOne.setText(Application.getInstance().FIRST_KEY_NUMBER());
		tKeyTimerOne.setText(String.valueOf(Application.getInstance().FIRST_SLOT_USE_DELAY()));
		
		cbTelegram.setSelected(Application.getInstance().TELEGRAM());
		tTelegramKey.setText(Application.getInstance().TELEGRAM_KEY());
		
		rbAutoFish.setSelected(Application.getInstance().PM_AUTOFISH());
		rbExitGame.setSelected(Application.getInstance().PM_EXIT_GAME());
		rbNothing.setSelected(Application.getInstance().PM_NOTHING());
		
		tCountRod.setText(String.valueOf(Application.getInstance().COUNT_ROD()));
		tTimeChangeRod.setText(String.valueOf(Application.getInstance().TIME_CHANGE_ROD()));
		
		tStartDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_START()));
		tStartDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_START()));
		
		tWaitDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_WAIT_FISH()));
		tWaitDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_WAIT_FISH()));
		
		tRodDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_CHANGE_ROD()));
		tRodDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_CHANGE_ROD()));
		
		tKapchaDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_KAPCHA()));
		tKapchaDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_KAPCHA()));
		
		tFilterDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_FILTER_LOOT()));
		tFilterDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_FILTER_LOOT()));
		
	}

	public Logger getLogger() {
		return logger;
	}

	public JTextField gettHash() {
		return tHash;
	}

	public JComboBox<String> gettPort() {
		return tPort;
	}

	public JTextField gettStartDelayAfter() {
		return tStartDelayAfter;
	}

	public JTextField gettStartDelayBefore() {
		return tStartDelayBefore;
	}

	public JTextField gettWaitDelayAfter() {
		return tWaitDelayAfter;
	}

	public JTextField gettWaitDelayBefore() {
		return tWaitDelayBefore;
	}

	public JTextField gettRodDelayAfter() {
		return tRodDelayAfter;
	}

	public JTextField gettRodDelayBefore() {
		return tRodDelayBefore;
	}

	public JTextField gettKapchaDelayBefore() {
		return tKapchaDelayBefore;
	}

	public JTextField gettKapchaDelayAfter() {
		return tKapchaDelayAfter;
	}

	public JTextField gettFilterDelayAfter() {
		return tFilterDelayAfter;
	}

	public JTextField gettFilterDelayBefore() {
		return tFilterDelayBefore;
	}

	public JCheckBox getCbFish() {
		return cbFish;
	}

	public JCheckBox getCbRock() {
		return cbRock;
	}

	public JCheckBox getCbEvent() {
		return cbEvent;
	}

	public JCheckBox getCbKey() {
		return cbKey;
	}

	public JCheckBox getCbUnknown() {
		return cbUnknown;
	}

	public JCheckBox getCbAutoOne() {
		return cbAutoOne;
	}

	public JTextField gettHotKeyOne() {
		return tHotKeyOne;
	}

	public JTextField gettKeyTimerOne() {
		return tKeyTimerOne;
	}

	public JCheckBox getCbMinigame() {
		return cbMinigame;
	}

	public JCheckBox getCbBeer() {
		return cbBeer;
	}

	public JTextField gettCountRod() {
		return tCountRod;
	}

	public JTextField gettTimeChangeRod() {
		return tTimeChangeRod;
	}

	public JCheckBox getCbTelegram() {
		return cbTelegram;
	}

	public JTextField gettTelegramKey() {
		return tTelegramKey;
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
}