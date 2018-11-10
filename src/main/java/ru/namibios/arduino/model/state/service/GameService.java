package ru.namibios.arduino.model.state.service;

import org.apache.log4j.Logger;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.model.state.service.sender.AbstractSender;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.event.KeyEvent;

public class GameService {

    private static final Logger LOG = Logger.getLogger(GameService.class);

    private static final String TOUCH_MATCHER = "[a-zA-Z]{3,}\\[{1}[0-9]{1,}[,]{1}[0-9]{1,}\\]{1}";
    private static final String SLOT_MATCHER = "[a-zA-Z]{4}\\[{1}[0-9]{1}\\]{1}";

    private AbstractSender sender;

    public GameService(AbstractSender sender) {
        this.sender = sender;
    }

    private void inputCaptcha(String  captcha){
        LOG.info("Input captcha " + captcha);
        sender.sendInput(captcha);
    }

    private void inputSpace(){
        LOG.info("Input space");
        sender.sendInput(KeyEvent.VK_SPACE);
    }

    private void useSlot(String command){

        command = command.replaceAll("\\s", "");
        if (!command.matches(SLOT_MATCHER)) {
            LOG.error("Unparsible command: " + command);
            return;
        }

        int hotKey = Integer.valueOf(command.substring(command.indexOf("[") + 1, command.indexOf("]")));

        LOG.debug("HotKey: " + hotKey);

        sender.sendInput(Character.forDigit(hotKey, 16));
    }

    private void changeRod(String command){
        sender.sendInput(KeyEvent.VK_I);
        clickByIndex(command);
        DelayUtils.delay(1000);
        sender.sendInput(KeyEvent.VK_I);
    }

    private void takeLootByIndex(String command){
        sender.sendInput(KeyEvent.VK_CONTROL);
        clickByIndex(command);
        sender.sendInput(KeyEvent.VK_CONTROL);
    }

    private void clickByIndex(String command){

        command = command.replaceAll("\\s", "");
        if(!command.matches(TOUCH_MATCHER)){
            LOG.error("Unparsible command: " + command);
            return;
        }

        int x = Integer.valueOf(command.substring(command.indexOf("[") + 1, command.indexOf(",")));
        int y = Integer.valueOf(command.substring(command.indexOf(",") + 1, command.indexOf("]")));

        LOG.debug("x: " + x + "; y: " + y);

        sender.clickRight(x, y);
    }

    private void takeAll() {
        LOG.info("Take all loot");
        sender.sendInput(KeyEvent.VK_R);
    }

    public boolean parseCommand(String command) {

        if (command.isEmpty()) {
            LOG.debug("Empty command");
            return false;
        }

        LOG.info("Parsing command: " + command);

        if (command.startsWith(ShortCommand.SPACE.getKey())) {
            inputSpace();
        } else if (command.startsWith(ShortCommand.LOOT.getKey())) {
            takeLootByIndex(command);
        } else if (command.startsWith(ShortCommand.ROD.getKey())) {
            changeRod(command);
        } else if (command.startsWith(ShortCommand.SLOT.getKey())) {
            useSlot(command);
        } else if (command.startsWith(ShortCommand.TAKE.getKey())) {
            takeAll();
        } else {
            inputCaptcha(command);
        }

        return true;
    }

}