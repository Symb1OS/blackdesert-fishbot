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

public class RootView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 520;
	private static final int HEIGHT = 300;

	public RootView() {
		
		Transfer transfer = null;
		
		setResizable(false);
		this.setTitle("Fish bot");
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(0, 400);
	    this.setAlwaysOnTop(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(new BorderLayout(0, 0));

	    JMenuBar menubar = new JMenuBar();

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
	    
	    JMenuItem feedback = new JMenuItem(UIManager.getString("rootview.menu.help.feedback"));
	    feedback.setIcon(new ImageIcon(UI.IMG_FEEDBACK));
	    feedback.addActionListener((e) -> ExecUtils.openUri(Message.URI_REPORT_PROBLEM));
	    help.add(feedback);
	    
	    menubar.add(file);
	    menubar.add(help);
	    
	    setJMenuBar(menubar);
	    
	    Image im = new ImageIcon(Path.ROOT_ICON).getImage();
	    setIconImage(im);
	    
	    JPanel workPanel = new JPanel();
	    getContentPane().add(workPanel, BorderLayout.CENTER);
	    workPanel.setLayout(new BorderLayout(0, 0));
	    
	    JPanel logPanel = new JPanel();
	    workPanel.add(logPanel);
	    logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.X_AXIS));
	    
	    JTextArea taLog = new JTextArea();
		taLog.setFont(new Font("Times New Roman", Font.PLAIN, 11));
	    taLog.setBackground(Color.BLACK);
	    taLog.setForeground(Color.GREEN);
	    taLog.setEditable(false);
	   
	    JScrollPane scrollPane = new JScrollPane(taLog);
	    logPanel.add(scrollPane);
	    
	    TextAreaAppender appender = new TextAreaAppender(taLog);
	    appender.setLayout(new PatternLayout("%d{dd.MM.yyyy HH:mm:ss}] - %m%n"));
		LogManager.getRootLogger().addAppender(appender);
	    
	    JPanel butonPanel = new JPanel();
	    getContentPane().add(butonPanel, BorderLayout.SOUTH);

	    butonPanel.setLayout(new FlowLayout());
	  
	    JButton bStart = new JButton(UIManager.getString("rootview.button.start"));
		bStart.setBackground(UI.BUTTON_STYLE);
	    bStart.setIcon(new ImageIcon(UI.IMG_PLAY));
	    StartController startController = new StartController(transfer, this);
	    bStart.addActionListener(startController);
	    butonPanel.add(bStart);
	    
	    JButton bStop = new JButton(UIManager.getString("rootview.button.stop"));
		bStop.setBackground(UI.BUTTON_STYLE);
		bStop.setIcon(new ImageIcon(UI.IMG_STOP));
	    StopController stopController = new StopController(transfer);
	    bStop.addActionListener(stopController);
	    butonPanel.add(bStop);
	    
	  /*  JButton bTest = new JButton(UIManager.getString("rootview.button.test"));
	    TestController testController = new TestController();
	    bTest.addActionListener(testController);
	    butonPanel.add(bTest)*/;
	    
	    setVisible(true);
	}
	
	public static class Launcher {

		public static void main(String[] args) {
			
			UIManager.getDefaults().addResourceBundle("locale");
			
			try {
				SwingUtilities.invokeLater( () -> new RootView() );
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}