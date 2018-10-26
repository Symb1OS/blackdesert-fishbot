package ru.namibios.arduino.model.command;


import org.junit.Before;
import org.junit.Test;
import ru.namibios.arduino.config.Application;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FishLootTest {
	
	@Before
	public void init() {
		Application.getInstance();
		Application.getInstance().setProperty("bot.loot.rock", "true");
		Application.getInstance().setProperty("bot.loot.key",  "true");
		Application.getInstance().setProperty("bot.loot.fish", "true");
		Application.getInstance().setProperty("bot.loot.event","true");

	}

	// ------------------------------ALL FILTER TRUE------------------------------//

	// --------------------TAKE ALL--------------------//

	@Test
	public void testOkOk() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/scala/scala.jpg", "resources/loot/ok/event/1.jpg");
		String key = fishLoot.getKey();

		assertEquals(ShortCommand.TAKE.getKey(), key);
	}


	// --------------------TAKE BY INDEX--------------------//
	@Test
	public void testOkTrash() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/key/key.jpg","resources/loot/trash/1.jpg");
		String key = fishLoot.getKey();

		assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot(), key);
	}
	
	@Test
	public void testTrashOk() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/trash/2.jpg","resources/loot/ok/scala/scala.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot(), key);
	}
	
	
	// --------------------IGNORE ALL--------------------//
	
	@Test
	public void testTrashTrash() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/trash/1.jpg", "resources/loot/trash/2.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(ShortCommand.IGNORE.getKey(), key);
	}

}