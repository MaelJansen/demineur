package classesSuprimées;
import java.awt.event.*;

import ihm.DFenetre;

public class EcouteurFenetre extends WindowAdapter{
	
	private DFenetre fenetre;

	public EcouteurFenetre(DFenetre f){
		fenetre = f;
	}
}