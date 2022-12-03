package other;
import javax.swing.*;

public class DChronoLabel extends JLabel implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread t;
	private boolean enMarche, pause;
	private int temps;

	public DChronoLabel() {
		super();
		initChrono();
	}

	public boolean estActif() {
		return enMarche;
	}

	public Thread getThread() {
		return t;
	}

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

	public void mettreEnPause() {
		pause = true;
	}

	public void enleverPause() {
		pause = false;
	}

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

	public void initChrono() {
		setText("000");
		temps = 0;
		enMarche = false;
		t = new Thread(this);
	}

	public int getTime() {
		return temps;
	}

}