package ru.namibios.bdofishbot.bot;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.utils.Matrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageParser {

	private final Logger LOG = Logger.getLogger(ImageParser.class);
	
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

	private int findSubImage(){

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
			if (Application.getInstance().DEBUG_IMAGE_PARSER()) {
				LOG.debug("Category: " + Loot.values()[index].name() + "(" + index + ")");
			}
			for (int[][] template : templateNumber) {
				if(!isCorrectrDimension(numberMatrix, template)) continue;
				coef.init(numberMatrix, template);
				coef.calculate(index);
				if (Application.getInstance().DEBUG_IMAGE_PARSER()) {
					LOG.debug(coef);
				}
				if (coef.isFullMatch()) {
					return coef.getRezultIndex();
				}
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
	
	public MatrixTemplate getNameTemplate() {
		for (int[][] numberMatrix : keyList) {
			int foundIndex= compare(numberMatrix);
			if(foundIndex != -1) {
				return collectionTemplate[foundIndex];
			}
		}
		return null;
	}

	public static int[][] mapImageMatrix(File file){

		try {

			BufferedImage image = ImageIO.read(file);
			ImageParser parser = new ImageParser(image);
			parser.parse(Screen.WHITE);
			return parser.getImageMatrix();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null ;
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
					if (collectionTemplate instanceof Loot[]){
						if(value[i][j] != template[i][j] && value[i][j] != 0) valueKoef--;
					}

				}
			}
		}
		
		void calculate(int index) {
			calcKoef = valueKoef / templateKoef;
			boolean isNewKoef = calcKoef > maxCalcKoef;
			if( isNewKoef ){
				rezultIndex = index;
				maxCalcKoef = calcKoef;
				LOG.debug("maxCalcKoef=" + maxCalcKoef);
			}
		}

		boolean isFullMatch(){
			return maxCalcKoef == 1;
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