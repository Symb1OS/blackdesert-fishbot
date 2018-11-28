package ru.namibios.arduino.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class AppUtils {

    public static String getVersion() {

        String version = "";

        try {

            version = Files.lines(Paths.get("version")).findFirst().get();

        } catch (IOException ignore) {
            ignore.printStackTrace();
        }

        return version;
    }

}