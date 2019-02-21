package ru.namibios.arduino.config.converter;

import org.aeonbits.owner.Converter;
import org.apache.log4j.Logger;
import ru.namibios.arduino.model.HotSlot;

import java.lang.reflect.Method;

public class HotSlotConverter implements Converter<HotSlot> {

    private static final Logger LOG = Logger.getLogger(HotSlotConverter.class);

    private HotSlot getSlot(String [] params){

        HotSlot hotSlot = new HotSlot();

        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setKey(params[1]);
        hotSlot.setDelay(params[2]);
        hotSlot.setPeriod(params[3]);
        hotSlot.setCommand("Slot");

        return hotSlot;
    }

    private HotSlot getSlotTask(String [] params){

        HotSlot hotSlot = new HotSlot();

        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setKey(params[1]);
        hotSlot.setDelay(params[2]);
        hotSlot.setPeriod(params[3]);
        hotSlot.setCommand(params[4]);

        return hotSlot;
    }

    private HotSlot getSmartTask(String [] params){

        HotSlot hotSlot = new HotSlot();

        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setDelay(params[1]);
        hotSlot.setCommand(params[2]);

        return hotSlot;
    }

    private HotSlot getSmartTaskWithRandom(String [] params){

        HotSlot hotSlot = new HotSlot();
        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setDelay(params[1]);
        hotSlot.setRandomDelay(params[2]);
        hotSlot.setPeriod(params[3]);
        hotSlot.setRandomPeriod(params[4]);
        hotSlot.setCommand(params[5]);

        return hotSlot;
    }

    private HotSlot getSlotActiveKey(String [] params){

        HotSlot hotSlot = new HotSlot();
        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setKey(params[1]);
        hotSlot.setCommand("Slot");

        return hotSlot;
    }

    private HotSlot getSmartTaskWithRandomPause(String [] params){

        HotSlot hotSlot = new HotSlot();
        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setDelay(params[1]);
        hotSlot.setRandomDelay(params[2]);
        hotSlot.setPeriod(params[3]);
        hotSlot.setRandomPeriod(params[4]);
        hotSlot.setPauseFrom(params[5]);
        hotSlot.setPauseTo(params[6]);
        hotSlot.setCommand(params[7]);

        return hotSlot;
    }
    @Override
    public HotSlot convert(Method method, String input) {

        HotSlot hotSlot;

        String[] split = input.replaceAll("\\s", "").split(",");
        switch (split.length) {
            case 2: hotSlot = getSlotActiveKey(split); break;
            case 3: hotSlot = getSmartTask(split); break;
            case 4: hotSlot = getSlot(split); break;
            case 5: hotSlot = getSlotTask(split); break;
            case 6: hotSlot = getSmartTaskWithRandom(split); break;
            case 8: hotSlot = getSmartTaskWithRandomPause(split); break;

            default: {
                LOG.error("Unknown constructor. Check: " + input);
                hotSlot = null;
            }
        }

        return hotSlot;
    }

}