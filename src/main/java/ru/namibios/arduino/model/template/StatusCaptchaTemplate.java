package ru.namibios.arduino.model.template;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.utils.MatrixUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum StatusCaptchaTemplate implements MatrixTemplate{

	SUCCESS("SUCCESS_EU", "SUCCESS_RU"),

	FAILED("FAILED_EU", "FAILED_RU");
	
	private final List<int[][]> templates;

	private final Logger LOG = Logger.getLogger(StatusCaptchaTemplate.class);
	
	@Override
	public List<int[][]> getTemplates() {
		return templates;
	}

	StatusCaptchaTemplate(String... filenames) {
		this.templates = new ArrayList<>();

		try {

			for (String filename : filenames) {
				List<String> list = Files.lines(Paths.get(Path.STATUS_KAPCHA, filename), StandardCharsets.UTF_8)
						.collect(Collectors.toCollection(ArrayList::new));

				if(list.isEmpty()){
					LOG.error("Empty template, please check " + filename);
					Application.closeBot(Application.CODE_EMPTY_STATUS_CAPTCHA);
				}

				templates.add(MatrixUtils.importTemplate(list));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}