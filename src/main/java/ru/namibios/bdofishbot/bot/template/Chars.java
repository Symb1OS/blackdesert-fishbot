package ru.namibios.bdofishbot.bot.template;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.config.Application;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

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
			Application.closeBot(Application.CODE_INIT_CHARS);
		}

		try {

            Arrays.stream(filenames)
                    .map(ImageParser::mapImageMatrix)
                    .forEach(templates::add);

        } catch (Exception e) {
            LOG.error(ExceptionUtils.getString(e));
            Application.closeBot(Application.CODE_INIT_CHARS);
        }

	}

}