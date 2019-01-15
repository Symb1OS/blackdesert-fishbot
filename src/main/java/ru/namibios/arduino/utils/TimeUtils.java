package ru.namibios.arduino.utils;

import java.util.concurrent.TimeUnit;

public final class TimeUtils {

    private static final int MINUTE = 1000 * 60;

    private TimeUtils(){}

    public static long getTime(long ms, TimeUnit timeUnit) {

        switch (timeUnit) {
            case SECONDS:
                return ms / 1000;
            case MINUTES:
                return ms / 1000 / 60;
            case HOURS:
                return ms / 1000 / 60 / 60;
            default:
                return ms / 1000;
        }
    }

    public static String getStringTime(long ms, TimeUnit timeUnit) {

        switch (timeUnit) {
            case SECONDS:
                return ms / 1000 + " second";
            case MINUTES:
                return ms / 1000 / 60 + " minute";
            default:
                return ms / 1000 + " second";
        }
    }

    public static String getStringTime(long ms) {
        return ms <= MINUTE
                ? getStringTime(ms, TimeUnit.SECONDS)
                : getStringTime(ms, TimeUnit.MINUTES);
    }
}
