package ru.namibios.bdofishbot.bot;

import ru.namibios.bdofishbot.utils.DateUtils;

public class StatsState {

    private long start;
    private long end;

    public StatsState() {
        this.start = DateUtils.now();
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void initEnd() {
        end = DateUtils.now();
    }
}
