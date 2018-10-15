package ru.namibios.arduino.model.template;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.status.StatusCut;
import ru.namibios.arduino.utils.MatrixUtils;

public enum StatusCutTemplate implements MatrixTemplate{
	
	PERFECT("PERFECT_RU","PERFECT_EN"),
	
	GOOD("GOOD_RU", "GOOD_EN"),
	
	BAD("BAD_RU", "BAD_EN");

	private final List<int[][]> templates;
	
	@Override
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	StatusCutTemplate(String... filenames) {
		this.templates = new ArrayList<>();

		try {

            for (String filename : filenames) {
                List<String> list = Files.lines(Paths.get(Path.STATUS_CUT, filename), StandardCharsets.UTF_8)
                        .collect(Collectors.toCollection(ArrayList::new));

                templates.add(MatrixUtils.importTemplate(list));
            }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}