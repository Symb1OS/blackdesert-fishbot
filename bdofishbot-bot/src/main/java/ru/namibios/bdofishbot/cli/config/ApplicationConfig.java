package ru.namibios.bdofishbot.cli.config;

import com.fazecast.jSerialComm.SerialPort;
import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;
import ru.namibios.bdofishbot.bot.HotSlot;
import ru.namibios.bdofishbot.bot.Touch;
import ru.namibios.bdofishbot.cli.config.converter.*;

import java.awt.*;

@Sources("file:resources/application.properties")
public interface ApplicationConfig extends Accessible, Mutable{

	@Key("bot.ui.theme")
	@DefaultValue("NIMBUS")
	@ConverterClass(UiThemeConverter.class)
	String THEME();

	@Key("bot.http")
	@DefaultValue("namibios.ru:9090")
	String URL_SERVER_HTTP();

	@Key("bot.https")
	@DefaultValue("namibios.ru:9443")
	String URL_SERVER_HTTPS();

	@Key("bot.http.connect_timeout")
	@DefaultValue("15000")
	int HTTP_DEFAULT_CONNECT_TIMEOUT();

	@Key("bot.http.socket_timeout")
	@DefaultValue("15000")
	int HTTP_DEFAULT_SOCKET_TIMEOUT();

	@Key("bot.http.socket_timeout.captcha")
	@DefaultValue("6000")
	int HTTP_CAPTCHA_SOCKET_TIMEOUT();

	@Key("bot.game_title")
	@DefaultValue("BLACK DESERT")
	String GAME_TITLE();

	@Key("bot.ws")
	@DefaultValue("wss://${bot.https}/fishingserver/tg_bot")
	String URL_WS();

    @Key("bot.local.port")
    @DefaultValue("9999")
    int LOCAL_PORT();

	@Key("bot.comport")
	@DefaultValue("")
	String COM_PORT();

	@Key("bot.mode")
	@DefaultValue("FISHING")
	Mode MODE();

	@Key("bot.input.mode")
	@DefaultValue("ROBOT")
	InputMode INPUT_MODE();

	@Key("bot.input.timeout")
	@DefaultValue("25000")
	long INPUT_TIMEOUT();

	@Key("bot.language")
	@DefaultValue("English")
	String LANGUAGE();

	@Key("bot.state.overflow")
	@DefaultValue("10")
	int STATE_OVERFLOW();

	@Key("bot.state.overflow.captcha")
	@DefaultValue("10")
	int STATE_STATUS_CAPTCHA_OVERFLOW();

	@Key("bot.state.overflow.cut")
	@DefaultValue("10")
	int STATE_CUT_OVERFLOW();

	@Key("bot.state.skip_calendar")
	@DefaultValue("true")
	boolean SKIP_CALENDAR();

	@Key("bot.hotkey.start")
	@DefaultValue("3655")
	int HOT_KEY_START();

	@Key("bot.hotkey.stop")
	@DefaultValue("3663")
	int HOT_KEY_STOP();

	@Key("bot.ui.window.root.x")
	@DefaultValue("0")
	int UI_WINDOW_ROOT_X();

	@Key("bot.ui.window.root.y")
	@DefaultValue("300")
	int UI_WINDOW_ROOT_Y();


	///////////////////////////////////////////////////////////////////////////
	// RODS
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.rod.count")
	@DefaultValue("0")
	int COUNT_ROD();
	
	@Key("bot.rod.changetime")
	@DefaultValue("180000")
	int TIME_CHANGE_ROD();

	@Key("bot.rod.x")
	@DefaultValue("1488")
	int ROD_START_X();
	
	@Key("bot.rod.y")
	@DefaultValue("342")
	int ROD_START_Y();
	
	@Key("bot.rod.dx")
	@DefaultValue("54")
	int ROD_DX();
	
	@Key("bot.rod.dy")
	@DefaultValue("54")
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
	
	@DefaultValue("0.85")
	@Key("bot.parsing.coefidentity")
	double COEF_IDENTITY();

	@DefaultValue("0.77")
	@Key("bot.parsing.palette_coefidentity")
	double PALETTE_COEF_IDENTITY();
	
	@Key("bot.port.serial")
	@DefaultValue("${bot.key}")
	@ConverterClass(SerialPortConverter.class)
	SerialPort physicalPort();

	@Key("bot.loot.touch")
	@Separator(";")
	@DefaultValue("{1434,534};{1488,534};{1542,534};{1596,534};{1650,534};{1704,534};" +
				  "{1434,588};{1488,588}")
	@ConverterClass(TouchConverter.class)
	Touch[] LOOT_TOUCH();


	///////////////////////////////////////////////////////////////////////////
	// LOOT FILTER
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.loot.frame.red")
	@DefaultValue("true")
	boolean RED_FRAME();

