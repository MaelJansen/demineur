package controllers;
import java.awt.event.*;
import java.io.*;

import ihm.DFenetre;
import metier.DCase;
import metier.DPartie;
import metier.IDPartie;


public class EcouteurSouris  implements MouseListener, MouseMotionListener{

	private DPartie partie;
	private DFenetre fenetre;
	private int sourisX, sourisY;
	private boolean gauchePresse;
	
	public EcouteurSouris(DFenetre f, DPartie p){
		partie = p;
		fenetre = f;
		gauchePresse = false;
	}
	
	public void mouseReleased(MouseEvent me){
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		try{
		  if(!partie.perdu() && !partie.gagne() 
		       && !(fenetre.getCase(sourisY,sourisX).yaDrapeau())){
			
			if(me.getButton()==me.BUTTON1){
				gauchePresse = false;
				try{
					fenetre.getCase(sourisY,sourisX).selectionner();
				}
				catch(NullPointerException npe){  }
				try{
					partie.devoilerCase(sourisY,sourisX);
				}
				catch(NullPointerException npe){  }
				fenetre.lancerChrono();
				if(partie.gagne()){
					fenetre.goGagne();
					fenetre.arretChrono();
				}					
				else
					if(partie.perdu()){
						fenetre.goPerdu();
						fenetre.arretChrono();
					}
					else
						fenetre.goCool();
			}	
			
				

			
		  }
		}
		catch(NullPointerException npe){  }
		me.getComponent().repaint();
	}
	
	public void mousePressed(MouseEvent me){
		System.out.println("  /////////// ");
		System.out.println("ECOUTEURSOURIS - MOUSEPRESSED ");
		System.out.println(" /////////// ");
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		if(!partie.perdu() && !partie.gagne()){
			
			
			
			if(me.getButton()==me.BUTTON1){
				gauchePresse = true;
				fenetre.getCase(sourisY,sourisX).selectionner();
				fenetre.goOups();
			}	
			if(me.getButton()==me.BUTTON3){
				partie.drapeauAction(sourisY,sourisX);					
				fenetre.miseAJourCompteur();
				
			}
		}
		me.getComponent().repaint();
	}
	
	public void mouseExited(MouseEvent me){
		if(gauchePresse)
			fenetre.goCool();
	}
	
	public void mouseEntered(MouseEvent me){
		if(gauchePresse)
			fenetre.goOups();	
	}
	
	
	public void mouseDragged(MouseEvent me){
		int x = me.getX()/20;
		int y = me.getY()/20;
		
		if(((x!=sourisX) || (y!=sourisY)) && gauchePresse){
			try{
				fenetre.getCase(sourisY,sourisX).deselectionner();
			}
			catch(NullPointerException npe){  }
			sourisX = x;
			sourisY = y;
			try{
				fenetre.getCase(sourisY,sourisX).selectionner();
			}
			catch(NullPointerException npe){  }
			me.getComponent().repaint();
		
		}	
	
	} 
	/* Non implement�  */
    public void mouseMoved(MouseEvent me){
    	
    }  
    
    public void mouseClicked(MouseEvent e){
    }
	

}
