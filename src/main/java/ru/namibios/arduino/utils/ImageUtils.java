package ru.namibios.arduino.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public final class ImageUtils {
	
	private ImageUtils(){}
	
	public static BufferedImage read(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return image;
	}
	
	public static byte[] imageToBytes(BufferedImage image) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes = null;
		
		try {
			
			ImageIO.write( image, "jpg", baos );
			baos.flush();
			bytes = baos.toByteArray();
			baos.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
		
	}
}
