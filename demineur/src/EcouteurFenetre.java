import java.awt.event.*;

public class EcouteurFenetre extends WindowAdapter{
	
	private DFenetre fenetre;
	
	public EcouteurFenetre(DFenetre f){
		fenetre = f;
	}
	
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	
	public void windowIconified(WindowEvent e){
		fenetre.pauseChrono();
	}
	
	public void windowDeiconified(WindowEvent e){
		fenetre.repriseChrono();
	}
	
	
}