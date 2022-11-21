import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DImageChooser extends JFileChooser {
	
	
	
	public DImageChooser(DImageur imageur, DPanneau panneau){
		super("./resources/Images/");
		setApproveButtonText("OK");
 		setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = this.showOpenDialog(new JFrame());
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String path = getSelectedFile().getPath();
			String fichier;
			if((fichier= estValide(path))==null){
				panneau.repaint();
				imageur.setRepertoire(path);
			}
			else
				JOptionPane.showMessageDialog(this,
				     fichier + " est manquant ou invalide"
				      ,"Répertoire invalide",JOptionPane.ERROR_MESSAGE);
		}
	}


	public String estValide(String rep){
		int i;
		File path = new File(rep);
        String[] fichiers = path.list();
        int nbrFichiers = fichiers.length;
        
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("1.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "1.GIF";

        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("2.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "2.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("3.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "3.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("4.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "4.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("5.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "5.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("6.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "6.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("7.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "7.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("8.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "8.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Cool.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Cool.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Croix.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Croix.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Drapeau.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Drapeau.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Gagne.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Gagne.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Inconnue.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Inconnue.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Mine.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Mine.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Oups.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Oups.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Perdu.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Perdu.GIF";
        
        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Select.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Select.GIF";

        for(i=0; i<nbrFichiers ;i++)
        	if(fichiers[i].equals("Vide.GIF"))
        		break;
        	if(i==nbrFichiers)
        		return "Vide.GIF";
        return null;
	}	
	
	
} 