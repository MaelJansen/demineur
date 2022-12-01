package metier;

public class DCase {

	private boolean mine;
	private int minesAlentour;
	private boolean drapeau;
	private boolean decouverte;
	private boolean selection;

	public DCase() {
		mine = false;
		decouverte = false;
		drapeau = false;
		selection = false;
		minesAlentour = -1;
	}

	public boolean estMine() {
		return mine;
	}

	public int getMinesAlentour() {
		return minesAlentour;
	}

	public boolean yaDrapeau() {
		return drapeau;
	}

	public boolean estDecouverte() {
		return decouverte;
	}

	public void setDecouverte() {
		decouverte = true;
	}

	public void selectionner() {
		selection = true;
	}

	public void deselectionner() {
		selection = false;
	}

	public boolean select() {
		return selection;
	}

	public void drapeauAction() {
		if (!decouverte)
			drapeau = (!drapeau);
	}

	public void poserBombe() {
		mine = true;
	}

	public void setMinesAlentour(int nb) {
		minesAlentour = nb;
	}

	public EtatCase getEtatCase(EtatPartie ep) {
		if (ep.equals(EtatPartie.ENCOURS)) {
			if (yaDrapeau())
				return EtatCase.DRAPEAU;
			if (!decouverte && !selection)
				return EtatCase.INCONNUE;
			if (!decouverte && selection)
				return EtatCase.SELECT;
			if (mine)
				return EtatCase.MINE;
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
		if (ep.equals(EtatPartie.PERDU)) {
			if (mine)
				return EtatCase.MINE;
			if (yaDrapeau() && !estMine())
				return EtatCase.CROIX;
			if (decouverte)
				return EtatCase.INCONNUE;
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
		} else {
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
}