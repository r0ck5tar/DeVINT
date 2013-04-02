package fonctionnement.jeu;

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
			this.joueurs.add(new Joueur(name));
			return true;
		}
		else return false;
	}
	
	public int startGame() {
		this.setPlateau(new Plateau());
		plateau = new Plateau();		
		return play();
	}
	
	
	private int play() {
		while(true) {
			if(this.joueurs.get(this.qui).ctontour())
				return qui;
			qui++;
			if(this.qui >= this.joueurs.size())
				this.qui = 0;
		}		
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
}
