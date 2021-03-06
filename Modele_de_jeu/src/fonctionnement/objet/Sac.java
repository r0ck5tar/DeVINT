
package fonctionnement.objet;

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
	
	public boolean isEmpty() {
		return this.stock.size() == 0;
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
	
	public boolean containsObjetEffet() {
		for(Objet o:stock) {
			if(o instanceof ObjetEffet) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<ObjetEffet> recupObjetEffet() {
		ArrayList<ObjetEffet> list = new ArrayList<ObjetEffet>();
		for(Objet o:stock) {
			if(o instanceof ObjetEffet) {
				list.add((ObjetEffet) o);
			}
		}		
		return list;
	}
	
	public void remove(TypeObjetEffet type) {
		for(int i = 0 ; i < this.stock.size(); i++) {
			if(this.stock.get(i) instanceof ObjetEffet && ((ObjetEffet)this.stock.get(i)).getType() == type) {
				this.stock.remove(i);
				return;
			}
		}
	}

	public boolean containsRessource() {
		for(Objet o:stock) {
			if(o instanceof Ressource) {
				return true;
			}
		}
		return false;
	}

	public Ressource getFirstRessource() {
		if(this.containsRessource()) {
			for(Objet o:stock) {
				if(o instanceof Ressource) {
					Ressource r = (Ressource) o;
					stock.remove(o);
					return r;
				}
			}			
		}
		return null;
	}
	
	public int getLimite() {
		return limite;
	}

	public int getQuantity(TypeRessource type) {
		int quantity =0;
		for(Objet r : this.stock) {
			if(r instanceof Ressource) {
				if(((Ressource) r).getType() == type) quantity++;
			}
		}
		return quantity;
	}
	
	public void remove(TypeRessource type, int quantite) {
		for(int i = 0 ; i < this.stock.size() && quantite != 0 ; i++) {
			if(((Ressource) this.stock.get(i)).getType() == type) {
				this.stock.remove(i);
				quantite--;
				i--;
			}
		}
	}
}
