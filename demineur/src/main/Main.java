package main;
import java.io.*;
import java.util.*;

import controllers.EcouteurFenetre;
import controllers.EcouteurGo;
import controllers.EcouteurMenu;
import ihm.DFenetre;
import metier.DPartie;

public class Main{
	public static void main(String[] args){	
			DPartie partie = new DPartie(9,9,10);
			DFenetre fenetre = new DFenetre(partie);
		
			EcouteurGo e = new EcouteurGo(fenetre,partie);
			fenetre.getGo().addActionListener(e);
		
			EcouteurFenetre ecouteurFenetre = new EcouteurFenetre(fenetre);
			fenetre.addWindowListener(ecouteurFenetre);
		
			EcouteurMenu ecouteurMenu = new EcouteurMenu(fenetre,partie);
			fenetre.ecouterMenu(ecouteurMenu);
	}	
}

