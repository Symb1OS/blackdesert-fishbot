package ru.namibios.arduino.model;

import java.util.ArrayList;
import java.util.List;

import ru.namibios.arduino.config.Application;

public class Rod {
	
	private int currentId;
	private List<Touch> rods;

	public Rod(int cntRod) {
		currentId = 0;
		rods = new ArrayList<>();
		
		int x = Application.getInstance().ROD_START_X();
		int y = Application.getInstance().ROD_START_Y();
		
		for (int i = 0; i < cntRod; i++) {
			rods.add(new Touch(x, y));
			x += Application.getInstance().ROD_DX();
			y += Application.getInstance().ROD_DY();
		}
	}
	
	public boolean hasNext(){
		return (currentId < rods.size()) ? true : false;
	}
	
	public Touch getNext(){
		Touch touch = rods.get(currentId);
		currentId++;
		return touch;
	}
	
}