package ru.namibios.bdofishbot.bot;

import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.bot.template.LootFrame;
import ru.namibios.bdofishbot.cli.Application;

import java.util.ArrayList;
import java.util.List;

public class Looter {

	private static final int UNKNOWN = -1;

	private List<LootType> lootTypeList;

    private LootCount count;
	
	public Looter(String[] slots, String[] frames, boolean isTakeUnknown) {

		List<Integer> lootFrameOk = new ArrayList<>();
		List<Integer> lootFrameTrash = new ArrayList<>();

        List<Integer> lootOk = new ArrayList<>();
        List<Integer> lootConfirm = new ArrayList<>();
        List<Integer> lootTrash = new ArrayList<>();
		this.lootTypeList = new ArrayList<>();

		if (Application.getInstance().RED_FRAME()) lootFrameOk.add(LootFrame.RED.ordinal()); else lootFrameTrash.add(LootFrame.RED.ordinal());
		if (Application.getInstance().GOLD_FRAME()) lootFrameOk.add(LootFrame.GOLD.ordinal()); else lootFrameTrash.add(LootFrame.GOLD.ordinal());
		if (Application.getInstance().BLUE_FRAME()) lootFrameOk.add(LootFrame.BLUE.ordinal()); else lootFrameTrash.add(LootFrame.BLUE.ordinal());
		if (Application.getInstance().GREEN_FRAME()) lootFrameOk.add(LootFrame.GREEN.ordinal()); else lootFrameTrash.add(LootFrame.GREEN.ordinal());
		if (Application.getInstance().GRAY_FRAME()) lootFrameOk.add(LootFrame.GRAY.ordinal()); else lootFrameTrash.add(LootFrame.GRAY.ordinal());

		if (Application.getInstance().USEFULL()) lootOk.add(Loot.USEFULL.ordinal()); else lootTrash.add(Loot.USEFULL.ordinal());
		if (Application.getInstance().CONFIRM()) lootConfirm.add(Loot.CONFIRM.ordinal()); else lootTrash.add(Loot.CONFIRM.ordinal());

		lootTrash.add(Loot.EXCEPTION.ordinal());

		for (int index = 0; index < slots.length; index++) {

			int slot = Integer.parseInt(slots[index]);
			int frame = Integer.parseInt(frames[index]);

			LootType lootType = new LootType(index);

			for (Integer trashIndex : lootTrash) {
				if (slot == trashIndex) lootType.setTrash(true);
			}

			for (Integer okIndex : lootOk) {
				if (slot == okIndex) lootType.setOk(true);
			}

			for (Integer confirmIndex : lootConfirm) {
				if (slot == confirmIndex) lootType.setConfirm(true);
			}

			if(slot == Loot.EMPTY.ordinal()) lootType.setEmpty(true);

			if (slot  == UNKNOWN) {

				for (Integer okIndex : lootFrameOk) {
					if (frame == okIndex) lootType.setOk(true);
				}

				for (Integer trashIndex : lootFrameTrash) {
					if (frame == trashIndex) lootType.setTrash(true);
				}

			}

			lootTypeList.add(lootType);
		}
		
		int length = lootTypeList.size();
		count = new LootCount(length);
		for (LootType lootType : lootTypeList) {
			if (lootType.isOk()) count.incOk();
			if (lootType.isConfirm()) count.incOk();
			if (lootType.isEmpty()) count.incEmpty();
			if (lootType.isTrash()) count.incTrash();
			if (lootType.isUnknown()) count.incUnknow();
		}

	}
	
	public List<LootType> getLootTypeList() {
		return lootTypeList;
	}

	public void info() {
		System.out.println("getOk = " + count.getOk());
		System.out.println("getTrash = " + count.getTrash());
		System.out.println("getUnknow = " + count.getUnknow());
		System.out.println("getEmpty = " + count.getEmpty());

	}

	public boolean isTakeAll() {
		return (count.getOk() + count.getEmpty() == count.getLength());
	}

	public boolean isIgnoreAll(){
		return (count.getTrash() + count.getEmpty() == count.getLength());
	}

	public boolean isTakeByIndex() {
		return (count.getOk() > 0 && count.getOk() < count.getLength());
	}

}