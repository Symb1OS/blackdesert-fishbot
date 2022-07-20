package ru.namibios.bdofishbot.cli.config.converter;

import org.aeonbits.owner.Converter;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.cli.Application;

import java.lang.reflect.Method;

public class ProfileConverter implements Converter<String> {

    private static final Logger LOG = Logger.getLogger(ProfileConverter.class);

    public static final String PROD_PORT = "9443";
    public static final String DEV_PORT  = "9943";

    @Override
    public String convert(Method method, String s) {
        String profile = System.getProperty("profile");
        if (profile == null || profile.isEmpty()) {
                LOG.error("Profile is not set");
                System.exit(Application.CODE_UNDEFINED_PROFILE);
        }

        switch (profile.toUpperCase()) {
            case "PROD":
                LOG.debug("Set port for https: " + PROD_PORT);
                return s.concat(":").concat(PROD_PORT);
            case "DEV":
                LOG.debug("Set port for https: " + DEV_PORT);
                return s.concat(":").concat(DEV_PORT);
            default:
                LOG.error("Undefined port");
                System.exit(Application.CODE_UNDEFINED_PROFILE);
                return null;
        }

    }

}