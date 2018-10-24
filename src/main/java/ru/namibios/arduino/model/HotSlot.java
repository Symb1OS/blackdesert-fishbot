package ru.namibios.arduino.model;

public class HotSlot {

    private boolean active;

    private String key;

    private long delay;

    private long period;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public void setDelay(String s) {
        this.delay = converterMills(s);
    }

    public void setPeriod(String s) {
        this.period = converterMills(s);
    }

    private long converterMills(String value) {
        long ms = -1;

        // К примеру если указано просто 0
        if (value.length() == 1) {
            return Long.parseLong(value);
        }

        char format = value.charAt(value.length() - 1);
        long unformat = Long.valueOf(value.substring(0, value.indexOf(format)));

        switch (format) {
            case 's' : ms = unformat * 1000;      break;
            case 'm' : ms = unformat * 1000 * 60; break;
            default  : ms = Long.parseLong(value);
        }

        return ms;
    }

    @Override
    public String toString() {
        return "HotSlot{" +
                "active=" + active +
                ", key='" + key + '\'' +
                ", delay=" + delay +
                ", period=" + period +
                '}';
    }

}