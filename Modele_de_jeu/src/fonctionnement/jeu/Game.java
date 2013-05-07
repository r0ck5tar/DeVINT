package fonctionnement.jeu;

import ihm.IHMGameView;

import java.util.ArrayList;
import fonctionnement.environnement.*;
import fonctionnement.objet.*;

public class Game {
	private final int MAXJOUEUR = 4;
	private ArrayList<Joueur> joueurs;
	private int qui;
	private Plateau plateau;
	
	public Game() {
		this.joueurs = new ArrayList<Joueur>();
		this.qui = 0;
		this.setPlateau(null);
		this.plateau = null;
	}
	
	public ArrayList<Joueur> getJoueurs() { return this.joueurs;}
	public int getQui() { return this.qui;}
	public void setQui(int qui) { this.qui = qui;}
	
	public boolean ajouterJoueur(String name) {
		if(this.joueurs.size() < this.MAXJOUEUR) {
			Joueur j = new Joueur(name);/*
			j.getCabane().getStock().getStock().add(new Ressource(TypeRessource.BOIS));
			j.getCabane().getStock().getStock().add(new Ressource(TypeRessource.BOIS));
			j.getCabane().getStock().getStock().add(new Ressource(TypeRessource.BOIS));
			j.getCabane().getStock().getStock().add(new Ressource(TypeRessource.BOIS));
			j.getCabane().getStock().getStock().add(new Ressource(TypeRessource.LIANE));
			j.getSac().getStock().add(new ObjetEffet(TypeObjetEffet.CATAPULTE));*/
			this.joueurs.add(j);
			return true;
		}
		else return false;
	}
	
	public void initialisePlateau() {
		plateau = new Plateau();	
	}
	
	public int startGame() {	
		return play();
	}
	
	
	private int play() {
		while(true) {
			if(this.joueurs.get(this.qui).ctontour()){
				return qui;
			}
			qui++;
			if(this.qui >= this.joueurs.size()){
				this.qui = 0;
			}
			
		}		
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
}
