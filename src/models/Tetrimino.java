package models;

import models.Cell;

public class Tetrimino {
	private int[][][] shape;
	private Cell[] grid;
	private int x;
	private int y;
	private int position;

	public Tetrimino(int x, int y, int[][][] shape) {
		grid = new Cell[4];
		this.setX(x);
		this.setY(y);
		this.setShape(shape);
		this.position = 0;
		this.initGrid();
	}

	public void setShape(int[][][] shape) {
		this.shape = shape;
	}

	public int[][][] getShape() {
		return this.shape;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void initGrid() {
		for (int i = 0; i < grid.length; i++) {
			grid[i] = null;
		}
		int cpt = 0;
		for (int i = 0; i < this.shape[position].length; i++) {
			for (int j = 0; j < shape[position][i].length; j++) {
				if (this.shape[position][i][j] != 0
						&& y - (shape[position].length - 1 - i) >= 0) {
					this.grid[cpt] = new Cell(y - (shape[position].length - 1 - i),
							x + j, shape[position][i][j]);
					cpt++;
				}
			}
		}
	}

	public Cell[] getCells() {
		return grid;
	}

	public void setCells(Cell[] cells) {
		grid = cells;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int pos) {
		position = pos;
	}

}