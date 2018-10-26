package ru.namibios.arduino.model;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.template.MatrixTemplate;
import ru.namibios.arduino.utils.Matrix;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageParser {
	
	private int[][] screenMatrix;
	private ArrayList<int[][]> keyList;
	
	private BufferedImage screenShot;
	
	private MatrixTemplate[] collectionTemplate;
	
	private double coefIdentification = Application.getInstance().COEF_IDENTITY();
	
	private int row;
	private int column;
	
	public ImageParser(Screen screen, MatrixTemplate[] matrixTemplate) {
		this.screenShot = screen.getScreenShot();
		this.row = screenShot.getHeight(); 
		this.column = screenShot.getWidth();
		this.screenMatrix = new int[row][column];
		this.collectionTemplate = matrixTemplate;
		this.keyList = new ArrayList<>();
	}
	
	public ImageParser(Screen screen){
		this.screenShot = screen.getScreenShot();
		this.row = screenShot.getHeight();
		this.column = screenShot.getWidth();
		this.screenMatrix = new int[row][column];
		this.keyList = new ArrayList<>();
	}
	
	public ImageParser(BufferedImage screenShot){
		this.screenShot = screenShot;
		this.row = screenShot.getHeight();
		this.column = screenShot.getWidth();
		this.screenMatrix = new int[row][column];
		this.keyList = new ArrayList<>();
	}
	
	public int[][] getImageMatrix() {
		return screenMatrix;
	}
	
	public void parse(Color identificationColor){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Color color = new Color(screenShot.getRGB(j, i));
				
				boolean isIdentified = color.getRed()   > identificationColor.getRed()
									&& color.getGreen() > identificationColor.getGreen()
									&& color.getBlue()  > identificationColor.getBlue();
				
				screenMatrix[i][j] = isIdentified ? 1 : 0;
			}
		}
		
		keyList.add(screenMatrix);
	}

	public int findSubImage(){

		int index = 0;
		for (MatrixTemplate templates : collectionTemplate) {
			List<int[][]> subTemplates = templates.getTemplates();
			for (int[][] template : subTemplates) {
				Coefficient coefficient = subCompare(index, template);
				if (coefficient.isFound()) {
					return coefficient.getRezultIndex();
				}
			}
			index++;
		}

		return -1;
	}

	private Coefficient subCompare(int index, int[][] template) {

		Coefficient coefficient = new Coefficient(coefIdentification);

		int rowTemlate = template.length;
		int columnTemplate = template[0].length;

		int row = screenMatrix.length;
		int column = screenMatrix[0].length;

		int nRow = row - rowTemlate;
		int nColumn = column - columnTemplate;

		Matrix matrix = new Matrix(screenMatrix);
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nColumn; j++) {
				int[][] subMatrix = matrix.getSubMatrix(i, j, rowTemlate, columnTemplate).get();
				coefficient.init(subMatrix, template);
				coefficient.calculate(index);
			}
		}

		return coefficient;
	}

	private int compare(int[][] numberMatrix) {
	
		Coefficient coef = new Coefficient(coefIdentification);
		
		int index = 0;
		while(index < collectionTemplate.length){
			List<int[][]> templateNumber = collectionTemplate[index].getTemplates();
			for (int[][] template : templateNumber) {
				if(!isCorrectrDimension(numberMatrix, template)) continue;
				coef.init(numberMatrix, template);
				coef.calculate(index);
			}
			index++;
		}
		
		return coef.isFound() ? coef.getRezultIndex() : coef.unknowIndex();
	}

	private boolean isCorrectrDimension(int[][] numberMatrix, int[][] template) {
		return (numberMatrix.length == template.length && numberMatrix[0].length == template[0].length);
	}
	
	public double getCoefWhite() {
		double allElement = row * column;
		double countElem = 0;
		for (int i = 0; i < screenMatrix.length; i++) {
			for (int j = 0; j < screenMatrix[0].length; j++) {
				if(screenMatrix[i][j] == 1) countElem++; 
			}
		}
		return countElem / allElement;
	}

	public String getKey() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyList) {
			rezult.append(compare(numberMatrix)).append(",");
		}	
		return rezult.toString();
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
	
	public MatrixTemplate getNameTemplate() {
		for (int[][] numberMatrix : keyList) {
			int foundIndex= compare(numberMatrix);
			if(foundIndex != -1) {
				return collectionTemplate[foundIndex];
			}
		}
		return null;
	}

	public MatrixTemplate getNameTemplateBySubImage() {

		int subImage = findSubImage();
		return subImage != -1 ? collectionTemplate[subImage] : null;
	}
	
	class Coefficient{

		private final double minKoef;
		
		private double maxCalcKoef;
		private double calcKoef;
		
		private double valueKoef;
		private double templateKoef;
		
		private int rezultIndex;
		
		Coefficient(double minKoef) {
			this.minKoef = minKoef; 
			this.calcKoef = 0;
			this.maxCalcKoef = 0;
		}
		
		void init(int[][] value, int[][] template) {
			
			valueKoef = templateKoef = 0;
			for (int i = 0; i < template.length; i++) {
				for (int j = 0; j < template[0].length; j++) {
					if(template[i][j] == 1) templateKoef++;
					if(value[i][j] == template[i][j] && template[i][j] != 0) valueKoef++;
				}
			}
		}
		
		void calculate(int index) {
			calcKoef = valueKoef / templateKoef;
			boolean isNewKoef = calcKoef > maxCalcKoef; 
			if( isNewKoef ){
				rezultIndex = index;
				maxCalcKoef = calcKoef;
			}
		}
		
		boolean isFound() {
			return maxCalcKoef > minKoef;
		}
		
		int unknowIndex() {
			return -1;
		}
		
		public void resetRezultIndex() {
			rezultIndex = -1;
		}
		
		int getRezultIndex() {
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