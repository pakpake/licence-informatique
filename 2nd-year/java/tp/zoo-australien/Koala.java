
/**
 * Write a description of class Koala here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Koala extends Herbivore {

    // instance variables - replace the example below with your own
    private int sommeil;

    /**
     * Constructor for objects of class Koala
     */
    public Koala(String nom, double poids, int sommeil)
    {
        super(nom,poids);
        this.sommeil = sommeil;
    }

    public int coutNourriture(){
        return sommeil;
    }
}
