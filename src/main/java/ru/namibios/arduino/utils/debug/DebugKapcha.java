package ru.namibios.arduino.utils.debug;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.namibios.arduino.model.command.Kapcha;
import ru.namibios.arduino.utils.DelayUtils;

public class DebugKapcha extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 700;
	private static final int WINDOW_HEIGHT = 150;
	
	private Container container = this.getContentPane();
	
	private JLabel kapchaimage = new JLabel();
	private JTextField kapchaParse = new JTextField();
	
	private JButton startButton = new JButton("Старт");
	
	private JButton correctButton = new JButton("Верно");
	private JButton incorrectButton = new JButton("Неверно");
	private JButton deleteButton = new JButton("Удалить");
	
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	
	private boolean isCorrect;
	private boolean isIncorrect;
	private boolean isDelete;
	
	public DebugKapcha() {
		
		super("Debug Kapcha");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    setLayout(new BorderLayout());
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    center.setLayout(new GridLayout(1, 2));
	    center.add(kapchaimage);
	    center.add(kapchaParse);
	    
	    correctButton.addActionListener((e) -> isCorrect = true);
	    incorrectButton.addActionListener((e) -> isIncorrect = true);
	    deleteButton.addActionListener((e) -> isDelete = true);
	    
	    JPanel correctButtonPanel = new JPanel();
	    
	    correctButtonPanel.setLayout(new GridLayout(1, 2));
	    correctButtonPanel.add(correctButton);
	    correctButtonPanel.add(incorrectButton);
	    correctButtonPanel.add(deleteButton);
	    
	    south.setLayout(new GridLayout(2, 1));
	    south.add(correctButtonPanel);
	    
	    startButton.addActionListener((e) -> {
	    	new Thread(() -> {
				try{
					
					String filename= "resources/debug";
					File folder = new File(filename);
					for (File file: folder.listFiles()) {
						if(file.isFile()){
							System.out.println(file.getName());
							kapchaimage.setIcon(new ImageIcon(file.toString()));
							
							Kapcha kapcha = new Kapcha(file.toString());
							String key = kapcha.getKey();
							kapchaParse.setText(key);	
							
							while(true){
								if(isCorrect){
									file.renameTo(new File("resources/debug/correct/" + key.replaceAll("\n", "") + ".jpg"));
								} 
								if(isIncorrect){
									file.renameTo(new File("resources/debug/incorrect/" + file.getName()));
								}
								if(isDelete){
									file.delete();;
								}
								if(isCorrect || isIncorrect  || isDelete){
									isCorrect = isIncorrect = isDelete = false;
									break;
								} 
								DelayUtils.delay(200);
							}	
						}
					}
				}catch(Exception ex){ex.printStackTrace();}
	
			}).start();
	    	
	    });
	    
	    south.add(startButton);
	    
	    container.add(center, BorderLayout.CENTER);
	    container.add(south, BorderLayout.SOUTH);
	    
	}
	
	public static void main(String[] args) {
		DebugKapcha debugKapcha = new DebugKapcha();
		debugKapcha.setVisible(true);
		
	}
	
}