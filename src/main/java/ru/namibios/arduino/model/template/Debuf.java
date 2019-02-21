package ru.namibios.arduino.model.template;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Debuf implements MatrixTemplate{

    DAY("resources/templates/debuff/day.jpg"),

    NIGHT("resources/templates/debuff/night.jpg");

    private final List<int[][]> templates;

    @Override
    public List<int[][]> getTemplates() {
        return templates;
    }

    Debuf(String... filenames) {
        this.templates = new ArrayList<>();

        ArrayList<int[][]> collect = Arrays.stream(filenames)
                .map(s -> {
                    BufferedImage image = ImageUtils.read(new File(s));
                    ImageParser parser = new ImageParser(image);
                    parser.parse(Screen.GRAY);
                    return parser.getImageMatrix();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        templates.addAll(collect);

    }

}