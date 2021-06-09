package ru.namibios.bdofishbot.bot.template;

import ru.namibios.bdofishbot.bot.PaletteParser;
import ru.namibios.bdofishbot.cli.config.Path;
import ru.namibios.bdofishbot.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ColorChars implements MatrixTemplate{

    space("line.jpg");

    private final List<int[][]> templates;

    public List<int[][]> getTemplates() {
        return templates;
    }

    ColorChars(String... filenames) {
        this.templates = new ArrayList<>();

        ArrayList<int[][]> collect = Arrays.stream(filenames)
                .map(s -> {
                    BufferedImage image = ImageUtils.read(new File(Path.SPACE + s));
                    PaletteParser parser = new PaletteParser(image);
                    parser.parse(PaletteParser.DEFAULT_PALETTE);
                    return parser.getImageMatrix();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        templates.addAll(collect);

    }

}