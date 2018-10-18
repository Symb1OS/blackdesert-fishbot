package ru.namibios.arduino.model;

public class Timer {

    private static final long NEVER_RUN = -1;

    private long initTime;
    private long timeLastRun;

    private long delay;
    private long period;

    private long readyTime;

    public Timer() {
        this.initTime = System.currentTimeMillis();
    }

    Timer(long delay, long period) {
        this.initTime = System.currentTimeMillis();
        this.timeLastRun = NEVER_RUN;
        this.readyTime = NEVER_RUN;
        this.delay = delay;
        this.period = period;
    }

    private long getWorkTime(long time){
        long current = System.currentTimeMillis();
        return current - time;
    }

    private boolean isNeverRunned(){
        return timeLastRun == NEVER_RUN;
    }

    boolean hasReady(){

        readyTime = isNeverRunned()
                        ? getWorkTime(initTime) - delay
                        : getWorkTime(timeLastRun) - period;

        return readyTime >= 0;
    }

    long getReadyTime(){
        return readyTime;
    }

    void resetTimeLastRun(){
        timeLastRun = System.currentTimeMillis();
    }

    public boolean isOver(long period){
        long current = System.currentTimeMillis();
        long workTime = current - initTime;

        return workTime >= period;
    }

    @Override
    public String toString() {
        return "Timer{" +
                ", delay=" + delay +
                ", period=" + period +
                ", readyTime=" + readyTime +
                '}';
    }
}