	@Key("bot.loot.frame.gold")
	@DefaultValue("true")
	boolean GOLD_FRAME();

	@Key("bot.loot.frame.blue")
	@DefaultValue("false")
	boolean BLUE_FRAME();

	@Key("bot.loot.frame.green")
	@DefaultValue("false")
	boolean GREEN_FRAME();

	@Key("bot.loot.frame.gray")
	@DefaultValue("false")
	boolean GRAY_FRAME();

	@Key("bot.loot.usefull")
	@DefaultValue("true")
	boolean USEFULL();

	@Key("bot.loot.confirm")
	@DefaultValue("true")
	boolean CONFIRM();

	@Key("bot.loot.unknown")
	@DefaultValue("false")
	boolean TAKE_UNKNOWN();
	
	@Key("bot.loot.save_unsort")
	@DefaultValue("true")
	boolean SAVE_UNSORT();

	@Key("bot.loot.save_unknown")
	@DefaultValue("true")
	boolean SAVE_UNKNOWN();

    @Key("bot.loot.sync")
    @DefaultValue("true")
	boolean LOOT_SYNC();

	@Key("bot.delay.filterloot.empty_coef")
	@DefaultValue("0.05")
	double LOOT_EMPTY_COEF();


	///////////////////////////////////////////////////////////////////////////
	// TASKS
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.autouse.beer")
	@DefaultValue("false, 7, 0, 0, 0, 0, Beer")
	@ConverterClass(HotSlotConverter.class)
	HotSlot SLOT_BEER();

	@Key("bot.autouse.beer.touchs")
	@Separator(";")
	@DefaultValue("{1500,854};{1090,440};!{1760,850}")
	@ConverterClass(TouchConverter.class)
	Touch[] BEER_TOUCHS();

	@Key("bot.autouse.pause")
	@DefaultValue("false, 120m, 180m, 120m, 180m, 20m, 30m, Pause")
	@ConverterClass(HotSlotConverter.class)
	HotSlot TASK_PAUSE();

	@Key("bot.autouse.start")
	@DefaultValue("false, 0, Start")
	@ConverterClass(HotSlotConverter.class)
	HotSlot TASK_START();

	@Key("bot.autouse.stop")
	@DefaultValue("false, 180m, Stop")
	@ConverterClass(HotSlotConverter.class)
	HotSlot TASK_STOP();

    @Key("bot.autouse.exit")
    @DefaultValue("false, 120m, Exit")
    @ConverterClass(HotSlotConverter.class)
    HotSlot TASK_EXIT_GAME();

    @Key("bot.autouse.exit.touchs")
    @Separator(";")
    @DefaultValue("{540,243};{1300,708};{1051,666}")
    @ConverterClass(TouchConverter.class)
    Touch[] EXIT_TOUCHS();


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
	@DefaultValue("2500")
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
	@DefaultValue("500")
	int DELAY_BEFORE_STATUS_CUT();
	
	@Key("bot.delay.statuscut.after")
	@DefaultValue("2000")
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

	@Key("bot.slot.debuf.day")
	@DefaultValue("false, 0")
	@ConverterClass(HotSlotConverter.class)
	HotSlot SLOT_DEBUF_DESERT_DAY();

	@Key("bot.slot.debuf.night")
	@DefaultValue("false, 0")
	@ConverterClass(HotSlotConverter.class)
	HotSlot SLOT_DEBUF_DESERT_NIGHT();


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
	// CRASH GAME CLIENT
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.crash_client.stop_bot")
	@DefaultValue("true")
	boolean CRASH_STOP_BOT();

	@Key("bot.crash_client.exit_bot")
	@DefaultValue("false")
	boolean CRASH_EXIT_BOT();

	@Key("bot.crash_client.shutdown_pc")
	@DefaultValue("false")
	boolean CRASH_SHUTDOWN_PC();


	///////////////////////////////////////////////////////////////////////////
	// SCREEN
	///////////////////////////////////////////////////////////////////////////

	@Key("bot.screen.fullscreen")
	@DefaultValue("0, 0, 1920, 1080")
	@ConverterClass(RectangleConverter.class)
	Rectangle FULL_SCREEN();

	@Key("bot.screen.space")
	@DefaultValue("900, 333, 120, 30")
	@ConverterClass(RectangleConverter.class)
	Rectangle SPACE();

	@Key("bot.screen.space.offset.x")
	@DefaultValue("0")
	int SPACE_OFFSET_X();

	@Key("bot.screen.space.offset.y")
	@DefaultValue("0")
	int SPACE_OFFSET_Y();

	@Key("bot.screen.line")
	@DefaultValue("820, 402, 278, 25")
	@ConverterClass(RectangleConverter.class)
	Rectangle LINE();

	@Key("bot.screen.subline")
	@DefaultValue("1011, 373, 10, 25")
	@ConverterClass(RectangleConverter.class)
	Rectangle SUB_LINE();

