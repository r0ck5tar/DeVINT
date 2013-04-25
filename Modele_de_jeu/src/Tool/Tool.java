package Tool;

import java.util.ArrayList;
import java.util.Random;

import fonctionnement.environnement.Case;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.ObjetEffet;

public class Tool {
	public static int lancerDe(int n) {
		Random rnd =  new Random();
		return rnd.nextInt(n);
	}

	public static Case changerPosition(ArrayList<Case> list, Joueur joueur) {
		
		return null;
	}
	
	
	
	public static void viderSac(Joueur j){
		
		
	}
	
	public static ArrayList<String> getBuildables(Joueur j){
		return null;
	}
	
	
	public static void construire(String choseAConstruire){
		
	}
}
