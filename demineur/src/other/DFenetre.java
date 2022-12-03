package other;
import ihm.DImageur;
import ihm.DPanneau;

import javax.swing.*;

import controllers.EcouteurSouris;
import metier.DCase;
import ihm.DImageChooser;
import metier.DPartie;
import ihm.Pref;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class DFenetre extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	final static int DEBUTANT = 1;
	final static int INTER = 2;
	final static int EXPERT = 3;
	final static int PERSO = 4;

	JMenuBar barreMenus;
	JMenu jeu, options, aPropos;

	public JRadioButtonMenuItem debutant, intermediaire, expert, perso;
	public JMenuItem nouvelle, quitter, aide, createur, design, stat;

	private JLabel minesRestantes;
	private DChronoLabel temps;
	private JPanel nord;

	private JButton go;
	private DPartie partie;

	private DImageur imageur;
	private DPanneau centre;

	/**
	 * Le constructeur de la classe DFenetre
	 * 
	 * @param p La partie en cours
	 */
	public DFenetre(DPartie p) {
		super("Demineur");
		menu();
		imageur = new DImageur();
		miseEnPage();

		connecterPartie(p);
	}
	
	/**
	 * Permet de (re)démarrer une partie
	 * 
	 * @param p La partie en cours
	 */
	public void connecterPartie(DPartie p){
		System.out.println("DFenetre - connecterPartie");

		partie = p;

		miseAJourCompteur();
		goCool();

		/* partie centrale : damier */
		if (centre != null)
			getContentPane().remove(centre);
		centre = new DPanneau(this, partie.getHauteur(), partie.getLargeur());

		EcouteurSouris ecouteurSouris = new EcouteurSouris(this, partie);
		centre.addMouseListener(ecouteurSouris);
		centre.addMouseMotionListener(ecouteurSouris);

		getContentPane().add(centre, BorderLayout.CENTER);

		/* Affichage */

		this.setSize(20 * partie.getLargeur() + 15, 20 * p.getHauteur() + 105);

		this.setResizable(false);

		this.setVisible(true);

		this.repaint();

	}
	
	/**
	 * Permet de lancer une nouvelle partie
	 * 
	 * @param h Le nombre de cases en hauteur de la partie (un entier)
	 * @param l Le nombre de cases en largeur de la partie (un entier)
	 * @param nbMines Le nombre de mines dans la partie (un entier)
	 */
	public void nouvellePartie(int h, int l, int nbMines) {
		partie.nouvellePartie(h, l, nbMines);
		connecterPartie(partie);
	}

	/**
	 * Permet de créer le menu pour changer la difficulté de la partie
	 */
	private void menu() {
		System.out.println("DFenetre - menu");
		/* creation du menu de jeu */
		jeu = new JMenu("Jeu");
		nouvelle = new JMenuItem("Nouvelle partie");
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		nouvelle.setToolTipText("Partie avec les m�mes param�tres");
		jeu.add(nouvelle);
		jeu.addSeparator();

		ButtonGroup groupRadio = new ButtonGroup();

		debutant = new JRadioButtonMenuItem("D�butant");
		debutant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		debutant.setToolTipText("81 cases 10 mines");

		intermediaire = new JRadioButtonMenuItem("Interm�diaire");
		intermediaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		intermediaire.setToolTipText("256 cases 40 mines");

		expert = new JRadioButtonMenuItem("Expert");
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		expert.setToolTipText("480 cases 99 mines");

		perso = new JRadioButtonMenuItem("Personnalis�...");
		perso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		perso.setToolTipText("Partie avec vos votres param�tres");

		jeu.add(debutant);
		jeu.add(intermediaire);
		jeu.add(expert);
		jeu.add(perso);

		groupRadio.add(debutant);
		groupRadio.add(intermediaire);
		groupRadio.add(expert);
		groupRadio.add(perso);

		jeu.addSeparator();
		quitter = new JMenuItem("Quitter");
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));

		jeu.add(quitter);

		/* creation du menu de options */
		options = new JMenu("\n" + "import (default package);Options");

		design = new JMenuItem("Graphisme");
		design.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		design.setToolTipText("Pour choisir le style d'image");
		options.add(design);
		options.addSeparator();

		stat = new JMenuItem("Statistiques");
		stat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		stat.setToolTipText("Pour connaitre les scores");
		options.add(stat);

		/* creation du menu A propos */
		aPropos = new JMenu("?");

		aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		aide.setToolTipText("Pour obtenir de l'aide");
		aPropos.add(aide);

		aPropos.addSeparator();

		createur = new JMenuItem("Cr�ateurs");
		createur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		createur.setToolTipText("Par qui ?");
		aPropos.add(createur);

		/* ajout des menus � la barre */
		barreMenus = new JMenuBar();
		barreMenus.add(jeu);
		barreMenus.add(options);
		barreMenus.add(aPropos);
		this.setJMenuBar(barreMenus);
	}

	/**
	 * Permet de redémarrer une partie avec une difficulté différente
	 */
	public void ecouterMenu() {
		System.out.println("DFenetre - ecouterMenu");

		ActionListener al = e -> {
			if (e.getSource() == this.getNouvelle()) {
				this.arretChrono();
				this.initChrono();
				this.nouvellePartie(this.getHauteur(), this.getLargeur(), this.getMines());
			}
			if (e.getSource() == this.getDebutant()) {
				this.arretChrono();
				this.initChrono();
				this.nouvellePartie(9, 9, 10);
			}

			if (e.getSource() == this.getIntermediaire()) {
				this.arretChrono();
				this.initChrono();
				this.nouvellePartie(16, 16, 40);
			}
			if (e.getSource() == this.getExpert()) {
				this.arretChrono();
				this.initChrono();
				this.nouvellePartie(16, 30, 99);
			}

			if (e.getSource() == this.getPerso()) {
				new Pref(this);

			}

			if (e.getSource() == this.getDesign()) {
				new DImageChooser(this.getImageur(), this.getPanneauCentral());
			}
			
			if (e.getSource() == this.getQuitter())
				System.exit(0);

			if (e.getSource() == this.getAide()) {
				File f = new File("resources/Aide");
				new Aide(f);
			}
			if (e.getSource() == this.getCreateur())
				JOptionPane.showMessageDialog(this, " R�alis� par Igor DAURIAC et Nicolas FRANCOIS, Projet IHM",
						"Cr�ateurs...", JOptionPane.INFORMATION_MESSAGE);
		};
		debutant.addActionListener(al);
		intermediaire.addActionListener(al);
		expert.addActionListener(al);
		perso.addActionListener(al);
		nouvelle.addActionListener(al);
		quitter.addActionListener(al);
		design.addActionListener(al);
		stat.addActionListener(al);
		aide.addActionListener(al);
		createur.addActionListener(al);
	}

	/**
	 * Permet de récupérer les paramètres d'une nouvelle partie
	 * 
	 * @return Un objet de la classe JMenuItem
	 */
	public JMenuItem getNouvelle() {
		System.out.println("DFenetre - getNouvelle");
		return nouvelle;
	}

	/**
	 * Permet de récupérer les paramètres d'une partie en débutant
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getDebutant() {
		System.out.println("DFenetre - getDebutant");
		return debutant;
	}

	/**
	 * Permet de récupérer les paramètres d'une partie en intermédiaire
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getIntermediaire() {
		System.out.println("DFenetre - getIntermediaire");
		return intermediaire;
	}

	/**
	 * Permet de récupérer les paramètres d'une partie en expert
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getExpert() {
		System.out.println("DFenetre - getExpert");
		return expert;
	}

	/**
	 * Permet de récupérer les paramètres d'une partie personnalisée
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getPerso() {
		System.out.println("DFenetre - getPerso");
		return perso;
	}

	/**
	 * Permet de récupérer l'item design
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getDesign() {
		System.out.println("DFenetre - getDesign");
		return design;
	}

	/**
	 * Permet de récupérer l'item quitter
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getQuitter() {
		System.out.println("DFenetre - getQuitter");
		return quitter;
	}

	/**
	 * Permet de récupérer l'item aide
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getAide() {
		System.out.println("DFenetre - getAide");
		return aide;
	}

	/**
	 * Permet de récupérer l'item créateur
	 * 
	 * @return Un objet de la classe JMenuItem 
	 */
	public JMenuItem getCreateur() {
		System.out.println("DFenetre - getCreateur");
		return createur;
	}

	/**
	 * Permet de récupérer le nombre de cases en hauteur de la partie
	 * 
	 * @return Un entier
	 */
	public int getHauteur() {
		return partie.getHauteur();
	}

	/**
	 * Permet de récupérer le nombre de cases en largeur de la partie
	 * 
	 * @return Un entier
	 */
	public int getLargeur() {
		return partie.getLargeur();
	}

	/**
	 * Permet de récupérer le nombre de mines de la partie
	 * 
	 * @return Un entier
	 */
	public int getMines() {
		return partie.getMines();
	}

	private void miseEnPage() {
		System.out.println("DFenetre - miseEnPage");
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout(5, 5));

		/* partie haute de l'IHM */
		nord = new JPanel();
		nord.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 0, 1, 0);

		/* en haut a gauche */
		minesRestantes = new JLabel("00");
		// nord.add(minesRestantes, BorderLayout.WEST);
		nord.add(minesRestantes, gbc);
		/* centre */
		go = new JButton();

		goCool();
		nord.add(go, gbc);

		temps = new DChronoLabel();
		nord.add(temps, gbc);

		c.add(nord, BorderLayout.NORTH);

	}

	/**
	 * Permet de récupérer le bouton go (bouton avec un emoji au milieu)
	 * 
	 * @return Un objet de la classe JButton
	 */
	public JButton getGo() {
		System.out.println("DFenetre - getGo");

		ActionListener al = e -> {
			this.arretChrono();
			this.initChrono();
			this.nouvellePartie(this.getHauteur(), this.getLargeur(), this.getMines());
		};
		go.addActionListener(al);
		return go;
	}

	/**
	 * Permet de récupérer le plateau de la partie
	 * 
	 * @return Un objet de la classe DPanneau
	 */
	public DPanneau getPanneauCentral() {
		System.out.println("DFenetre - getPanneauCentral");
		return centre;
	}

	/**
	 * Permet de récupérer toutes les images utilisées dans une partie
	 * 
	 * @return Un objet de la classe DImageur
	 */
	public DImageur getImageur() {
		System.out.println("DFenetre - getImageur");
		return imageur;
	}

	/**
	 * Change l'image du bouton go (bouton avec un emoji au milieu)
	 */
	public void goPerdu() {
		System.out.println("DFenetre - goPerdu");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Perdu.GIF"));
	}

	/**
	 * Change l'image du bouton go (bouton avec un emoji au milieu)
	 */
	public void goGagne() {
		System.out.println("DFenetre - goGagne");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Gagne.GIF"));
	}

	/**
	 * Change l'image du bouton go (bouton avec un emoji au milieu)
	 */
	public void goOups() {
		System.out.println("DFenetre - goOups");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Oups.GIF"));
	}

	/**
	 * Change l'image du bouton go (bouton avec un emoji au milieu)
	 */
	public void goCool() {
		System.out.println("DFenetre - goCool");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Cool.GIF"));
	}
	
	/**
	 * Permet de mettre à jour l'affichage du nombre de mines restantes en fonction du nombre de drapeau posé
	 */
	public void miseAJourCompteur(){
		System.out.println("DFenetre - miseAjourCompteur");
			int nb = partie.getMines()
			          -partie.nbrDrapeau();
			Integer integer = nb;
			
			if((nb>9) || (nb<0))
				minesRestantes.setText(integer.toString());
			else
				minesRestantes.setText("0"+integer.toString());
	}

	/**
	 * Permet de lancer le chronomètre de la partie
	 */
	public void lancerChrono() {
		System.out.println("DFenetre - lancerChrono");
		if (!temps.estActif())
			temps.getThread().start();
	}

	/**
	 * Permet d'arrêter le chronomètre de la partie
	 */
	public void arretChrono() {
		System.out.println("DFenetre - arretChrono");
		temps.stop();
	}

	/**
	 * Permet d'initialiser le chronomètre de la partie (le met à 0)
	 */
	public void initChrono() {
		System.out.println("DFenetre - initChrono");
		temps.initChrono();
	}

	/**
	 * Permet de mettre le chronomètre en pause
	 */
	public void pauseChrono() {
		System.out.println("DFenetre - pauseChrono");
		temps.mettreEnPause();
	}

	/**
	 * Permet de reprendre le décompte du chronomètre
	 */
	public void repriseChrono() {
		System.out.println("DFenetre - repriseChrono");
		temps.enleverPause();
	}

	/**
	 * Permet de récupérer le temps du chronomètre
	 * 
	 * @return Un entiers
	 */
	public int getChrono() {
		System.out.println("DFenetre - getChrono");
		return temps.getTime();
	}

	/**
	 * Permet de récupérer l'image d'une case avec ses coordonnées
	 * 
	 * @param i Un entier
	 * @param j Un entier
	 * @return Un objet de la classe ImageIcon
	 */
	public ImageIcon getIcon(int i, int j) {
		System.out.println("DFenetre - getIcon");
		return imageur.getIcon(partie.getEtatCase(i, j));
	}
		
	/**
	 * Permet de récupérer une case avec ses coordonnées
	 * 
	 * @param i Un entier
	 * @param j Un entier
	 * @return Un objet de la classe DCase
	 */
	public DCase getCase(int i, int j)  {
		System.out.println("DFenetre - getCase");
		try{
			return partie.getMatrice()[i][j];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Permet de savoir si la partie est gagnée
	 * 
	 * @return True si la partie est gagnée, False sinon
	 */
	public boolean gagne() {
		return partie.gagne();
	}

	/**
	 * Permet de savoir si une partie est perdue
	 * 
	 * @return True si la partie est perdue, False sinon
	 */
	public boolean perdu() {
		return partie.perdu();
	}

	/**
	 * permet de dévoiler une case
	 * 
	 * @param i Un entier
	 * @param j Un entier
	 */
	public void devoilerCase(int i, int j) {
		partie.devoilerCase(i, j);
	}

	/**
	 * Appelé quand une fenêtre est ouverte
	 */
	@Override
	public void windowOpened(WindowEvent e) {}

	/**
	 * Appelé quand une fenêtre est fermé avec le menu système de la fenêtre
	 */
	@Override
	public void windowClosing(WindowEvent e) {}

	/**
	 * Appelé quand la fenêtre est fermé
	 */
	@Override
	public void windowClosed(WindowEvent e) {}

	/**
	 * Appelé quand une fenêtre passe d'un état normal à réduit
	 */
	@Override
	public void windowIconified(WindowEvent e) {}

	/**
	 * Appelé quand une fenêtre passe d'un état réduit à normal
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {}

	/**
	 * Appelé quand la fenêtre est définie comme la fenêtre active
	 */
	@Override
	public void windowActivated(WindowEvent e) {}

	/**
	 * Appelé quand la fenêtre est définie comme une fenêtre non active
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {}
}
