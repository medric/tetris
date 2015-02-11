import view.MainFrame;
import models.ModelObject;

/**
 * 
 */

/**
 * @author epulapp
 *
 */
public class Game {
	private ModelObject mod;
	private MainFrame frame;
	
	public Game(){
		this.mod = new ModelObject();
		this.frame = new MainFrame(this.mod);
		this.mod.addObserver(this.frame);
	}
	
	public void init(){
		// Thread pour le modèle
		Thread t1 = new Thread(this.mod);
		t1.start();
	}
}
