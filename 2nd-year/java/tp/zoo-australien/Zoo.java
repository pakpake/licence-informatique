import java.util.ArrayList;

/**
 * Décrivez votre classe Zoo ici.
 *
 * @author  pakpake
 * @version 1
 */
public class Zoo
{
    private String nom; // nom du zoo
    private ArrayList<Animal> animals; // liste des animaux du zoo
    private int capaciteMax; // capacité maximal d'accueil dans le zoo

    /**
     * Nouveau zoo
     * @param nom le nom du zoo
     */
    public Zoo(String nom)
    {
        this(nom,100);
    }

    /**
     * Nouveau zoo
     * @param nom le nom du zoo
     * @param c la caapcité maximale d'accueil
     */
    public Zoo(String nom, int c)
    {
        // A COMPLETER
        this.nom = nom;
        capaciteMax = c;
    }

    /**
     * Calculer le nombre d'animaux présents dans le zoo
     * @return     le nombre d'animaux dans le zoo
     */
    public int nbAnimaux()
    {   
        animals = new ArrayList<Animal>();
        return animals.size();
    }

    /**
     * Ajouter un animal dans le zoo si la capacité max n'est pas atteinte
     * @param a l'animal à ajouter au zoo
     */
    public void ajoutAnimal(Animal a){
        if (animals.size() >= capaciteMax){
            System.out.println("La capacité maximale d'animaux est atteinte : " + capaciteMax);
        }else{
            animals.add(a);
        }
    }

    /**
     * Supprimer le premier animal dans le zoo en fonction de son nom
     * @param nom de l'animal à  supprimer
     */
    public void supprimeAnimal(String nom){
        int i = 0;
        while(nom != animals.get(i).getNom()){
            i++;
        } 
        animals.remove(i);
    }

    /**
     * Calculer le coût total de la nourriture nécessaire pour les animaux
     * @return le coût total 
     */
    public double coutTotal() {
        int total = 0;
        for(int i=0; i< animals.size(); i++){
            total += animals.get(i).coutNourriture();  
        }
        return total;
    }

    /**
     * Afficher les informations sur le zoo
     */
    public void printDetails() {
        if(animals.size() == 0){
            System.out.println("Aucun animal dans le zoo" + nom);
        }else{
            System.out.println("Le zoo " +nom+ "("+capaciteMax+"animaux maximum) accueille :");
            for(int i=0; i< animals.size();i++){
                System.out.println(super.toString());
            }
        }
    }


    /**
     * Fonction de test
     */
    public static void main(String[] args) {
        System.out.println("##-------------------------------------------##");
        System.out.println("##----------------   CAS 1   ----------------##");
        Zoo z = new Zoo("Healsvile", 20);
        z.ajoutAnimal(new Kangourou("Skippy", 75));
        z.ajoutAnimal(new Ornithorynque("Platypus", 6.5));
        //z.ajoutAnimal(new Koala("Buster", 6, 18));
        z.printDetails();
        System.out.println("##-------------------------------------------##");
        System.out.println("");
        System.out.println("");

        System.out.println("##-------------------------------------------##");
        System.out.println("##----------------   CAS 2   ----------------##");
        Zoo zempty = new Zoo("Empty");
        zempty.printDetails();
        System.out.println("##-------------------------------------------##");
        System.out.println("");
        System.out.println("");

        System.out.println("##-------------------------------------------##");
        System.out.println("##----------------   CAS 3   ----------------##");
        Zoo znull = new Zoo("Null");
        Kangourou kou = null;
        znull.ajoutAnimal(kou);
        znull.ajoutAnimal(new Kangourou("Skippy", 75));
        znull.printDetails();
        System.out.println("##-------------------------------------------##");
        System.out.println("");
        System.out.println("");
    }

}
