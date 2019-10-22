package ru.namibios.bdofishbot.bot.command;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.config.Path;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Ignore
public class FishLootTest {
	
	@Before
	public void init() {
		Application.getInstance();
		Application.getInstance().setProperty("bot.loot.rock", "true");
		Application.getInstance().setProperty("bot.loot.key",  "true");
		Application.getInstance().setProperty("bot.loot.fish", "true");
		Application.getInstance().setProperty("bot.loot.event","true");
		Application.getInstance().setProperty("bot.loot.confirm","true");

	}

    private String ok;
    private String confirm;
    private String empty;
    private String trash;

    @Before
    public void before() {
        ok      = Path.TEST_RESOURCES + "parsing/loot/ok/scala/scala.jpg";
        empty   = Path.TEST_RESOURCES + "parsing/loot/ok/empty/empty.jpg";
        confirm = Path.TEST_RESOURCES + "parsing/loot/ok/confirm/archer_seal_2.jpg";
        trash   = Path.TEST_RESOURCES + "parsing/loot/trash/1.jpg";

    }

    // ------------------------------ALL FILTER TRUE------------------------------//

	// --------------------TAKE ALL--------------------//

    @Test
    public void testOkEmptyEmpty() throws IOException{
        FishLoot fishLoot = new FishLoot(ok, empty, empty);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.TAKE.getKey(), key);
    }

    @Test
    public void testOkOkEmpty() throws IOException{
        FishLoot fishLoot = new FishLoot(ok, ok, empty);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.TAKE.getKey(), key);
    }

    @Test
    public void testOkOkOK() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, ok, ok);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.TAKE.getKey(), key);
    }

    @Test
    public void testOkMax() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, ok, ok, ok, ok, ok, ok, ok);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.TAKE.getKey(), key);
    }

    // --------------------TAKE BY INDEX--------------------//

    @Test
    public void testOkConfirmTrash() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, confirm, trash);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot() +
                Application.getInstance().LOOT_TOUCH()[1].toCommandConfirmLoot(), key);
    }

    @Test
    public void testConfirmTrashOk() throws IOException {
        FishLoot fishLoot = new FishLoot(confirm, trash, ok);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandConfirmLoot() +
                Application.getInstance().LOOT_TOUCH()[2].toCommandLoot(), key);
    }

    @Test
    public void testOkTrashEmpty() throws IOException{
        FishLoot fishLoot = new FishLoot(ok, trash, empty);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot(), key);
    }
	
	@Test
	public void testTrashOkEmpty() throws IOException{
		FishLoot fishLoot = new FishLoot(trash, ok, empty);
		String key = fishLoot.getKey();
		
		assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot(), key);
	}

    @Test
    public void testOkOkTrash() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, ok, trash);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot()
                + Application.getInstance().LOOT_TOUCH()[1].toCommandLoot(), key);
    }

    @Test
    public void testOkTrashTrash() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, trash, trash);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot(), key);
    }

    @Test
    public void testTrashOkTrash() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, ok, trash);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot(), key);
    }

    @Test
    public void testTrashTrashOk() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, trash, ok);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[2].toCommandLoot(), key);
    }

    @Test
    public void testOkTrashOk() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, trash, ok);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot() +
                Application.getInstance().LOOT_TOUCH()[2].toCommandLoot(), key);
    }

    @Test
    public void testTrashOkOk() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, ok, ok);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot() +
                Application.getInstance().LOOT_TOUCH()[2].toCommandLoot(), key);
    }

    @Test
    public void testOkTrashX4() throws IOException {
        FishLoot fishLoot = new FishLoot(ok, trash, ok, trash, ok, trash, ok, trash);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandLoot() +
                Application.getInstance().LOOT_TOUCH()[2].toCommandLoot()+
                Application.getInstance().LOOT_TOUCH()[4].toCommandLoot()+
                Application.getInstance().LOOT_TOUCH()[6].toCommandLoot(), key);
    }

    @Test
    public void testOkTrashX4Reverse() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, ok, trash, ok, trash, ok, trash, ok);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandLoot() +
                Application.getInstance().LOOT_TOUCH()[3].toCommandLoot()+
                Application.getInstance().LOOT_TOUCH()[5].toCommandLoot()+
                Application.getInstance().LOOT_TOUCH()[7].toCommandLoot(), key);
    }

    @Test
    public void testConfirmTrashX4() throws IOException {
        FishLoot fishLoot = new FishLoot(confirm, trash, confirm, trash, confirm, trash, confirm, trash);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[0].toCommandConfirmLoot() +
                Application.getInstance().LOOT_TOUCH()[2].toCommandConfirmLoot()+
                Application.getInstance().LOOT_TOUCH()[4].toCommandConfirmLoot()+
                Application.getInstance().LOOT_TOUCH()[6].toCommandConfirmLoot(), key);
    }

    @Test
    public void testConfirmTrashX4Reverse() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, confirm, trash, confirm, trash, confirm, trash, confirm);
        String key = fishLoot.getKey();

        assertEquals(Application.getInstance().LOOT_TOUCH()[1].toCommandConfirmLoot() +
                Application.getInstance().LOOT_TOUCH()[3].toCommandConfirmLoot()+
                Application.getInstance().LOOT_TOUCH()[5].toCommandConfirmLoot()+
                Application.getInstance().LOOT_TOUCH()[7].toCommandConfirmLoot(), key);
    }

    // --------------------IGNORE ALL--------------------//

    @Test
    public void testTrashEmptyEmpty() throws IOException{
        FishLoot fishLoot = new FishLoot(trash, empty, empty);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.IGNORE.getKey(), key);
    }

    @Test
    public void testEmptyX8() throws IOException{
        FishLoot fishLoot = new FishLoot(trash, empty, empty, empty, empty, empty, empty, empty);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.IGNORE.getKey(), key);
    }

    @Test
    public void testTrashTrashEmpty() throws IOException{
        FishLoot fishLoot = new FishLoot(trash, trash, empty);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.IGNORE.getKey(), key);
    }

    @Test
    public void testTrashTrashTrash() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, trash, trash);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.IGNORE.getKey(), key);
    }

    @Test
    public void testTrashX8() throws IOException {
        FishLoot fishLoot = new FishLoot(trash, trash, trash, trash, trash, trash, trash, trash);
        String key = fishLoot.getKey();

        assertEquals(ShortCommand.IGNORE.getKey(), key);
    }
}