package ru.namibios.arduino.config.converter;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import org.aeonbits.owner.Converter;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.lang.reflect.Method;

public class UiThemeConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(UiThemeConverter.class);

    @Override
    public String convert(Method method, String input) {

        LOG.debug("Theme: " + input);

        switch (input) {
            case "DARK":
                LOG.debug("Use HiFiLookAndFeel..");
                return HiFiLookAndFeel.class.getName();

            case "NIMBUS":
                LOG.debug("Use NimbusLookAndFeel..");
                return NimbusLookAndFeel.class.getName();

            default:
                LOG.debug("Use default system..");
                return UIManager.getSystemLookAndFeelClassName();
        }
    }

}
