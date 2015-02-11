/**
 * 
 */
package models;


/**
 * @author epulapp
 *
 */
public class Grid {
	
	private Cell[][] table;
	
	public Grid(){}
	
	public Grid(int x, int y){
		table = new Cell[x][y];
	}
	
	public void init(){
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j++){
				this.table[i][j] = new Cell(i,j, 1);
			}
		}
	}
	
	public Cell[][] getTable(){
		return table;
	}
	
	public void setTable(Cell[][] tab){
		table = tab;
	}
}
