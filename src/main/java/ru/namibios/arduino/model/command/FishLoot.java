package ru.namibios.arduino.model.command;

import org.apache.log4j.Logger;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.*;
import ru.namibios.arduino.model.template.Loot;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FishLoot implements Command{
	
	private final static Logger LOG = Logger.getLogger(FishLoot.class);

	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	private Screen three;

	private ImageParser imageParser;
	
	public FishLoot(String fileLootOne, String fileLootTwo) throws IOException {
		this.scrins = new ArrayList<>();
		this.scrins.add(new Screen(fileLootOne));
		this.scrins.add(new Screen(fileLootTwo));
		
	}

	public FishLoot(String fileLootOne, String fileLootTwo, String fileLootThree) throws IOException {
		this.scrins = new ArrayList<>();
		this.scrins.add(new Screen(fileLootOne));
		this.scrins.add(new Screen(fileLootTwo));
		this.scrins.add(new Screen(fileLootThree));

	}
	
	public FishLoot() throws AWTException {
		this.scrins = new ArrayList<>();
		
		this.one = new Screen(Application.getInstance().LOOT_SLOT_ONE());
		this.two = new Screen(Application.getInstance().LOOT_SLOT_TWO());
		this.three = new Screen(Application.getInstance().LOOT_SLOT_THREE());

		if(Application.getInstance().SAVE_UNSORT()) {
			this.one.saveImage("loot/unsort");
			this.two.saveImage("loot/unsort");
			this.three.saveImage("loot/unsort");
		}
		
		this.scrins.add(one);
		this.scrins.add(two);
		this.scrins.add(three);

	}
	
	private String[] getLootIndices() {
		String loots = "";
		for (Screen screen : scrins) {
			imageParser = new ImageParser(screen, Loot.values());
			imageParser.parse(Screen.GRAY);

			String key = imageParser.getKey();
			if (key.equals("-1,")) {
				double coefWhite = imageParser.getCoefWhite();
				LOG.debug("Loot cell is not defined");
				if (coefWhite == 0) {
					LOG.debug("CoefWhite = " + coefWhite + ". Replaced on empty cell");
					key = Loot.EMPTY.ordinal() + ",";
				}
			}

			loots+= key;

		}

		LOG.debug("Loot indexes: " + loots);
		return loots.split(",");
	}
	
	@Override
	public String getKey(){
		
		String[] arrayLoots = getLootIndices();

		boolean isTakeUnknown = Application.getInstance().TAKE_UNKNOWN();
		Looter looter = new Looter(arrayLoots, isTakeUnknown);
		
		if(looter.isTakeAll()) {
			LOG.info("Loot ok. Take all..");
			return ShortCommand.TAKE.getKey();
		}

        if(looter.isIgnoreAll()) {
            LOG.info("Trash. Throw out all..");
            return ShortCommand.IGNORE.getKey();
        }

        if(looter.isTakeByIndex()) {
            LOG.info("Take by index");
            String command = "";
            for(LootType type : looter.getLootTypeList()) {
                if(type.isOk()) {
                    int index = type.getIndex();
                    Touch touch = Application.getInstance().LOOT_TOUCH()[index];
                    command+= touch.toCommandLoot();
                }
            }
            return command;
        }
		
		LOG.info("Strategy is not defined. Take..");
		return ShortCommand.TAKE.getKey();
	}

}