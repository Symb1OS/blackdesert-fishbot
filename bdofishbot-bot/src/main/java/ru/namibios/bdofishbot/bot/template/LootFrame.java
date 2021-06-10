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

    GRAY("gray.jpg"),

    GREEN("green.jpg"),

    BLUE("blue.jpg"),

    GOLD("gold.jpg"),

    RED("red.jpg"),;

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

    public static String toString(String[] lootFrames) {
        StringBuilder sb = new StringBuilder();
        for (String frame : lootFrames) {
            LootFrame value = values()[Integer.parseInt(frame)];
            sb.append(value).append(",");
        }
        return sb.toString();
    }

}