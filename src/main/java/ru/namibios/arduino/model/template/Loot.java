package ru.namibios.arduino.model.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.utils.ImageUtils;

public enum Loot implements MatrixTemplate{
	
	SCALA("resources/loot/ok/scala"),
	
	KEY("resources/loot/ok/key"),
	
	FISH("resources/loot/ok/fish"),
	
	TRASH("resources/loot/trash"),
	
	EVENT("resources/loot/ok/event"),
	
	EMPTY("resources/loot/ok/empty");
	
	private final List<int[][]> templates;
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	private Loot(String fileFolderName){
		this.templates = new ArrayList<int[][]>();
		
		Stream.of(new File(fileFolderName).listFiles())
				.map(ImageUtils::read)
				.forEach((image) -> {
					ImageParser parser = new ImageParser(image);
					parser.parse(Screen.GRAY);
					
					templates.add(parser.getImageMatrix());
				});
	}
	
}