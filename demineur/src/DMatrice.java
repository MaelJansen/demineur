import java.lang.*;
import java.util.Random;

public class DMatrice{
	
	private int hauteur, largeur, nbMines; // parametres de la partie
	private DCase[][] matrice; 
	private int caseNonMineeRestante;
	private boolean explosion;
	
	public DMatrice(int h, int l, int nb){
		hauteur = h;
		largeur = l;
		nbMines = nb;
		explosion = false;
		matrice = new DCase[h][l];
		for(int i=0; i<hauteur; i++)
			for(int j=0; j<largeur; j++)
			matrice[i][j] = new DCase();
		miner();
		preparerAlentour(); 
		caseNonMineeRestante = hauteur*largeur-nbMines;
	}
	
	public DCase getCase(int i, int j)  {
		try{
			return matrice[i][j];
		}
		catch(ArrayIndexOutOfBoundsException e){ return null; }
	
	}
	
	public int getHauteur(){
		return hauteur;
	}
	
	public int getLargeur(){
		return largeur;
	}
	
	public int getMines(){
		return nbMines;
	}
	
	
	public void devoilerCase(int i,int j){

		/* Case découverte */
		try{
			   	matrice[i][j].setDecouverte();
			   	caseNonMineeRestante--;
		}
		catch(ArrayIndexOutOfBoundsException e){  }
		
		
		  /* on regarde si la case est minée */
		try{
			  if(matrice[i][j].estMine())
			  	explosion = true;
			  else{
			  
		  	
		 		/* propagation éventuelle */

				if(matrice[i][j].getMinesAlentour()==0){
					
					try{
						if(!matrice[i-1][j-1].estDecouverte())
							devoilerCase(i-1,j-1);
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i-1][j].estDecouverte())
							devoilerCase(i-1,j);
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i-1][j+1].estDecouverte())
							devoilerCase(i-1,j+1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i][j-1].estDecouverte())
							devoilerCase(i,j-1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i][j+1].estDecouverte())
							devoilerCase(i,j+1);
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i+1][j-1].estDecouverte())
							devoilerCase(i+1,j-1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				
					try{
						if(!matrice[i+1][j].estDecouverte())
							devoilerCase(i+1,j);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
								
					try{
						if(!matrice[i+1][j+1].estDecouverte())
							devoilerCase(i+1,j+1);	
					}
					catch(ArrayIndexOutOfBoundsException e){  }
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){  }
		
	}
		
	public void drapeauAction(int i, int j){
		matrice[i][j].drapeauAction();
	}

	private void preparerAlentour(){
		int minesCompteur;
			
		for(int i=0; i<hauteur; i++)
			for(int j=0; j<largeur; j++){
					minesCompteur=0;
					if(!matrice[i][j].estMine()){
						try{
							if(matrice[i-1][j-1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
								
						try{
							if(matrice[i-1][j].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i-1][j+1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i][j-1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i][j+1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
							
						try{
							if(matrice[i+1][j-1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i+1][j].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
						
						try{
							if(matrice[i+1][j+1].estMine()) 
								minesCompteur++;
						}
						catch(ArrayIndexOutOfBoundsException e){  }
					
		
				
						/* les mines ont étés comptés*/
						matrice[i][j].setMinesAlentour(minesCompteur);								
					}
			
			}
				
		}
	
	private void miner(){
		int x,y;
		int i=0;
		Random alea = new Random();
		while(i<nbMines){
			x = alea.nextInt(hauteur);
			y = alea.nextInt(largeur);
			if(!matrice[x][y].estMine()){
				matrice[x][y].poserBombe();
				i++;
			}
			
		}
	}

	public int nbrDrapeau(){
		int compteur = 0;
		for(int i=0;i<hauteur;i++)
			for(int j=0;j<largeur;j++){
				if(matrice[i][j].yaDrapreau())
					compteur++;
			}
		return compteur;
}

	public boolean aExplose(){
		return explosion;
	}
	
	public int getCaseNonMineeRestante(){
		return caseNonMineeRestante;
	}

	public void afficher(){
		System.out.println("****carte****");
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				if(matrice[i][j].estMine())
					System.out.print("M ");
				else
					System.out.print(matrice[i][j].getMinesAlentour()+" ");
			}
			System.out.println("");
			
		}
		System.out.println("****carte connue****");
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				if(!matrice[i][j].estDecouverte())
					System.out.print("* ");
				else
					if(matrice[i][j].estMine())
						System.out.print("M ");
					else
						System.out.print(matrice[i][j].getMinesAlentour()+" ");
			}
			System.out.println("");
			
		}
	}


}