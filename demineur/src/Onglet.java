import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Onglet{
	JFrame maFen;
	JTabbedPane jtp;
	
	private JPanel panelCarte(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10,10));
		panel.add(new JTextArea("Carte"), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel panelCasierJud(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10,10));
		panel.add(new JTextArea("Casier Judiciaire"), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel panelDebutant(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10,10));
		panel.add(new JTextArea("Debutant"), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel panelConfirme(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10,10));
		panel.add(new JTextArea("Confirme"), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel panelExpert(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10,10));
		panel.add(new JTextArea("Expert"), BorderLayout.CENTER);
		
		return panel;
	}
	
	private void miseEnPage(){
		Container c = maFen.getContentPane();
		c.setLayout(new BorderLayout());
		
		JTabbedPane jtp1 = new JTabbedPane();
		JTabbedPane jtp2 = new JTabbedPane();
		
		try{	
			jtp = new JTabbedPane();
		}catch(IllegalArgumentException iae){
				System.out.println("Erreur construction jtp");
											}
		jtp1.add(panelCarte(), "Carte");
	    jtp1.add(panelCasierJud(), "Casier Judiciaire");
		
		jtp2.add(panelDebutant(), "Debutant");
		jtp2.add(panelConfirme(), "Confirme");
		jtp2.add(panelExpert(), "Expert");
		
		jtp.add(jtp1, "jtp1");
		jtp.add(jtp2, "jtp2");
		
		c.add(jtp, BorderLayout.CENTER);
	}
	
	
	public Onglet(){
			maFen = new JFrame("Onglet");
			/* mise en page */
			miseEnPage();
			/* affichage */
			maFen.setSize(300, 200);
			maFen.setVisible(true);
	}
	
   }
