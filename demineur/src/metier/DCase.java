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
		System.out.println("Dcase - estMine");
		return mine;
	}

	public int getMinesAlentour() {
		System.out.println("Dcase - getMinesAlentour");
		return minesAlentour;
	}

	public boolean yaDrapeau() {
		System.out.println("Dcase - yaDrapeau");
		return drapeau;
	}

	public boolean estDecouverte() {
		System.out.println("Dcase - estDecouverte");
		return decouverte;
	}

	public void setDecouverte() {
		System.out.println("Dcase - setDecouverte");
		decouverte = true;
	}

	public void selectionner() {
		System.out.println("Dcase - selectionner");
		selection = true;
	}

	public void deselectionner() {
		System.out.println("Dcase - deselectionner");
		selection = false;
	}

	public boolean select() {
		System.out.println("Dcase - select");
		return selection;
	}

	public void drapeauAction() {
		System.out.println("Dcase - drapeauAction");
		if (!decouverte) {
			drapeau = (!drapeau);
		}
			
	}

	public void poserBombe() {
		System.out.println("Dcase - poserBombe");
		mine = true;
	}

	public void setMinesAlentour(int nb) {
		System.out.println("Dcase - setMinesAlentour");
		minesAlentour = nb;
	}

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