package ru.namibios.arduino.model;

public class MatrixElement {
	
	private Integer minRow;
	private Integer maxRow;
	
	private Integer minColumn;
	private Integer maxColumn;
	
	private Integer rowLength;
	private Integer columnLength;
	
	public MatrixElement(int minRow, int maxRow, int minColumn, int maxColumn) {
		this.minRow = minRow;
		this.maxRow = maxRow;
		this.minColumn = minColumn;
		this.maxColumn = maxColumn;
		this.rowLength = getRowLength();
		this.columnLength = getColumnLength();
	}

	public Integer getMinRow() {
		return minRow;
	}

	public void setMinRow(int minRow) {
		this.minRow = minRow;
	}

	public Integer getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public Integer getMinColumn() {
		return minColumn;
	}

	public void setMinColumn(int minColumn) {
		this.minColumn = minColumn;
	}

	public Integer getMaxColumn() {
		return maxColumn;
	}

	public void setMaxColumn(int maxColumn) {
		this.maxColumn = maxColumn;
	}
	
	public Integer getColumnLength() {
		this.columnLength = maxColumn - minColumn; 
		return columnLength;
	}
	
	public Integer getRowLength() {
		this.rowLength = maxRow - minRow;
		return rowLength;
	}
	
	@Override
	public String toString() {
		return "MatrixElement [minRow=" + minRow + ", maxRow=" + maxRow + ", minColumn=" + minColumn + ", maxColumn="
				+ maxColumn + ", rowLength=" + rowLength + ", columnLength=" + columnLength + "]";
	}
	
}