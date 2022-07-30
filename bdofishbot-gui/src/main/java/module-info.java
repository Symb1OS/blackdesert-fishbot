module bdofishbot.gui {

    requires java.desktop;
    requires java.logging;
    requires java.sql;
    requires log4j;
    requires jnativehook;
    requires jSerialComm;
    requires java.gui.forms.rt;
    requires spring.websocket;
    requires spring.messaging;

    requires bdofishbot.utils;
    requires bdofishbot.bot;


}