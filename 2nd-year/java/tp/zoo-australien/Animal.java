
/**
 * Write a description of class Animal here.
 * 
 * @author (your name)  pakpake
 * @version (a version number or a date) December 19 2019
 */
public abstract class Animal
{
    // instance variables - replace the example below with your own
    private String nom;
    private double poids;
       /**
     * Constructor for objects of class Animal
     */
    public Animal(String nom, double poids)
    {
        this.nom = nom;
        this.poids = poids;
    }

    public String getNom(){
        return nom;
    }

    public double getPoids(){
        return poids;
    }

    public String toString(){
        return "Le nom de l'animal est :" + nom + "et il pèse :" + poids + " kg";
    }

    public abstract double coutNourriture();
}
