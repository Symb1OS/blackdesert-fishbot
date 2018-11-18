package ru.namibios.arduino.config;

import com.fazecast.jSerialComm.SerialPort;
import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;
import ru.namibios.arduino.config.converter.HotSlotConverter;
import ru.namibios.arduino.config.converter.LootTouchConverter;
import ru.namibios.arduino.config.converter.RectangleConverter;
import ru.namibios.arduino.config.converter.SerialPortConverter;
import ru.namibios.arduino.model.HotSlot;
import ru.namibios.arduino.model.Touch;

import java.awt.*;

@Sources("file:resources/application.properties")
public interface ApplicationConfig extends Accessible, Mutable{

	@Key("bot.http")
	@DefaultValue("23.95.61.97:9090")
	String URL_CAPTCHA_SERVICE();

	@Key("bot.port")
	@DefaultValue("")
	String PORT();

	@Key("bot.input.mode")
	@DefaultValue("ROBOT")
    InputMode INPUT_MODE();

	@Key("bot.language")
	@DefaultValue("English")
	String LANGUAGE();

	///////////////////////////////////////////////////////////////////////////
	// RODS
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.rod.count")
	@DefaultValue("0")
	int COUNT_ROD();
	
	@Key("bot.rod.changetime")
	@DefaultValue("180000")
	int TIME_CHANGE_ROD();

	@Key("bot.calendar.skip.time")
	@DefaultValue("150000")
	int TIME_CALENDAR_SKIP();
	
	@Key("bot.rod.x")
	@DefaultValue("1484")
	int ROD_START_X();
	
	@Key("bot.rod.y")
	@DefaultValue("350")
	int ROD_START_Y();
	
	@Key("bot.rod.dx")
	@DefaultValue("54")
	int ROD_DX();
	
	@Key("bot.rod.dy")
	@DefaultValue("0")
	int ROD_DY();
	
	@Key("bot.delay.rod.before")
	@DefaultValue("0")
	int DELAY_BEFORE_CHANGE_ROD();
	
	@Key("bot.delay.rod.after")
	@DefaultValue("15000")
	int DELAY_AFTER_CHANGE_ROD();


	///////////////////////////////////////////////////////////////////////////
	//	CAPTCHA
	///////////////////////////////////////////////////////////////////////////

	@DefaultValue("false")
	@Key("bot.kapcha.stored")
	boolean STORED_CAPTCHA();

	@DefaultValue("50")
	@Key("bot.kapcha.noise.iteration")
	int CNT_KAPCHA();
	
	@DefaultValue("0.88")
	@Key("bot.parsing.coefidentity")
	double COEF_IDENTITY();
	
	@Key("bot.port.serial")
	@DefaultValue("${bot.key}")
	@ConverterClass(SerialPortConverter.class)
	SerialPort physicalPort();
	
	@Key("bot.loot.touch")
	@Separator(";")
	@DefaultValue("{1561,616};{1608,616}")
	@ConverterClass(LootTouchConverter.class)
	Touch[] LOOT_TOUCH();


	///////////////////////////////////////////////////////////////////////////
	// LOOT FILTER
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.loot.fish")
	@DefaultValue("true")
	boolean FISH();
	
	@Key("bot.loot.key")
	@DefaultValue("true")
	boolean KEY();
	
	@Key("bot.loot.rock")
	@DefaultValue("true")
	boolean ROCK();
	
	@Key("bot.loot.event")
	@DefaultValue("true")
	boolean EVENT();
	
	@Key("bot.loot.trash")
	@DefaultValue("false")
	boolean TRASH();
	
	@Key("bot.loot.unknown")
	@DefaultValue("false")
	boolean TAKE_UNKNOWN();
	
	@Key("bot.loot.save_unsort")
	@DefaultValue("true")
	boolean SAVE_UNSORT();


	///////////////////////////////////////////////////////////////////////////
	// TASKS
	///////////////////////////////////////////////////////////////////////////

    @Key("bot.autouse.beer")
    @DefaultValue("true, 7, 0m, 120m")
    @ConverterClass(HotSlotConverter.class)
    HotSlot SLOT_BEER();

    @Key("bot.autouse.beer.touchs")
    @Separator(";")
    @DefaultValue("{1507,847};{1192,494};{1646,844}")
    @ConverterClass(LootTouchConverter.class)
    Touch[] BEER_TOUCHS();

	@Key("bot.autouse.minigame")
	@DefaultValue("false")
	boolean MINIGAME();


	///////////////////////////////////////////////////////////////////////////
	// STATE DELAYS
	///////////////////////////////////////////////////////////////////////////
	
	@Key("bot.delay.start.before")
	@DefaultValue("3000")
	int DELAY_BEFORE_START();
	
	@Key("bot.delay.start.after")
	@DefaultValue("5000")
	int DELAY_AFTER_START();
	
	@Key("bot.delay.waitfish.before")
	@DefaultValue("3000")
	int DELAY_BEFORE_WAIT_FISH();
	
	@Key("bot.delay.waitfish.after")
	@DefaultValue("0")
	int DELAY_AFTER_WAIT_FISH();

