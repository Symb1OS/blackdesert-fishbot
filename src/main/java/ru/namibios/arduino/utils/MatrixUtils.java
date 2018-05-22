package ru.namibios.arduino.utils;

public class MatrixUtils {

	private MatrixUtils() {}
	
	public static void printMatrix(int[][] tmp){
		int row = tmp.length;
		int column = tmp[0].length;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(tmp[i][j]!= 0 ? 1 : " ");
			}
			System.out.println();
		}
	}
	
	public static void printTemplate(int[][] tmp){
		int row = tmp.length;
		int column = tmp[0].length;
		
		System.out.println("new int[][]{");
		for (int i = 0; i < row; i++) {
			System.out.print("{");
			for (int j = 0; j < column; j++) {
				System.out.print((tmp[i][j] == 0 ? "0" : "1") + ", ");
			}
			System.out.print("},");
			System.out.println();
		}
		System.out.println("}");
		System.out.println();
	}
}
