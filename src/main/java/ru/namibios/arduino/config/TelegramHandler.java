package ru.namibios.arduino.config;

import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.bot.service.HttpService;
import ru.namibios.arduino.utils.ExceptionUtils;
import ru.namibios.arduino.utils.ImageUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Type;

public class TelegramHandler extends StompSessionHandlerAdapter {

    private static final Logger LOG = Logger.getLogger(TelegramHandler.class);

    private final JButton buttonStart;
    private final JButton buttonStop;

    private HttpService httpService;

    public TelegramHandler(JButton buttonStart, JButton buttonStop) {

        this.httpService = new HttpService();

        this.buttonStart = buttonStart;
        this.buttonStop = buttonStop;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        LOG.debug("New session established : " + session.getSessionId());

        session.subscribe("/hash/topic/reg", this);
        session.subscribe("/hash/topic/commands", this);

        RemoteMessage msg = new RemoteMessage();
        msg.setHash(Application.getUser().getHash());
        msg.setKey(Application.getInstance().TELEGRAM_KEY());

        session.send("/app/tg_bot", msg);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        LOG.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return RemoteMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {

        RemoteMessage msg = (RemoteMessage) payload;

        LOG.debug("Received:" + msg);

        if (msg.getRemoteCommand() != null) {
            switch (msg.getRemoteCommand()) {
                case "SCREEN":
                    LOG.info("Get screenshot..");

                    try {

                        BufferedImage screen = Screen.getScreen(Application.getInstance().FULL_SCREEN());
                        httpService.sendTelegramPhoto(Application.getInstance().TELEGRAM_KEY(), ImageUtils.imageToBytes(screen));

                    } catch (IOException e) {
                        LOG.error(ExceptionUtils.getString(e));
                    }

                    break;

                case "START":
                    LOG.info("Starting fishing bot..");

                    buttonStart.doClick();

                    try {

                        httpService.sendTelegramMessage(Application.getInstance().TELEGRAM_KEY(), "Bot starting..");

                    } catch (IOException e) {
                        LOG.error(ExceptionUtils.getString(e));
                    }

                    break;

                case "STOP":
                    LOG.info("Stopping fishing bot");

                    buttonStop.doClick();

                    try {

                        httpService.sendTelegramMessage(Application.getInstance().TELEGRAM_KEY(), "Bot stopping..");

                    } catch (IOException e) {
                        LOG.error(ExceptionUtils.getString(e));
                    }


                    break;
            }

        }

    }

}

class RemoteMessage {

    private String hash;
    private String key;
    private String remoteCommand;
    private String time;
    private String text;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRemoteCommand() {
        return remoteCommand;
    }

    public void setRemoteCommand(String remoteCommand) {
        this.remoteCommand = remoteCommand;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RemoteMessage{" +
                "hash='" + hash + '\'' +
                ", key='" + key + '\'' +
                ", remoteCommand='" + remoteCommand + '\'' +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}