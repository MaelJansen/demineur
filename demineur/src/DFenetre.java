import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DFenetre extends JFrame {

	final static int DEBUTANT = 1;
	final static int INTER = 2;
	final static int EXPERT = 3;
	final static int PERSO = 4;
	
	int nb_col;
	int nb_lgn;
	int nb_mines;
	
	JMenuBar barreMenus;
	JMenu jeu, options, aPropos;
	
	public JRadioButtonMenuItem debutant, intermediaire, expert, perso;
	public JMenuItem nouvelle, quitter, aide, createur, design, stat;
	
	private JLabel minesRestantes;
	private DChronoLabel temps;
	private JPanel nord; 
	private int tailleX, tailleY;
	
	private JButton go;
	private DButton[][] boutons;
	private DPartie partie;

	private DImageur imageur;
	private DPanneau centre;
	
	int type;
	
	public DFenetre(DPartie p){
		super("Demineur");
			menu();
		type = DEBUTANT;
		imageur = new DImageur(p);
		miseEnPage();
	
		connecterPartie(p);
	}
	
	public void connecterPartie(DPartie p){
		partie = p; 
		nb_lgn = p.getMatrice().getHauteur();
		nb_col = p.getMatrice().getLargeur();
		nb_mines = p.getMatrice().getMines();
		

		miseAJourCompteur();
		goCool();
		
		/* partie centrale : damier */
		if(centre!=null)
			getContentPane().remove(centre);
		centre = new DPanneau(imageur, nb_lgn, nb_col);
		
		EcouteurSouris ecouteurSouris = new EcouteurSouris(this, partie);
		centre.addMouseListener(ecouteurSouris);
		centre.addMouseMotionListener(ecouteurSouris);
		
		getContentPane().add(centre, BorderLayout.CENTER);	
		
		/* Affichage */
		
		this.setSize(20*nb_col + 6, 20*nb_lgn + 50 + 23 + 25);
		
		this.setResizable(false);
	 	
	 	this.setVisible(true);
	 	
	 	this.repaint();
	

	}
	
	private void menu(){
		/* creation du menu de jeu */
		jeu  = new JMenu("Jeu");
		nouvelle = new JMenuItem("Nouvelle partie");
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		nouvelle.setToolTipText("Partie avec les mêmes paramètres");
		jeu.add(nouvelle);
		jeu.addSeparator();
		
		ButtonGroup groupRadio=new ButtonGroup();
		
		debutant = new JRadioButtonMenuItem("Débutant");
		debutant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		debutant.setToolTipText("81 cases 10 mines");
		
		intermediaire = new JRadioButtonMenuItem("Intermédiaire");
		intermediaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_MASK));
		intermediaire.setToolTipText("256 cases 40 mines");

		expert = new  JRadioButtonMenuItem("Expert");
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
		expert.setToolTipText("480 cases 99 mines");

		perso = new  JRadioButtonMenuItem("Personnalisé...");
		perso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		perso.setToolTipText("Partie avec vos votres paramètres");
		
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
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));

		jeu.add(quitter);
	
				
		/* creation du menu de options */
		options = new JMenu("Options");
		
		design = new JMenuItem("Graphisme");
		design.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
		design.setToolTipText("Pour choisir le style d'image");
		options.add(design);
		options.addSeparator();
						
		stat = new JMenuItem("Statistiques");
		stat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		stat.setToolTipText("Pour connaitre les scores");
		options.add(stat);
		
		
		/* creation du menu A propos */
		aPropos = new JMenu("?");
		
		aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
		aide.setToolTipText("Pour obtenir de l'aide");
		aPropos.add(aide);

		aPropos.addSeparator();
		
		createur = new JMenuItem("Créateurs");
		createur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		createur.setToolTipText("Par qui ?");
		aPropos.add(createur);
		
				
		/* ajout des menus à la barre */
		barreMenus = new JMenuBar();
		barreMenus.add(jeu);
		barreMenus.add(options);
		barreMenus.add(aPropos);
		this.setJMenuBar(barreMenus);
	}
	
	public void ecouterMenu(ActionListener al){
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
	
	public JMenuItem getNouvelle(){
		return nouvelle;
	}
	
	public JMenuItem getDebutant(){
		return debutant;
	}
	
	public JMenuItem getIntermediaire(){
		return intermediaire;
	}
	
	public JMenuItem getExpert(){
		return expert;
	}
	
	public JMenuItem getPerso(){
		return perso;
	}
	
	public JMenuItem getDesign(){
		return design;
	}
	
	public JMenuItem getStat(){
		return stat;
	}
	
	public JMenuItem getQuitter(){
		return quitter;
	}	
	
	public JMenuItem getAide(){
		return aide;
	}
	
	public JMenuItem getCreateur(){
		return createur;
	}
	
	public int getHauteur(){
		return nb_lgn;
	}

	public int getLargeur(){
		return nb_col;
	}
	
	public int getMines(){
		return nb_mines;
	}	
	
	private void miseEnPage(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout(5, 5));

		/* partie haute de l'IHM */
		nord = new JPanel();
		nord.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
     	gbc.anchor = GridBagConstraints.CENTER;
     	gbc.weightx = 1;
      	gbc.weighty = 1;
		gbc.insets = new Insets(5,0,1,0);
				
		/* en haut a gauche */
		minesRestantes = new JLabel("00");
		//nord.add(minesRestantes, BorderLayout.WEST);
		nord.add(minesRestantes, gbc);
		/* centre */
		go = new JButton();
		
		goCool();
		nord.add(go, gbc);	
		
		temps = new DChronoLabel();	
		nord.add(temps,gbc);
		
		
		
		c.add(nord, BorderLayout.NORTH);
		

	}
	
	public JButton getGo(){
		return go;
	}
	
	public JMenu getMenuPartie(){
		return jeu;
	}
	
	public DPanneau getPanneauCentral(){
		return centre;
	}
	
	public DImageur getImageur(){
		return imageur;
	}
	
	public void goPerdu(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Perdu.GIF"));
	}
	
	public void goGagne(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Gagne.GIF"));
	}
	
	public void goOups(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Oups.GIF"));
	}
	
	public void goCool(){
		go.setIcon(new ImageIcon(imageur.getRepertoire()+"/Cool.GIF"));
	}
	
	public void miseAJourCompteur(){
			int nb = partie.getMatrice().getMines()
			          -partie.getMatrice().nbrDrapeau();
			Integer integer = new Integer(nb);
			
			if((nb>9) || (nb<0))
				minesRestantes.setText(integer.toString());
			else
				minesRestantes.setText("0"+integer.toString());
			
	}
	
	public void lancerChrono(){
		if(!temps.estActif())
			temps.getThread().start();
	}
	
	public void arretChrono(){
		temps.stop();	
	}
	
	public void initChrono(){
		temps.initChrono();
	}
	
	public void pauseChrono(){
		temps.mettreEnPause();	
	}
	
	public void repriseChrono(){
		temps.enleverPause();
	}
	
	public int getChrono(){
		return temps.getTime();
	}
}
