package fonctionnement.objet;


public class Objet {
	// 0 : Ressource / 1 : ObjetEffet
	protected boolean typeObjet;

	protected String nom;
	
	public Objet(boolean typeObjet) {
		this.typeObjet = typeObjet;
	}
	public String toString() {
		return nom;
	}
	public boolean isSpecial() { return this.typeObjet;}
}
