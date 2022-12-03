package ihm;
import javax.swing.*;

import other.DFenetre;

import java.awt.*;

public class DPanneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private DFenetre fenetre;
	private int hauteur, largeur;
	
	/**
	 * Le constructeur de la classe DPanneau
	 * 
	 * @param fe La fenêtre ouverte
	 * @param h La hauteur de la fenêtre (un entier)
	 * @param l La largeur de la fenêtre (un entier)
	 */
	public DPanneau(DFenetre fe, int h, int l){
		super();
		fenetre = fe;
		largeur = l;
		hauteur = h;
	}
	
	/**
	 * Permet de (re)dessiner les cases du jeu
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<hauteur; i++)
			for(int j=0;j<largeur; j++){
				g.drawImage(fenetre.getIcon(i,j).getImage(),j*20,i*20,this);
			}
	}	
}