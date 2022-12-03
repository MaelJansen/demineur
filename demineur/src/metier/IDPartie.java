package metier;

public interface IDPartie {
	
	public int getHauteur();
	public int getLargeur();
	public int getMines();
	public EtatCase getEtatCase(int i, int j);
	public int nbrDrapeau();
	public DCase[][] getMatrice();
}
