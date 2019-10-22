package ru.namibios.bdofishbot.bot;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.config.Application;
import ru.namibios.bdofishbot.config.Path;
import ru.namibios.bdofishbot.utils.DateUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	public Screen(String filename, boolean isGray) throws IOException{
		this.screenShot = ImageIO.read(new File(filename));
		if (isGray) {
			makeGray();
		}
	}

	public Screen(String filename, Rectangle zone) throws IOException{
		this.screenShot = ImageIO.read(new File(filename));
		this.screenShot = getScreenZone(zone);
		makeGray();
	}

	public Screen(String filename, Rectangle zone, boolean isGray) throws IOException{
		this.screenShot = ImageIO.read(new File(filename));
		this.screenShot = getScreenZone(zone);
		if (isGray) {
			makeGray();
		}

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

    public Screen(BufferedImage screenShot) throws AWTException {
        this.screenShot = screenShot;
    }

	public void toZone(Rectangle zone) {
		this.screenShot = screenShot.getSubimage(zone.x, zone.y, zone.width, zone.height);
	}

	public BufferedImage getScreenZone(Rectangle zone) {
		return screenShot.getSubimage(zone.x, zone.y, zone.width, zone.height);
	}

	public static BufferedImage getScreen(Rectangle zone){

		try {

			Robot robot = new Robot();
			BufferedImage screenCapture = robot.createScreenCapture(zone);

			return screenCapture;

		} catch (AWTException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void clearNoise(int iteration) throws AWTException {
		logger.info("Cleaning captcha...");
		int cnt = 0;
		while(cnt < iteration){
			BufferedImage noiseImage = new Screen(Application.getInstance().CAPTCHA()).getScreenShot();
			noise.addNoise(noiseImage);
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

			if (!Files.exists(Paths.get(Path.DEBUG))) {
				Files.createDirectory(Paths.get(Path.DEBUG));
			}

			ImageIO.write(screenShot, "jpg", new File(Path.DEBUG + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));

		} catch (IOException e) {
			logger.error("Exception " + e);
		}
	}

	private void saveToStore(String name, String folder){
		if (Application.getInstance().STORED_CAPTCHA()){
			try {
				ImageIO.write(screenShot, "jpg", new File(folder + name + ".jpg"));
			} catch (IOException e) {
				logger.error("Exception " + e);
			}
		}
	}

	public void saveDirty(String name){
		saveToStore(name, Path.STORE_CAPTCHA_DIRTY);
	}

	public void saveClean(String name){
		saveToStore(name, Path.STORE_CAPTCHA_CLEAN);
	}

	public void saveImage(String folder) {
		
		try {

			String filename = Path.RESOURCES + folder + "/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg";
			File file = new File(filename);
            file.mkdirs();

			ImageIO.write(screenShot, "jpg", file);

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
	
private class Noise {
		
		private BufferedImage image;
		
		private int width;
		
		private int height;
		
		private List<BufferedImage> noises;
		
		Noise(BufferedImage image) {
			this.image = image;
			this.height = this.image.getHeight();
			this.width = this.image.getWidth();
			this.noises = new ArrayList<>();
			
		}
		
		void addNoise(BufferedImage image){
			this.noises.add(image);
		}
		
		BufferedImage clear(){
			
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