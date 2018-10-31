package ru.namibios.arduino.model.command;


import org.junit.Before;
import org.junit.Test;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.config.Path;

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
	public void testOkEmpty() throws IOException{
		FishLoot fishLoot = new FishLoot(Path.TEST_RESOURCES + "parsing/loot/ok/scala/scala.jpg", Path.TEST_RESOURCES + "parsing/loot/ok/empty/empty.jpg");
		String key = fishLoot.getKey();

		assertEquals(ShortCommand.TAKE.getKey(), key);
	}

	@Test
	public void testOkOk() throws IOException{
		FishLoot fishLoot = new FishLoot(Path.TEST_RESOURCES + "parsing/loot/ok/scala/scala.jpg", Path.TEST_RESOURCES + "parsing/loot/ok/event/1.jpg");
		String key = fishLoot.getKey();

		assertEquals(ShortCommand.TAKE.getKey(), key);
	}


	// --------------------TAKE BY INDEX--------------------//
	@Test
	public void testOkTrash() throws IOException{
		FishLoot fishLoot = new FishLoot(Path.TEST_RESOURCES + "parsing/loot/ok/key/key.jpg", Path.TEST_RESOURCES + "parsing/loot/trash/1.jpg");
		String key = fishLoot.getKey();

		assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot(), key);
	}
	
	@Test
	public void testTrashOk() throws IOException{
		FishLoot fishLoot = new FishLoot(Path.TEST_RESOURCES + "parsing/loot/trash/2.jpg", Path.TEST_RESOURCES + "parsing/loot/ok/scala/scala.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot(), key);
	}
	
	
	// --------------------IGNORE ALL--------------------//
	
	@Test
	public void testTrashTrash() throws IOException{
		FishLoot fishLoot = new FishLoot(Path.TEST_RESOURCES + "parsing/loot/trash/1.jpg", Path.TEST_RESOURCES + "parsing/loot/trash/2.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(ShortCommand.IGNORE.getKey(), key);
	}

}