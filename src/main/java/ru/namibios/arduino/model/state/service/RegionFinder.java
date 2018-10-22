package ru.namibios.arduino.model.state.service;

import ru.namibios.arduino.model.command.Command;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegionFinder {

    private Rectangle rectangle;

    private int offsetX;
    private int offsety;

    public RegionFinder(Rectangle rectangle, int offsetx, int offsety) {
        this.rectangle = rectangle;
        this.offsetX = offsetx;
        this.offsety = offsety;
    }

    public Command getKey(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        int startX = rectangle.x;
        int startY = rectangle.y;

        Constructor<?> constructor = clazz.getConstructor(Rectangle.class);

        for (int x = startX; x <= startX + offsetX; x++) {
            rectangle.x = x;
            for (int y = startY; y <= startY + offsety; y++) {
                rectangle.y = y;

                Object instance = constructor.newInstance(rectangle);
                Method method = instance.getClass().getMethod("getKey");
                String key = (String) method.invoke(instance);

                if (!key.equals("")) {
                    return () -> key;
                }

            }
        }

        return () -> "";
    }

}
