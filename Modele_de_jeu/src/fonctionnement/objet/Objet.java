package fonctionnement.objet;
import fonctionnement.environnement.*;

public class Objet {
	// 0 : Ressource / 1 : ObjetEffet
	protected boolean typeObjet;
	//private TypeRessource type;
	
	public Objet(boolean typeObjet, TypeRessource type) {
		this.typeObjet = typeObjet;
		//this.type=type; 
	}
	
	
	/*public TypeRessource getType(){
		return this.type; 
	}
	
	public void setType(TypeRessource t){
		this.type=t;
	}*/
	
	public boolean isSpecial() { return this.typeObjet;}
}
