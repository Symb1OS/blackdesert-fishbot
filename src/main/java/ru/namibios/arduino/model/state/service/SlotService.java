package ru.namibios.arduino.model.state.service;

import ru.namibios.arduino.model.Slot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SlotService {

    private List<Slot> slotList;

    public SlotService(List<Slot> slots) {
        this.slotList = new ArrayList<>(slots);
    }

    public boolean isReady(){
        long count = slotList.stream()
                .filter(Slot::isNeedUse)
                .count();

        return count > 0;
    }

    public String getKey(){
        Slot firstReady = getFirstReady();
        return firstReady.getKey();
    }

    private Slot getFirstReady(){
        return slotList.stream()
                .max(Comparator.comparingLong(Slot::getReadyTime)).get();
    }

}
