package ru.namibios.arduino.config.converter;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import static org.junit.Assert.assertEquals;

public class UiThemeConverterTest {

    private UiThemeConverter uiThemeConverter = new UiThemeConverter();

    @Test
    public void testDefaultTheme() {
        String convert = uiThemeConverter.convert(null, "");
        assertEquals(UIManager.getSystemLookAndFeelClassName(), convert);

    }

    @Test
    public void testDarkTheme() {
        String convert = uiThemeConverter.convert(null, "DARK");
        assertEquals(HiFiLookAndFeel.class.getName(), convert);
    }

    @Test
    public void testNumnusTheme() {
        String convert = uiThemeConverter.convert(null, "NIMBUS");
        assertEquals(NimbusLookAndFeel.class.getName(), convert);
    }
}