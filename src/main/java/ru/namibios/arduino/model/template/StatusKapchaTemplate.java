package ru.namibios.arduino.model.template;

import org.apache.log4j.Logger;
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

	private final Logger LOG = Logger.getLogger(StatusKapchaTemplate.class);
	
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

				if(list.isEmpty()){
					LOG.error("Empty template, please check " + filename);
					System.exit(1);
				}

				templates.add(MatrixUtils.importTemplate(list));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}