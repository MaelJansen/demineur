package ihm;
import metier.EtatCase;

import javax.swing.*;

public class DImageur {

	public String repertoire = "resources/Images/Classic";

	public DImageur() {
	}

	public String getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(String s) {
		repertoire = s;
	}

	public ImageIcon getIcon(EtatCase ec) {
		switch (ec) {
		case SELECT :
			return new ImageIcon(repertoire + "/Select.GIF");
		case INCONNUE :
			return new ImageIcon(repertoire + "/Inconnue.GIF");
		case MINE :
			return new ImageIcon(repertoire + "/Mine.GIF");
		case DRAPEAU :
			return new ImageIcon(repertoire + "/Drapeau.GIF");
		case VIDE :
			return new ImageIcon(repertoire + "/Vide.GIF");
		case CROIX :
			return new ImageIcon(repertoire + "/Croix.GIF");
		case UN :
			return new ImageIcon(repertoire + "/1.GIF");
		case DEUX :
			return new ImageIcon(repertoire + "/2.GIF");
		case TROIS :
			return new ImageIcon(repertoire + "/3.GIF");
		case QUATRE :
			return new ImageIcon(repertoire + "/4.GIF");
		case CINQ :
			return new ImageIcon(repertoire + "/5.GIF");
		case SIX :
			return new ImageIcon(repertoire + "/6.GIF");
		case SEPT :
			return new ImageIcon(repertoire + "/7.GIF");
		case HUIT :
			return new ImageIcon(repertoire + "/8.GIF");
		default :
			return new ImageIcon(repertoire + "/Vide.GIF");
		}
	}

}