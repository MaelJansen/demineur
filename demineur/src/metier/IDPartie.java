package metier;

public interface IDPartie {
	/**
	 * Permet de connaître la nombre de case en hauteur de la partie
	 * 
	 * return Un entier
	 */
	public int getHauteur();
	
	/**
	 * Permet de connaître la nombre de case en largeur de la partie
	 * 
	 * return Un entier
	 */
	public int getLargeur();
	
	/**
	 * Permet de connaître le nombre de mines dans la partie
	 * 
	 * return Un entier
	 */
	public int getMines();
	
	/**
	 * Permet de connaître l'état d'une case
	 * 
	 * return Un composant de la classe EtatCase
	 */
	public EtatCase getEtatCase(int i, int j);
	
	/**
	 * Permet de compter le nombre de drapeau de la partie
	 * 
	 * return Un entier
	 */
	public int nbrDrapeau();
	
	/**
	 * Permet de récupérer le tableau à 2 dimensions simulant le plateau du jeu
	 * 
	 * return Un tableau à 2 dimensions d'objet de la classe DCase
	 */
	public DCase[][] getMatrice();
}
