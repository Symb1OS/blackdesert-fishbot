package ru.namibios.arduino.gui.controller;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.view.SettingsView;
import ru.namibios.arduino.model.Touch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveController implements ActionListener{

	private static final String FORMAT_SLOT = "%s,%s,%s,%s";
	private static final String FORMAT_SLOT_TASK = "%s,%s,%s,%s, %s";
	private static final String FORMAT_BEER_TOUCHS = "%s;%s;%s";
	private static final String FORMAT_SMART_TASK = "%s,%s,%s";

	private SettingsView view;

	public SaveController(SettingsView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Application.getInstance().setProperty("bot.language", String.valueOf(view.getCbLanguage().getSelectedItem()));

		Application.getInstance().setProperty("bot.comport", getPortName(String.valueOf(view.getCbPort().getSelectedItem())));
		Application.getInstance().setProperty("bot.input.mode", String.valueOf(view.getCbMode().getSelectedItem()));

		Application.getInstance().setProperty("bot.loot.key",   String.valueOf(view.getCbKey().isSelected()));
		Application.getInstance().setProperty("bot.loot.rock",  String.valueOf(view.getCbRock().isSelected()));
		Application.getInstance().setProperty("bot.loot.fish",  String.valueOf(view.getCbFish().isSelected()));
		Application.getInstance().setProperty("bot.loot.event", String.valueOf(view.getCbEvent().isSelected()));
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

		Application.getInstance().setProperty("bot.autouse.stop", String.format(FORMAT_SMART_TASK, view.getCbStopBot().isSelected(), view.getTfStopBot().getText() , "Stop"));
		Application.getInstance().setProperty("bot.autouse.exit", String.format(FORMAT_SMART_TASK, view.getCbExitGame().isSelected(), view.getTfExitGame().getText(), "Exit"));

		Application.getInstance().setProperty("bot.delay.start.before", view.getTfBeforeStart().getText().trim());
		Application.getInstance().setProperty("bot.delay.start.after", view.getTfAfterStart().getText().trim());
		
		Application.getInstance().setProperty("bot.delay.waitfish.before", view.getTfBeforeWait().getText().trim());
		Application.getInstance().setProperty("bot.delay.waitfish.after", view.getTfAfterWait().getText().trim());
		
		Application.getInstance().setProperty("bot.delay.rod.before", view.getTfBeforeChangeRod().getText().trim());
		Application.getInstance().setProperty("bot.delay.rod.after", view.getTfAfterChangeRod().getText().trim());

		Application.getInstance().setProperty("bot.pm.event.exitgame", String.valueOf(view.getRbExitGame().isSelected()));
		Application.getInstance().setProperty("bot.pm.event.autofish", String.valueOf(view.getRbAutoFish().isSelected()));
		Application.getInstance().setProperty("bot.pm.event.nothing",  String.valueOf(view.getRbNothing().isSelected()));
		
		Application.getInstance().setProperty("bot.delay.kapcha.before", view.getTfBeforeCaptcha().getText().trim());
		Application.getInstance().setProperty("bot.delay.kapcha.after", view.getTfAfterCaptcha().getText().trim());
		
		Application.getInstance().setProperty("bot.delay.filterloot.before", view.getTfBeforeFilterLoot().getText().trim());
		Application.getInstance().setProperty("bot.delay.filterloot.after", view.getTfAfterFilterLoot().getText().trim());
		
		Application.record();

		view.dispose();
	}

	private String getPortName(String descriptionPort){
		return descriptionPort.indexOf(")") != -1 
				? descriptionPort.substring(descriptionPort.indexOf("(") + 1, descriptionPort.indexOf(")"))
				: descriptionPort;
	}
	
}