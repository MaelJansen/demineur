package metier;
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
				      ,"R�pertoire invalide",JOptionPane.ERROR_MESSAGE);
		}
	}


	public String estValide(String rep){
		int i;
		File path = new File(rep);
        String[] fichiers = path.list();
        int nbrFichiers = fichiers.length;
        
        
        for(i=0; i<nbrFichiers ;i++)
        	if(i==nbrFichiers)
        		return "GIF";
        return null;
	}	
	
	
} 