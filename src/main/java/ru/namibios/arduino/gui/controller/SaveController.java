package ru.namibios.arduino.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.gui.view.SettingsView;

public class SaveController implements ActionListener{
	
	private SettingsView view;

	public SaveController(SettingsView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Application.getInstance().setProperty("bot.key", view.gettHash().getText().trim());
		Application.getInstance().setProperty("bot.port", getPortName(view.gettPort().getSelectedItem().toString()));
		
		Application.getInstance().setProperty("bot.loot.key",   String.valueOf(view.getCbKey().isSelected()));
		Application.getInstance().setProperty("bot.loot.rock",  String.valueOf(view.getCbRock().isSelected()));
		Application.getInstance().setProperty("bot.loot.fish",  String.valueOf(view.getCbFish().isSelected()));
		Application.getInstance().setProperty("bot.loot.event", String.valueOf(view.getCbEvent().isSelected()));
		Application.getInstance().setProperty("bot.loot.unknown", String.valueOf(view.getCbUnknown().isSelected()));
		
		Application.getInstance().setProperty("bot.autouse.beer",  String.valueOf(view.getCbBeer().isSelected()));
		Application.getInstance().setProperty("bot.autouse.minigame", String.valueOf(view.getCbMinigame().isSelected()));
		
		Application.getInstance().setProperty("bot.slot.first", String.valueOf(view.getCbAutoOne().isSelected()));
		Application.getInstance().setProperty("bot.slot.first.key", String.valueOf(view.gettHotKeyOne().getText()));
		Application.getInstance().setProperty("bot.slot.first.delayuse", String.valueOf(view.gettKeyTimerOne().getText()));
		
		Application.getInstance().setProperty("bot.rod.count", String.valueOf(view.gettCountRod().getText()));
		Application.getInstance().setProperty("bot.rod.changetime", String.valueOf(view.gettTimeChangeRod().getText()));
		
		Application.getInstance().setProperty("bot.delay.start.before", view.gettStartDelayBefore().getText().trim());
		Application.getInstance().setProperty("bot.delay.start.after", view.gettStartDelayAfter().getText().trim());
		
		Application.getInstance().setProperty("bot.delay.waitfish.before", view.gettWaitDelayBefore().getText().trim());
		Application.getInstance().setProperty("bot.delay.waitfish.after", view.gettWaitDelayAfter().getText().trim());
		
		Application.getInstance().setProperty("bot.delay.rod.before", view.gettRodDelayBefore().getText().trim());
		Application.getInstance().setProperty("bot.delay.rod.after", view.gettRodDelayAfter().getText().trim());
		
		Application.getInstance().setProperty("bot.notification.telegram", String.valueOf(view.getCbTelegram().isSelected()));
		Application.getInstance().setProperty("bot.notification.telegram.key", view.gettTelegramKey().getText().trim());
		
		Application.getInstance().setProperty("bot.pm.event.exitgame", String.valueOf(view.getRbExitGame().isSelected()));
		Application.getInstance().setProperty("bot.pm.event.autofish", String.valueOf(view.getRbAutoFish().isSelected()));
		Application.getInstance().setProperty("bot.pm.event.nothing",  String.valueOf(view.getRbNothing().isSelected()));
		
		Application.getInstance().setProperty("bot.delay.kapcha.before", view.gettKapchaDelayBefore().getText().trim());
		Application.getInstance().setProperty("bot.delay.kapcha.after", view.gettKapchaDelayAfter().getText().trim());
		
		Application.getInstance().setProperty("bot.delay.filterloot.before", view.gettFilterDelayBefore().getText().trim());
		Application.getInstance().setProperty("bot.delay.filterloot.after", view.gettFilterDelayAfter().getText().trim());
		
		Application.record();
		
		view.dispose();
	}
	
	private String getPortName(String descriptionPort){
		return descriptionPort.indexOf(")") != -1 
				? descriptionPort.substring(descriptionPort.indexOf("(") + 1, descriptionPort.indexOf(")"))
				: descriptionPort;
	}
	
}