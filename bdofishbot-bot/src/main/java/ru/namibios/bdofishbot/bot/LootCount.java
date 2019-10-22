package ru.namibios.bdofishbot.bot;

public class LootCount {

	private int ok;
	private int trash;
	private int empty;
	private int unknow;
	
	private int length;

	LootCount(int length) {
		this.length = length;
		ok = 0;
		trash = 0;
		empty = 0;
		unknow = 0;
	}

	void incOk() {
		ok++;
	}

	void incTrash() {
		trash++;
	}

	void incEmpty() {
		empty++;
	}

	void incUnknow() {
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