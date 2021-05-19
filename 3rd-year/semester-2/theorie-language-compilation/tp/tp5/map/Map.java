/**
 * Machine à pile MAP : lanceur de la MAP
 * @author F. Raimbault
 */
package map;

/**
 * Lanceur de la machine à pile MAP 
 */
public final class Map { //  NE PAS MODIFIER

    /**
     * taille des mots (en bits) de la MAP
     */
    public static final int TAILLE_MOT = 16; // 16 bits

    /**
     * valeurs limites des entiers signés et non-signés
     */
    public static final int ENTIER_MIN = -(1 << (TAILLE_MOT - 1));
    public static final int ENTIER_MAX = (1 << (TAILLE_MOT - 1)) - 1;
    public static final int VALEUR_MAX = (1 << TAILLE_MOT);
    
    /** indicateur de debug */
    public static boolean trace_mode= false;
    
    /**
     * fichier "binaire" contenant le code à exécuter
     */
    public static String fichier_map= null;
    
    /**
     * Lanceur de la machine MAP
     * @param args un seul paramètre : le nom du fichier de code 
     */
    public static void main(String[] args) {

        Erreur.message("MAP version du 10/05/2019 de F. Raimbault");
        for (int i=0; i<args.length; i++){
          if (args[i].endsWith(".map")){
            fichier_map= args[i];
          }else if (args[i].startsWith("-d")){
            trace_mode= true;
          }
        }
        if (fichier_map==null) {
            Erreur.chargement("manque le nom d'un fichier de code binaire en parametre");
        }
        Chargeur.lire(args[0]);
        Sequenceur.executer();
    }
}