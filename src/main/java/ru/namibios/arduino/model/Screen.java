package ru.namibios.arduino.model;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.utils.DateUtils;
import ru.namibios.arduino.utils.Http;

public class Screen {
	
	private final static Logger logger = Logger.getLogger(Screen.class);

	public static final Rectangle FULL_SCREEN   = new Rectangle(0, 0, 1920, 1080);
	public static final Rectangle SPACE 	    = new Rectangle(928, 194, 63, 25);
	public static final Rectangle LINE 			= new Rectangle(820, 402, 278, 25);
	public static final Rectangle SUB_LINE 	 	= new Rectangle(997, 402, 10, 25);
	public static final Rectangle STATUS_CUT 	= new Rectangle(874, 480, 171, 33);
	public static final Rectangle KAPCHA 		= new Rectangle(780, 350, 372, 58);
	public static final Rectangle STATUS_KAPCHA = new Rectangle(810, 495, 295, 85);
	public static final Rectangle LOOT_SLOT_ONE = new Rectangle(1537, 592, 47, 48);
	public static final Rectangle LOOT_SLOT_TWO = new Rectangle(1584, 592, 47, 48);
	public static final Rectangle CHAT 			= new Rectangle(5, 1000, 355, 40);
	
	public static final Color WHITE = new Color(120,120,120);
	public static final Color GRAY = new Color(40,40,40);
	
	public BufferedImage screenShot;
	private Noise noise;
	
	public Screen(String filename) throws IOException{
		this.screenShot = ImageIO.read(new File(filename));
		makeGray();
	}
	
	public Screen(Rectangle zone, boolean gray) throws AWTException {
		Robot robot = new Robot();
		screenShot = robot.createScreenCapture(zone);
		if(gray) makeGray();
		noise = new Noise(screenShot);
	}
	
	public Screen(Rectangle zone) throws AWTException {
		Robot robot = new Robot();
		screenShot = robot.createScreenCapture(zone);
		makeGray();
		noise = new Noise(screenShot);
	}
	
	public void clearNoise(int iteration) throws AWTException {
		logger.info("Clean the noise...");
		int cnt = 0;
		while(cnt < iteration){
			BufferedImage noiseImage = new Screen(Screen.KAPCHA).getScreenShot();
			noise.addNois(noiseImage);
			cnt++;
		}
		screenShot = noise.clear();
		logger.info("Clean ended...");
	}

	private void makeGray(){
	    for (int x = 0; x < screenShot.getWidth(); ++x)
	    for (int y = 0; y < screenShot.getHeight(); ++y)
	    {
	        int rgb = screenShot.getRGB(x, y);
	        int r = (rgb >> 16) & 0xFF;
	        int g = (rgb >> 8) & 0xFF;
	        int b = (rgb & 0xFF);

	        int grayLevel = (r + g + b) / 3;
	        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
	        screenShot.setRGB(x, y, gray);
	    }
	}
	
	public void saveDebugImage(){
		try {
			ImageIO.write(screenShot, "jpg", new File(Path.DEBUG + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
		} catch (IOException e) {
			logger.error("Exception " + e);
		}
	}
	
	public void saveImage(String folder) {
		
		try {
			ImageIO.write(screenShot, "jpg", new File(Path.RESOURCES + folder + "/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
		} catch (IOException e) {
			logger.error("Exception " + e);
		}
	}
	
	public BufferedImage getScreenShot() {
		return screenShot;
	}

	public void setScreenShot(BufferedImage screenShot) {
		this.screenShot = screenShot;
	}
	
	public byte[] toByteArray() {
		
		byte[] imageInByte = null;
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(screenShot, "jpg", baos );
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		return imageInByte;
	}
	
	public void upload() throws ClientProtocolException, IOException{
		Http http = new Http();
		http.uploadImage(Application.getInstance().HASH(), screenShot);
	}
	
public class Noise {
		
		private BufferedImage image;
		
		private int width;
		
		private int height;
		
		private List<BufferedImage> noises;
		
		public Noise(BufferedImage image) {
			this.image = image;
			this.height = this.image.getHeight();
			this.width = this.image.getWidth();
			this.noises = new ArrayList<>();
			
		}
		
		public void addNois(BufferedImage image){
			this.noises.add(image);
		}
		
		public BufferedImage clear(){
			
			for (BufferedImage bufferedImage : noises) {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
					Color color = new Color(image.getRGB(j, i));
					Color noiseColor = new Color(bufferedImage.getRGB(j, i));
					boolean isOldColor = color.getRGB() < noiseColor.getRGB();
					if(isOldColor){
						image.setRGB(j, i, color.getRGB());
					}else{
						image.setRGB(j, i, noiseColor.getRGB());
					} 
						
					}
				}
			}
			return image;
		}
		
	}

}