/**
 * 
 */
package activity;

import java.awt.KeyEventPostProcessor;
import java.awt.event.KeyEvent;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardEndHandler;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author epulapp
 *
 */
public class Controls{
     private static  int DOWN_ARROW = KeyEvent.VK_DOWN;
     private static  int UP_ARROW = KeyEvent.VK_UP;
     private static int LEFT_ARROW = KeyEvent.VK_LEFT;
     private static int RIGHT_ARROW = KeyEvent.VK_RIGHT;
     private static int X_KEY = KeyEvent.VK_X;
     private static int W_KEY = KeyEvent.VK_W;
     private static int C_KEY = KeyEvent.VK_C;
     private static int SPACE_KEY = KeyEvent.VK_SPACE;
     
     public static Boolean CheckActionDown(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
         return keyPressed == DOWN_ARROW;
     }

     public static Boolean CheckActionLeft(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
         return keyPressed == LEFT_ARROW;
     }

     public static Boolean CheckActionRight(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
         return keyPressed == RIGHT_ARROW;
     }
     
     public static Boolean CheckActionUp(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
    	 return keyPressed == UP_ARROW;
     }
     
     public static Boolean CheckActionX(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
    	 return keyPressed == X_KEY;
     }
     
     public static Boolean CheckActionW(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
    	 return keyPressed == W_KEY   ;
     }
     
     public static Boolean CheckActionC(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
    	 return keyPressed == C_KEY;
     }
     
     public static Boolean CheckActionSpace(KeyEvent evt)
     {
    	 int keyPressed = evt.getKeyCode();
    	 return keyPressed == SPACE_KEY;
     }
}	
