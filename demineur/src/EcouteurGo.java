import java.awt.event.*;

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
		
		partie.nouvellePartie(partie.getMatrice().getHauteur(),
		                      partie.getMatrice().getLargeur(),
		                      partie.getMatrice().getMines());
		fenetre.connecterPartie(partie);
		
	}
	
}