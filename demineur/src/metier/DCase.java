package metier;

public class DCase {

	private boolean mine;
	private int minesAlentour;
	private boolean drapeau;
	private boolean decouverte;
	private boolean selection;

	/**
	 * Le constructeur de la classe DCase
	 */
	public DCase() {
		mine = false;
		decouverte = false;
		drapeau = false;
		selection = false;
		minesAlentour = -1;
	}

	/**
	 * Permet de savoir si une case est minée
	 * 
	 * @return True si la case est minée, False sinon
	 */
	public boolean estMine() {
		System.out.println("Dcase - estMine");
		return mine;
	}

	/**
	 * permet de connaître le nombre de mines autour d'une case
	 * 
	 * @return Un entier
	 */
	public int getMinesAlentour() {
		System.out.println("Dcase - getMinesAlentour");
		return minesAlentour;
	}
	
	/**
	 * Permet de savoir si une case a un drapeau
	 * 
	 * @return True si il y a un drapeau sur la case, False sinon
	 */
	public boolean yaDrapeau() {
		System.out.println("Dcase - yaDrapeau");
		return drapeau;
	}

	/**
	 * Permet de savoir si une case est découverte
	 * 
	 * @return True si une case est découverte, False sinon
	 */
	public boolean estDecouverte() {
		System.out.println("Dcase - estDecouverte");
		return decouverte;
	}

	/**
	 * Permet de modifier l'état d'une case de non découverte à découverte
	 */
	public void setDecouverte() {
		System.out.println("Dcase - setDecouverte");
		decouverte = true;
	}
	
	/**
	 * Permet de sélectionner une case
	 */
	public void selectionner() {
		System.out.println("Dcase - selectionner");
		selection = true;
	}
	
	/**
	 * Permet de déselectionner une case
	 */
	public void deselectionner() {
		System.out.println("Dcase - deselectionner");
		selection = false;
	}
	
	/**
	 * Permet de savoir si une case est sélectionnée
	 * 
	 * @return True si la case est sélectionnée, False sinon
	 */
	public boolean select() {
		System.out.println("Dcase - select");
		return selection;
	}

	/**
	 * Permet de poser ou enlever un drapeau sur une case non découverte
	 */
	public void drapeauAction() {
		System.out.println("Dcase - drapeauAction");
		if (!decouverte) {
			drapeau = (!drapeau);
		}	
	}

	/**
	 * Permet de miner une case
	 */
	public void poserBombe() {
		System.out.println("Dcase - poserBombe");
		mine = true;
	}

	/**
	 * Permet de modifier le nombre de mines autour d'une case
	 * 
	 * @param nb
	 */
	public void setMinesAlentour(int nb) {
		System.out.println("Dcase - setMinesAlentour");
		minesAlentour = nb;
	}
	
	/**
	 * Permet de connaître l'état d'une case en fonction de l'état de la partie
	 * 
	 * @param ep la partie en cours
	 * @return Un composant de la classe EtatCase
	 */
	public EtatCase getEtatCase(EtatPartie ep) {
		System.out.println("Dcase - getEtatCase");
		if (ep.equals(EtatPartie.ENCOURS)) {
			if (yaDrapeau())
				return EtatCase.DRAPEAU;
			if (!decouverte && !selection)
				return EtatCase.INCONNUE;
			if (!decouverte && selection)
				return EtatCase.SELECT;
		}
		if (ep.equals(EtatPartie.PERDUE)) {
			if (yaDrapeau() && !estMine())
				return EtatCase.CROIX;
			if (mine) {
				return EtatCase.MINE;
			}
			if (!decouverte && !selection)
				return EtatCase.INCONNUE;
		}
		if (ep.equals(EtatPartie.GAGNEE)) {
			if (yaDrapeau() || mine)
				return EtatCase.DRAPEAU;
		}
		switch (minesAlentour) {
		case 0:
			return EtatCase.VIDE;
		case 1:
			return EtatCase.UN;
		case 2:
			return EtatCase.DEUX;
		case 3:
			return EtatCase.TROIS;
		case 4:
			return EtatCase.QUATRE;
		case 5:
			return EtatCase.CINQ;
		case 6:
			return EtatCase.SIX;
		case 7:
			return EtatCase.SEPT;
		default:
			return EtatCase.HUIT;
		}
	}
}