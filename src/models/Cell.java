/**
 * 
 */
package models;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


/**
 * @author epulapp
 *
 */
public class Cell{
	private int color;
	private int x;
	private int y;
	
	public Cell(int x, int y, int color){	
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public int getColor(){
		return this.color;
	}
}	
