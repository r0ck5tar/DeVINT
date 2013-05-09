package fonctionnement.objet;



public enum TypeRessource
{
    BOIS("bois"),
    EAU("eau"),
    PIERRE("pierre"),
    LIANE("liane"),
    NOURRITURE("nourriture"),
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
