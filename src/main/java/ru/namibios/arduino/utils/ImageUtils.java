package ru.namibios.arduino.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

	public static URL toUrl(String filename) {

		try {

			return new File(filename).toURI().toURL();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static void toImage(String fileFrom, String fileImageTo){

		try {

			List<String> lines = Files.readAllLines(Paths.get(fileFrom));

			int row = lines.size();
			int column = lines.get(0).toCharArray().length;

			int[] pixels = new int[row * column];

			int count = 0;
			for (String s : lines) {

				char[] chars = s.toCharArray();
				for (char symbol : chars) {
					switch (symbol) {
						case '0':
							pixels[count] = Color.BLACK.getRGB();
							break;
						case '1':
							pixels[count] = Color.WHITE.getRGB();
							break;
					}
					count++;
				}

			}

			BufferedImage pixelImage = new BufferedImage(column, row, BufferedImage.TYPE_INT_RGB);
			pixelImage.setRGB(0, 0, column, row, pixels, 0, column);
			ImageIO.write(pixelImage, "JPG", new File(fileImageTo));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		toImage("resources/templates/space/SPACE_RU", "resources/SPACE_RU.jpg");
		toImage("resources/templates/space/SPACE_EU", "resources/SPACE_EU.jpg");
		toImage("resources/templates/space/LINE", "resources/LINE.jpg");

		toImage("resources/templates/statuscut/BAD_EN", "resources/BAD_EN.jpg");
		toImage("resources/templates/statuscut/BAD_RU", "resources/BAD_RU.jpg");
		toImage("resources/templates/statuscut/GOOD_EN", "resources/GOOD_EN.jpg");
		toImage("resources/templates/statuscut/GOOD_RU", "resources/GOOD_RU.jpg");
		toImage("resources/templates/statuscut/PERFECT_EN", "resources/PERFECT_EN.jpg");
		toImage("resources/templates/statuscut/PERFECT_RU", "resources/PERFECT_RU.jpg");

		toImage("resources/templates/statuskapcha/FAILED_EU", "resources/FAILED_EU.jpg");
		toImage("resources/templates/statuskapcha/FAILED_RU", "resources/FAILED_RU.jpg");
		toImage("resources/templates/statuskapcha/SUCCESS_EU", "resources/SUCCESS_EU.jpg");
		toImage("resources/templates/statuskapcha/SUCCESS_RU", "resources/SUCCESS_RU.jpg");

	}

}