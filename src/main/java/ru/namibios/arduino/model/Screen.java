package ru.namibios.arduino.model;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.utils.DateUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Screen {
	
	private final static Logger logger = Logger.getLogger(Screen.class);

	public static final Color WHITE = new Color(120,120,120);
	public static final Color GRAY = new Color(40,40,40);
	
	private BufferedImage screenShot;
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

	public void toZone(Rectangle zone) throws IOException {
		this.screenShot = screenShot.getSubimage(zone.x, zone.y, zone.width, zone.height);
	}

	public void clearNoise(int iteration) throws AWTException {
		logger.info("Cleaning captcha...");
		int cnt = 0;
		while(cnt < iteration){
			BufferedImage noiseImage = new Screen(Application.getInstance().KAPCHA()).getScreenShot();
			noise.addNois(noiseImage);
			cnt++;
		}
		screenShot = noise.clear();
		logger.info("Cleaning ended...");
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
	
	public String saveImage(String folder) {
		
		try {
			String filename = Path.RESOURCES + folder + "/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg";
			File file = new File(filename);
			ImageIO.write(screenShot, "jpg", file);
			return file.getAbsolutePath();
		} catch (IOException e) {
			logger.error("Exception " + e);
		}
		return null;
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