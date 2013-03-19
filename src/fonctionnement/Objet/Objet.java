package Objet;

public class Objet {
	// 0 : Ressource / 1 : ObjetEffet
	protected boolean typeObjet;
	
	public Objet(boolean typeObjet) {
		this.typeObjet = typeObjet;
	}
	
	public boolean isSpecial() { return this.typeObjet;}
}
