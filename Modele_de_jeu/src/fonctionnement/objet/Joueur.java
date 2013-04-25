package fonctionnement.objet;


import fonctionnement.environnement.Case;
import fonctionnement.jeu.Game;

import ihm.IHMGameView;

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
	
	public Cabane getCabane(){
		return this.cabane;
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
		list = this.position.getChoixCase(de,null,this);
		this.position = Tool.changerPosition(list,this);
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

	
	/**
	 * Methode arrangerListe. Cette m�thode permet de ranger l'ArrayList de case obtenue gr�ce � la m�thode d�placement. 
	 * Rangement�par ordre croissant: comparaison des x. Si les x sont �gaux, on regarde les y. 
	 * @param cases
	 * @param position
	 * @return
	 */
	
	private ArrayList<Case> arrangerListe(ArrayList<Case> cases, int position){
		int saveVal=0;
		if(cases.get(position+1)==null || cases.get(position).getPosX()<cases.get(position+1).getPosX()){
			return cases; 
		}
		else if(cases.get(position).getPosX()==cases.get(position+1).getPosX()){
			if(cases.get(position).getPosY()>cases.get(position+1).getPosY()){
				saveVal=cases.get(position).getPosY(); 
				cases.get(position).setPosY(cases.get(position+1).getPosY());
				cases.get(position+1).setPosY(saveVal); 
				return arrangerListe(cases,position++);
			}
			else{
				return arrangerListe(cases,position++);
			}
		}
		else if(cases.get(position).getPosX()>cases.get(position+1).getPosX()){
			saveVal=cases.get(position).getPosX(); 
			cases.get(position).setPosX(cases.get(position+1).getPosX());
			cases.get(position+1).setPosX(saveVal);
			return this.arrangerListe(cases, position++); 
		}
		return null;
	}
	
	
	public ArrayList<Case> arrangerListe(ArrayList<Case> cases){
		for(int i =0; i <cases.size(); i++){

			this.arrangerListe(cases, i);
		}
		return cases;
	}
	
	public void decisionApresDe(Case choix){
		
	}
	
	public int getDeplacementMax() {
		return deplacementMax;
	}


	public void setDeplacementMax(int deplacementMax) {
		this.deplacementMax = deplacementMax;
	}

	public Cabane getCabane() {
		return cabane;
	}

	public void setCabane(Cabane cabane) {
		this.cabane = cabane;
	}

	public Sac getSac() {
		return sac;
	}

	public void setSac(Sac sac) {
		this.sac = sac;
	}
}
