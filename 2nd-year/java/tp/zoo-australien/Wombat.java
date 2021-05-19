
/**
 * Décrivez votre classe Wombat ici.
 *
 * @author 
 * @version (
 */
public class Wombat extends Herbivore
{
    /**
     * Constructeur d'objets de classe Wombat
     */
    public Wombat(String nom, double poids)
    {
        super(nom, poids);
    }

  
    public String toString()
    {
        return super.toString() + " un wombat";
    }
}
