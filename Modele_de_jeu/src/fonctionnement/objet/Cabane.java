package fonctionnement.objet;

import fonctionnement.environnement.Case;

public class Cabane {
	private Joueur joueur;
	private Stock stock;
	private Bateau bateau;
	// Niveau de construction
	private boolean toit;
	private boolean tonneau;
	private boolean filet;
	private boolean atelier;
	private Case position;
	
	public Case getPosition() {
		return this.position;
	}

	public Cabane(Joueur joueur) {
		this.joueur = joueur;
		this.stock = new Stock();
		this.bateau = new Bateau(this);

		this.toit = false;
		this.tonneau = false;
		this.filet = false;
		this.atelier = false;
	}

	public Joueur getJoueur() { return this.joueur;}
	public Stock getStock() { return this.stock;}
	public Bateau getBateau() { return this.bateau;}

	public void setJoueur(Joueur joueur) { this.joueur = joueur;}
	public void setStock(Stock stock) { this.stock = stock;}
	public void setBateau(Bateau bateau) { this.bateau = bateau;}

	public boolean isToit() { return this.toit;}
	public boolean isTonneau() { return this.tonneau;}
	public boolean isFilet() { return this.filet;}
	public boolean isAtelier() { return this.atelier;}

	public boolean isBuildableToit() {
		// this.Stock.contains(enum de la ressource , quantite de cette ressource)
		return (!this.toit && this.stock.contains(TypeRessource.BOIS,2) && this.stock.contains(TypeRessource.LIANE,1));
	}

	public boolean isBuildableTonneau() {
		return (!this.tonneau && this.stock.contains(TypeRessource.BOIS,2));
	}

	public boolean isBuildableFilet() {
		return (!this.filet && this.stock.contains(TypeRessource.FILET,1));
	}

	public boolean isBuildableAtelier() {
		return (!this.atelier && this.stock.contains(TypeRessource.PIERRE,3) && this.stock.contains(TypeRessource.BOIS,2)&& this.stock.contains(TypeRessource.LIANE,1));
	}
	
	public boolean isBuildableStock() {
		return (this.stock.getNiveau() < this.stock.getNIVEAUMAX() && this.stock.contains(TypeRessource.PIERRE,1) && this.stock.contains(TypeRessource.BOIS,1));
	}
	
	public void construireToit() {
		this.toit = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		this.stock.remove(TypeRessource.BOIS,2);
		this.stock.remove(TypeRessource.LIANE,1);
	}
	
	public void construireTonneau() {
		this.tonneau = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		this.stock.remove(TypeRessource.BOIS,2);
	}
	
	public void construireFilet() {
		this.filet = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		this.stock.remove(TypeRessource.FILET,1);
	}
	
	public void construireAtelier() {
		this.atelier = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		this.stock.remove(TypeRessource.PIERRE,3);
		this.stock.remove(TypeRessource.BOIS,2);
		this.stock.remove(TypeRessource.LIANE,1);
	}
	
	public void construireStock() {
		this.atelier = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		this.stock.remove(TypeRessource.PIERRE,1);
		this.stock.remove(TypeRessource.BOIS,1);
	}
}