	@Key("bot.screen.statuscut")
	@DefaultValue("837,448,234,42")
	@ConverterClass(RectangleConverter.class)
	Rectangle STATUS_CUT();

	@Key("bot.screen.captcha")
	@DefaultValue("770,348,380,60")
	@ConverterClass(RectangleConverter.class)
	Rectangle CAPTCHA();

	@Key("bot.screen.statuscaptcha")
	@DefaultValue("865,490,195,72")
	@ConverterClass(RectangleConverter.class)
	Rectangle STATUS_CAPTCHA();

	@Key("bot.screen.loot_window")
	@DefaultValue("1400,450, 336, 230")
	@ConverterClass(RectangleConverter.class)
	Rectangle LOOT_WINDOW();

	@Key("bot.screen.loot_window_close")
	@DefaultValue("1702,466, 18, 18")
	@ConverterClass(RectangleConverter.class)
	Rectangle LOOT_WINDOW_CLOSE();

	@Key("bot.screen.loot_slot_list")
	@Separator(";")
    @DefaultValue("1412, 512, 42, 42;"
                + "1466, 512, 42, 42;"
                + "1520, 512, 42, 42;"
                + "1574, 512, 42, 42;"
                + "1628, 512, 42, 42;"
                + "1682, 512, 42, 42;"
                + "1412, 566, 42, 42;"
                + "1466, 566, 42, 42")
	@ConverterClass(RectangleConverter.class)
	Rectangle[] LOOT_SLOT_LIST();

	@Key("bot.screen.loot_slot_list.color")
	@Separator(";")
	@DefaultValue("1411, 511, 45, 1;"
				+ "1465, 511, 45, 1;"
				+ "1519, 511, 45, 1;"
				+ "1573, 511, 45, 1;"
				+ "1627, 511, 45, 1;"
				+ "1681, 511, 45, 1;"
				+ "1411, 565, 45, 1;"
				+ "1465, 565, 45, 1")
	@ConverterClass(RectangleConverter.class)
	Rectangle[] LOOT_SLOT_LIST_COLOR();

	@Key("bot.screen.chat")
	@DefaultValue("5, 1000, 355, 40")
	@ConverterClass(RectangleConverter.class)
	Rectangle CHAT();

	@Key("bot.screen.inventory")
	@DefaultValue("960, 0, 960, 1080")
	@ConverterClass(RectangleConverter.class)
	Rectangle INVENTORY();

    @Key("bot.screen.debuff_desert")
    @DefaultValue("676, 868, 28, 28")
    @ConverterClass(RectangleConverter.class)
	Rectangle DEBUFF_DESERT();

	@Key("bot.screen.calendar")
	@Separator(";")
	@DefaultValue("1308, 106, 18, 18;"
			    + "1308, 126, 18, 18")
	@ConverterClass(RectangleConverter.class)
	Rectangle[] CALENDAR();


    ///////////////////////////////////////////////////////////////////////////
    // EMULATION
    ///////////////////////////////////////////////////////////////////////////

    @Key("bot.keyboard.input.delay")
    @DefaultValue("150")
    int PRESS_KEY_DELAY();

	@Key("bot.mouse.click_sequence.delay")
	@DefaultValue("2000")
	long MOUSE_CLICK_SEQUENCE_DELAY();

	@Key("bot.mouse.correction_factor")
	@DefaultValue("1")
    double MOUSE_CORRECTION_FACTOR();


    ///////////////////////////////////////////////////////////////////////////
    // DEBUG
    ///////////////////////////////////////////////////////////////////////////

    @Key("bot.screen.debug")
    @DefaultValue("false")
    boolean DEBUG_SCREEN();

	@Key("bot.screen.debug.image_parser")
	@DefaultValue("false")
	boolean DEBUG_IMAGE_PARSER();

    @Key("bot.screen.debug.waitfish")
    @DefaultValue("false")
    boolean DEBUG_WAITFISH();

    @Key("bot.screen.debug.statuscaptcha")
    @DefaultValue("false")
    boolean DEBUG_STATUS_CAPTCHA();

    @Key("bot.screen.debug.statuscut")
    @DefaultValue("false")
    boolean DEBUG_STATUS_CUT();

    @Key("bot.screen.debug.subline")
    @DefaultValue("false")
    boolean DEBUG_SUBLINE();

    @Key("bot.screen.debug.captcha")
    @DefaultValue("false")
    boolean DEBUG_CAPTCHA();

    @Key("bot.screen.debug.filterloot")
    @DefaultValue("false")
    boolean DEBUG_FILTER_LOOT();

	@Key("bot.screen.debug.pmmessage")
	@DefaultValue("false")
    boolean DEBUG_PM_MESSAGE();

	@Key("bot.screen.debug.debuf")
	@DefaultValue("false")
	boolean DEBUG_DEBUF();

}