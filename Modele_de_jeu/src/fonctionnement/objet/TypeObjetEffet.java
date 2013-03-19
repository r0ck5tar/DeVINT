package fonctionnement.objet;

public enum TypeObjetEffet
{
    LANCE("lance"),
    BOUSSOLE("boussole"),
    CATAPULTE("catapulte"),
    SAC("sac");
 
    // Membres :
    private final String nom;
 
    TypeObjetEffet(String nom)
    {
        this.nom = nom;
    }
 
    public String getNom(){ return this.nom; }
	public String toString(){ return this.nom; }
};
