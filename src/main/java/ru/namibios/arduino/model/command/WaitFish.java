package ru.namibios.arduino.model.command;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;

import java.awt.*;

public class WaitFish implements Command{

    private Screen screen;
	
	private ImageParser imageParser;

	public WaitFish() {}

	@Override
	public String getKey() {

		try {

			return getKeyByRegion(Application.getInstance().SPACE_OFFSET_X(), Application.getInstance().SPACE_OFFSET_Y());

		} catch (AWTException e) {
			e.printStackTrace();
		}

		return ShortCommand.IGNORE.getKey();
	}

	private String getRegionKey(Rectangle region) throws AWTException {

		screen = new Screen(region);
		imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(Screen.WHITE);

        if (Application.getInstance().DEBUG_SCREEN() || Application.getInstance().DEBUG_WAITFISH()) {
            screen.saveImage(Path.DEBUG_WAITFISH);
        }

//		MatrixUtils.printMatrix(imageParser.getImageMatrix(), "0");

		return imageParser.getNumber();
	}

	private String getKeyByRegion(int offsetX, int offsetY) throws AWTException {

		Rectangle region = Application.getInstance().SPACE();

		int startX = region.x;
		int startY = region.y;

		String key = "";

		for (int x = startX; x <= startX + offsetX; x++) {
			region.x = x;
			for (int y = startY; y <= startY + offsetY; y++) {
				region.y = y;
				key = getRegionKey(region);
				if (!key.isEmpty()) {
					return key;
				}
			}
		}

		return key;
	}

}