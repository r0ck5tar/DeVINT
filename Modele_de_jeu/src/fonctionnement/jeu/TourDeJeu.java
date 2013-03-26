package fonctionnement.jeu;

import fonctionnement.objet.Joueur;

public class TourDeJeu {
	
	private Joueur joueur; 
	private int valDe; 
	
	public TourDeJeu(Joueur joueur, int valDe){
		this.joueur=joueur; 
		this.initValeurDe();		
	}
	
	public void deroulementTourDeJeu(){
		joueur.deplacement(this.valDe); 
		
	}
	
	public void initValeurDe(){
		this.valDe=(int) (Math.random()*6+1);
	}
	
	
}
