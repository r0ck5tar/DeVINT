package fonctionnement.objet;

<<<<<<< HEAD
import fonctionnement.environnement.*;
=======
>>>>>>> f80616f3b7eb668c200c24d179732a01f7a0e3b6

public class ObjetEffet extends Objet {
	private TypeObjetEffet type;

	public ObjetEffet(TypeObjetEffet type) {
		super(true);
		this.type = type;
	}

	public TypeObjetEffet getType() {
		return this.type;
	}

	public String toString() {
		return this.type.getNom();
	}
	
	public boolean goEffect(Joueur joueur) {
		switch(type) {
			case LANCE :
				return true;
			case BOUSSOLE :
				return true;
			case CATAPULTE :
				return true;
			case SAC :
				return true;
			default :
				return false;
		}
	}
	
	public ObjetEffet copy() {
		return new ObjetEffet(this.type);
	}
}
