package Tool;

import java.util.ArrayList;
import java.util.Random;

import fonctionnement.environnement.Case;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.Objet;
import fonctionnement.objet.ObjetEffet;
import fonctionnement.objet.Ressource;
import fonctionnement.objet.Sac;
import fonctionnement.objet.Stock;

public class Tool {

	public static int lancerDe(int n) {
		Random rnd =  new Random();
		return rnd.nextInt(n);
	}

	public static Case changerPosition(ArrayList<Case> list, Joueur joueur) {
		
		return null;
	}
	
	public static void parler(String phrase) {
		
	}
	
	public static ArrayList<Case> getChoixCase(int n,Joueur joueur) {
		// Laisser peut-etre dans IHM
		return joueur.getPosition().getChoixCase(n, joueur.getPosition(),joueur);
	}
	
	public static Objet recupRessource(Joueur j) {
		return j.getPosition().recupererObjet();
	}
	
	public static boolean mettreRessource(Ressource r, Joueur j) {
		if(!j.getSac().isFull()) {
			j.getSac().ajouterObjet(r);
			return true;
		}
		else return false;
	}
	
	public static boolean containsObjetEffet(Joueur j) {
		// Laisser peut-etre dans IHM
		return j.getSac().containsObjetEffet();
	}
	
	public static ArrayList<ObjetEffet> recupObjetSpecial(Joueur j) {
		// Laisser peut-etre dans IHM
		return j.getSac().recupObjetEffet();
	}
	
	public static boolean appliquerEffet(Joueur j, ObjetEffet o) {
		switch(o.getType()) {
		case CATAPULTE:
			j.setPosition(j.getCabane().getPosition());
			return true;
		default:
			return false;
		}
	}
	
	
	public static void viderSac(Joueur j){
		Stock stock = j.getCabane().getStock();
		Sac sac = j.getSac();
		
		while(sac.containsRessource() || stock.isFull()) {
			stock.ajouterRessource(sac.getFirstRessource());
		}
	}
	
	public static ArrayList<String> getBuildables(Joueur j){
		return null;
	}
	
	
	public static void construire(String choseAConstruire){
		
	}
}
