package ru.namibios.bdofishbot.bot.command;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.*;
import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.bot.template.LootFrame;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Path;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FishLoot implements Command{

	private final static Logger LOG = Logger.getLogger(FishLoot.class);

	private static final String UNKNOWN_INDEX = "-1";

	private List<Screen> screens;

	private List<Screen> colorScreens;

	public FishLoot(String... loot) throws IOException {
		if (loot.length <= 0 || loot.length > 8) {
			throw new IllegalArgumentException("Expected 1-8 files.. Actual " + loot.length);
		}

		this.screens = new ArrayList<>();
		for (String filename : loot) {
			this.screens.add(new Screen(filename));
		}

	}

	private FishLoot(final String fullscreen) {

		Rectangle[] rectangles = Application.getInstance().LOOT_SLOT_LIST();

		this.screens = Arrays.stream(rectangles)
				.map(rectangle -> toScreen(fullscreen, rectangle, true))
				.collect(Collectors.toList());

		rectangles = Application.getInstance().LOOT_SLOT_LIST_COLOR();

		this.colorScreens = Arrays.stream(rectangles)
				.map(rectangle -> toScreen(fullscreen, rectangle, false))
				.collect(Collectors.toList());

	}

	public FishLoot() {
		LOG.info("Init filter");

		Rectangle[] rectangles = Application.getInstance().LOOT_SLOT_LIST();

		this.screens = Arrays.stream(rectangles)
				.map(rectangle -> toScreen(rectangle, true))
				.collect(Collectors.toList());

		rectangles = Application.getInstance().LOOT_SLOT_LIST_COLOR();

		this.colorScreens = Arrays.stream(rectangles)
				.map(rectangle -> toScreen(rectangle, false))
				.collect(Collectors.toList());

	}

	private Screen toScreen(String fullscreen, Rectangle rectangle, boolean isGray) {

		if (fullscreen != null) {

			try {

				return new Screen(fullscreen, rectangle, isGray);

			} catch (IOException e) {
				LOG.error(ExceptionUtils.getString(e));
			}

		}

		return null;
	}

    private Screen toScreen(Rectangle rectangle, boolean gray) {

	    try {

            return new Screen(rectangle, gray);

        } catch (AWTException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

        return null;
    }

    private String[] getLootFrames(){

		StringBuilder colorLoots = new StringBuilder();

		for (Screen colorScreen : colorScreens) {

			PaletteParser parser = new PaletteParser(colorScreen, LootFrame.values());
			parser.parse(PaletteParser.LOOT_FRAME_PALETTE);

			MatrixTemplate value = parser.getValue();
			LootFrame lootFrame = LootFrame.valueOf(value.toString());
			colorLoots.append(lootFrame.ordinal()).append(",");
		}

		return colorLoots.toString().split(",");
	}

    private String[] getLootIndices() {

		String loots = "";

		for (Screen screen : screens) {
			ImageParser imageParser = new ImageParser(screen, Loot.values());
			imageParser.parse(Screen.GRAY);

			String key = imageParser.getKey();
			if (key.equals("-1,")) {
				double coefWhite = imageParser.getCoefWhite();
				LOG.debug("Loot cell is not defined");
				if (coefWhite < 0.05) {
					LOG.debug("CoefWhite = " + coefWhite + ". Replaced on empty cell");
					key = Loot.EMPTY.ordinal() + ",";
				}
			}

			loots+= key;

		}

		return loots.split(",");
	}

	private void saveLoot(String[] arrayLoots){

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_FILTER_LOOT()) {
			try {
				Screen screen = new Screen(Application.getInstance().FULL_SCREEN(), false);
				screen.saveImage(Path.DEBUG_FILTERLOOT);
			} catch (AWTException e) {
				LOG.error(ExceptionUtils.getString(e));
			}
		}

		if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_FILTER_LOOT()) {
			screens.forEach(screen -> screen.saveImage(Path.DEBUG_FILTERLOOT));
		}

		if (Application.getInstance().SAVE_UNKNOWN()) {
			for (int i = 0; i < arrayLoots.length; i++) {
				if (arrayLoots[i].equals(UNKNOWN_INDEX)) {
					screens.get(i).saveImage(Path.LOOT_UNKNOWN);
				}
			}
		}

	}
	
	@Override
	public String getKey(){
		
		String[] arrayLoots = getLootIndices();
		LOG.info("Loot types: " + Loot.toString(arrayLoots));

		String[] lootFrames = getLootFrames();
		LOG.info("Loot frames: " + LootFrame.toString(lootFrames));

		saveLoot(arrayLoots);

		boolean isTakeUnknown = Application.getInstance().TAKE_UNKNOWN();
		Looter looter = new Looter(arrayLoots, lootFrames, isTakeUnknown);

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
                if(type.isOk() || type.isConfirm()) {
                    int index = type.getIndex();
                    Touch touch = Application.getInstance().LOOT_TOUCH()[index];
                    command+= type.isOk() ? touch.toCommandLoot() : touch.toCommandConfirmLoot();
                }
            }
            LOG.info(command);
            return command;
        }
		
		LOG.info("Strategy is not defined. Take..");
		return ShortCommand.TAKE.getKey();
	}

}