	@Key("bot.delay.cutfish.before")
	@DefaultValue("0")
	int DELAY_BEFORE_CUT_FISH();
	
	@Key("bot.delay.cutfish.after")
	@DefaultValue("0")
	int DELAY_AFTER_CUT_FISH();
	
	@Key("bot.delay.statuscut.before")
	@DefaultValue("0")
	int DELAY_BEFORE_STATUS_CUT();
	
	@Key("bot.delay.statuscut.after")
	@DefaultValue("1000")
	int DELAY_AFTER_STATUS_CUT();

	@Key("bot.delay.kapcha.before")
	@DefaultValue("0")
	int DELAY_BEFORE_KAPCHA();
	
	@Key("bot.delay.kapcha.after")
	@DefaultValue("0")
	int DELAY_AFTER_KAPCHA();
	
	@Key("bot.delay.statuskapcha.before")
	@DefaultValue("0")
	int DELAY_BEFORE_STATUS_KAPCHA();
	
	@Key("bot.delay.statuskapcha.after")
	@DefaultValue("0")
	int DELAY_AFTER_STATUS_KAPCHA();

	@Key("bot.delay.filterloot.before")
	@DefaultValue("4000")
	int DELAY_BEFORE_FILTER_LOOT();
	
	@Key("bot.delay.filterloot.after")
	@DefaultValue("0")
	int DELAY_AFTER_FILTER_LOOT();


	///////////////////////////////////////////////////////////////////////////
	// NOTIFICATION
	///////////////////////////////////////////////////////////////////////////
	
	@Key("bot.notification.telegram")
	@DefaultValue("false")
	boolean TELEGRAM();
	
	@Key("bot.notification.telegram.key")
	String TELEGRAM_KEY();


	///////////////////////////////////////////////////////////////////////////
	// SLOTS
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.slot.one")
	@DefaultValue("false, 0, 0s, 91m")
	@ConverterClass(HotSlotConverter.class)
	HotSlot SLOT_ONE();

	@Key("bot.slot.two")
	@DefaultValue("false, 9, 31m, 111m")
	@ConverterClass(HotSlotConverter.class)
	HotSlot SLOT_TWO();

	@Key("bot.slot.three")
	@DefaultValue("false, 8, 62m, 91m")
	@ConverterClass(HotSlotConverter.class)
	HotSlot SLOT_THREE();


	///////////////////////////////////////////////////////////////////////////
	// NOTIFICATION
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.pm.event.coef")
	@DefaultValue("0.1")
	double PM_COEF();
	
	@Key("bot.pm.event.exitgame")
	@DefaultValue("false")
	boolean PM_EXIT_GAME();
	
	@Key("bot.pm.event.autofish")
	@DefaultValue("false")
	boolean PM_AUTOFISH();
	
	@Key("bot.pm.event.nothing")
	@DefaultValue("true")
	boolean PM_NOTHING();


	///////////////////////////////////////////////////////////////////////////
	// SCREEN
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.screen.fullscreen")
	@DefaultValue("0, 0, 1920, 1080")
	@ConverterClass(RectangleConverter.class)
	Rectangle FULL_SCREEN();

	@Key("bot.screen.space")
	@DefaultValue("928, 210, 63, 25")
	@ConverterClass(RectangleConverter.class)
	Rectangle SPACE();

	@Key("bot.screen.space.offset.x")
    @DefaultValue("0")
	int SPACE_OFFSET_X();

    @Key("bot.screen.space.offset.y")
    @DefaultValue("20")
    int SPACE_OFFSET_Y();

	@Key("bot.screen.line")
	@DefaultValue("820, 402, 278, 25")
	@ConverterClass(RectangleConverter.class)
	Rectangle LINE();

	@Key("bot.screen.subline")
	@DefaultValue("997, 402, 10, 25")
	@ConverterClass(RectangleConverter.class)
	Rectangle SUB_LINE();

	@Key("bot.screen.statuscut")
	@DefaultValue("874, 480, 171, 33")
	@ConverterClass(RectangleConverter.class)
	Rectangle STATUS_CUT();

	@Key("bot.screen.captcha")
	@DefaultValue("780, 350, 372, 58")
	@ConverterClass(RectangleConverter.class)
	Rectangle CAPTCHA();

	@Key("bot.screen.statuscaptcha")
	@DefaultValue("760,  485, 390, 105")
	@ConverterClass(RectangleConverter.class)
	Rectangle STATUS_KAPCHA();

	@Key("bot.screen.lootslotone")
	@DefaultValue("1539, 597, 43, 43")
	@ConverterClass(RectangleConverter.class)
	Rectangle LOOT_SLOT_ONE();

	@Key("bot.screen.lootslottwo")
	@DefaultValue("1586, 597, 43, 43")
	@ConverterClass(RectangleConverter.class)
	Rectangle LOOT_SLOT_TWO();

	@Key("bot.screen.chat")
	@DefaultValue("5, 1000, 355, 40")
	@ConverterClass(RectangleConverter.class)
	Rectangle CHAT();
}