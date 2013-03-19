public class Stock {
	private int niveau;
	private final int NIVEAUMAX = 10;
	public Stock() {
		this.niveau = 4;
	}
	
	public int getNiveau() { return this.niveau;}
	public int getNIVEAUMAX() { return this.NIVEAUMAX;}
	
	public boolean contains(TypeRessource type, int quantite) {
		return false;
	}
	
	public void remove(TypeRessource type, int quantite) {
	
	}
}
