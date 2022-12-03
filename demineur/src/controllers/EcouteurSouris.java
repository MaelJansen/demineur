package controllers;
import java.awt.event.*;

import other.DFenetre;
import metier.DPartie;


public class EcouteurSouris  implements MouseListener, MouseMotionListener{

	private DFenetre fenetre;
	private int sourisX, sourisY;
	private boolean gauchePresse;
	
	/**
	 * Le constructeur de la classe EcouteurSouris
	 * 
	 * @param f La fenêtre ouverte
	 * @param p La partie en cours
	 */
	public EcouteurSouris(DFenetre f, DPartie p){
		fenetre = f;
		gauchePresse = false;
	}
	
	/**
	 * Appelé quand l'un des boutons de la souris est relâché sur un composant
	 */
	public void mouseReleased(MouseEvent me){
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		try{
		  if(!fenetre.perdu() && !fenetre.gagne() 
				  && !(fenetre.getCase(sourisY,sourisX).yaDrapeau())){
			
			if(me.getButton()==MouseEvent.BUTTON1){
				gauchePresse = false;
				try{
					fenetre.getCase(sourisY,sourisX).selectionner();
				}
				catch(NullPointerException npe){  }
				try{
					fenetre.devoilerCase(sourisY,sourisX);
				}
				catch(NullPointerException npe){  }
				fenetre.lancerChrono();
				if(fenetre.gagne()){
					fenetre.goGagne();
					fenetre.arretChrono();
				}					
				else
					if(fenetre.perdu()){
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
	
	/**
	 * Appelé quand l'un des boutons de la souris est pressé sur un composant
	 */
	public void mousePressed(MouseEvent me){
		System.out.println("  /////////// ");
		System.out.println("ECOUTEURSOURIS - MOUSEPRESSED ");
		System.out.println(" /////////// ");
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		if(!fenetre.perdu() && !fenetre.gagne()){
			
			
			
			if(me.getButton()==MouseEvent.BUTTON1){
				gauchePresse = true;
				fenetre.getCase(sourisY,sourisX).selectionner();
				fenetre.goOups();
			}	
			if(me.getButton()==MouseEvent.BUTTON3){
				fenetre.getCase(sourisY,sourisX).drapeauAction();;					
				fenetre.miseAJourCompteur();
				
			}
		}
		me.getComponent().repaint();
	}
	
	/**
	 * Appelé quand la souris quitte un composant
	 */
	public void mouseExited(MouseEvent me){
		if(gauchePresse)
			fenetre.goCool();
	}
	
	/**
	 * Appelé quand la souris entre dans un composant
	 */
	public void mouseEntered(MouseEvent me){
		if(gauchePresse)
			fenetre.goOups();	
	}
	
	/**
	 * Appelé lorsqu'un bouton de la souris est enfoncé sur un composant, puis glissé. Les événements MOUSE_DRAGGED 
	 * continueront d'être transmis au composant d'où provient le glissement jusqu'à ce que le bouton de la souris 
	 * soit relâché
	 */
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
    
    /* Non implement�  */
    public void mouseClicked(MouseEvent e){
    }
	

}
