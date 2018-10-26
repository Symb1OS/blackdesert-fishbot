package ru.namibios.arduino.model.template;

import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.utils.MatrixUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum StatusKapchaTemplate implements MatrixTemplate{

	SUCCESS("SUCCESS_EU"),

	FAILED("FAILED_EU");
	
	private final List<int[][]> templates;
	
	@Override
	public List<int[][]> getTemplates() {
		return templates;
	}

	StatusKapchaTemplate(String... filenames) {
		this.templates = new ArrayList<>();

		try {

			for (String filename : filenames) {
				List<String> list = Files.lines(Paths.get(Path.STATUS_KAPCHA, filename), StandardCharsets.UTF_8)
						.collect(Collectors.toCollection(ArrayList::new));

				templates.add(MatrixUtils.importTemplate(list));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}