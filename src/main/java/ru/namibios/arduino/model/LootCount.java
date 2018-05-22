package ru.namibios.arduino.model;

public class LootCount {

	private int ok;
	private int trash;
	private int empty;
	private int unknow;
	
	private int length;

	public LootCount(int length) {
		this.length = length;
		ok = 0;
		trash = 0;
		empty = 0;
		unknow = 0;
	}

	public void incOk() {
		ok++;
	}

	public void incTrash() {
		trash++;
	}

	public void incEmpty() {
		empty++;
	}

	public void incUnknow() {
		unknow++;
	}
	
	public int getOk() {
		return ok;
	}

	public int getTrash() {
		return trash;
	}

	public int getEmpty() {
		return empty;
	}

	public int getUnknow() {
		return unknow;
	}

	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return "LootCount [ok=" + ok + ", trash=" + trash + ", empty=" + empty + ", unknow=" + unknow + ", length="
				+ length + "]";
	}
}