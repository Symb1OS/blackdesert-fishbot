package ru.namibios.arduino.model;

public class TimeService {

    private long timeStart;

    public TimeService() {
        timeStart = System.currentTimeMillis();
    }

    public boolean isOver(long period){
        long current = System.currentTimeMillis();
        long workTime = current - timeStart;

        return workTime >= period;
    }

}
