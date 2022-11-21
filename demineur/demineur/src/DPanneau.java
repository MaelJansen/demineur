import javax.swing.*;
import java.awt.*;

public class DPanneau extends JPanel{
	
	private DImageur imageur;
	private int hauteur, largeur;
	
	public DPanneau(DImageur im, int h, int l){
		super();
		imageur = im;
		hauteur = h;
		largeur = l;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<hauteur; i++)
			for(int j=0;j<largeur; j++){
				g.drawImage(imageur.getImage(i,j).getImage(),j*20,i*20,this);
			}
	}	
}