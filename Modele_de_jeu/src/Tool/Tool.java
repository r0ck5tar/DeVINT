package Tool;

import java.util.ArrayList;
import java.util.Random;

import fonctionnement.environnement.Case;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.ObjetEffet;
import fonctionnement.objet.Ressource;

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
		return null;
	}
	
	public static Ressource recupRessource(Ressource r, Joueur j) {
		return null;
	}
	
	public static Joueur mettreRessource(Ressource r, Joueur j) {
		return null;
	}
	
	public static boolean containsObjetEffet(Joueur j) {
		return false;
	}
	
	public static ArrayList<ObjetEffet> recupObjetSpecial(Joueur j) {
		return null;
	}
	
	public static boolean appliquerEffet(Joueur j, ObjetEffet o) {
		// return true si deplacement
		// return false si pas de deplacement
		return false;
	}
	
	
	public static void viderSac(Joueur j){
		
		
	}
	
	public static ArrayList<String> getBuildables(Joueur j){
		return null;
	}
	
	
	public static void construire(String choseAConstruire){
		
	}
}
