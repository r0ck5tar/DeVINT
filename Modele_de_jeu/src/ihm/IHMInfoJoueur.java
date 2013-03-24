package ihm;

import java.awt.Component;

import javax.swing.*; 

import fonctionnement.objet.Joueur;

public class IHMInfoJoueur extends JPanel{

	private JLabel infos; 
	//private Joueur joueur; 
	
	public IHMInfoJoueur (Joueur joueur){
		this.infos= new JLabel(joueur.getNom()); 
	}
	
	
}
