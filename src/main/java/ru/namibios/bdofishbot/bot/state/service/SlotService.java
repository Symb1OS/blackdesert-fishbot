package ru.namibios.bdofishbot.bot.state.service;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Slot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SlotService {

    private static final Logger LOG = Logger.getLogger(SlotService.class);

    private List<Slot> slotList;

    public SlotService(List<Slot> slots) {
        this.slotList = new ArrayList<>(slots);
    }

    public void info(){

        Optional<Slot> optional = slotList.stream()
                .filter(Slot::isActive)
                .max(Comparator.comparingLong(Slot::getReadyTime));

        if (optional.isPresent()) {
            LOG.info(optional.get().info());
        } else {
            LOG.info("No active slot/task");
        }

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

    public boolean isActiveTasks() {
        return slotList.stream().anyMatch(Slot::isActive);
    }
}
