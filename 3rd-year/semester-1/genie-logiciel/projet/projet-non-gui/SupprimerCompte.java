import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Pour la connection et l'envoit de données avec la BDD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// supprimer son compte = supprimer le compte de l'utilisateur actuellement connecté 
public class SupprimerCompte {
    static int n=0;

    public static void pageDeSuppression(Connection c) {
        // lecture des entrées clavier
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println("\n====================================");
        System.out.println("\tSupprimer son compte");
        System.out.println("====================================");
        System.out.println("\nVoulez-vous vraiment supprimer votre compte ? (oui/non)");
        // enregistrement de la reponse
        str = sc.nextLine();
        if (str.equals("oui")) {
            System.out.println("Veuillez tapez votre mot de passe pour confirmer l'action : ");
            // enregistrement du mot de passe
            str = sc.nextLine();
            // appel de la fonction connexSuppressIon
            while (!connexSuppressIon(c, SeConnecter.numsecu, str)) {
                System.out.println("Mot de passe incorrect !");
                System.out.print("Veuillez ré-essayer : ");
                str = sc.nextLine();
            }
            // appel de la méthode de suppression
            deleteUser(c, SeConnecter.numsecu);
            // le mot de passe est le bon, le compte est supprimé
            System.out.println("Mot de passe correct\nCOMPTE SUPPRIMÉ\n\n");
            Main.realMain();
        } else {
            System.out.println("Abandon de la suppression du compte...");
            // redirection vers l'accueil de l'abonné
            /*** A changer avec la classe de la VUE ***/
            AccueilAbonne.realmain(c);
        }
    }

    // methode qui permet de verifier que le mdp saisit juste avant existe dans la bdd
    public static boolean connexSuppressIon(Connection c, String numero, String pass) {
        n=0;
        int x=0,y=0;

        try {
            // create a database connection to Librairie.db
            // connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            /* on fait les requêtes dans les 2 tables car on ne sait pas
             * à qui appartient le numéro.
             * On a le numéro, mais on ne sait pas son rôle (Biblio ou Abonné) */

            // vérification que le numéro de l'Abonne existe bien
            ResultSet rsX = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.numSecu=\""+numero+"\" AND Ab.mdp=\""+pass+"\";");
            // on récupère la valeur de retour dans l'entier x
            while (rsX.next()) { x = rsX.getInt(1); }            

            // vérification que le numéro du Bibliothecaires existe bien
            ResultSet rsY = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires AS Bi WHERE Bi.numSecu=\""+numero+"\" AND Bi.mdp=\""+pass+"\";");
            // on récupère la valeur de retour dans l'entier y
            while (rsY.next()) { y = rsY.getInt(1); }

            // la somme doit valloir soit 1 (existe) soit 0 (n'existe pas)
            n=x+y;

        }catch (SQLException e) { System.err.println(e.getMessage()); }
        return n==1;
    }

    // méthode de suppression de l'abonné
    public static void deleteUser(Connection c, String numero) {
        // méthode void car on est sûr que l'abonné ou le bibliothécaire exsite bien,
        // grace à la méthode connexSuppressIon();
        // donc pas besoin de valeur de retour pour savoir si ça s'est bien passé
        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            // TODO: Abonnes ou Biblio !!!!!!!
            statement.executeUpdate("DELETE FROM Abonnes WHERE numSecu='"+numero+"';");

        }catch (SQLException e) { System.err.println(e.getMessage()); } 
    }

    // fonction principale
    public static void main(Connection c) {    
        // affichage de la page de connexion
        pageDeSuppression(c);
    }
}
