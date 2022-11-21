import javax.swing.*;
import java.awt.*;

public class DButton extends JButton{
	
	int x,y; // permet de connaitre l'emplacement du bouton

	public DButton(int i, int j){
			super();
			x = i;
			y = j;
    }
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	
}