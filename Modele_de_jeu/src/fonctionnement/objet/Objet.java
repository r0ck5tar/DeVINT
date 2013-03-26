package fonctionnement.objet;
import fonctionnement.environnement.*;

public class Objet {
	// 0 : Ressource / 1 : ObjetEffet
	protected boolean typeObjet;
	protected String nom;
	
	public Objet(boolean typeObjet) {
		this.typeObjet = typeObjet;
	}
	
	
	/*public TypeRessource getType(){
		return this.type; 
	}
	public void setType(TypeRessource t){
		this.type=t;
	}*/
	
	public boolean isSpecial() { return this.typeObjet;}
}
