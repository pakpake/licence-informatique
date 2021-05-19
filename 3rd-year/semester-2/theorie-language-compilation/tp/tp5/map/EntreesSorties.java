/**
 * Machine à pile MAP : instructions d'E/S de la MAP
 * @author F. Raimbault
 */
package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Méthodes d'entrées-sorties de la machine MAP
 */
public final class EntreesSorties {  // NE PAS MODIFIER

    /**
     * Affiche un message à l'écran
     * @param m message à afficher
     */
    public static void affiche(String m){
        System.out.println(m);
        
    }
    /**
     * Affiche une information à l'écran
     * @param m information à afficher
     */
    public static void afficheInfo(String m){
        affiche("[MAP information] "+m);
    }
    	
    /**
     * Affiche un entier court à l'écran
     * @param x entier à afficher
     */
    public static void ecrireEntier(short x) {
        affiche("[MAP résultat] "+x);
    }

    /**
     * tampon de lecture du clavier
     */
    private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Affiche une invite puis lit un entier court au clavier
     * @return l'entier lu
     */
    public static short lireEntier(){
        short x= 0;
        try {
            afficheInfo("entrer un entier au clavier :");
            String ligne= console.readLine();
            x= Short.parseShort(ligne);
        } catch (IOException e) {
            Erreur.execution("échec de la lecture d'un entier");
        } catch (NumberFormatException e) {
          Erreur.execution("échec de la lecture d'un entier");
        }
        return x;	
    }	
    
}