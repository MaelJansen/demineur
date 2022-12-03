package controllers;
import java.awt.event.*;
import java.io.*;

import ihm.DFenetre;
import metier.DPartie;


public class EcouteurSouris  implements MouseListener, MouseMotionListener{

	private DFenetre fenetre;
	private int sourisX, sourisY;
	private boolean gauchePresse;
	
	public EcouteurSouris(DFenetre f, DPartie p){
		fenetre = f;
		gauchePresse = false;
	}
	
	public void mouseReleased(MouseEvent me){
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		try{
		  if(!fenetre.perdu() && !fenetre.gagne() 
		       && !(fenetre.yaDrapeau(sourisY, sourisX))){
			
			if(me.getButton()==me.BUTTON1){
				gauchePresse = false;
				try{
					fenetre.selectionner(sourisY, sourisX);
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
	
	public void mousePressed(MouseEvent me){
		sourisX = me.getX()/20;
		sourisY = me.getY()/20;
		if(!fenetre.perdu() && !fenetre.gagne()){
			
			
			
			if(me.getButton()==me.BUTTON1){
				gauchePresse = true;
				fenetre.selectionner(sourisY, sourisX);
				fenetre.goOups();
			}	
			if(me.getButton()==me.BUTTON3){
				fenetre.drapeauAction(sourisY,sourisX);					
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
				fenetre.deselectionner(sourisY, sourisX);
			}
			catch(NullPointerException npe){  }
			sourisX = x;
			sourisY = y;
			try{
				fenetre.selectionner(sourisY, sourisX);
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
