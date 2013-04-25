package ihm;

import java.util.ArrayList;

import fonctionnement.objet.Joueur;

public class IHMGame {

	private ArrayList<Joueur> joueurs; 
	
	public IHMGame(ArrayList<String> listeJoueur){
		ArrayList<Joueur> joueurs= new ArrayList<Joueur>(); 
		for(int i =0; i<listeJoueur.size(); i++){
			joueurs.add(new Joueur(listeJoueur.get(i))); 
		}
	}
	
}
