package ru.namibios.bdofishbot.bot.service.input;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Touch;
import ru.namibios.bdofishbot.bot.command.Command;
import ru.namibios.bdofishbot.bot.command.ShortCommand;
import ru.namibios.bdofishbot.bot.service.RodService;
import ru.namibios.bdofishbot.bot.service.input.emulation.AWTRobot;
import ru.namibios.bdofishbot.bot.service.input.emulation.AbstractEmulationInput;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.utils.DelayUtils;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class EmulationService implements InputService{

    private static final Logger LOG = Logger.getLogger(EmulationService.class);

    private static final String TOUCH_MATCHER = "\\[{1}[0-9]{1,}[,]{1}[0-9]{1,}\\]{1}";
    private static final String SLOT_MATCHER = "[a-zA-Z]{4}\\[{1}[0-9]{1}\\]{1}";

    private static final int ONE_SECONDS = 1000;
    private static final int TWO_SECONDS = 2000;
    private static final String CONFIRM_FLAG = "!";

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
        DelayUtils.delay(2000);
        clickByIndex(command.replace(ShortCommand.ROD.getKey(), ""));
        DelayUtils.delay(1500);
        emulationInput.sendInput(KeyEvent.VK_I);
    }

    private void clickSequence(Touch[] touches){
        for (Touch touch : touches) {
            if (touch.isActive()) {
                LOG.debug(touch);
                emulationInput.clickLeft(touch.getX(),touch.getY());
                DelayUtils.delay(Application.getInstance().MOUSE_CLICK_SEQUENCE_DELAY());
            }
        }
    }

    private static void testChangeRods() throws IOException {

        InputService emulationService = new EmulationService(new AWTRobot());
        RodService rodService = new RodService(8);

        while (rodService.hasNext()) {
            DelayUtils.delay(1000);
            emulationService.send(() -> rodService.getNext());
        }
    }

    private void beer(String command){

        useSlot(command);
        DelayUtils.delay(ONE_SECONDS);

        clickSequence(Application.getInstance().BEER_TOUCHS());

        useSlot(command);

    }

    public static void main(String[] args) throws IOException {
        EmulationService emulationService = new EmulationService(new AWTRobot());
        emulationService.takeLootByIndex("Loot[1608,616]Loot[1655,616]!");

    }

    private void takeLootByIndex(String command){
        emulationInput.sendInput(KeyEvent.VK_CONTROL);
        for (String touch : command.split(ShortCommand.LOOT.getKey())) {

            LOG.debug("Loot" + touch + "");
            if (touch.isEmpty()) continue;

            boolean isConfirm = touch.endsWith("!");
            touch = touch.replaceAll(CONFIRM_FLAG, "");

            clickByIndex(touch);
            DelayUtils.delay(TWO_SECONDS);

            if (isConfirm) {
                LOG.debug("Loot confirm..");
                emulationInput.sendInput(KeyEvent.VK_F);
                DelayUtils.delay(TWO_SECONDS);

                emulationInput.sendInput(KeyEvent.VK_ENTER);
                DelayUtils.delay(TWO_SECONDS);
            }

        }
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

    private void exit() {
        LOG.info("Exit game");

        emulationInput.sendInput(KeyEvent.VK_ESCAPE);
        DelayUtils.delay(200);

        clickSequence(Application.getInstance().EXIT_TOUCHS());
    }

    private void skipCalendar() {
        LOG.info("Close pop-up windows");

        emulationInput.sendInput(KeyEvent.VK_ESCAPE);
        DelayUtils.delay(500);
        emulationInput.sendInput(KeyEvent.VK_ESCAPE);

    }

    private void inventory(){
        LOG.info("Open/close inventory");
        emulationInput.sendInput(KeyEvent.VK_I);
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
        } else if (key.startsWith(ShortCommand.BEER.getKey())) {
            beer(key);
        } else if (key.startsWith(ShortCommand.SLOT.getKey())) {
            useSlot(key);
        } else if (key.startsWith(ShortCommand.TAKE.getKey())) {
            takeAll();
        } else if (key.startsWith(ShortCommand.SKIP_CALENDAR.getKey())) {
            skipCalendar();
        } else if (key.startsWith(ShortCommand.INVENTORY.getKey())) {
            inventory();
        } else if (key.startsWith(ShortCommand.EXIT.getKey())) {
            exit();
        } else {
            inputCaptcha(key);
        }

        return true;
    }

}