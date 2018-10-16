package ru.namibios.arduino.config;

import org.aeonbits.owner.Converter;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RectangleConverter implements Converter<Rectangle> {

    @Override
    public Rectangle convert(Method method, String input) {

        int[] array = Arrays.stream(input.replaceAll("\\s","")
                                         .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        if(array.length == 4){
            return new Rectangle(array[0], array[1], array[2], array[3]);
        }

        return null;
    }
}
