package models;

import java.util.ArrayList;
import java.util.Observable;

public class ModelObject extends Observable implements Runnable {

	private Grid grid;
	private Tetrimino next;
	private Tetrimino hold;
	private Tetrimino currentTetrimino;
	private int score;
	private int level;
	private Boolean pause;

	public ModelObject() {
		grid = new Grid(10, 22);
		this.setCurrentTerimino(new Tetrimino(3, 0, Shapes.newShape()));
		this.SetNext(new Tetrimino(3, 0, Shapes.newShape()));
		this.pause = false;
		this.setLevel(1);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 - ((score / 1000) * 90));
				this.gridUpdate(1);
				setChanged();
				notifyObservers();
				if(this.pause)
				{
					synchronized (this) {
						this.wait();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pause(){
		if(this.pause)
		{
			synchronized (this) {
				this.notify();
			}
			this.pause = false;
		}
		else
		{
			this.pause = true;
		}
	}
	
	public Grid getGrid() {
		return grid;
	}

	public Tetrimino getNext() {
		return next;
	}

	public void SetNext(Tetrimino n) {
		next = n;
	}

	public Tetrimino getHold() {
		return hold;
	}

	public void setHold(Tetrimino h) {
		hold = h;
	}

	public Tetrimino getCurrentTetrimino() {
		return currentTetrimino;
	}

	public void setCurrentTerimino(Tetrimino tetrimino) {
		currentTetrimino = tetrimino;
	}

	public void gridUpdate(int sens) {
		// vers le bas
		boolean possible = true;
		if (!this.getPause()) {
		if (sens == 1) {
			for (Cell cell : currentTetrimino.getCells()) {

				if (cell != null
						&& ((cell.getX() + 1) >= grid.getTable()[0].length || grid
								.getTable()[cell.getY()][cell.getX() + 1] != null)) {
					possible = false;
					break;
				}
			}
			if (possible) {
				// Si ne sort pas de la grille et pas de collision avec d'autres
				// tetriminos
				currentTetrimino.setY(currentTetrimino.getY() + 1);
				currentTetrimino.initGrid();
			} else {
				newTetrimino(false);
			}
		}
		// vers la gauche
		if (sens == 2) {
			for (Cell cell : currentTetrimino.getCells()) {
				if (cell != null
						&& ((cell.getY() - 1) < 0 || grid.getTable()[cell
								.getY() - 1][cell.getX()] != null)) {
					possible = false;
					break;
				}
			}
			if (possible) {
				currentTetrimino.setX(currentTetrimino.getX() - 1);
				currentTetrimino.initGrid();
			}
		}
		// vers la droite
		if (sens == 3) {
			for (Cell cell : currentTetrimino.getCells()) {
				if (cell != null
						&& ((cell.getY() + 1) >= grid.getTable().length || grid
								.getTable()[cell.getY() + 1][cell.getX()] != null)) {
					possible = false;
					break;
				}
			}
			if (possible) {
				currentTetrimino.setX(currentTetrimino.getX() + 1);
				currentTetrimino.initGrid();
			}
		}
		// vers le haut
		if (sens == 4) {
			while (possible) {
				for (Cell cell : currentTetrimino.getCells()) {
					if (cell != null
							&& ((cell.getX() + 1) >= grid.getTable()[0].length || grid
									.getTable()[cell.getY()][cell.getX() + 1] != null)) {
						possible = false;
						break;
					}
				}
				if (possible) {
					// Si ne sort pas de la grille et pas de collision avec
					// d'autres tetriminos
					currentTetrimino.setY(currentTetrimino.getY() + 1);
					currentTetrimino.initGrid();
				}
			}
			newTetrimino(false);
		}
	}
	}

	private void newTetrimino(boolean hold) {
		ArrayList<Integer> possibleLine = new ArrayList<Integer>();
		
		if(!hold)
		{
			for (Cell cell : currentTetrimino.getCells()) {
				if (cell != null
						&& grid.getTable()[cell.getY()][cell.getX()] != null) {
					// GAMEOVER
					System.exit(0);
				}
				if (cell != null) {
					grid.getTable()[cell.getY()][cell.getX()] = cell;
					possibleLine.add(cell.getX());
				}
			}
		}
		
		currentTetrimino = next;
		next = new Tetrimino(3, 0, Shapes.newShape());
		ArrayList<Integer> lineWin = new ArrayList<Integer>();
		while (!possibleLine.isEmpty()) {
			int y = possibleLine.get(0);
			if (!lineWin.contains(y)) {
				boolean line = true;
				for (int i = 0; i < grid.getTable().length; i++) {
					if (grid.getTable()[i][y] == null) {
						line = false;
						break;
					}
				}
				if (line) {
					lineWin.add(y);
				}
			}
			possibleLine.remove(0);
		}
		for (Integer y : lineWin) {
			if (y != 0) {
				for (int yi = y; yi > 0; yi--) {
					for (int x = 0; x < grid.getTable().length; x++) {
						grid.getTable()[x][yi] = grid.getTable()[x][yi - 1];
					}
				}
			} else {
				for (int x = 0; x < grid.getTable().length; x++) {
					grid.getTable()[x][0] = null;
				}
			}
		}
		//Gestion du score
		if (lineWin.size() == 1) {
			addScore(100);
		} else if (lineWin.size() == 2) {
			addScore(250);
		} else if (lineWin.size() == 3) {
			addScore(600);
		} else if (lineWin.size() == 4) {
			addScore(1000);
		}
		// Mise à jour du niveau
		updateLevel();
	}
	
	public void updateLevel(){
		// Si niveau supérieur
		if(this.getScore() / 1000 + 1 > this.getLevel()){
			this.setLevel(this.getScore() / 1000 + 1);
		}
	}

	public int getScore() {
		return score;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	private void addScore(int add) {
		score += add;
	}

	public void left() {
		if(this.currentTetrimino.getX() == -1){
			this.currentTetrimino.setX(0);
		}
		Tetrimino futureTetri = new Tetrimino(currentTetrimino.getX(),
				currentTetrimino.getY(), currentTetrimino.getShape());
		futureTetri.setPosition(currentTetrimino.getPosition() - 1);
		if (futureTetri.getPosition() < 0) {
			futureTetri.setPosition(futureTetri.getShape().length-1);
		}

		if (this.checkRotation(futureTetri)) {
			currentTetrimino.setPosition(currentTetrimino.getPosition() - 1);
			if (currentTetrimino.getPosition() < 0) {
				currentTetrimino.setPosition(currentTetrimino.getShape().length-1);
			}
			currentTetrimino.initGrid();
		}
	}

	public void right() {
		if(this.currentTetrimino.getX() == -1){
			this.currentTetrimino.setX(0);
		}
		Tetrimino futureTetri = new Tetrimino(currentTetrimino.getX(),
				currentTetrimino.getY(), currentTetrimino.getShape());
		futureTetri.setPosition(currentTetrimino.getPosition() + 1);
		//remise à "0" -> tour complet
		if (futureTetri.getPosition() >= futureTetri.getShape().length) {
			futureTetri.setPosition(0);
		}
	
		if (this.checkRotation(futureTetri)) {
			currentTetrimino.setPosition(currentTetrimino.getPosition() + 1);
			if (currentTetrimino.getPosition() >= currentTetrimino.getShape().length) {
				currentTetrimino.setPosition(0);
			}
			currentTetrimino.initGrid();
		}
		
	}
	
	public void putHold(){
		if (this.hold != null) 
		{
			Tetrimino saved = currentTetrimino;
			this.setCurrentTerimino(this.getHold());
			
			currentTetrimino.setX(saved.getX());
			currentTetrimino.setY(saved.getY());
			
			hold = saved;
			this.setHold(saved);
			currentTetrimino.initGrid();
			for (int i = 0; i < currentTetrimino.getCells().length; i++) {
				if (currentTetrimino.getCells()[i].getY() < 0) {
					i = 0;
					currentTetrimino.setX(currentTetrimino.getX()+1);
					currentTetrimino.initGrid();
				}
			}
		}
		else
		{
			hold = currentTetrimino;
			this.newTetrimino(true);
		}
	}
	
	public boolean checkRotation(Tetrimino future){
		if (this.getPause()) {
			return false;
		}
		boolean possible = true;
		for (Cell cell : future.getCells()) {
			if (cell != null && (cell.getY()<0 ||cell.getY()>grid.getTable()[0].length)) {
				possible = false;
				break;
			}
			if (cell != null
					&& grid.getTable()[cell.getY()][cell.getX()] != null) {
				possible = false;
				break;
			}
		}
		return possible;
	}

	public boolean getPause() {
		return this.pause;
	}
	
	
	
	
	

}