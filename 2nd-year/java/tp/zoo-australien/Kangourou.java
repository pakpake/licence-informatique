
/**
 * Décrivez votre classe Kangourou ici.
 *
 * @author 
 * @version 
 */
public class Kangourou extends Herbivore
{
    /**
     * Constructeur d'objets de classe Kangourou
     */
    public Kangourou(String nom, double poids)
    {
        super(nom, poids);
    }

  
    public String toString()
    {
        return super.toString() + " un kangourou";
    }
}
