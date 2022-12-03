package metier;

import java.util.Random;

public class DPartie implements IDPartie{
	private int hauteur, largeur, nbMines; // parametres de la partie
	private DCase[][] matrice;
	private int caseNonMineeRestante;
	private boolean explosion;
	
	/**
	 * Le constructeur de la classe DPartie
	 * 
	 * @param h Le nombre de case en hauteur de la partie (une entier)
	 * @param l Le nombre de case en largeur de la partie (une entier)
	 * @param nb Le nombre de mines dans la partie 
	 */
	public DPartie(int h, int l, int nb) {
		nouvellePartie(h, l, nb);
	}
	
	/**
	 * Permet de lancer une nouvelle partie
	 * 
	 * @param h Le nombre de case en hauteur de la partie (une entier)
	 * @param l Le nombre de case en largeur de la partie (une entier)
	 * @param nb Le nombre de mines dans la partie 
	 */
	public void nouvellePartie(int h, int l, int nb) {
		System.out.println("DPartie - nouvellePartie");
		hauteur = h;
		largeur = l;
		nbMines = nb;
		explosion = false;
		matrice = new DCase[h][l];
		for (int i = 0; i < hauteur; i++)
			for (int j = 0; j < largeur; j++)
				matrice[i][j] = new DCase();
		miner();
		preparerAlentour();
		caseNonMineeRestante = hauteur * largeur - nbMines;
	}

	/**
	 * Permet de savoir si la partie est gagnée
	 * 
	 * @return True si la partie est gagnée, False sinon
	 */
	public boolean gagne() {
		System.out.println("DPartie - gagne");
		return (getCaseNonMineeRestante() == 0);
	}

	/**
	 * Permet de savoir si la partie est perdue
	 * 
	 * @return True si la partie est perdue, False sinon
	 */
	public boolean perdu() {
		System.out.println("DPartie - perdu");
		return aExplose();
	}

	/**
	 * Permet de connaître la nombre de case en hauteur de la partie
	 * 
	 * return Un entier
	 */
	@Override
	public int getHauteur() {
		System.out.println("DPartie - getHauteur");
		return hauteur;
	}

	/**
	 * Permet de connaître la nombre de case en largeur de la partie
	 * 
	 * return Un entier
	 */
	@Override
	public int getLargeur() {
		System.out.println("DPartie - getLargeur");
		return largeur;
	}

	/**
	 * Permet de connaître le nombre de mines dans la partie
	 * 
	 * return Un entier
	 */
	@Override
	public int getMines() {
		System.out.println("DPartie - getMines");
		return nbMines;
	}

	/**
	 * Permet de dévoiler une case
	 * 
	 * @param i Un entier
	 * @param j Un entier
	 */
	public void devoilerCase(int i, int j) {
		System.out.println("DPartie - devoilerCase");
		/* Case d�couverte */
		try {
			matrice[i][j].setDecouverte();
			caseNonMineeRestante--;
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		/* on regarde si la case est min�e */
		try {
			if (matrice[i][j].estMine())
				explosion = true;
			else {

				/* propagation �ventuelle */

				if (matrice[i][j].getMinesAlentour() == 0) {

					try {
						if (!matrice[i - 1][j - 1].estDecouverte())
							devoilerCase(i - 1, j - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i - 1][j].estDecouverte())
							devoilerCase(i - 1, j);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i - 1][j + 1].estDecouverte())
							devoilerCase(i - 1, j + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i][j - 1].estDecouverte())
							devoilerCase(i, j - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i][j + 1].estDecouverte())
							devoilerCase(i, j + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i + 1][j - 1].estDecouverte())
							devoilerCase(i + 1, j - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i + 1][j].estDecouverte())
							devoilerCase(i + 1, j);
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (!matrice[i + 1][j + 1].estDecouverte())
							devoilerCase(i + 1, j + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

	}

	/**
	 * Permet de poser ou enlerver un drapeau sur une case non découverte
	 * 
	 * @param i Un entier
	 * @param j Un entier
	 */
	public void drapeauAction(int i, int j) {
		System.out.println("DPartie - drapeauAction");
		matrice[i][j].drapeauAction();
	}

	/**
	 * Permet de compter le nombre de mines autour de chaque case 
	 */
	private void preparerAlentour() {
		System.out.println("DPartie - preparerAlentour");
		int minesCompteur;

		for (int i = 0; i < hauteur; i++)
			for (int j = 0; j < largeur; j++) {
				minesCompteur = 0;
				if (!matrice[i][j].estMine()) {
					try {
						if (matrice[i - 1][j - 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i - 1][j].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i - 1][j + 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i][j - 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i][j + 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i + 1][j - 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i + 1][j].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					try {
						if (matrice[i + 1][j + 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}

					/* les mines ont �t�s compt�s */
					matrice[i][j].setMinesAlentour(minesCompteur);
				}

			}
	}

	/**
	 * Permet de poser toutes les bombes de la partie de manière aléatoire
	 */
	private void miner() {
		System.out.println("DPartie - miner");
		int x, y;
		int i = 0;
		Random alea = new Random();
		while (i < nbMines) {
			x = alea.nextInt(hauteur);
			y = alea.nextInt(largeur);
			if (!matrice[x][y].estMine()) {
				matrice[x][y].poserBombe();
				i++;
			}

		}
	}

	/**
	 * Permet de compter le nombre de drapeau de la partie
	 * 
	 * return Un entier
	 */
	@Override
	public int nbrDrapeau() {
		System.out.println("DPartie - nbrDrapeau");
		int compteur = 0;
		for (int i = 0; i < hauteur; i++)
			for (int j = 0; j < largeur; j++) {
				if (matrice[i][j].yaDrapeau())
					compteur++;
			}
		return compteur;
	}

	/**
	 * Permet de savoir si le joueur a cliqué sur une case avec une bombe
	 * 
	 * @return True si une bombe a été découverte, False sinon
	 */
	public boolean aExplose() {
		System.out.println("DPartie - aExplose");
		return explosion;
	}

	/**
	 * Permet de connaître le nombre de cases non minées et non découvertes
	 * 
	 * @return Un entier
	 */
	public int getCaseNonMineeRestante() {
		System.out.println("DPartie - getCaseNonMineeRestante");
		return caseNonMineeRestante;
	}

	/**
	 * Permet de connaître l'état d'une case
	 * 
	 * return Un composant de la classe EtatCase
	 */
	@Override
	public EtatCase getEtatCase(int i, int j) {
		System.out.println("DPartie - getEtatCase");
		DCase dcase = null;
		try {
			dcase = matrice[i][j];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erreur");

		}
		if (caseNonMineeRestante != 0 && explosion) {
			return dcase.getEtatCase(EtatPartie.PERDUE);
		}
		if (caseNonMineeRestante == 0 && !explosion) {
			return dcase.getEtatCase(EtatPartie.GAGNEE);
		}
		return dcase.getEtatCase(EtatPartie.ENCOURS);
	}

	/**
	 * Permet de récupérer le tableau à 2 dimensions simulant le plateau du jeu
	 * 
	 * return Un tableau à 2 dimensions d'objet de la classe DCase
	 */
	@Override
	public DCase[][] getMatrice() {
		System.out.println("DPartie - getMatrice");
		return matrice;
	}
}