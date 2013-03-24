package fonctionnement.objet;
import fonctionnement.environnement.*;


import java.util.ArrayList;

public class Sac {
	private Joueur joueur;
	private int limite;
	private ArrayList<Objet> stock;
	
	public Sac(Joueur joueur) {
		this.limite = 4;
		this.stock = new ArrayList<Objet>();
		this.joueur = joueur;
	}

	public void addPochette() {
		if (this.limite <= 8) this.limite++;
	}
	
	public ArrayList<Objet> getStock(){
		return stock;
	}
	
	public boolean isFull() {
		return this.stock.size() >= this.limite;
	}	
	
	public void ajouterObjet(Objet r){
		if (this.stock.size() < this.limite)
			stock.add(r);
	}
	
	public void viderSac(){ this.stock.clear();}
	
	public void utiliserObjet(int index) {
		if (this.stock.get(index).isSpecial()) {
			if(((ObjetEffet)this.stock.get(index)).goEffect(joueur))
				this.stock.remove(index);
		}
	}
	
	public Ressource recupererRessource(int index) {
		if(!this.stock.get(index).isSpecial()) {
			Ressource ressource;
			ressource = (Ressource) this.stock.get(index);
			this.stock.remove(index);
			return ressource;
		}
		else return null;
	}

}
