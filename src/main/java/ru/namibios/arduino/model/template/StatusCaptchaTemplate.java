package ru.namibios.arduino.model.template;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.utils.ExceptionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StatusCaptchaTemplate implements MatrixTemplate{

    SUCCESS("resources/templates/statuskapcha/success"),

    FAILED("resources/templates/statuskapcha/failed");
	
	private final List<int[][]> templates;

	private final Logger LOG = Logger.getLogger(StatusCaptchaTemplate.class);

	@Override
	public List<int[][]> getTemplates() {
		return templates;
	}

	StatusCaptchaTemplate(String fileFolderName) {
		this.templates = new ArrayList<>();

        File[] filenames = new File(fileFolderName).listFiles();
        if (filenames != null && filenames.length == 0) {
            LOG.error("Folder is empty: " + fileFolderName);
            Application.closeBot(Application.CODE_INIT_STATUS_CAPTCHA);
        }

		try {

            Arrays.stream(filenames)
                    .map(ImageParser::mapImageMatrix)
                    .forEach(templates::add);

		} catch (Exception e) {
			LOG.error(ExceptionUtils.getString(e));
			Application.closeBot(Application.CODE_INIT_STATUS_CAPTCHA);
		}

	}
}