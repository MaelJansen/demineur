public class DPartie {
	
	private DMatrice dm;
	private boolean commence, perdu;
	
	public DPartie(int h, int l, int nb){
		nouvellePartie(h,l,nb);
	}
	
	public void nouvellePartie(int h, int l, int nb){
		commence = false;
		perdu = false;
		dm = new DMatrice(h,l,nb);		
	}
	
	public DMatrice getMatrice(){
		return dm;
	}
	
	public void commencer(){
		commence = true;
	}
	
	public boolean gagne(){
		return (dm.getCaseNonMineeRestante()==0);
	}
	
	public boolean perdu(){
		return dm.aExplose();
	}
	
	public boolean fini(){
		return(commence && (!perdu()) && (!gagne()));
	}

	
}