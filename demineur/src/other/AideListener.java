package other;
import java.io.*;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class AideListener implements ActionListener, TreeSelectionListener {
	Aide arbo;
	JButton fermer;
	JEditorPane jep;

	/**
	 * Le constructeur de la classe AideListener
	 * 
	 * @param a Un objet de la classe Aide
	 */
	public AideListener(Aide a) {
		arbo = a;
		fermer = a.btnFermer;
		jep = a.jep;
	}

	/**
	 * Appelé quand une action se produit
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			jep.setPage("resources/Aide/Conseil.htm#Statistiques");
		} catch (IOException ioe) {
			System.out.println("Erreur chgt jep");
		}
		jep.scrollToReference("Statistiques");
	}

	/**
	 * Permet de récupérer le chemin d'un fichier
	 * 
	 * @param tp Un objet de la classe TreePath
	 * @return Une chaîne de caractère
	 */
	private String chemin(TreePath tp) {
		StringTokenizer st = new StringTokenizer(tp.toString(), "[, ]");
		String ficSelect = new String(".");
		while (st.hasMoreElements()) {
			ficSelect = new String(ficSelect + "/" + st.nextToken());
		}
		return ficSelect;
	}

    /**
     * Appelé quand la valeur de la sélection change
     * 
     * @param e Un objet de la classe TreeSelectionEvent
     */
	public void valueChanged(TreeSelectionEvent tse) {
		try {
			JTree tree = (JTree) tse.getSource();
			TreePath tp = tree.getSelectionPath();
			String dernier = tp.getLastPathComponent().toString();

			if (tp.getPathCount() == 2) {
				arbo.chargerEditor(chemin(tp));
				System.out.println("Charger " + chemin(tp));
			}
			if (tp.getPathCount() == 3) {
				arbo.chargerEditor(chemin(tp));
				jep.scrollToReference(dernier);
				System.out.print("Charger " + chemin(tp.getParentPath()));
				System.out.println(" // " + dernier);
			}
			jep.repaint();
		} catch (NullPointerException npe) {
		}
	}

}