/**
 * Machine à pile MAP : gestion des erreurs de la MAP
 * @author F. Raimbault
 * NE PAS MODIFIER
 */
package map;

import static map.Registres.PC;

/**
 * Erreurs possibles en cours d'exécution de MAP
 */
public class Erreur {

    /**
     * Affiche un message d'information (ce n'est pas une erreur !)
     * @param m message à afficher
     */
    public static void message(String m) {
        EntreesSorties.affiche(m);
    }

    /**
     * Affiche un message d'erreur lié au chargement du code
     * Stoppe l'exécution
     * @param m
     */
    public static void chargement(String m) {
        message("[MAP erreur au chargement du code] " + m);
        System.exit(0);
    }

    /**
     * Affiche un message d'erreur lié à l'exécution d'une instruction
     * Arrete le séquenceur
     * @param m
     */
    public static void execution(String m) {
        message("[MAP erreur à l'exécution (PC="+(PC.lire())+"] " + m);
        Sequenceur.stop();
    }

    /**
     * Affiche un message d'erreur lié à une erreur interne
     * Stoppe l'exécution
     * @param m
     */
    public static void interne(String m) {
        message("[MAP erreur interne] " + m);
        System.exit(1);
    }
}

