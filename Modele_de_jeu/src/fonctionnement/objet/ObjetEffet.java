package fonctionnement.objet;

import fonctionnement.environnement.*;

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
