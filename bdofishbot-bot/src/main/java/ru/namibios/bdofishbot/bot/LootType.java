package ru.namibios.bdofishbot.bot;

public class LootType {
	
	private int index;
	
	private boolean isOk;

	private boolean isConfirm;

	private boolean isTrash;
	
	private boolean isUnknown;
	
	private boolean isEmpty;

	LootType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public boolean isConfirm() {
		return isConfirm;
	}

	public void setConfirm(boolean confirm) {
		isConfirm = confirm;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isUnknown() {
		return isUnknown;
	}

	public void setUnknown(boolean isUnknow) {
		this.isUnknown = isUnknow;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public String toString() {
		return "LootIndex [index=" + index + ", isOk=" + isOk + ", isTrash=" + isTrash + ", isUnknown=" + isUnknown
				+ ", isEmpty=" + isEmpty + "]";
	}
	
}