
/**
 * Write a description of class Dingo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dingo extends Carnivore
{
    // instance variables - replace the example below with your own


    /**
     * Constructeur d'objets de classe Dingo
     */
    public Dingo(String nom, double poids)
    {
        super(nom, poids);
    }

  
    public String toString()
    {
        return super.toString() + " un dingo";
    }
}
