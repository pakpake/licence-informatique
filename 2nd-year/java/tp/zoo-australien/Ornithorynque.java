
/**
 * Décrivez votre classe Ornithorynque ici.
 *
 * @author  
 * @version 
 */
public class Ornithorynque extends Carnivore
{
    /**
     * Constructeur d'objets de classe Ornithorynque
     */
    public Ornithorynque(String nom, double poids)
    {
        super(nom, poids);
    }

  
    public String toString()
    {
        return super.toString() + " un ornithorynque";
    }
}
