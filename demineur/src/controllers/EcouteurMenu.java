package controllers;
import java.awt.event.*;
import main.Aide;
import javax.swing.*;

import ihm.DFenetre;
import ihm.Pref;
import main.Aide;
import metier.DImageChooser;
import metier.DPartie;

import java.io.*;

public class EcouteurMenu implements ActionListener{
	
	private DPartie partie;
	private DFenetre fenetre;
	
	public EcouteurMenu(DFenetre f, DPartie p){
		partie = p;
		fenetre = f;
	}
	
	
	public void actionPerformed(ActionEvent ae){
	    	if(ae.getSource() == fenetre.getNouvelle()){
			fenetre.arretChrono();
			fenetre.initChrono();
			partie.nouvellePartie(partie.getHauteur(),
		                      partie.getLargeur(),
		                      partie.getMines());
			fenetre.connecterPartie(partie);
		}
	       	if(ae.getSource() == fenetre.getDebutant()){
				fenetre.arretChrono();
				fenetre.initChrono();
				partie.nouvellePartie(9,9,10);
				fenetre.connecterPartie(partie);
				}
	    
		if(ae.getSource() == fenetre.getIntermediaire()){
				fenetre.arretChrono();
				fenetre.initChrono();
				partie.nouvellePartie(16,16,40);
				fenetre.connecterPartie(partie);
		}	
		if(ae.getSource() == fenetre.getExpert()){
				fenetre.arretChrono();
				fenetre.initChrono();
				partie.nouvellePartie(16,30,99);
				fenetre.connecterPartie(partie);
		}
		
		if(ae.getSource() == fenetre.getPerso()){
			Pref pref = new Pref(fenetre, partie);

		}
		
		if(ae.getSource() == fenetre.getDesign()){
			DImageChooser choix = 
			      new DImageChooser(fenetre.getImageur(),
			                        fenetre.getPanneauCentral() );
		}
		
		if(ae.getSource() == fenetre.getQuitter())
							System.exit(0);
							
		if(ae.getSource()==fenetre.getAide()){
			File f = new File("resources/Aide");
			Aide a = new Aide(f);;
		}
		if(ae.getSource()==fenetre.getCreateur()) 
				JOptionPane.showMessageDialog(fenetre,
				     " R�alis� par Igor DAURIAC et Nicolas FRANCOIS, Projet IHM"
				      ,"Cr�ateurs...",JOptionPane.INFORMATION_MESSAGE);
						
	}
}
