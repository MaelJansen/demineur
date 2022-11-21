public class DPartie {
	
	private DMatrice dm;
	
	public DPartie(int h, int l, int nb){
		nouvellePartie(h,l,nb);
	}
	
	public void nouvellePartie(int h, int l, int nb){
		dm = new DMatrice(h,l,nb);		
	}
	
	public DMatrice getMatrice(){
		return dm;
	}
	
	public boolean gagne(){
		return (dm.getCaseNonMineeRestante()==0);
	}
	
	public boolean perdu(){
		return dm.aExplose();
	}
}