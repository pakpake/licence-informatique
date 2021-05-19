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

public class SeConnecter {
    static User ConnectUser = new User();

    public static String numsecu="";       // variable globale

    static int n=0;
    static int x=0,y=0;

    public static void afficheCo() {
        // lecture des entrées clavier
        Scanner sc = new Scanner(System.in);
        String str;
        String punct = "\\p{Punct}+";     // not one of :  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~

        System.out.println("\n==========================");
        System.out.println("\tConnexion");
        System.out.println("==========================");
        System.out.print("\nidentifiant : ");
        // enregistrement de la lecture clavier
        str = sc.nextLine();
        while(Pattern.matches(punct, str)) {
            System.out.println("\nPas de punctuation dans l'identifiant...");
            System.out.println("Veuillez ré-essayer : ");
            str = sc.nextLine();
        }
        // setter de l'identifiant de l'User
        ConnectUser.setPseudo(str);

        System.out.print("\nmot de passe : ");
        // enregistrement de la lecture clavier
        str = sc.nextLine();
        while(Pattern.matches(punct, str)) {
            System.out.println("\nPas de punctuation dans le mot de passe...");
            System.out.println("Veuillez ré-essayer : ");
            str = sc.nextLine();
        }
        // setter du mot de passe de l'User
        ConnectUser.setMdp(str);
    }

    // méthode de connexion (avec verification des informations de login)
    public static boolean connexion(Connection c, String pseudo, String pass) {
        n=0;
        x=0;
        y=0;

        try {
            // create a database connection to Librairie.db
            // connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // vérification que l'Abonne existe bien
            ResultSet rsX = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.identifiant=\""+pseudo+"\" AND Ab.mdp=\""+pass+"\";");
            while (rsX.next()) { x = rsX.getInt(1); }            

            // vérification que l'Bibliothecaires existe bien
            ResultSet rsY = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires AS Bi WHERE Bi.identifiant=\""+pseudo+"\" AND Bi.mdp=\""+pass+"\";");
            while (rsY.next()) { y = rsY.getInt(1); }

            // la somme doit valloir soit 1 (exist) soit 0 (n'existe pas)
            n=x+y;

        }catch (SQLException e) { System.err.println(e.getMessage()); }
        return n==1;
    }

    // méthode permettant d'enregistrer dans une variable globale le numero de secu
    public static void numeroSecurite(Connection c,String pseudo, String pass) {
        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            String kic="Abonnes";   // valeur par defaut
            if (x==1) kic="Abonnes";
            else if (y==1) kic="Bibliothecaires";


            ResultSet rs = statement.executeQuery("SELECT numSecu FROM "+kic+" AS Ki WHERE Ki.identifiant=\""+pseudo+"\" AND Ki.mdp=\""+pass+"\";");
            // enregistrement du numero de secu dans la variable globale
            while (rs.next()) { numsecu = rs.getString(1); }            

        }catch (SQLException e) { System.err.println(e.getMessage()); }
    }

    // fonction principale
    public static void main(Connection c) {    
        // affichage de la page de connexion
        afficheCo();
        String pseudo=ConnectUser.getPseudo();
        String mdp=ConnectUser.getMdp();
        // appel de la fonction connexion
        if(!connexion(c,pseudo,mdp)) {
            System.out.println("\n*** Identifiant ou mot de passe incorrect !");
            System.out.println("Veuillez ré-essayer : ");
            main(c);
        }
        // connexion réussie
        System.out.println("\nConnexion réussie\n");
        // affectation de son numero de secu
        numeroSecurite(c,pseudo,mdp);
        // redirection vers le menu correspondant à son role
        if (y==1) {
            // c'est un biblio
            // redirection vers le menu
            AccueilBiblio.main(c);
        }
        // c'est un abonné
        AccueilAbonne.main(c);
    }
}
