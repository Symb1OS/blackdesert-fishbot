package ru.namibios.arduino.model.template;

import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.PaletteParser;
import ru.namibios.arduino.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Debuf implements MatrixTemplate{

    DAY("day.jpg"),

    NIGHT("night.jpg");

    private final List<int[][]> templates;

    @Override
    public List<int[][]> getTemplates() {
        return templates;
    }

    Debuf(String... filenames) {
        this.templates = new ArrayList<>();

        ArrayList<int[][]> collect = Arrays.stream(filenames)
                .map(s -> {
                    BufferedImage image = ImageUtils.read(new File(Path.DEBUF + s));
                    PaletteParser parser = new PaletteParser(image);
                    parser.parse();
                    return  parser.getImageMatrix();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        templates.addAll(collect);

    }

}