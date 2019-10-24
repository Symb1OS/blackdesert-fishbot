module bdofishbot.bot {

    requires java.desktop;
    requires log4j;
    requires jSerialComm;
    requires httpclient;
    requires httpcore;
    requires httpmime;
    requires jackson.mapper.asl;
    requires jackson.core.asl;
    requires commons.lang3;
    requires jna.platform;
    requires owner;
    requires JTattoo;
    requires spring.messaging;
    requires zip4j;
    requires bdofishbot.utils;

    exports ru.namibios.bdofishbot.bot;
    exports ru.namibios.bdofishbot.bot.state;
    exports ru.namibios.bdofishbot.bot.service;
    exports ru.namibios.bdofishbot.cli;
    exports ru.namibios.bdofishbot.cli.config;
    exports ru.namibios.bdofishbot.cli.config.converter;

    opens ru.namibios.bdofishbot.cli.config to jackson.mapper.asl;

}