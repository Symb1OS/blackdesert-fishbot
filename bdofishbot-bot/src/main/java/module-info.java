module bdofishbot.bot {

    requires java.desktop;
    requires log4j;
    requires jSerialComm;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpmime;
    requires org.apache.commons.lang3;
    requires com.fasterxml.jackson.databind;
    requires jna.platform;
    requires owner;
    requires JTattoo;
    requires spring.messaging;
    requires zip4j;
    requires bdofishbot.utils;

    exports ru.namibios.bdofishbot.bot;
    exports ru.namibios.bdofishbot.bot.state;
    exports ru.namibios.bdofishbot.bot.template;
    exports ru.namibios.bdofishbot.bot.service;
    exports ru.namibios.bdofishbot.cli;
    exports ru.namibios.bdofishbot.cli.config;
    exports ru.namibios.bdofishbot.cli.config.converter;

    opens ru.namibios.bdofishbot.cli.config to com.fasterxml.jackson.databind;

}