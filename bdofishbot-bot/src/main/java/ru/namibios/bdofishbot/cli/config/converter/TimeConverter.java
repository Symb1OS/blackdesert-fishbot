package ru.namibios.bdofishbot.cli.config.converter;

public class TimeConverter {

    private static final long SECOND = 1000;
    private static final long MINUTE = 1000 * 60;
    private long value;

    public TimeConverter(long value) {
        this.value = value;
    }

    public TimeConverter(String value) {
        this.value = Long.parseLong(value);
    }

    public String getValue(){
        if (value < SECOND) {
            return String.valueOf(value) + " ms";
        }

        if (value < MINUTE) {
            return value / SECOND + " second";
        }

        int n = (int) (value / MINUTE);
        long mod = value % MINUTE;

        long s = mod / SECOND;

        return n + " minute " + s + " second";

    }
}
