package other;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import javax.swing.tree.*;
import javax.swing.*;
import org.xml.sax.helpers.*;
import java.awt.*;


public class Aide {
	SAXParserFactory saxParserFactory;
	SAXParser saxParser;
	DefaultMutableTreeNode racine;
	DefaultTreeModel dtm;
	JTree tree;
	HandlerArbre handler;
	XMLReader r;
	JFrame maFen;
	JEditorPane jep;
	JButton btnFermer;
	AideListener ecouteur;

	private void creerArbo(File rep) {
		Reader unReader;
		racine = new DefaultMutableTreeNode("Aide");

		File dossier[] = rep.listFiles();
		for (int i = 0; i < dossier.length; i++) {
			try {
				saxParserFactory = SAXParserFactory.newInstance();
				saxParser = saxParserFactory.newSAXParser();
				File fichier = dossier[i];
				unReader = new FileReader(fichier);
				r = saxParser.getXMLReader();
				handler = new HandlerArbre(fichier);
				r.setContentHandler(handler);
				r.parse(new InputSource(unReader));

			} catch (Exception e) {
			}
			;

			racine.add(handler.getBranche());
		}
	}

	public void chargerEditor(String url) {
		try {
			jep.setContentType("text/html");
			jep.read(new FileReader(url), null);
			jep.setEditable(false);
		} catch (Exception ioe) {}
	}

	/**
	 * Permet de créer la fenêtre avec du texte d'aide 
	 * 
	 * @return
	 */
	private JPanel centerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		jep = new JEditorPane();
		chargerEditor("resources/Aide/Conseil.htm");
		JScrollPane jsp = new JScrollPane(jep);
		panel.add(jsp, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * Permet de créer un bouton fermer et de l'ajouter à la fenêtre d'aide
	 * 
	 * @return Un objet de la classe JPanel
	 */
	private JPanel bouton() {
		JPanel panel = new JPanel();
		btnFermer = new JButton("Fermer");

		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(btnFermer);

		return panel;
	}

	/**
	 * Le constructeur de la classe Aide
	 * 
	 * @param rep Un fichier
	 */
	public Aide(File rep) {

		// creation de l'arboresence
		creerArbo(rep);

		// ajout de l'arbre dans la fenetre
		dtm = new DefaultTreeModel(racine);
		tree = new JTree(dtm);

		maFen = new JFrame("Aide");
		JScrollPane jsp = new JScrollPane(tree);
		Container c = maFen.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(jsp, BorderLayout.WEST);

		c.add(centerPanel(), BorderLayout.CENTER);
		c.add(bouton(), BorderLayout.SOUTH);

		// ecouteur
		ecouteur = new AideListener(this);
		btnFermer.addActionListener(ecouteur);
		tree.addTreeSelectionListener(ecouteur);

		maFen.setSize(400, 500);
		maFen.setVisible(true);
	}

}

class HandlerArbre extends DefaultHandler {
	DefaultMutableTreeNode branche;
	File fichier;
	// creer pile

	/**
	 * Le constructeur de la classe HandlerArbre
	 * 
	 * @param f Un fichier
	 */
	public HandlerArbre(File f) {
		fichier = f;
		branche = new DefaultMutableTreeNode(fichier.getName());
	}

	public DefaultMutableTreeNode getBranche() {
		return branche;
	}

	public void startElement(String uri, String locName, String qName, Attributes atr) {
		int nb = atr.getLength();
		if (nb != 0) {
			for (int i = 0; i < nb; i++) {
				if (atr.getQName(i).compareTo("name") == 0) {
					DefaultMutableTreeNode feuille = new DefaultMutableTreeNode(atr.getValue(i));
					branche.add(feuille);
				}
			}
		}
	}
}
