package ru.namibios.bdofishbot.bot;

import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.bot.template.LootFrame;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;

import java.util.ArrayList;
import java.util.List;

public class Looter {

	private List<LootType> lootTypeList;

    private LootCount count;
	
	public Looter(List<MatrixTemplate> slots, List<MatrixTemplate> frames, boolean isTakeUnknown) {

		List<MatrixTemplate> lootFrameOk = new ArrayList<>();
		List<MatrixTemplate> lootFrameTrash = new ArrayList<>();

        List<MatrixTemplate> lootOk = new ArrayList<>();
        List<MatrixTemplate> lootConfirm = new ArrayList<>();
        List<MatrixTemplate> lootTrash = new ArrayList<>();
		this.lootTypeList = new ArrayList<>();

		if (Application.getInstance().RED_FRAME()) lootFrameOk.add(LootFrame.RED); else lootFrameTrash.add(LootFrame.RED);
		if (Application.getInstance().GOLD_FRAME()) lootFrameOk.add(LootFrame.GOLD); else lootFrameTrash.add(LootFrame.GOLD);
		if (Application.getInstance().BLUE_FRAME()) lootFrameOk.add(LootFrame.BLUE); else lootFrameTrash.add(LootFrame.BLUE);
		if (Application.getInstance().GREEN_FRAME()) lootFrameOk.add(LootFrame.GREEN); else lootFrameTrash.add(LootFrame.GREEN);
		if (Application.getInstance().GRAY_FRAME()) lootFrameOk.add(LootFrame.GRAY); else lootFrameTrash.add(LootFrame.GRAY);

		if (Application.getInstance().USEFULL()) lootOk.add(Loot.USEFULL); else lootTrash.add(Loot.USEFULL);
		if (Application.getInstance().CONFIRM()) lootConfirm.add(Loot.CONFIRM); else lootTrash.add(Loot.CONFIRM);

		lootTrash.add(Loot.EXCEPTION);

		for (int index = 0; index < slots.size(); index++) {

			MatrixTemplate slot = slots.get(index);
			MatrixTemplate frame = frames.get(index);

			LootType lootType = new LootType(index);

			for (MatrixTemplate trash : lootTrash) {
				if (slot == trash) lootType.setTrash(true);
			}

			for (MatrixTemplate ok : lootOk) {
				if (slot == ok) lootType.setOk(true);
			}

			for (MatrixTemplate confirm : lootConfirm) {
				if (slot == confirm) lootType.setConfirm(true);
			}

			if (slot == Loot.EMPTY) lootType.setEmpty(true);

			if (slot == null) {

				for (MatrixTemplate frameOk : lootFrameOk) {
					if (frame == frameOk) lootType.setOk(true);
				}

				for (MatrixTemplate frameTrash : lootFrameTrash) {
					if (frame == frameTrash) lootType.setTrash(true);
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