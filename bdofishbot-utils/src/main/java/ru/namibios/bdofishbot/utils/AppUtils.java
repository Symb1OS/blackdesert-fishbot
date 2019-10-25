package ru.namibios.bdofishbot.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public final class AppUtils {

    public static String getVersion() {

        String version = null;

        try {

            InputStream inputStream =  ClassLoader.getSystemClassLoader().getResourceAsStream("version");

            if (inputStream != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    version =  br.lines().collect(Collectors.joining(System.lineSeparator()));
                }
            }

        } catch (Exception ignore) {

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