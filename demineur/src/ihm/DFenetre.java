package ihm;

import javax.swing.*;

import controllers.EcouteurSouris;
import main.Aide;
import metier.DCase;
import metier.DChronoLabel;
import metier.DImageChooser;
import metier.DImageur;
import metier.DPanneau;
import metier.DPartie;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class DFenetre extends JFrame implements WindowListener {

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

	public DFenetre(DPartie p) {
		super("Demineur");
		menu();
		imageur = new DImageur();
		miseEnPage();

		connecterPartie(p);
	}
	
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

	public void nouvellePartie(int h, int l, int nbMines) {
		partie.nouvellePartie(h, l, nbMines);
		connecterPartie(partie);
	}

	private void menu() {
		System.out.println("DFenetre - menu");
		/* creation du menu de jeu */
		jeu = new JMenu("Jeu");
		nouvelle = new JMenuItem("Nouvelle partie");
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		nouvelle.setToolTipText("Partie avec les m�mes param�tres");
		jeu.add(nouvelle);
		jeu.addSeparator();

		ButtonGroup groupRadio = new ButtonGroup();

		debutant = new JRadioButtonMenuItem("D�butant");
		debutant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		debutant.setToolTipText("81 cases 10 mines");

		intermediaire = new JRadioButtonMenuItem("Interm�diaire");
		intermediaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		intermediaire.setToolTipText("256 cases 40 mines");

		expert = new JRadioButtonMenuItem("Expert");
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		expert.setToolTipText("480 cases 99 mines");

		perso = new JRadioButtonMenuItem("Personnalis�...");
		perso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
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
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

		jeu.add(quitter);

		/* creation du menu de options */
		options = new JMenu("\n" + "import (default package);Options");

		design = new JMenuItem("Graphisme");
		design.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		design.setToolTipText("Pour choisir le style d'image");
		options.add(design);
		options.addSeparator();

		stat = new JMenuItem("Statistiques");
		stat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		stat.setToolTipText("Pour connaitre les scores");
		options.add(stat);

		/* creation du menu A propos */
		aPropos = new JMenu("?");

		aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		aide.setToolTipText("Pour obtenir de l'aide");
		aPropos.add(aide);

		aPropos.addSeparator();

		createur = new JMenuItem("Cr�ateurs");
		createur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		createur.setToolTipText("Par qui ?");
		aPropos.add(createur);

		/* ajout des menus � la barre */
		barreMenus = new JMenuBar();
		barreMenus.add(jeu);
		barreMenus.add(options);
		barreMenus.add(aPropos);
		this.setJMenuBar(barreMenus);
	}

	public void ecouterMenu(ActionListener al) {
		System.out.println("DFenetre - ecouterMenu");
		
	public void ecouterMenu() {
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
				Pref pref = new Pref(this);

			}

			if (e.getSource() == this.getDesign()) {
				DImageChooser choix = new DImageChooser(this.getImageur(), this.getPanneauCentral());
			}

			if (e.getSource() == this.getQuitter())
				System.exit(0);

			if (e.getSource() == this.getAide()) {
				File f = new File("resources/Aide");
				Aide a = new Aide(f);
				;
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

	public JMenuItem getNouvelle() {
		System.out.println("DFenetre - getNouvelle");
		return nouvelle;
	}

	public JMenuItem getDebutant() {
		System.out.println("DFenetre - getDebutant");
		return debutant;
	}

	public JMenuItem getIntermediaire() {
		System.out.println("DFenetre - getIntermediaire");
		return intermediaire;
	}

	public JMenuItem getExpert() {
		System.out.println("DFenetre - getExpert");
		return expert;
	}

	public JMenuItem getPerso() {
		System.out.println("DFenetre - getPerso");
		return perso;
	}

	public JMenuItem getDesign() {
		System.out.println("DFenetre - getDesign");
		return design;
	}

	public JMenuItem getQuitter() {
		System.out.println("DFenetre - getQuitter");
		return quitter;
	}

	public JMenuItem getAide() {
		System.out.println("DFenetre - getAide");
		return aide;
	}

	public JMenuItem getCreateur() {
		System.out.println("DFenetre - getCreateur");
		return createur;
	}

	public int getHauteur() {
		return partie.getHauteur();
	}

	public int getLargeur() {
		return partie.getLargeur();
	}

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

	public DPanneau getPanneauCentral() {
		System.out.println("DFenetre - getPanneauCentral");
		return centre;
	}

	public DImageur getImageur() {
		System.out.println("DFenetre - getImageur");
		return imageur;
	}

	public void goPerdu() {
		System.out.println("DFenetre - goPerdu");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Perdu.GIF"));
	}

	public void goGagne() {
		System.out.println("DFenetre - goGagne");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Gagne.GIF"));
	}

	public void goOups() {
		System.out.println("DFenetre - goOups");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Oups.GIF"));
	}

	public void goCool() {
		System.out.println("DFenetre - goCool");
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Cool.GIF"));
	}
	
	public void miseAJourCompteur(){
		System.out.println("DFenetre - miseAjourCompteur");
			int nb = partie.getMines()
			          -partie.nbrDrapeau();
			Integer integer = new Integer(nb);
			
			if((nb>9) || (nb<0))
				minesRestantes.setText(integer.toString());
			else
				minesRestantes.setText("0"+integer.toString());
			
	}

	public void lancerChrono() {
		System.out.println("DFenetre - lancerChrono");
		if (!temps.estActif())
			temps.getThread().start();
	}

	public void arretChrono() {
		System.out.println("DFenetre - arretChrono");
		temps.stop();
	}

	public void initChrono() {
		System.out.println("DFenetre - initChrono");
		temps.initChrono();
	}

	public void pauseChrono() {
		System.out.println("DFenetre - pauseChrono");
		temps.mettreEnPause();
	}

	public void repriseChrono() {
		System.out.println("DFenetre - repriseChrono");
		temps.enleverPause();
	}

	public int getChrono() {
		System.out.println("DFenetre - getChrono");
		return temps.getTime();
	}

	public ImageIcon getIcon(int i, int j) {
		System.out.println("DFenetre - getIcon");
		return imageur.getIcon(partie.getEtatCase(i, j));
	}
		
	public DCase getCase(int i, int j)  {
		System.out.println("DFenetre - getCase");
		try{
			return partie.getMatrice()[i][j];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public boolean gagne() {
		return partie.gagne();
	}

	public boolean perdu() {
		return partie.perdu();
	}

	public void devoilerCase(int i, int j) {
		partie.devoilerCase(i, j);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
