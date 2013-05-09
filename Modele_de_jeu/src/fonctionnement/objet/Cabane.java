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
	public void setPosition(Case c) {
		this.position = c;
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
		return (!this.toit && (this.stock.getQuantity(TypeRessource.BOIS) + joueur.getSac().getQuantity(TypeRessource.BOIS) >=1)
				&& (this.stock.getQuantity(TypeRessource.LIANE) + joueur.getSac().getQuantity(TypeRessource.LIANE) >=1) 
				&& (this.stock.getQuantity(TypeRessource.EAU) + joueur.getSac().getQuantity(TypeRessource.EAU) >=1));
	}

	public boolean isBuildableTonneau() {
		return (!this.tonneau && (this.stock.getQuantity(TypeRessource.BOIS) + joueur.getSac().getQuantity(TypeRessource.BOIS) >=2)
				&& (this.stock.getQuantity(TypeRessource.EAU) + joueur.getSac().getQuantity(TypeRessource.EAU) >=1));
	}

	public boolean isBuildableFilet() {
		return (!this.filet && (this.stock.getQuantity(TypeRessource.FILET) + joueur.getSac().getQuantity(TypeRessource.FILET) >=1));
	}

	public boolean isBuildableAtelier() {
		return (!this.atelier && (this.stock.getQuantity(TypeRessource.PIERRE) + joueur.getSac().getQuantity(TypeRessource.PIERRE) >= 2) 
				&& (this.stock.getQuantity(TypeRessource.BOIS) + joueur.getSac().getQuantity(TypeRessource.BOIS) >=1)
				&& (this.stock.getQuantity(TypeRessource.LIANE) + joueur.getSac().getQuantity(TypeRessource.LIANE) >=1) 
				&& (this.stock.getQuantity(TypeRessource.EAU) + joueur.getSac().getQuantity(TypeRessource.EAU) >=1));
	}
	
	public boolean isBuildableStock() {
		return (this.stock.getNiveau() < this.stock.getNIVEAUMAX() 
				&& (this.stock.getQuantity(TypeRessource.PIERRE) + joueur.getSac().getQuantity(TypeRessource.PIERRE) >= 1) 
				&& (this.stock.getQuantity(TypeRessource.BOIS) + joueur.getSac().getQuantity(TypeRessource.BOIS) >= 1) 
				&& (this.stock.getQuantity(TypeRessource.EAU) + joueur.getSac().getQuantity(TypeRessource.EAU) >= 1));
	}
	
	public void construireToit() {
		this.toit = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		if(this.stock.getQuantity(TypeRessource.BOIS) >=1) {this.stock.remove(TypeRessource.BOIS,1);}
		else {joueur.getSac().remove(TypeRessource.BOIS, 1);}
		
		if(this.stock.getQuantity(TypeRessource.LIANE) >=1) {this.stock.remove(TypeRessource.LIANE,1);}
		else {joueur.getSac().remove(TypeRessource.LIANE, 1);}
		
		if(this.stock.getQuantity(TypeRessource.EAU) >=1) {this.stock.remove(TypeRessource.EAU,1);}
		else {joueur.getSac().remove(TypeRessource.EAU,1);}
	}
	
	public void construireTonneau() {
		this.tonneau = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		int nbBoisInStock = this.stock.getQuantity(TypeRessource.BOIS);
		if(nbBoisInStock >=1) {this.stock.remove(TypeRessource.BOIS,nbBoisInStock);}
		if(nbBoisInStock <2) {joueur.getSac().remove(TypeRessource.BOIS, 2-nbBoisInStock);}
		
		if(this.stock.getQuantity(TypeRessource.EAU) >=1) {this.stock.remove(TypeRessource.EAU,1);}
		else {joueur.getSac().remove(TypeRessource.EAU, 1);}
	}
	
	public void construireFilet() {
		this.filet = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		if(this.stock.getQuantity(TypeRessource.FILET) >=1) {this.stock.remove(TypeRessource.FILET,1);}
		else {joueur.getSac().remove(TypeRessource.FILET, 1);}
	}
	
	public void construireAtelier() {
		this.atelier = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		int nbPierreInStock = this.stock.getQuantity(TypeRessource.PIERRE);
		if(nbPierreInStock >=1) {this.stock.remove(TypeRessource.PIERRE,nbPierreInStock);}
		if(nbPierreInStock <2) {joueur.getSac().remove(TypeRessource.PIERRE, 2-nbPierreInStock);}
		
		if(this.stock.getQuantity(TypeRessource.BOIS) >=1) {this.stock.remove(TypeRessource.BOIS,1);}
		else {joueur.getSac().remove(TypeRessource.BOIS, 1);}
		
		if(this.stock.getQuantity(TypeRessource.LIANE) >=1) {this.stock.remove(TypeRessource.LIANE,1);}
		else {joueur.getSac().remove(TypeRessource.LIANE, 1);}
		
		if(this.stock.getQuantity(TypeRessource.EAU) >=1) {this.stock.remove(TypeRessource.EAU,1);}
		else {joueur.getSac().remove(TypeRessource.EAU, 1);}
	}
	
	public void construireStock() {
		this.atelier = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		if(this.stock.getQuantity(TypeRessource.PIERRE) >=1) {this.stock.remove(TypeRessource.PIERRE,1);}
		else {joueur.getSac().remove(TypeRessource.PIERRE, 1);}
		
		if(this.stock.getQuantity(TypeRessource.BOIS) >=1) {this.stock.remove(TypeRessource.BOIS,1);}
		else {joueur.getSac().remove(TypeRessource.BOIS, 1);}
		
		if(this.stock.getQuantity(TypeRessource.EAU) >=1) {this.stock.remove(TypeRessource.EAU,1);}
		else {joueur.getSac().remove(TypeRessource.EAU, 1);}
	}
}
