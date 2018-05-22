package ru.namibios.arduino.config;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.Touch;

@Sources("file:resources/application.properties")
public interface ApplicationConfig extends Accessible, Mutable{
	
	@Key("bot.key")
	String HASH();
	
	@Key("bot.port")
	String PORT();
	
	@Key("bot.http")
	@DefaultValue("46.188.5.59:9090")
	String HTTP_SERVER();
	
	@Key("bot.ws.action")
	@DefaultValue("ws://${bot.http}/monitoring/action/${bot.key}")
	String WS_ACTION();
	
	@Key("bot.rod.count")
	@DefaultValue("0")
	int COUNT_ROD();
	
	@Key("bot.rod.changetime")
	@DefaultValue("180000")
	int TIME_CHANGE_ROD();
	
	@Key("bot.rod.x")
	@DefaultValue("1532")
	int ROD_START_X();
	
	@Key("bot.rod.y")
	@DefaultValue("355")
	int ROD_START_Y();
	
	@Key("bot.rod.dx")
	@DefaultValue("48")
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
	boolean TAKE_UNKNOWN();
	
	@Key("bot.autouse.beer")
	@DefaultValue("true")
	boolean BEER();
	
	@Key("bot.autouse.minigame")
	@DefaultValue("false")
	boolean MINIGAME();
	
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
	@DefaultValue("0")
	int DELAY_AFTER_STATUS_CUT();

	@Key("bot.delay.kapcha.before")
	@DefaultValue("0")
	int DELAY_BEFORE_KAPCHA();
	
	@Key("bot.delay.kapcha.after")
	@DefaultValue("10000")
	int DELAY_AFTER_KAPCHA();
	
	@Key("bot.delay.statuskapcha.before")
	@DefaultValue("0")
	int DELAY_BEFORE_STATUS_KAPCHA();
	
	@Key("bot.delay.statuskapcha.after")
	@DefaultValue("0")
	int DELAY_AFTER_STATUS_KAPCHA();

	@Key("bot.delay.filterloot.before")
	@DefaultValue("5000")
	int DELAY_BEFORE_FILTER_LOOT();
	
	@Key("bot.delay.filterloot.after")
	@DefaultValue("0")
	int DELAY_AFTER_FILTER_LOOT();
	
	@Key("bot.notification.telegram")
	@DefaultValue("false")
	boolean TELEGRAM();
	
	@Key("bot.notification.telegram.key")
	String TELEGRAM_KEY();
	
	@Key("bot.slot.first")
	@DefaultValue("false")
	boolean FIRST_SLOT();
	
	@Key("bot.slot.first.key")
	@DefaultValue("1")
	String FIRST_KEY_NUMBER();
	
	@Key("bot.slot.first.delayuse")
	@DefaultValue("5000")
	int FIRST_SLOT_USE_DELAY();
	
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
	
	/**	0 - NN;  
	 *  1 - ALG  work only 1920*1080
	 *  									*/
	@Key("bot.parse.variable")
	@DefaultValue("0")
	int PARSE_VARIABLE();
	
}