package fonctionnement.objet;
import fonctionnement.environnement.*;
public enum TypeObjetEffet
{
    LANCE("lance"),
    BOUSSOLE("boussole"), // + 1 en deplacement
    CATAPULTE("catapulte"), // rentrer chez soi
    SAC("sac"); // + ameliorer la capacite du sac
 
    // Membres :
    private final String nom;
 
    TypeObjetEffet(String nom)
    {
        this.nom = nom;
    }
 
    public String getNom(){ return this.nom; }
	public String toString(){ return this.nom; }
};
