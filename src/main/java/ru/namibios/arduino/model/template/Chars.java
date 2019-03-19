package ru.namibios.arduino.model.template;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Chars implements MatrixTemplate{

	space("resources/templates/space");
	
	private final List<int[][]> templates;

	private final Logger LOG = Logger.getLogger(Chars.class);
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	Chars(String fileFolderName) {

		this.templates = new ArrayList<>();

		File[] filenames = new File(fileFolderName).listFiles();
		if (filenames != null && filenames.length == 0) {
			LOG.error("Folder is empty: " + fileFolderName);
			Application.closeBot(Application.CODE_EMPTY_CHARS);
		}

		Arrays.stream(filenames)
				.map(ImageParser::mapImageMatrix)
				.forEach(templates::add);
	}

}