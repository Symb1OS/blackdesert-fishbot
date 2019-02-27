package ru.namibios.arduino.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

    public static String getForwaded() {

        String forwarded = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com").openStream()))) {

            forwarded = in.readLine();

        } catch (Exception ignore) {

        }

        return forwarded;
    }

}