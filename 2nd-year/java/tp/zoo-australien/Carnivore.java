
/**
 * Décrivez votre classe abstraite Carnivore ici.
 *
 * @author  
 * @version 
 */
public abstract class Carnivore extends Animal
{
    // A COMPLETER (si nécessaire)
    private final double prixViande = 10;
    public Carnivore(String nom, double poids)
    {
        super(nom,poids);
    }

    public double coutNourriture(){
        double prixTotal = 0;
        prixTotal = getPoids()*0.20*prixViande;
        return prixTotal;
    }
}
