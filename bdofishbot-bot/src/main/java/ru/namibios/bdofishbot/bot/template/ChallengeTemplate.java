package ru.namibios.bdofishbot.bot.template;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ChallengeTemplate implements MatrixTemplate{

    SKIP("resources/templates/challenge");

    private final Logger LOG = Logger.getLogger(ChallengeTemplate.class);

    private final List<int[][]> templates;

    @Override
    public List<int[][]> getTemplates() {
        return templates;
    }

    ChallengeTemplate(String fileFolderName) {

        this.templates = new ArrayList<>();

        File[] filenames = new File(fileFolderName).listFiles();
        if (filenames != null && filenames.length == 0) {
            LOG.error("Folder is empty: " + fileFolderName);
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
