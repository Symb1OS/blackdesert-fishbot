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

public enum LootFrame implements MatrixTemplate{

    black("black.jpg"),

    green("green.jpg"),

    blue("blue.jpg"),

    gold("gold.jpg"),

    red("red.jpg"),;

    private final List<int[][]> templates;

    public List<int[][]> getTemplates() {
        return templates;
    }

    LootFrame(String... filenames) {
        this.templates = new ArrayList<>();

        ArrayList<int[][]> collect = Arrays.stream(filenames)
                .map(s -> {
                    BufferedImage image = ImageUtils.read(new File(Path.LOOT_FRAMES + s));
                    PaletteParser parser = new PaletteParser(image);
                    parser.parse(PaletteParser.LOOT_FRAME_PALETTE);
                    return parser.getImageMatrix();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        templates.addAll(collect);

    }

}