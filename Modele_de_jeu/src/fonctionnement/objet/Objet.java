package fonctionnement.objet;


public class Objet {
	// 0 : Ressource / 1 : ObjetEffet
	protected boolean typeObjet;
<<<<<<< HEAD
	protected String nom;
=======
>>>>>>> f80616f3b7eb668c200c24d179732a01f7a0e3b6
	
	public Objet(boolean typeObjet) {
		this.typeObjet = typeObjet;
	}
	
<<<<<<< HEAD
	
	/*public TypeRessource getType(){
		return this.type; 
	}
	public void setType(TypeRessource t){
		this.type=t;
	}*/
	
=======
>>>>>>> f80616f3b7eb668c200c24d179732a01f7a0e3b6
	public boolean isSpecial() { return this.typeObjet;}
}
