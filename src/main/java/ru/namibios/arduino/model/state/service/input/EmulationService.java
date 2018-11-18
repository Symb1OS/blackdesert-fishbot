package ru.namibios.arduino.model.state.service.input;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Touch;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.ShortCommand;
import ru.namibios.arduino.model.state.service.input.emulation.AbstractEmulationInput;
import ru.namibios.arduino.utils.DelayUtils;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class EmulationService implements InputService{

    private static final Logger LOG = Logger.getLogger(EmulationService.class);

    private static final String TOUCH_MATCHER = "[a-zA-Z]{3,}\\[{1}[0-9]{1,}[,]{1}[0-9]{1,}\\]{1}";
    private static final String SLOT_MATCHER = "[a-zA-Z]{4}\\[{1}[0-9]{1}\\]{1}";

    private AbstractEmulationInput emulationInput;

    public EmulationService(AbstractEmulationInput emulationInput) {
        this.emulationInput = emulationInput;
    }

    private void inputCaptcha(String  captcha){
        LOG.info("Input captcha " + captcha);
        emulationInput.sendInput(captcha);
    }

    private void inputSpace(){
        LOG.info("Input space");
        emulationInput.sendInput(KeyEvent.VK_SPACE);
    }

    private void useSlot(String command){

        command = command.replaceAll("\\s", "");
        if (!command.matches(SLOT_MATCHER)) {
            LOG.error("Unparsible command: " + command);
            return;
        }

        int hotKey = Integer.valueOf(command.substring(command.indexOf("[") + 1, command.indexOf("]")));

        LOG.debug("HotKey: " + hotKey);

        emulationInput.sendInput(Character.forDigit(hotKey, 16));
    }

    private void changeRod(String command){
        emulationInput.sendInput(KeyEvent.VK_I);
        clickByIndex(command);
        DelayUtils.delay(1000);
        emulationInput.sendInput(KeyEvent.VK_I);
    }

    private void beer(String command){

        useSlot(command);
        DelayUtils.delay(2000);

        for (Touch touch : Application.getInstance().BEER_TOUCHS()) {
            emulationInput.clickLeft(touch.getX(),touch.getY());
            DelayUtils.delay(2000);
        }

        emulationInput.sendInput(KeyEvent.VK_ESCAPE);
    }

    private void takeLootByIndex(String command){
        emulationInput.sendInput(KeyEvent.VK_CONTROL);
        clickByIndex(command);
        emulationInput.sendInput(KeyEvent.VK_CONTROL);
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

        emulationInput.clickRight(x, y);
    }

    private void takeAll() {
        LOG.info("Take all loot");
        emulationInput.sendInput(KeyEvent.VK_R);
    }


    private void skipCalendar() {
        LOG.info("Close pop-up windows");

        emulationInput.sendInput(KeyEvent.VK_ESCAPE);
        DelayUtils.delay(200);
        emulationInput.sendInput(KeyEvent.VK_ESCAPE);

    }

    @Override
    public boolean send(Command command) throws IOException {

        String key = command.getKey();

        if (key.isEmpty()) {
            LOG.debug("Empty command");
            return false;
        }

        LOG.info("Parsing command: " + key);

        if (key.startsWith(ShortCommand.SPACE.getKey())) {
            inputSpace();
        } else if (key.startsWith(ShortCommand.LOOT.getKey())) {
            takeLootByIndex(key);
        } else if (key.startsWith(ShortCommand.ROD.getKey())) {
            changeRod(key);
        } else if (key.startsWith(ShortCommand.SLOT.getKey())) {
            useSlot(key);
        } else if (key.startsWith(ShortCommand.TAKE.getKey())) {
            takeAll();
        } else if (key.startsWith(ShortCommand.SKIP_CALENDAR.getKey())) {
            skipCalendar();
        }else {
            inputCaptcha(key);
        }

        return true;
    }

}