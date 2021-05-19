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

// supprimer le compte d'un abonné, en le recherchant via son identifiant
// fonctionnalité disponible pour le compte d'un bibliothécaire
public class SupprimerAbonne {
    /*** A changer, quand on saura gérer des sessions en java ***/
    //static String numSecu="122334455566677";    // celui d'un biblio forcément
    static int n=0;

    public static void pageDeSuppression(Connection c) {
        // lecture des entrées clavier
        Scanner sc = new Scanner(System.in);
        String str,idab;

        System.out.println("\n=====================================");
        System.out.println("\tSuppression d'un compte");
        System.out.println("=====================================");

        System.out.println("Veuillez écrire l'identifiant du compte à supprimer : ");
        // enregistrement de l'identifiant 
        idab = sc.nextLine();


        System.out.println("\nVoulez-vous vraiment supprimer ce compte ? (oui/non)");
        // enregistrement de la reponse
        str = sc.nextLine();
        if (str.equals("oui")) {
            System.out.println("Veuillez tapez votre mot de passe pour confirmer l'action : ");
            // enregistrement du mot de passe
            str = sc.nextLine();
            // appel de la fonction connexSuppressIon
            while (!connexSuppressIon(c, idab, str)) {
                System.out.println("Mot de passe incorrect !");
                System.out.print("Veuillez ré-essayer : ");
                str = sc.nextLine();
            }
            // appel de la fonction de suppression
            deleteUser(c, idab);
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

    // methode qui permet de verifier que le numero/mdp saisit juste avant existe dans la bdd
    public static boolean connexSuppressIon(Connection c, String idab, String pass) {
        n=0;
        int x=0,y=0;
        try {
            // create a database connection to Librairie.db
            // connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // vérification que l'Abonne existe bien
            ResultSet rsX = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.identifiant=\""+idab+"\";");
            // on récupère la valeur de retour dans l'entier x
            while (rsX.next()) { x = rsX.getInt(1); }            

            // vérification que le mdp du biblio est correct ET
            // qu'il correspond bien à son compte
            ResultSet rsY = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires AS Bi WHERE Bi.numSecu=\""+SeConnecter.numsecu+"\" AND Bi.mdp=\""+pass+"\";");
            while (rsY.next()) { y = rsY.getInt(1); }            

            n=x+y;

        }catch (SQLException e) { System.err.println(e.getMessage()); }
        return n==2;
    }

    // méthode de suppression de l'abonné
    public static void deleteUser(Connection c, String idab) {
        // méthode void car on est sûr que l'abonné exsite bien,
        // grace à la méthode connexSuppressIon();
        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            statement.executeUpdate("DELETE FROM Abonnes WHERE identifiant='"+idab+"';");

        }catch (SQLException e) { System.err.println(e.getMessage()); } 
    }

    // fonction principale
    public static void main(Connection c) {    
        // affichage de la page de connexion
        pageDeSuppression(c);
    }
}
