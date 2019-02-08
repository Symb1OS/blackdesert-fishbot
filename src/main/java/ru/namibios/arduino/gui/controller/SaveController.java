package ru.namibios.arduino.gui.controller;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.view.TabSettingsView;
import ru.namibios.arduino.model.Touch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveController implements ActionListener{

	private static final String FORMAT_SLOT = "%s,%s,%s,%s";
	private static final String FORMAT_SCREEN = "%s,%s,%s,%s";
	private static final String FORMAT_SLOT_TASK = "%s,%s,%s,%s, %s";
	private static final String FORMAT_BEER_TOUCHS = "%s;%s;%s";
	private static final String FORMAT_SMART_TASK = "%s,%s,%s";
	private static final String FORMAT_SMART_TASK_WITH_RANDOM = "%s,%s,%s,%s,%s,%s,%s,%s";
	private static final String FORMAT_TOUCH = "%s;%s;%s;%s;%s;%s;%s;%s";
	private static final String FORMAT_SCREEN_LOOT_LIST = "%s,%s,%s,%s;" +
														" %s,%s,%s,%s;" +
														" %s,%s,%s,%s;" +
														" %s,%s,%s,%s;" +
														" %s,%s,%s,%s;" +
														" %s,%s,%s,%s;" +
														" %s,%s,%s,%s;" +
														" %s,%s,%s,%s";

	private TabSettingsView view;

	public SaveController(TabSettingsView view) {
		this.view = view;
	}

	private void saveGeneral(){

		Application.getInstance().setProperty("bot.comport", getPortName(String.valueOf(view.getCbPort().getSelectedItem())));
		Application.getInstance().setProperty("bot.input.mode", String.valueOf(view.getCbMode().getSelectedItem()));

		Application.getInstance().setProperty("bot.loot.key",   String.valueOf(view.getCbKey().isSelected()));
		Application.getInstance().setProperty("bot.loot.rock",  String.valueOf(view.getCbRock().isSelected()));
		Application.getInstance().setProperty("bot.loot.fish",  String.valueOf(view.getCbFish().isSelected()));
		Application.getInstance().setProperty("bot.loot.event", String.valueOf(view.getCbEvent().isSelected()));
		Application.getInstance().setProperty("bot.loot.confirm", String.valueOf(view.getCbConfirm().isSelected()));
		Application.getInstance().setProperty("bot.loot.unknown", String.valueOf(view.getCbUnknown().isSelected()));

		Application.getInstance().setProperty("bot.autouse.beer", String.format(FORMAT_SLOT_TASK, view.getCbBeer().isSelected(), view.getBeerKey().getText(), 0, view.getBeerPeriod().getText(), "Beer"));

		Touch[] touches = Application.getInstance().BEER_TOUCHS();
		touches[2].setActive(view.getCbRepeatWork().isSelected());
		Application.getInstance().setProperty("bot.autouse.beer.touchs", String.format(FORMAT_BEER_TOUCHS,
				touches[0].toSettingTouch(),
				touches[1].toSettingTouch(),
				touches[2].toSettingTouch()));

		Application.getInstance().setProperty("bot.slot.one", String.format(FORMAT_SLOT, view.getCbFirstSlotActive().isSelected(), view.getTfFirstSlotKey().getText(), view.getTfFirstSlotDelay().getText(), view.getTfFirstSlotPeriod().getText()));
		Application.getInstance().setProperty("bot.slot.two", String.format(FORMAT_SLOT, view.getCbSecondSlotActive().isSelected(),view.getTfSecondSlotKey().getText(), view.getTfSecondSlotDelay().getText(), view.getTfSecondSlotPeriod().getText()));
		Application.getInstance().setProperty("bot.slot.three", String.format(FORMAT_SLOT, view.getCbThirdSlotActive().isSelected(),view.getTfThirdSlotKey().getText(), view.getTfThirdSlotDelay().getText(), view.getTfThirdSlotPeriod().getText()));

		Application.getInstance().setProperty("bot.rod.count", String.valueOf(view.getTfRodCount().getText()));
		Application.getInstance().setProperty("bot.rod.changetime", String.valueOf(view.getTfRodChange().getText()));

		Application.getInstance().setProperty("bot.notification.telegram", String.valueOf(view.getCbTelegram().isSelected()));
		Application.getInstance().setProperty("bot.notification.telegram.key", view.getTfTelegramKey().getText().trim());

		Application.getInstance().setProperty("bot.pm.event.exitgame", String.valueOf(view.getRbExitGame().isSelected()));
		Application.getInstance().setProperty("bot.pm.event.autofish", String.valueOf(view.getRbAutoFish().isSelected()));
		Application.getInstance().setProperty("bot.pm.event.nothing",  String.valueOf(view.getRbNothing().isSelected()));

		Application.getInstance().setProperty("bot.autouse.pause", String.format(FORMAT_SMART_TASK_WITH_RANDOM, view.getCbPause().isSelected(), view.getTfPauseDelayFrom().getText(), view.getTfPauseDelayTo().getText(), view.getTfPauseDelayFrom().getText(), view.getTfPauseDelayTo().getText(), view.getTfPauseDowntimeFrom().getText(), view.getTfPauseDowntimeTo().getText(), "Pause"));
		Application.getInstance().setProperty("bot.autouse.start", String.format(FORMAT_SMART_TASK, view.getCbDeferredStart().isSelected(), view.getTfDeferredStart().getText() , "Start"));
		Application.getInstance().setProperty("bot.autouse.stop", String.format(FORMAT_SMART_TASK, view.getCbStopBot().isSelected(), view.getTfStopBot().getText() , "Stop"));
		Application.getInstance().setProperty("bot.autouse.exit", String.format(FORMAT_SMART_TASK, view.getCbExitGame().isSelected(), view.getTfExitGame().getText(), "Exit"));

		Application.getInstance().setProperty("bot.crash_client.stop_bot", String.valueOf(view.getRbCrashStopBot().isSelected()));
		Application.getInstance().setProperty("bot.crash_client.exit_bot", String.valueOf(view.getRbCrashExitBot().isSelected()));
		Application.getInstance().setProperty("bot.crash_client.shutdown_pc", String.valueOf(view.getRbCrashShutdownPc().isSelected()));

	}

	private void saveAdd(){

		Application.getInstance().setProperty("bot.language", String.valueOf(view.getCbLanguage().getSelectedItem()));
		Application.getInstance().setProperty("bot.ui.theme", String.valueOf(view.getCbTheme().getSelectedItem()));

		Application.getInstance().setProperty("bot.state.skip_calendar", String.valueOf(view.getCbSkipCalendar().isSelected()));
		Application.getInstance().setProperty("bot.keyboard.input.delay", view.getTfInputDelay().getText());
		Application.getInstance().setProperty("bot.parsing.coefidentity", view.getTfParseCoef().getText());
		Application.getInstance().setProperty("bot.kapcha.noise.iteration", view.getTfCaptchaNoiseIteration().getText());
		Application.getInstance().setProperty("bot.state.overflow", view.getTfState().getText());
		Application.getInstance().setProperty("bot.state.overflow.captcha", view.getTfCapcthaState().getText());
		Application.getInstance().setProperty("bot.state.overflow.cut", view.getTfCutState().getText());

		Application.getInstance().setProperty("bot.loot.touch", String.format(FORMAT_TOUCH,
				new Touch(Integer.valueOf(view.getTfLootSlotOneX().getText()), Integer.valueOf(view.getTfLootSlotOneY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotTwoX().getText()), Integer.valueOf(view.getTfLootSlotTwoY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotThreeX().getText()), Integer.valueOf(view.getTfLootSlotThreeY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotFourX().getText()), Integer.valueOf(view.getTfLootSlotFourY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotFiveX().getText()), Integer.valueOf(view.getTfLootSlotFiveY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotSixX().getText()), Integer.valueOf(view.getTfLootSlotSixY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotSevenX().getText()), Integer.valueOf(view.getTfLootSlotSevenY().getText())).toSettingTouch(),
				new Touch(Integer.valueOf(view.getTfLootSlotEightX().getText()), Integer.valueOf(view.getTfLootSlotEightY().getText())).toSettingTouch()));

		Application.getInstance().setProperty("bot.rod.x", view.getTfRodX().getText());
		Application.getInstance().setProperty("bot.rod.y", view.getTfRodY().getText());
		Application.getInstance().setProperty("bot.rod.dx", view.getTfRodDX().getText());
		Application.getInstance().setProperty("bot.rod.dy", view.getTfRodDY().getText());

	}

	private void saveCoord() {

		Application.getInstance().setProperty("bot.screen.fullscreen", String.format(FORMAT_SCREEN, view.getTfFullscreenX().getText(), view.getTfFullscreenY().getText(), view.getTfFullscreenWidth().getText(), view.getTfFullscreenHeight().getText()));
		Application.getInstance().setProperty("bot.screen.space", String.format(FORMAT_SCREEN,view.getTfSpaceX().getText(), view.getTfSpaceY().getText(), view.getTfSpaceWidth().getText(), view.getTfSpaceHeight().getText()));
		Application.getInstance().setProperty("bot.screen.line", String.format(FORMAT_SCREEN, view.getTfLineX().getText(), view.getTfLineY().getText(), view.getTfLineWidth().getText(), view.getTfLineHeight().getText()));
		Application.getInstance().setProperty("bot.screen.subline", String.format(FORMAT_SCREEN, view.getTfSubLineX().getText(), view.getTfSubLineY().getText(), view.getTfSubLineWidth().getText(), view.getTfSubLineHeight().getText()));
		Application.getInstance().setProperty("bot.screen.statuscut", String.format(FORMAT_SCREEN, view.getTfStatusCutX().getText(), view.getTfStatusCutY().getText(), view.getTfStatusCutWidth().getText(), view.getTfStatusCutHeight().getText()));
		Application.getInstance().setProperty("bot.screen.captcha", String.format(FORMAT_SCREEN, view.getTfCaptchaX().getText(), view.getTfCaptchaY().getText(), view.getTfCaptchaWidth().getText(), view.getTfCaptchaHeight().getText()));
		Application.getInstance().setProperty("bot.screen.statuscaptcha", String.format(FORMAT_SCREEN, view.getTfStatusCaptchaX().getText(), view.getTfStatusCaptchaY().getText(), view.getTfStatusCaptchaWidth().getText(), view.getTfStatusCaptchaHeight().getText()));
		Application.getInstance().setProperty("bot.screen.loot_slot_list", String.format(FORMAT_SCREEN_LOOT_LIST,
				view.getTfLootOneX().getText(), view.getTfLootOneY().getText(), view.getTfLootOneWidth().getText(), view.getTfLootOneHeight().getText(),
				view.getTfLootTwoX().getText(), view.getTfLootTwoY().getText(), view.getTfLootTwoWidth().getText(), view.getTfLootTwoHeight().getText(),
				view.getTfLootThreeX().getText(), view.getTfLootThreeY().getText(), view.getTfLootThreeWidth().getText(), view.getTfLootThreeHeight().getText(),
				view.getTfLootFourX().getText(), view.getTfLootFourY().getText(), view.getTfLootFourWidth().getText(), view.getTfLootFourHeight().getText(),
				view.getTfLootFiveX().getText(), view.getTfLootFiveY().getText(), view.getTfLootFiveWidth().getText(), view.getTfLootFiveHeight().getText(),
				view.getTfLootSixX().getText(), view.getTfLootSixY().getText(), view.getTfLootSixWidth().getText(), view.getTfLootSixHeight().getText(),
				view.getTfLootSevenX().getText(), view.getTfLootSevenY().getText(), view.getTfLootSevenWidth().getText(), view.getTfLootSevenHeight().getText(),
				view.getTfLootEightX().getText(), view.getTfLootEightY().getText(), view.getTfLootEightWidth().getText(), view.getTfLootEightHeight().getText()
				));

		Application.getInstance().setProperty("bot.screen.chat", String.format(FORMAT_SCREEN, view.getTfChatX().getText(), view.getTfChatY().getText(), view.getTfChatWidth().getText(), view.getTfChatHeight().getText()));
	}

	private void saveDelay() {

		Application.getInstance().setProperty("bot.delay.start.before", String.valueOf(view.getTfDelayStartBefore().getText()));
		Application.getInstance().setProperty("bot.delay.start.after", String.valueOf(view.getTfDelayStartAfter().getText()));
		Application.getInstance().setProperty("bot.delay.waitfish.before", String.valueOf(view.getTfDelayWaitfishBefore().getText()));
		Application.getInstance().setProperty("bot.delay.waitfish.after", String.valueOf(view.getTfDelayWaitfishAfter().getText()));
		Application.getInstance().setProperty("bot.delay.cutfish.before", String.valueOf(view.getTfDelayCutBefore().getText()));
		Application.getInstance().setProperty("bot.delay.cutfish.after", String.valueOf(view.getTfDelayCutAfter().getText()));
		Application.getInstance().setProperty("bot.delay.statuscut.before", String.valueOf(view.getTfDelayStatusCutBefore().getText()));
		Application.getInstance().setProperty("bot.delay.statuscut.after", String.valueOf(view.getTfDelayStatusCutAfter().getText()));
		Application.getInstance().setProperty("bot.delay.kapcha.before", String.valueOf(view.getTfDelayCaptchaBefore().getText()));
		Application.getInstance().setProperty("bot.delay.kapcha.after", String.valueOf(view.getTfDelayCaptchaAfter().getText()));
		Application.getInstance().setProperty("bot.delay.statuskapcha.before", String.valueOf(view.getTfDelayStatusCaptchaBefore().getText()));
		Application.getInstance().setProperty("bot.delay.statuskapcha.after", String.valueOf(view.getTfDelayStatusCaptchaAfter().getText()));
		Application.getInstance().setProperty("bot.delay.filterloot.before", String.valueOf(view.getTfDelayLootFilterBefore().getText()));
		Application.getInstance().setProperty("bot.delay.filterloot.after", String.valueOf(view.getTfDelayLootFilterAfter().getText()));

	}

	private void saveDebug() {
		Application.getInstance().setProperty("bot.screen.debug.waitfish", String.valueOf(view.getCbDebugWaitFish().isSelected()));
		Application.getInstance().setProperty("bot.screen.debug.statuscaptcha", String.valueOf(view.getCbDebugStatusCaptcha().isSelected()));
		Application.getInstance().setProperty("bot.screen.debug.statuscut", String.valueOf(view.getCbDebugStatusCut().isSelected()));
		Application.getInstance().setProperty("bot.screen.debug.subline", String.valueOf(view.getCbDebugLine().isSelected()));
		Application.getInstance().setProperty("bot.screen.debug.captcha", String.valueOf(view.getCbDebugCaptcha().isSelected()));
		Application.getInstance().setProperty("bot.screen.debug.filterloot", String.valueOf(view.getCbDebugLootFilter().isSelected()));
		Application.getInstance().setProperty("bot.screen.debug.pmmessage", String.valueOf(view.getCbDebugPersonalMessage().isSelected()));
		Application.getInstance().setProperty("bot.loot.save_unsort", String.valueOf(view.getCbUnsortLoot().isSelected()));
		Application.getInstance().setProperty("bot.loot.save_unknown", String.valueOf(view.getCbUnknownLoot().isSelected()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		saveGeneral();
		saveAdd();
		saveCoord();
		saveDelay();
		saveDebug();

		Application.record();

		try {

			if (!Application.getUser().getVersion().equals("${pom.version}")) {
				Application.restart();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		view.dispose();
	}

	private String getPortName(String descriptionPort){
		return descriptionPort.indexOf(")") != -1 
				? descriptionPort.substring(descriptionPort.indexOf("(") + 1, descriptionPort.indexOf(")"))
				: descriptionPort;
	}
	
}