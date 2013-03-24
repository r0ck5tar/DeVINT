package fonctionnement.environnement;
import java.util.ArrayList;

import fonctionnement.objet.*;


public class MainTest {
	public static void main(String[] args) {
		Plateau p = new Plateau();
		p.printCases();
		
		ArrayList<Case> a = p.getCases();
		
		/*int i = 0;
		for (Case e : a){
			if (e.getOuest() != null){
				System.out.println(e.getOuest().getPosX() + "  "+ e.getOuest().getPosY() + "   " + i);
				i++;
			}
			
		}*/
		
		Joueur j = new Joueur("Clem");
		j.setPosition(a.get(9));
		
		
		ArrayList<Case> b = j.deplacement(3);
		
		for (Case c : b){
			System.out.println(c.getPosX() +" " + c.getPosY());
		}
	}
	
	

}
