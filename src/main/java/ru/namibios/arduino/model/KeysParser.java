package ru.namibios.arduino.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.template.MatrixTemplate;
import ru.namibios.arduino.utils.MatrixUtils;

public class KeysParser {
	
	private static final Logger logger=  Logger.getLogger(ImageParser.class);
	
	private static final double CHARS_MIN_KOEF = 0.88;
	
	private int[][] imageMatrix;
	private ArrayList<int[][]> keyList;

	private MatrixTemplate[] collectionTemplate;
	
	public KeysParser(int[][] imageMatrix, MatrixTemplate[] matrixTemplate){
		this.imageMatrix = imageMatrix;
		this.keyList = new ArrayList<int[][]>();
		this.collectionTemplate = matrixTemplate;
	}
	
	public void parse(){
		
		FillMatrix fillMatrix = new FillMatrix(imageMatrix);
		fillMatrix.markupMatrix();
		fillMatrix.cleanOfBounds(36, 100);
		imageMatrix = fillMatrix.getMatrix();
		
		MatrixUtils.printMatrix(imageMatrix);
		
		List<int[][]> list = fillMatrix.toListMatrix();
		keyList = new ArrayList<int[][]>(list);
		
		for (int[][] template : list) {
			MatrixUtils.printTemplate(template);
		}
	}
	
	public int[][] getImageMatrix(){
		return imageMatrix;
	}
			
	private int compare(int[][] numberMatrix) {
		
		Coefficient coef = new Coefficient(CHARS_MIN_KOEF);
		
		int index = 0;
		while(index < collectionTemplate.length){
			List<int[][]> templateNumber = collectionTemplate[index].getTemplates(); 
			logger.debug("Index " + index);
			for (int[][] template : templateNumber) {
				if(!isCorrectrDimension(numberMatrix, template)) continue;
				coef.init(numberMatrix, template);
				coef.calculate(index);
				logger.debug(coef);
			}
			
			if(coef.isFound()) break; else coef.resetRezultIndex();
			index++;
		}
		
		return coef.getRezultIndex();
	}

	private boolean isCorrectrDimension(int[][] numberMatrix, int[][] template) {
		return (numberMatrix.length == template.length && numberMatrix[0].length == template[0].length);
	}

	public String getKey() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyList) {
			rezult.append(compare(numberMatrix));
		}	
		return rezult.toString().replace("-1", "");
	}
	 
	public String getNumber() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyList) {
			int rezultIndex = compare(numberMatrix);
			if(rezultIndex != -1){
				rezult.append(collectionTemplate[rezultIndex]);
			}
		}	
		return rezult.toString();
	}
	
	class Coefficient{

		private final double minKoef;
		
		private double maxCalcKoef;
		private double calcKoef;
		
		private double valueKoef;
		private double templateKoef;
		
		private int rezultIndex;
		
		public Coefficient(double minKoef) {
			this.minKoef = minKoef; 
			this.calcKoef = 0;
			this.maxCalcKoef = 0;
		}
		
		public void init(int[][] value, int[][] template) {
			
			valueKoef = 0;
			templateKoef = 0;
			for (int i = 0; i < template.length; i++) {
				for (int j = 0; j < template[0].length; j++) {
					if(template[i][j] == 1) templateKoef++;
					if(value[i][j] == template[i][j] && template[i][j] != 0) valueKoef++;
				}
			}
		}
		
		public void calculate(int index) {
			calcKoef = valueKoef / templateKoef;
			boolean isNewKoef = calcKoef > maxCalcKoef; 
			if( isNewKoef ){
				rezultIndex = index;
				maxCalcKoef = calcKoef;
			}
		}
		
		public boolean isFound() {
			return maxCalcKoef > minKoef;
		}
		
		public void resetRezultIndex() {
			rezultIndex = -1;
		}
		
		public int getRezultIndex() {
			return rezultIndex;
		}

		@Override
		public String toString() {
			return "Coefficient [minKoef=" + minKoef + ", maxCalcKoef=" + maxCalcKoef + ", calcKoef=" + calcKoef
					+ ", valueKoef=" + valueKoef + ", templateKoef=" + templateKoef + ", rezultIndex=" + rezultIndex
					+ "]";
		}
	}
}