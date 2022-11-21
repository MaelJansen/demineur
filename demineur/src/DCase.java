public class DCase{
	
	private boolean mine;
	private int mimesAlentour;
	private boolean drapeau;
	private boolean decouverte;
	private boolean selection;
	
	public DCase(){
		mine = false;
		decouverte = false;
		drapeau = false;
		selection = false;
		mimesAlentour = -1;
	}
	
	
	public boolean estMine(){
		return mine;
	}
	
	public int getMinesAlentour(){		
		return mimesAlentour;
	}
	
	public boolean yaDrapreau(){
		return drapeau;
	}
	
	public boolean estDecouverte(){
		return decouverte;
	}
		
	public void setDecouverte(){
		decouverte = true;
	}
	
	public void selectionner(){
		selection = true;
	}
	
	public void deselectionner(){
		selection = false;
	}
	
	public boolean select(){
		return selection;
	}
	
	public void drapeauAction(){
		if(!decouverte)
			drapeau = (!drapeau);
			
	}
	
	public void poserBombe(){
		mine = true;
	}
	
	public void setMinesAlentour(int nb){
		mimesAlentour = nb;
	}
}