package fonctionnement.objet;


import fonctionnement.environnement.Case;
import java.util.ArrayList;
import Tool.Tool;

public class Joueur {
	private int deplacementMax;
	private int attaqueMax;
	private String nom;
	private Sac sac;
	private Cabane cabane;
	private Case position;

	public Joueur(String nom) {
		this.deplacementMax = 6;
		this.attaqueMax = 2;
		this.nom = nom;
		this.sac = new Sac(this);
		this.cabane = new Cabane(this);
		this.position = null;
	
	}
	
	public boolean ctontour() {
		this.deplacement();
		this.recupererObjet();
			
		/*
		 * > Choix de prendre ou pas la ressource
		 * > Proposition de construction
		 */
		//this.sac.apliquerEffetObjet();
		return this.cabane.getBateau().gameOver();
	}


	public void ameliorerDeplacement() {
		this.deplacementMax = 8;
	}

	public void diminuerDeplacement() {
		this.deplacementMax = 6;
	}

	public int getAttaqueMax() {
		return this.attaqueMax;
	}
	
	public void setPosition(Case c){
		this.position = c;
	}

	public String getNom() {
		return this.nom;
	}
	
	public Case getPosition(){
		return position;

	}
	public void deplacement() {
		// Ajouter le fait qu'on peut avoir la catapulte
		int de = 0;
		ArrayList<Case> list;
		de = Tool.lancerDe(deplacementMax);
		list = this.position.getChoixCase(de,null);
		this.position = Tool.changerPosition(list);
	}
	

	public void recupererObjet() {
		Objet o = this.position.recupererObjet();
		if (o instanceof ObjetEffet
				&& ((ObjetEffet) o).getType() != TypeObjetEffet.CATAPULTE) {
			switch (((ObjetEffet) o).getType()) {
			case LANCE:
				this.attaqueMax = 4;
				break;
			case BOUSSOLE:
				this.ameliorerDeplacement();
				break;
			case SAC:
				this.sac.addPochette();
				break;
			default:
				return;
			}
		} else {
			if (!this.sac.isFull())
				this.sac.ajouterObjet(o);
			else {
				Tool.parler("Vous n'avez pas pu ramasser l'objet ");
			}
		}
	}
}
