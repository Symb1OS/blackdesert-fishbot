package ru.namibios.arduino.model.template;

import ru.namibios.arduino.utils.MatrixUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Chars implements MatrixTemplate{

	space("resources/templates/space");
	
	private final List<int[][]> templates;
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	Chars(String fileFolderName) {
		this.templates = new ArrayList<>();

		try {

			File[] filenames = new File(fileFolderName).listFiles();

			for (File filename : filenames) {
				List<String> list = Files.lines(Paths.get(filename.getPath()), StandardCharsets.UTF_8)
						.collect(Collectors.toCollection(ArrayList::new));

				if(list.isEmpty()){
					throw new IOException("Empty template, please check " + filename);
				}

				templates.add(MatrixUtils.importTemplate(list));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}