package ru.namibios.bdofishbot.bot.template;

import ru.namibios.bdofishbot.bot.ImageParser;
import ru.namibios.bdofishbot.bot.Screen;
import ru.namibios.bdofishbot.cli.config.Path;
import ru.namibios.bdofishbot.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CalendarTemplate implements MatrixTemplate{

    REWARD("reward.jpg"),

    CHALLENGE("challenge.jpg");

    private final List<int[][]> templates;

    @Override
    public List<int[][]> getTemplates() {
        return templates;
    }

    CalendarTemplate(String... filenames) {
        this.templates = new ArrayList<>();

        ArrayList<int[][]> collect = Arrays.stream(filenames)
                .map(s -> {
                    BufferedImage image = ImageUtils.read(new File(Path.CLOSE + s));
                    ImageParser parser = new ImageParser(image);
                    parser.parse(Screen.GRAY);
                    return parser.getImageMatrix();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        templates.addAll(collect);
    }

}
