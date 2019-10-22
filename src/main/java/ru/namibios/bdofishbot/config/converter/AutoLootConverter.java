package ru.namibios.bdofishbot.config.converter;

import org.aeonbits.owner.Converter;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AutoLootConverter implements Converter<List<Rectangle>> {

    private static final int IDX_LOOT_COUNT = 6;
    private static final int MIN_CELL_COUNT = 3;
    private static final int MAX_CELL_COUNT = 9;
    private static final int PARAMETER_SIZE = 7;

    @Override
    public List<Rectangle> convert(Method method, String input) {

        String[] split = input.replaceAll("\\s", "").split(",");

        if (split.length != PARAMETER_SIZE) {
            throw new IllegalArgumentException(String.format("Incorrect parameter size. Expected:%s. Actual:%s", 7, split.length));
        }

        int count = Integer.parseInt(split[IDX_LOOT_COUNT]);
        if (count < MIN_CELL_COUNT || count > MAX_CELL_COUNT) {
            throw new IllegalArgumentException(String.format("Incorrect loot count. Actual: %s", count));
        }

        List<Rectangle> rectangles = new ArrayList<>(count);

        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int width = Integer.parseInt(split[2]);
        int height = Integer.parseInt(split[3]);
        int dx = Integer.parseInt(split[4]);
        int dy = Integer.parseInt(split[5]);

        int startX = x;

        for (int i = 0; i < count; i++) {
            rectangles.add(new Rectangle(x, y, width, height));

            x += dx;
            if ((i + 1) % 3 == 0) {
                x = startX;
                y+= dy;
            }
        }

        return rectangles;
    }

}