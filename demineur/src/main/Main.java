package main;
import java.io.*;
import java.util.*;

import classesSuprimées.EcouteurFenetre;
import classesSuprimées.EcouteurGo;
import classesSuprimées.EcouteurMenu;
import ihm.DFenetre;
import metier.DPartie;

public class Main{
	public static void main(String[] args){	
			DPartie partie = new DPartie(9,9,10);
			DFenetre fenetre = new DFenetre(partie);
		
			fenetre.getGo();
			fenetre.ecouterMenu();
	}
}

