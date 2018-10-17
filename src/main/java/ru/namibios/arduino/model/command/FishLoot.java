package ru.namibios.arduino.model.command;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.*;
import ru.namibios.arduino.model.template.Loot;
import ru.namibios.arduino.utils.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FishLoot implements Command{
	
	private final static Logger LOG = Logger.getLogger(FishLoot.class);

	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	
	private ImageParser imageParser;
	
	public FishLoot(String fileLootOne, String fileLootTwo) throws IOException {
		this.scrins = new ArrayList<>();
		this.scrins.add(new Screen(fileLootOne));
		this.scrins.add(new Screen(fileLootTwo));
		
	}
	
	public FishLoot() throws AWTException {
		this.scrins = new ArrayList<>();
		
		this.one = new Screen(Application.getInstance().LOOT_SLOT_ONE());
		this.two = new Screen(Application.getInstance().LOOT_SLOT_TWO());
		
		if(Application.getInstance().SAVE_UNSORT()) {
			this.one.saveImage("loot/unsort");
			this.two.saveImage("loot/unsort");
		}
		
		this.scrins.add(one);
		this.scrins.add(two);
		
	}
	
	private String[] getLootIndices() {
		String loots = "";
		for (Screen screen : scrins) {
			imageParser = new ImageParser(screen, Loot.values());
			imageParser.parse(Screen.GRAY);
			loots+= imageParser.getKey();
		}
		System.out.println(loots);
		return loots.split(",");
	}
	
	@Override
	public String getKey(){
		
		String[] arrayLoots = getLootIndices();
		
		boolean isTakeUnknow = Application.getInstance().TAKE_UNKNOWN();
		Looter looter = new Looter(arrayLoots, isTakeUnknow);
		
		if(looter.isTakeAll()) {
			LOG.info("Loot ok. Take all..");
			return Keyboard.Keys.TAKE;
		}
		
		if(looter.isIgnoreAll()) {
			LOG.info("Trash. Throw out all..");
			return Keyboard.Keys.IGNORE;
		}
		
		if(looter.isTakeByIndex()) {
			LOG.info("Take by index");
			for(LootType type : looter.getLootTypeList()) {
				if(type.isOk()) {
					int index = type.getIndex();
					Touch touch = Application.getInstance().LOOT_TOUCH()[index];
					return "Loot" + touch;
				}
			}
		}
		
		LOG.info("Strategy is not defined. Take..");
		return Keyboard.Keys.TAKE;
	}

	public Screen getOne() {
		return one;
	}

	public Screen getTwo() {
		return two;
	}
	
}