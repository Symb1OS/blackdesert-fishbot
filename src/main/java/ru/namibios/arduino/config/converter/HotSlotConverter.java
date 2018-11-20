package ru.namibios.arduino.config.converter;

import org.aeonbits.owner.Converter;
import org.apache.log4j.Logger;
import ru.namibios.arduino.model.HotSlot;

import java.lang.reflect.Method;

public class HotSlotConverter implements Converter<HotSlot> {

    private static final Logger LOG = Logger.getLogger(HotSlotConverter.class);

    @Override
    public HotSlot convert(Method method, String input) {

        HotSlot hotSlot = new HotSlot();

        String[] split = input.replaceAll("\\s", "").split(",");
        if (split.length == 4 || split.length == 5 ) {

            hotSlot.setActive(Boolean.valueOf(split[0]));
            hotSlot.setKey(split[1]);
            hotSlot.setDelay(split[2]);
            hotSlot.setPeriod(split[3]);

            String command = split.length == 5 ? split[4] : "Slot";
            hotSlot.setCommand(command);

        } else {
            LOG.error("Expected length: 4-5. Actual: " + split.length);
        }

        return hotSlot;
    }

}
