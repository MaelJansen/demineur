package classesSuprimées;
import java.awt.event.*;

import ihm.DFenetre;
import metier.DPartie;

public class EcouteurGo implements ActionListener{
	
	DFenetre fenetre;
	
	public EcouteurGo(DFenetre f){
		fenetre = f;
	}
	
	
	public void actionPerformed(ActionEvent ae){
		fenetre.arretChrono();
		fenetre.initChrono();
		
		fenetre.nouvellePartie(fenetre.getHauteur(),
		                      fenetre.getLargeur(),
		                      fenetre.getMines());
	}
	
}