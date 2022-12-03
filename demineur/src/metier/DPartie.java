package metier;

import java.util.Random;

public class DPartie implements IDPartie{
	private int hauteur, largeur, nbMines; // parametres de la partie
	private DCase[][] matrice;
	private int caseNonMineeRestante;
	private boolean explosion;

	public DPartie(int h, int l, int nb) {
		nouvellePartie(h, l, nb);
	}

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

	public boolean gagne() {
		System.out.println("DPartie - gagne");
		return (getCaseNonMineeRestante() == 0);
	}

	public boolean perdu() {
		System.out.println("DPartie - perdu");
		return aExplose();
	}

	@Override
	public int getHauteur() {
		System.out.println("DPartie - getHauteur");
		return hauteur;
	}

	@Override
	public int getLargeur() {
		System.out.println("DPartie - getLargeur");
		return largeur;
	}

	@Override
	public int getMines() {
		System.out.println("DPartie - getMines");
		return nbMines;
	}

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

	public void drapeauAction(int i, int j) {
		System.out.println("DPartie - drapeauAction");
		matrice[i][j].drapeauAction();
	}

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

	public boolean aExplose() {
		System.out.println("DPartie - aExplose");
		return explosion;
	}

	public int getCaseNonMineeRestante() {
		System.out.println("DPartie - getCaseNonMineeRestante");
		return caseNonMineeRestante;
	}

	public void afficher() {
		System.out.print("DPartie - afficher");
		System.out.println("****carte****");
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if (matrice[i][j].estMine())
					System.out.print("M ");
				else
					System.out.print(matrice[i][j].getMinesAlentour() + " ");
			}
			System.out.println("");

		}
		System.out.println("****carte connue****");
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if (!matrice[i][j].estDecouverte())
					System.out.print("* ");
				else if (matrice[i][j].estMine())
					System.out.print("M ");
				else
					System.out.print(matrice[i][j].getMinesAlentour() + " ");
			}
			System.out.println("");

		}
	}

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

	@Override
	public DCase[][] getMatrice() {
		System.out.println("DPartie - getMatrice");
		return matrice;
	}
}