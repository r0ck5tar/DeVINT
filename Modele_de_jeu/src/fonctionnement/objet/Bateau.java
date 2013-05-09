package fonctionnement.objet;


public class Bateau {
	private Cabane cabane;
	// Niveaux de constructions
	private boolean coque;
	private boolean mat;
	private boolean gouvernail;
	private boolean voile;
	
	public Bateau() {
		this(null);
	}
	
	public Bateau(Cabane cabane) {
		this.cabane = cabane;
		this.coque = false;
		this.mat = false;
		this.gouvernail = false;
		this.voile = false;		
	}
	
	public boolean gameOver() {
		return this.coque && this.mat && this.gouvernail && this.voile;
	}
	
	public Cabane getCabane() { return this.cabane;}
	
	public void setCabane(Cabane cabane) { this.cabane = cabane;}
	
	public boolean isCoque() { return this.coque;}
	public boolean isMat() { return this.mat;}
	public boolean isGouvernail() { return this.gouvernail;}
	public boolean isVoile() { return this.voile;}
	
	public boolean isBuildableCoque() {
		return (!this.coque && (this.cabane.getStock().getQuantity(TypeRessource.BOIS) + cabane.getJoueur().getSac().getQuantity(TypeRessource.BOIS) >=3)
				&& this.cabane.getStock().getQuantity(TypeRessource.LIANE) + cabane.getJoueur().getSac().getQuantity(TypeRessource.LIANE) >=1);
	}
	
	public boolean isBuildableMat() {
		return (!this.mat && (this.cabane.getStock().getQuantity(TypeRessource.BOIS) + cabane.getJoueur().getSac().getQuantity(TypeRessource.BOIS) >=1) 
				&& (this.cabane.getStock().getQuantity(TypeRessource.LIANE) + cabane.getJoueur().getSac().getQuantity(TypeRessource.LIANE) >=3));
	}
	
	public boolean isBuildableGouvernail() {
		return (!this.gouvernail && (this.cabane.getStock().getQuantity(TypeRessource.PIERRE) + cabane.getJoueur().getSac().getQuantity(TypeRessource.PIERRE) >=3) 
				&& (this.cabane.getStock().getQuantity(TypeRessource.BOIS) +cabane.getJoueur().getSac().getQuantity(TypeRessource.BOIS) >=1)
				&& (this.cabane.getStock().getQuantity(TypeRessource.LIANE) + cabane.getJoueur().getSac().getQuantity(TypeRessource.LIANE) >=1 ));
	}
	
	public boolean isBuildableVoile() {
		return (!this.voile && this.cabane.getStock().contains(TypeRessource.VOILE,1));
	}
	
	public void construireCoque() {
		this.coque = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		int nbBoisInStock = this.cabane.getStock().getQuantity(TypeRessource.BOIS);
		if(nbBoisInStock<3){
			this.cabane.getStock().remove(TypeRessource.BOIS,nbBoisInStock);
			this.cabane.getJoueur().getSac().remove(TypeRessource.BOIS, 3-nbBoisInStock);
		}
		else if(nbBoisInStock >= 3) {this.cabane.getStock().remove(TypeRessource.BOIS,3);}
		
		if(this.cabane.getStock().getQuantity(TypeRessource.LIANE) >=1) {this.cabane.getStock().remove(TypeRessource.LIANE,1);}
		else {this.cabane.getJoueur().getSac().remove(TypeRessource.LIANE, 1);}
	}
	
	public void construireMat() {
		this.coque = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		if(this.cabane.getStock().getQuantity(TypeRessource.BOIS) >=1) {this.cabane.getStock().remove(TypeRessource.BOIS,1);}
		else {this.cabane.getJoueur().getSac().remove(TypeRessource.BOIS, 1);}
		
		int nbLianeInStock = this.cabane.getStock().getQuantity(TypeRessource.LIANE);
		if(nbLianeInStock <3) {
			this.cabane.getStock().remove(TypeRessource.LIANE,nbLianeInStock);
			this.cabane.getJoueur().getSac().remove(TypeRessource.LIANE, 3-nbLianeInStock);
		}
		else if(nbLianeInStock >=3) {this.cabane.getStock().remove(TypeRessource.LIANE,3);} 
	}
	
	public void construireGouvernail() {
		this.coque = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		int nbPierreInStock = this.cabane.getStock().getQuantity(TypeRessource.PIERRE);
		if(nbPierreInStock <3) {
			this.cabane.getStock().remove(TypeRessource.PIERRE,nbPierreInStock);
			this.cabane.getJoueur().getSac().remove(TypeRessource.PIERRE, 3-nbPierreInStock);
		}
		else if(nbPierreInStock >=3) {this.cabane.getStock().remove(TypeRessource.PIERRE,3);} 
		
		if(this.cabane.getStock().getQuantity(TypeRessource.BOIS) >=1) {this.cabane.getStock().remove(TypeRessource.BOIS,1);}
		else {this.cabane.getJoueur().getSac().remove(TypeRessource.BOIS, 1);}
		
		if(this.cabane.getStock().getQuantity(TypeRessource.LIANE) >=1) {this.cabane.getStock().remove(TypeRessource.LIANE,1);}
		else {this.cabane.getJoueur().getSac().remove(TypeRessource.LIANE, 1);}
	}
	
	public void construireVoile() {
		this.coque = true;
		// this.stock.remove(enum de la ressource , quantite de cette ressource)
		if(this.cabane.getStock().getQuantity(TypeRessource.VOILE) >=1) {this.cabane.getStock().remove(TypeRessource.VOILE,1);}
		else {this.cabane.getJoueur().getSac().remove(TypeRessource.VOILE, 1);}
	}
}
