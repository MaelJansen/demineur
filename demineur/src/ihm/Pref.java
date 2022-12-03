package ihm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import other.DFenetre;

public class Pref implements ActionListener, FocusListener{
	JFrame maFen;
	JTextField jtfLargeur, jtfHauteur, jtfMines;
	JLabel jlLargeur, jlHauteur, jlMines;
	JButton btnOk, btnAnnuler;
	
	
	static int NB_MINES_MIN = 10;
	static int NB_MINES_MAX = 665;
	static int DIMENSION_MIN = 9;
	static int DIMENSION_MAX = 25;
	
	int hauteur, largeur, mines;
	
	boolean validation = false;
	
	
	private DFenetre fenetre;
	
	/**
	 * Un constructeur de la classe Pref
	 * 
	 * @param lng La hauteur de la fenêtre des préférences (un entier)
	 * @param lrg La largeur de la fenêtre des préférences (un entier)
	 * @param nbMines
	 */
	public Pref(int lng, int lrg, int nbMines){
		maFen = new JFrame("Preferences");
		
		System.out.println("Choix");
		
		hauteur = lng;
		largeur = lrg;
		mines = nbMines;
		
		miseEnPage();
		ajoutEcouteurs();
		
		maFen.setSize(300, 200);
		maFen.setVisible(true);
	}
	
	/**
	 * Un constructeur de la classe Pref
	 * 
	 * @param fen La fenêtre ouverte
	 */
	public Pref(DFenetre fen){
		maFen = new JFrame("Preferences");
		fenetre = fen;
		System.out.println("Choix");
		
		hauteur = fenetre.getHauteur();
		largeur = fenetre.getLargeur();
		mines = fenetre.getMines();
		
		miseEnPage();
		ajoutEcouteurs();
		
		maFen.setSize(300, 200);
		maFen.setVisible(true);

		
	}

	/**
	 * Permet d'ajouter les labels Largeur, Hauteur et Mines
	 * 
	 * @return Un composant de la classe JPanel
	 */
	private JPanel label(){
		/* Creation */
		jlLargeur = new JLabel("Largeur");
		jlHauteur = new JLabel("Hauteur");
		jlMines = new JLabel("Mines");
		JPanel panel = new JPanel();
		/* Ajout */
		panel.setLayout(new GridLayout(3,1, 15,5));
		panel.add(jlLargeur);
		panel.add(jlHauteur);
		panel.add(jlMines);
		/* Retour */
		return panel;
	}
	
	/**
	 * Permet d'ajouter les zones de texte pour changer la hauteur, la largeur et le nombre de mines du jeu
	 * 
	 * @return Un composant de la classe JPanel
	 */
	private JPanel saisie(){
		/* Creation */
		jtfLargeur = new JTextField(Integer.toString(largeur));
		jtfHauteur = new JTextField(Integer.toString(hauteur));
		jtfMines = new JTextField(Integer.toString(mines));
		JPanel panel = new JPanel();
		/* Ajout */
		panel.setLayout(new GridLayout(3,1, 15,5));
		panel.add(jtfLargeur);
		panel.add(jtfHauteur);
		panel.add(jtfMines);
		/* Retour */
		return panel;
	}
	
	/**
	 * Permet d'ajouter les boutons Ok et Annuler
	 * 
	 * @return Un composant de la classe JPanel
	 */
	private JPanel boutons(){
		/* Creation */
		btnOk = new JButton("Ok");
		btnAnnuler = new JButton("Annuler");
		/* Ajout */
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(btnOk, BorderLayout.NORTH);
		panel.add(btnAnnuler, BorderLayout.SOUTH);		
		/* Retour */
		return panel;
	}
	
	/**
	 * Permet d'ajouter des écouteurs aux boutons et aux zones de texte
	 */
	private void ajoutEcouteurs(){
		btnOk.addActionListener(this);
		btnAnnuler.addActionListener(this);
		
		jtfLargeur.addFocusListener(this);
		jtfHauteur.addFocusListener(this);
		jtfMines.addFocusListener(this);
	}
	
	/**
	 * Permet d'ajouter les labels, zones de texte et boutons à la fenêtre des préférences
	 */
	private void miseEnPage(){
		Container c = maFen.getContentPane();
		
		//  Ajout des labels, zones de saisie et des boutons
		Container container = new Container();
		container.setLayout(new BorderLayout(20, 0));
		container.add(label(), BorderLayout.WEST);
		container.add(saisie(), BorderLayout.CENTER);
		container.add(boutons(), BorderLayout.EAST);
		
		c.setLayout(new BorderLayout());
		c.add(container, BorderLayout.NORTH);
		
	}
	
	/**
	 * Appelé quand une action se produit
	 */
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource().equals(btnOk)){
			validation = true;
			maFen.setVisible(false);
			
			hauteur = Integer.parseInt(jtfHauteur.getText());
			largeur = Integer.parseInt(jtfLargeur.getText());
			mines = Integer.parseInt(jtfMines.getText());
			if (mines>hauteur*largeur)
				mines = hauteur*largeur-5;
				
			fenetre.arretChrono();
			fenetre.initChrono();
		
			fenetre.nouvellePartie(hauteur,largeur,mines);
				
		}
		else{
			validation = false;
		}
		
	}
	
	/**
	 * Appelé quand un composant récupère la priorité sur le clavier
	 */
	public void focusGained(FocusEvent e){
	}
	
	public void focusLost(FocusEvent e){
		JTextField jtf = (JTextField)e.getSource();
		int val = Integer.parseInt(jtf.getText());
		
		if(jtf.equals(jtfMines)){
			if(val<NB_MINES_MIN)
				jtf.setText(Integer.toString(NB_MINES_MIN));
			if(val>NB_MINES_MAX)
				jtf.setText(Integer.toString(NB_MINES_MAX));
		}
		else{
			if(val<DIMENSION_MIN)
				jtf.setText(Integer.toString(DIMENSION_MIN));
			if(val>DIMENSION_MAX)
				jtf.setText(Integer.toString(DIMENSION_MAX));
		}
		
	}
	
	/**
	 * Permet de savoir si les changements de préférences sont validés
	 * 
	 * @return True si les changements sont validés, False sinon
	 */
	public boolean getValidation(){
		return validation;
	}
	
	/**
	 * Permet de connaître la largeur de la fenêtre
	 * 
	 * @return Un entier
	 */
	public int getLargeur(){
		return largeur;
	}
	
	/**
	 * Permet de connaître la hauteur de la fenêtre
	 * 
	 * @return Un entier
	 */
	public int getHauteur(){
		return hauteur;
	}
	
	/**
	 * Permet de connaître le nombre de mines choisies
	 * 
	 * @return Un entier
	 */
	public int getMines(){
		return mines;
	}
	
	/**
	 * Permet de lancer la fenêtre de préférences de l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Pref p = new Pref(10, 10 ,10);	
		System.out.println("Fin");
		System.out.println(p.getLargeur() + " " + p.getHauteur() + " " + p.getMines());
		
	}
	
}