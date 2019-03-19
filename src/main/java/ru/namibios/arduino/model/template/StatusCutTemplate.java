package ru.namibios.arduino.model.template;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.MatrixUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StatusCutTemplate implements MatrixTemplate{

	PERFECT("resources/templates/statuscut/perfect"),

	GOOD("resources/templates/statuscut/good"),

	BAD("resources/templates/statuscut/bad");

	private final List<int[][]> templates;

	private final Logger LOG = Logger.getLogger(StatusCutTemplate.class);

	@Override
	public List<int[][]> getTemplates() {
		return templates;
	}

	StatusCutTemplate(String fileFolderName) {
		this.templates = new ArrayList<>();

        File[] filenames = new File(fileFolderName).listFiles();
        if (filenames != null && filenames.length == 0) {
            LOG.error("Folder is empty: " + fileFolderName);
            Application.closeBot(Application.CODE_EMPTY_STATUS_CUT);
        }

        try {

            Arrays.stream(filenames)
                    .map(ImageParser::mapImageMatrix)
                    .forEach(templates::add);

        } catch (Exception e) {
            LOG.error(ExceptionUtils.getString(e));
            Application.closeBot(Application.CODE_EMPTY_STATUS_CUT);
        }

	}

    public static void main(String[] args) {
        StatusCutTemplate a = BAD;
        List<int[][]> templates = a.getTemplates();
        for (int[][] template : templates) {
            MatrixUtils.printMatrix(template, "0");
        }
    }

}