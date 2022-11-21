import java.io.*;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AideListener implements ActionListener, TreeSelectionListener{
	Aide arbo;
	JButton fermer;
	JEditorPane jep;
	
	
	public AideListener(Aide a){
		arbo = a;
		fermer = a.btnFermer;
		jep = a.jep;		
	}
	
	public void actionPerformed(ActionEvent e){
		try{
			jep.setPage("resources/Aide/Conseil.htm#Statistiques");
		}catch(IOException ioe){System.out.println("Erreur chgt jep");}
		jep.scrollToReference("Statistiques");
	}
	
	private String chemin(TreePath tp){
		StringTokenizer st = new StringTokenizer(tp.toString(), "[, ]");
		String ficSelect = new String(".");
		while(st.hasMoreElements()){
			ficSelect = new String(ficSelect +"/"+ st.nextToken() );
		}
		return ficSelect;
	}
	
	public void valueChanged(TreeSelectionEvent tse){
		try{
			JTree tree = (JTree)tse.getSource();
			TreePath tp = tree.getSelectionPath();
			String dernier = tp.getLastPathComponent().toString();
			
			if(tp.getPathCount()==2){
				
				arbo.chargerEditor(chemin(tp));
			System.out.println("Charger " + chemin(tp));
			}
			if(tp.getPathCount()==3){
				arbo.chargerEditor(chemin(tp));
				jep.scrollToReference(dernier);
				System.out.print("Charger " + chemin(tp.getParentPath()));
				System.out.println(" // " + dernier);
			}
			jep.repaint();
		}catch(NullPointerException npe){};
	}
	
}