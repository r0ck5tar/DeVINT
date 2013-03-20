package fonctionnement.objetmathias;

public class Joueur {
	private String nom;
	private Sac sac;
	private Cabane cabane;
	//private Case position;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.sac = new Sac(this);
		this.cabane = new Cabane(this);
		//this.position = null;
	}
}
