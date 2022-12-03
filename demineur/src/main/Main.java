package main;
import other.DFenetre;
import metier.DPartie;

public class Main{
	/**
	 * Permet de lancer la fenêtre princiaple de l'application
	 *
	 * @param args
	 */
	public static void main(String[] args){	
			DPartie partie = new DPartie(9,9,10);
			DFenetre fenetre = new DFenetre(partie);
		
			fenetre.getGo();
			fenetre.ecouterMenu();
	}
}