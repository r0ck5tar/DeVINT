package Objet;

public class Ressource extends Objet{
	private TypeRessource type;

	public Ressource(TypeRessource type) {
		super(false);
		this.type = type;
	}

	public TypeRessource getType() {
		return this.type;
	}

	public String toString() {
		return this.type.getNom();
	}
}
