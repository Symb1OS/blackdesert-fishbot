package ru.namibios.bdofishbot.config.converter;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import org.aeonbits.owner.Converter;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UiThemeConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(UiThemeConverter.class);

    public static final Map<String, String> THEMES = new HashMap<>();

    static {
        THEMES.put("DARK", HiFiLookAndFeel.class.getName());
        THEMES.put("NIMBUS", NimbusLookAndFeel.class.getName());
    }

    @Override
    public String convert(Method method, String input) {

        LOG.debug("Theme: " + input);

        String theme = THEMES.get(input);
        if (theme == null) {
            theme = UIManager.getSystemLookAndFeelClassName();
        }

        LOG.debug("Use " + theme);

        return  theme;

    }

    public static String unconvert(String value){
        for (Map.Entry<String, String> entry : THEMES.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }

        return "";
    }

}
