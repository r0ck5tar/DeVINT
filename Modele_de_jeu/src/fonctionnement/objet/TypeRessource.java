package fonctionnement.objet;

public enum TypeRessource
{
    PIERRE("pierre"),
    BOIS("bois"),
    EAU("eau"),
    NOURRITURE("nourriture"),
    LIANE("liane"),
    VOILE("voile"),
    FILET("filet");
 
    // Membres :
    private final String nom;
 
    TypeRessource(String nom)
    {
        this.nom = nom;
    }
 
    public String getNom(){ return this.nom; }
	public String toString(){ return this.nom; }
};
