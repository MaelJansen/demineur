package controllers;
import java.awt.event.*;

import ihm.DFenetre;
import metier.DPartie;

public class EcouteurGo implements ActionListener{
	
	DFenetre fenetre;
	DPartie partie;
	
	public EcouteurGo(DFenetre f, DPartie p){
		fenetre = f;
		partie = p;
	}
	
	
	public void actionPerformed(ActionEvent ae){
		fenetre.arretChrono();
		fenetre.initChrono();
		
		partie.nouvellePartie(partie.getHauteur(),
		                      partie.getLargeur(),
		                      partie.getMines());
		fenetre.connecterPartie(partie);
		
	}
	
}