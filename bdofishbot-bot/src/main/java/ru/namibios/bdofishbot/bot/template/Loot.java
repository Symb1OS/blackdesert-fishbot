package ru.namibios.bdofishbot.bot.template;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum  Loot implements MatrixTemplate {
	
	USEFULL("resources/loot/usefull"),

	CONFIRM("resources/loot/confirm"),

	EXCEPTION("resources/loot/exception"),

	EMPTY("resources/loot/empty");

	private final List<int[][]> templates;

	private final Logger LOG = Logger.getLogger(Loot.class);
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	Loot(String fileFolderName) {
		this.templates = new ArrayList<>();

		File[] files = new File(fileFolderName).listFiles();
		if (files != null && files.length == 0) {
			LOG.error("Folder is empty: " + fileFolderName);
			return;
		}

		for (File file : files) {
			if (file.getName().endsWith(".jpg")) {
				BufferedImage image = ImageUtils.read(file);
				ImageParser parser = new ImageParser(image);
				parser.parse(Screen.GRAY);

				templates.add(parser.getImageMatrix());
			}

		}
	}

	public static String toString(String[] array) {

		StringBuilder sb = new StringBuilder();

		for (String s : array) {
			if (s.equals("-1")){
				sb.append("UNKNOWN,");
				continue;
			}

			Loot value = values()[Integer.parseInt(s)];
			sb.append(value.toString()).append(",");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Loot[] values = Loot.values();
		for (Loot value : values) {
			int size = value.getTemplates().size();
			System.out.println(size);
			System.out.println(value);
		}
	}

}