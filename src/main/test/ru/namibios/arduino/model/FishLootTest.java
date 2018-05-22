package ru.namibios.arduino.model;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.utils.Keyboard;

public class FishLootTest {
	
	@Before
	public void init() {
		Application.getInstance();
	}
	
	// ------------------------------ALL FILTER TRUE------------------------------//
	
	// --------------------TAKE ALL--------------------//
	@Test
	public void okEmpty() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/scala/scala.jpg", "resources/loot/ok/empty/empty.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Keyboard.Keys.TAKE, key);
		
	}
	
	@Test
	public void okOk() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/fish/xarius.jpg", "resources/loot/ok/fish/losos.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Keyboard.Keys.TAKE, key);
	}
	
	
	// --------------------TAKE BY INDEX--------------------//
	@Test
	public void OkTrash() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/ok/fish/zerex.jpg","resources/loot/trash/macropod.jpg");
		String key = fishLoot.getKey();
		assertEquals("Loot[1561,616]", key);
	}
	
	@Test
	public void trashOk() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/trash/boot.jpg","resources/loot/ok/fish/ersh.jpg");
		String key = fishLoot.getKey();
		
		assertEquals("Loot[1608,616]", key);
	}
	
	
	// --------------------IGNORE ALL--------------------//
	
	@Test
	public void trashTrash() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/trash/boot.jpg", "resources/loot/trash/mechenosec.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Keyboard.Keys.IGNORE, key);
	}
	
	
	@Test
	public void trashEmpty() throws IOException {
		FishLoot fishLoot = new FishLoot("resources/loot/trash/gorchak.jpg","resources/loot/ok/empty/empty.jpg");
		String key = fishLoot.getKey();
		
		assertEquals(Keyboard.Keys.IGNORE, key);
	} 
	
	@Test
	public void unknowEmpty() throws IOException{
		FishLoot fishLoot = new FishLoot("resources/loot/unknow/20171021_173648_334.jpg", "resources/loot/ok/empty/empty.jpg");
		String key = fishLoot.getKey();
		
		assertEquals("", key);
		
	}
	
}