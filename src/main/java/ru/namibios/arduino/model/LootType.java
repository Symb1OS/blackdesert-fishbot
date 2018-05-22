package ru.namibios.arduino.model;

public class LootType {
	
	private int index;
	
	private boolean isOk;
	
	private boolean isTrash;
	
	private boolean isUnknow;
	
	private boolean isEmpty;

	public LootType(int index) {
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

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isUnknow() {
		return isUnknow;
	}

	public void setUnknow(boolean isUnknow) {
		this.isUnknow = isUnknow;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public String toString() {
		return "LootIndex [index=" + index + ", isOk=" + isOk + ", isTrash=" + isTrash + ", isUnknow=" + isUnknow
				+ ", isEmpty=" + isEmpty + "]";
	}
	
}