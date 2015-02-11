package models;



/**
 * @author epulapp
 *
 */
public class Shapes {	
	// I-Tetrimino
	public static int[][] ITetriminoBottom = { 
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{1,1,1,1}
	};
	
	public static int[][] ITetriminoLeft = { 
		{0,1,0,0},
		{0,1,0,0},
		{0,1,0,0},
		{0,1,0,0}
	};
	
	// O-Tetrimino
	public static int[][] OTetrimino = { 
		{0,0,0,0},
		{0,0,0,0},
		{0,2,2,0},
		{0,2,2,0}
	};
	
	// T-Tetrimino
	public static int[][] TTetriminoBottom = { 
		{0,0,0,0},
		{0,0,0,0},
		{0,3,0,0},
		{3,3,3,0}
	};
	
	public static int[][] TTetriminoLeft = { 
		{0,0,0,0},
		{0,3,0,0},
		{0,3,3,0},
		{0,3,0,0}
	};
	
	public static int[][] TTetriminoRight = { 
		{0,0,0,0},
		{0,0,3,0},
		{0,3,3,0},
		{0,0,3,0}
	};
	
	public static int[][] TTetriminoTop = { 
		{0,0,0,0},
		{0,0,0,0},
		{3,3,3,0},
		{0,3,0,0}
	};
	
	// L-Tetrimino
	public static int[][] LTetriminoTop = { 
		{0,0,0,0},
		{0,0,0,0},
		{4,4,4,0},
		{4,0,0,0}
	};
	
	public static int[][] LTetriminoLeft = { 
		{0,0,0,0},
		{0,4,0,0},
		{0,4,0,0},
		{0,4,4,0}
	};
	
	public static int[][] LTetriminoRight = { 
		{0,0,0,0},
		{4,4,0,0},
		{0,4,0,0},
		{0,4,0,0}
	};
	
	public static int[][] LTetriminoBottom = { 
		{0,0,0,0},
		{0,0,0,0},
		{0,0,4,0},
		{4,4,4,0}
	};
	
	// J-Tetrimino
	public static int[][] JTetriminoTop = { 
		{0,0,0,0},
		{0,0,0,0},
		{5,5,5,0},
		{0,0,5,0}
	};
	
	public static int[][] JTetriminoRight = { 
		{0,0,0,0},
		{0,0,5,0},
		{0,0,5,0},
		{0,5,5,0}
	};
	
	public static int[][] JTetriminoLeft = { 
		{0,0,0,0},
		{0,5,5,0},
		{0,5,0,0},
		{0,5,0,0}
	};
	
	public static int[][] JTetriminoBottom = { 
		{0,0,0,0},
		{0,0,0,0},
		{5,0,0,0},
		{5,5,5,0}
	};
	
	// Z-Tetrimino
	public static int[][] ZTetriminoBottom = { 
		{0,0,0,0},
		{0,0,0,0},
		{6,6,0,0},
		{0,6,6,0}
	};
	
	public static int[][] ZTetriminoLeft = { 
		{0,0,0,0},
		{0,0,6,0},
		{0,6,6,0},
		{0,6,0,0}
	};
	
	// S-Tetrimino
	public static int[][] STetriminoBottom = { 
		{0,0,0,0},
		{0,0,0,0},
		{0,7,7,0},
		{7,7,0,0}
	};
	
	public static int[][] STetriminoRight = { 
		{0,0,0,0},
		{0,7,0,0},
		{0,7,7,0},
		{0,0,7,0}
	};
	
	public static int[][][] Stab = {STetriminoBottom, STetriminoRight, STetriminoBottom, STetriminoRight};
	
	public static int[][][] Ztab = {ZTetriminoBottom, ZTetriminoLeft, ZTetriminoBottom, ZTetriminoLeft};
	
	public static int[][][] Jtab = {JTetriminoBottom, JTetriminoLeft, JTetriminoTop, JTetriminoRight};
	
	public static int[][][] Ltab = {LTetriminoBottom, LTetriminoLeft, LTetriminoTop, LTetriminoRight};
	
	public static int[][][] Ttab = {TTetriminoBottom, TTetriminoLeft, TTetriminoTop, TTetriminoRight};
	
	public static int[][][] Itab = {ITetriminoBottom, ITetriminoLeft, ITetriminoBottom, ITetriminoLeft};
	
	public static int[][][] Otab = {OTetrimino, OTetrimino, OTetrimino, OTetrimino};
	
	public static int[][][] newShape(){
		int nb = (int)(Math.random() * 7);
		switch(nb){
		case 0:
			return Itab;
		case 1:
			return Ttab;
			
		case 2:
			return Stab;
		case 3:
			return Ztab;
		case 4:
			return Ltab;
		case 5:
			return Jtab;
		case 6:
			return Otab;
		default:
			return null;
		}
	}
}
