package ru.namibios.arduino.model;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.template.Loot;

import java.util.ArrayList;
import java.util.List;

public class Looter {

	private static final int UNKNOWN = -1;

	private List<LootType> lootTypeList;

    private LootCount count;
	
	public Looter(String[] slots, boolean isTakeUnknown) {

        List<Integer> lootOk = new ArrayList<>();
        List<Integer> lootConfirm = new ArrayList<>();
        List<Integer> lootTrash = new ArrayList<>();
		this.lootTypeList = new ArrayList<>();
		
		if(Application.getInstance().ROCK())  lootOk.add(Loot.SCALA.ordinal()); else lootTrash.add(Loot.SCALA.ordinal());
		if(Application.getInstance().KEY())   lootOk.add(Loot.KEY.ordinal());   else lootTrash.add(Loot.KEY.ordinal());
		if(Application.getInstance().FISH())  lootOk.add(Loot.FISH.ordinal());  else lootTrash.add(Loot.FISH.ordinal());
		if(Application.getInstance().EVENT()) lootOk.add(Loot.EVENT.ordinal()); else lootTrash.add(Loot.EVENT.ordinal());
		if(Application.getInstance().CONFIRM()) lootConfirm.add(Loot.CONFIRM.ordinal()); else lootTrash.add(Loot.CONFIRM.ordinal());

		lootTrash.add(Loot.TRASH.ordinal());

		for (int index = 0; index < slots.length; index++) {

			int slot = Integer.parseInt(slots[index]);

			LootType lootType = new LootType(index);
			for (Integer okIndex : lootOk) {
				if(slot == okIndex) lootType.setOk(true);
			}

			for (Integer confirmIndex : lootConfirm) {
				if (slot == confirmIndex) lootType.setConfirm(true);
			}

			for (Integer trashIndex : lootTrash) {
				if(slot == trashIndex) lootType.setTrash(true);
			}

			if (slot  == UNKNOWN) {
				if(isTakeUnknown) {
					lootType.setOk(true);
				}else {
					lootType.setTrash(true);
				}
			}

			if(slot == Loot.EMPTY.ordinal()) lootType.setEmpty(true);
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