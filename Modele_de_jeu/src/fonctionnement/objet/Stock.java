package fonctionnement.objet;

import java.util.ArrayList;

public class Stock {
	private ArrayList<Ressource> stock;
	private int niveau;
	private final int NIVEAUMAX = 10;
	public Stock() {
		this.niveau = 4;
		this.stock = new ArrayList<Ressource>();
	}
	
	public int getNiveau() { return this.niveau;}
	public int getNIVEAUMAX() { return this.NIVEAUMAX;}
	
	public ArrayList<Ressource> getStock() { return stock;}
	
	public boolean contains(TypeRessource type, int quantite) {
		int toRet = 0;
		for(Ressource r : this.stock) {
			if(r.getType() == type) toRet++;
		}
		return toRet >= quantite;
	}
	
	public void remove(TypeRessource type, int quantite) {
		for(int i = 0 ; i < this.stock.size() && quantite != 0 ; i++) {
			if(this.stock.get(i).getType() == type) {
				this.stock.remove(i);
				quantite--;
				i--;
			}
		}
	}
	
	public void ajouterRessource(Ressource r) {
		if(!isFull()) {
			stock.add(r);
		}
	}
	
	public boolean isFull() { return this.stock.size() >= niveau;}
	public boolean isEmpty() { return this.stock.size() == 0;}
}
