package ru.namibios.arduino.utils;

public class Matrix {

    private int[][] data;

    private int x, y, columns, rows;

    public Matrix(int[][] data) {
        this(data, 0, 0, data.length, data[0].length);
    }

    private Matrix(int[][] data, int x, int y, int rows, int columns ) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.columns = columns;
        this.rows = rows;
    }

    public Matrix getSubMatrix(int x, int y, int rows, int columns) {
        return new Matrix(data, this.x + x , this.y + y, rows, columns);
    }

    public int[][] get(){
        int row = 0;
        int column = 0;

        int[][] ints = new int[rows][columns];
        for (int i = x; i < x + rows; i++) {
            for (int j = y; j < y + columns; j++) {
                ints[row][column] = data[i][j];
                column++;
            }
            row++;
            column=0;
        }

        return ints;
    }

}
