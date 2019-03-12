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

    private static final String CHECKS[] = {"http://checkip.amazonaws.com","http://icanhazip.com","http://ipecho.net/plain", "http://ident.me", "http://bot.whatismyipaddress.com"};

    public static String getForwaded() {

        String forwarded = "";

        for (String check : CHECKS) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(check).openStream()))) {
                forwarded = in.readLine();
                if (!forwarded.isEmpty()) {
                    return forwarded;
                }

            } catch (Exception ignore) {

            }
        }

        return forwarded;
    }

}