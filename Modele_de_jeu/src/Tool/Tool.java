package Tool;

import java.util.ArrayList;
import java.util.Random;

import fonctionnement.environnement.Case;
import fonctionnement.objet.Joueur;

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
}
