/**
 * NE PAS MODIFIER
 */
package as;

/**  
 * Gestion des erreurs (et des messages) possibles au cours de l'assemblage
 */
    public class Erreur{

        /**
         * Affichage d'un message
         * @param m message à afficher
         */
      public static void message(String m){
            System.out.println(m);
        }

        /**
         * Erreur interne : stoppe l'exécution
         * @param m message 
         */
        public static void interne(String m) {
            message("[Erreur interne] "+m);
            System.exit(1);
        }

        /**
         * Erreur sur la ligne de commande : stoppe l'exécution
         * @param m message
         */
        public static void parametre(String m) {
            message("[Parametre incorrect] "+m);
            System.exit(1);
        }

        /**
         * Erreur de syntaxe dans le programme source assembleur : stoppe l'exécution
         * @param m message
         */
        public static void syntaxe(String m) {
            message("[Erreur de syntaxe] "+m);
            System.exit(1);
        }
    }


