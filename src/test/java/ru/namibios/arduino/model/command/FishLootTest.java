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
	public void testOkEmpty() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/scala/scala.jpg", "resources/loot/ok/empty/empty.jpg");
		String key = fishLoot.getKey();

		assertEquals(ShortCommand.TAKE.getKey(), key);

	}

	@Test
	public void testOkOk() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/fish/xarius.jpg", "resources/loot/ok/fish/losos.jpg");
		String key = fishLoot.getKey();

		assertEquals(ShortCommand.TAKE.getKey(), key);
	}


	// --------------------TAKE BY INDEX--------------------//
	@Test
	public void testOkTrash() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/fish/zerex.jpg","resources/loot/trash/macropod.jpg");
		String key = fishLoot.getKey();

		assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot(), key);
	}
	
	@Test
	public void testTrashOk() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/trash/boot.jpg","resources/loot/ok/fish/ersh.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot(), key);
	}
	
	
	// --------------------IGNORE ALL--------------------//
	
	@Test
	public void testTrashTrash() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/trash/boot.jpg", "resources/loot/trash/mechenosec.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(ShortCommand.IGNORE.getKey(), key);
	}
	
	
	@Test
	public void testTrashEmpty() throws IOException {
		FishLoot fishLoot = new FishLoot("resources/loot/trash/gorchak.jpg","resources/loot/ok/empty/empty.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(ShortCommand.IGNORE.getKey(), key);
	}

}