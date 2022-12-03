package other;
import javax.swing.*;

public class DChronoLabel extends JLabel implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread t;
	private boolean enMarche, pause;
	private int temps;

	/**
	 * Le constructeur de la classe DChronoLabel
	 */
	public DChronoLabel() {
		super();
		initChrono();
	}

	/**
	 * Permet de savoir si le chronomètre est actif
	 * 
	 * @return True si le chronomètre est actif, False sinon
	 */
	public boolean estActif() {
		return enMarche;
	}

	/**
	 * Permet de récupérer la tâche en cours
	 * 
	 * @return Un objet de la classe Thread
	 */
	public Thread getThread() {
		return t;
	}

	/**
	 * Permet de lancer le chronomètre
	 */
	public void run() {
		temps = 0;
		enMarche = true;
		pause = false;
		while (enMarche) {
			temps++;
			setText(convertirTemps());
			while (pause) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
			}
		}

	}

	/**
	 * Permet de mettre le chronomètre en pause
	 */
	public void mettreEnPause() {
		pause = true;
	}

	/**
	 * Permet de reprendre le chronomètre
	 */
	public void enleverPause() {
		pause = false;
	}

	/**
	 * Permet d'arrêtre le chronomètre
	 */
	public void stop() {
		enMarche = false;
	}

	String convertirTemps() {
		Integer i = temps;
		if (temps < 10)
			return ("00" + i.toString());
		else if (temps < 100)
			return ("0" + i.toString());
		else
			return i.toString();
	}

	/**
	 * Permet d'initialiser le chronomètre (le met à 0)
	 */
	public void initChrono() {
		setText("000");
		temps = 0;
		enMarche = false;
		t = new Thread(this);
	}

	/**
	 * Permet de récupérer le temps actuel du chronomètre
	 * 
	 * @return Un entier
	 */
	public int getTime() {
		return temps;
	}

}