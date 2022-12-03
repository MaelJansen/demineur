package metier;
import javax.swing.*;

import controllers.EcouteurSouris;
import ihm.DFenetre;

import java.awt.*;

public class DPanneau extends JPanel{
	
	private DFenetre fenetre;
	private int hauteur, largeur;
	
	public DPanneau(DFenetre fe, int h, int l){
		super();
		fenetre = fe;
		largeur = l;
		hauteur = h;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<hauteur; i++)
			for(int j=0;j<largeur; j++){
				g.drawImage(fenetre.getIcon(i,j).getImage(),j*20,i*20,this);
			}
	}	
}