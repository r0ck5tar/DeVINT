package Tool;

import java.util.ArrayList;
import java.util.Random;

import fonctionnement.environnement.Case;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.ObjetEffet;
import fonctionnement.objet.Ressource;

public class Tool {

	public static int lancerDe(int n) {
		Random rnd = new Random();
		return rnd.nextInt(n);
	}

	public static Case changerPosition(ArrayList<Case> list, Joueur joueur) {

		return null;
	}

	public static void parler(String phrase) {

	}

	public static ArrayList<Case> getChoixCase(int n, Joueur joueur) {
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

	public static void viderSac(Joueur j) {

	}

	public static ArrayList<String> getBuildables(Joueur j) {
		ArrayList<String> buildableThings = new ArrayList<String>();
		
		if (j.getCabane().isBuildableAtelier()){
			buildableThings.add("atelier");
		}
		
		if (j.getCabane().isBuildableFilet()){
			buildableThings.add("filet");
		}
		
		if (j.getCabane().isBuildableStock()){
			buildableThings.add("stock");
		}
		
		if (j.getCabane().isBuildableToit()){
			buildableThings.add("toit");
		}
		if (j.getCabane().isBuildableTonneau()){
			buildableThings.add("tonneau");
		}
		
		if (j.getCabane().getBateau().isBuildableCoque()){
			buildableThings.add("coque");
		}
		
		if (j.getCabane().getBateau().isBuildableGouvernail()){
			buildableThings.add("gouvernail");
		}
		
		if (j.getCabane().getBateau().isBuildableMat()){
			buildableThings.add("mat");
		}
		
		if (j.getCabane().getBateau().isBuildableVoile()){
			buildableThings.add("voile");
		}
		
		return buildableThings;
	}

	public static void construire(String choseAConstruire, Joueur j) {
		switch (choseAConstruire) {
		case "toit":
			j.getCabane().construireToit();
			break;

		case "atelier":
			j.getCabane().construireAtelier();
			break;
			
		case "filet":
			j.getCabane().construireFilet();
			break;
			
		case "stock":
			j.getCabane().construireStock();
			break;
			
		case "tonneau":
			j.getCabane().construireTonneau();
			break;
			
		case "mat":
			j.getCabane().getBateau().construireMat();
			break;
			
		case "coque":
			j.getCabane().getBateau().construireCoque();
			break;
			
		case "gouvernail":
			j.getCabane().getBateau().construireGouvernail();
			break;
			
		case "voile":
			j.getCabane().getBateau().construireVoile();
			break;
			
		default:
				
		}
	}
}
