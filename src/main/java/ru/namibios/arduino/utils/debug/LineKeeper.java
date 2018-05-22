package ru.namibios.arduino.utils.debug;

import java.awt.AWTException;
import java.io.File;
import java.util.stream.Stream;

import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.Screen;

public class LineKeeper {
	
	private long workTime;
	
	public LineKeeper(long time){
		this.workTime = time;
	}
	
	private String getNewFolderName()  {
		long count = 0;
		
		String path = Path.RESOURCES + Path.DEBUG_LINE;
		
		count = Stream.of(new File(path).listFiles())
						.filter(File::isDirectory)
						.count();
		
		File newFolder = new File(path + String.valueOf(count + 1));
		newFolder.mkdir();
		return newFolder.getName();
	}
	
	public void saveImages() throws AWTException  {
		
		String folder = getNewFolderName();
		String imagesFolder = Path.DEBUG_LINE + folder + "/"; 
		
		long start = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - start < workTime) {
			Screen screen = new Screen(Screen.LINE);
			screen.saveImage(imagesFolder);
		}
	}
	
	public static void main(String[] args) throws AWTException {
		LineKeeper keeper = new LineKeeper(3000);
		keeper.saveImages();
		
	}
}