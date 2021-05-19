import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MenuPrincipal {
    static void afficherMenu(Connection c) {
        int choix=0;    // par défaut l'usager (ni un adhérent ni un bibliothécaire) quittera le programme
        // affichage du menu pour les usagers
        System.out.println("[1] --- Se connecter"); 
        System.out.println("[2] --- Créer un compte"); 
        System.out.println("[0] --- Quitter"); 

        // lecture de l'entrée
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        System.out.println("Votre choix est "+choix);

        /* on affiche le menu tant que l'usager ne quitte pas
        // explicitement le programme
        while(true)
            */
        // action à faire en fonction du choix
        switch(choix) {
            case 0: //quitter
                System.out.println("le programme va se fermer tout seul ! Ne touche à rien !");
                System.exit(0);
                break;
            case 1: // se connecter
                // appel de la classe SeConnecter
                SeConnecter.main(c);
                break;
            case 2: // inscription
                // appel de la classe CreationCompte
                CreationCompte.main(c);
                break;
            default: // autres cas
                System.out.println("Ce choix n'existe pas !");
                break;
        }
    }


    public static void main(String[] args) {}
    /* pas besoin 
    public static void main(String[] args) {
        MenuPrincipal m = new MenuPrincipal();
        m.afficherMenu();
    }*/

}
