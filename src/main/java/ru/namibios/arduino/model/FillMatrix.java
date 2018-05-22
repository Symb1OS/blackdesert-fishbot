package ru.namibios.arduino.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ru.namibios.arduino.utils.MatrixUtils;

public class FillMatrix {
	
	private static final Logger logger = Logger.getLogger(FillMatrix.class);

	private static final int OVERFLAW = 10000;

	private static final int MIN_ROW_INDEX = 20;
	
	public static final int SYMBOL_ROW = 14;
	public static final int SYMBOL_COLUMN = 12;
	
	private static final int EMPTY = 0;
	private static final int VALUE = 1;
	
	private int[][] matrix;
	private Map<Integer, MatrixElement> elements;
	
	private int maxRow;
	private int maxColumn;
	
	private int counter;

	private int iteration;
	
	public FillMatrix(int[][] matrix) {
		this.matrix = matrix;
		this.elements = new HashMap<Integer, MatrixElement>();
		
		this.maxRow = matrix.length;
		this.maxColumn = matrix[0].length;
		
		this.counter = 2;
		this.iteration = 0;
	}
	
	private void fill(int row, int column){
		
		if(iteration > OVERFLAW) return;
		iteration++;
		
		//up
		if(row > 0 && matrix[row - 1][column] == VALUE){
			matrix[row - 1][column] = counter;
			fill(row - 1 , column);
		}
		
		//down
		if(row < maxRow - 1 && matrix[row + 1][column] == VALUE){
			matrix[row + 1][column] = counter;
			fill(row + 1 , column);
		}
		
		//left
		if(column > 0 && matrix[row][column - 1] == VALUE){
			matrix[row][column - 1]= counter;
			fill(row , column - 1);
		}
		
		//right
		if(column < maxColumn - 1  && matrix[row][column + 1] == VALUE){
			matrix[row][column + 1] = counter;
			fill(row , column + 1);
		}
	}
	
	public int getCounter(){
		return this.counter;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}

	private void clear(int obj){
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				if(matrix[i][j] == obj) matrix[i][j] = EMPTY;
			}
		}
	}
	
	public void cleanOfBounds(int minCnt, int maxCnt){
		int objCnt= 2;
		
		while(objCnt < counter){
			int sum=0;
			int minIndexRow=maxRow;
			int minIndexColumn= maxColumn;
			int maxIndexRow = 0;
			int maxIndexColumn= 0;
			
			for (int i = 0; i < maxRow; i++) {
				for (int j = 0; j < maxColumn; j++) {
					if(matrix[i][j] == objCnt){
						
						if(minIndexRow > i)    minIndexRow= i;
						if(minIndexColumn > j) minIndexColumn= j; 
						if(maxIndexRow < i)    maxIndexRow= i; 
						if(maxIndexColumn < j) maxIndexColumn= j; 
						
						sum++;
					} 
				}
			}
			int maxRow = maxIndexRow - minIndexRow;
			int maxColumn = maxIndexColumn - minIndexColumn;
			
			boolean isIntervalOk = sum < minCnt || sum > maxCnt;
			boolean isDimensionOk = maxRow < 5 || maxColumn < 5 || maxRow > SYMBOL_ROW || maxColumn > SYMBOL_COLUMN;
			boolean isMinIndexOk = minIndexRow < MIN_ROW_INDEX;
			if(isIntervalOk || isDimensionOk || isMinIndexOk){
				clear(objCnt);
				logger.debug("Clear object=" + objCnt + "| sum= " + sum + " | " + "isIntervalOk " + isIntervalOk + "| isDimensionOk " + isDimensionOk + "| isMinIndexOk " + isMinIndexOk);
			} else{
				MatrixElement element = new MatrixElement(minIndexRow, maxIndexRow, minIndexColumn, maxIndexColumn);
				elements.put(objCnt, element);
				logger.debug("Object " + objCnt + " sum= " + sum + " | " + element.toString());
			}
			objCnt++;
		}
		
	}
	
	@SuppressWarnings("unused")
	private Map<Integer, MatrixElement> debugPrint(Map<Integer, MatrixElement> elements){
		
		Map<Integer, MatrixElement> rezultMap = getSortedMap(elements);
		
		for (Entry<Integer, MatrixElement> entry : rezultMap.entrySet()) {
			MatrixElement e = rezultMap.get(entry.getKey());
			System.out.println(e.toString());
			MatrixUtils.printMatrix(matrix, e);
			System.out.println();
		}
		
		return rezultMap;
	}
	
	private Map<Integer, MatrixElement> getSortedMap(Map<Integer, MatrixElement> elements) {
		
		LinkedList<Entry<Integer, MatrixElement>> list = new LinkedList<Map.Entry<Integer, MatrixElement>>(elements.entrySet());
		
		Collections.sort(list, (o1,o2) -> o1.getValue().getMinColumn().compareTo(o2.getValue().getMinColumn()));
		
		Map<Integer, MatrixElement> result = new LinkedHashMap<Integer, MatrixElement>();
	    for (Map.Entry<Integer, MatrixElement> entry : list) {
	        result.put(entry.getKey(), entry.getValue());
	    }
	    
	    return result;
	}

	public List<int[][]> toListMatrix(){
		List<int[][]> rezult = new ArrayList<int[][]>();
		elements = getSortedMap(elements);
//		debugPrint(elements);
		
		for (int key : elements.keySet()) {
			
			MatrixElement element = elements.get(key);
			
			if(!isCorrectElement(element)) {
				continue;
			} 
			
			int[][] symbol = new int[SYMBOL_ROW][SYMBOL_COLUMN];
			int row=0;
			int column=0;
			for (int i = element.getMinRow(); i <= element.getMaxRow(); i++) {
				column=0;
				for (int j = element.getMinColumn(); j <= element.getMaxColumn(); j++) {
					symbol[row][column] = matrix[i][j] > 0 ? 1: 0;
					column++;
				}
				row++;
			}
			rezult.add(symbol);
		}

		return rezult;
	}
	
	private boolean isCorrectElement(MatrixElement element) {
		logger.debug("Incorrect dimension" + element.toString());
		return element.getColumnLength() < SYMBOL_COLUMN && element.getRowLength() < SYMBOL_ROW;
	}

	public void markupMatrix(){
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				if(matrix[i][j] == VALUE){
					fill(i, j);
					counter++;
				}
			}
		}
	}
	
}