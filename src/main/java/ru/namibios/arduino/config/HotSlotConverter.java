package ru.namibios.arduino.config;

import org.aeonbits.owner.Converter;
import ru.namibios.arduino.model.HotSlot;

import java.lang.reflect.Method;

public class HotSlotConverter implements Converter<HotSlot> {

    @Override
    public HotSlot convert(Method method, String input) {

        HotSlot hotSlot = new HotSlot();

        String[] split = input.replaceAll("\\s", "").split(",");
        if(split.length == 4){

            hotSlot.setActive(Boolean.valueOf(split[0]));
            hotSlot.setKey(split[1]);
            hotSlot.setDelay(split[2]);
            hotSlot.setPeriod(split[3]);
        }

        return hotSlot;
    }

}
