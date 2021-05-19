import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

// Pour la connection et l'envoit de données avec la BDD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccueilAbonne {

    public static void realmain(Connection c) {
        System.out.println("\n\t\t***** Accueil de l'Abonné *****\n\n");
        int choix=0;
        System.out.println("[0] --- Quitter");
        System.out.println("[1] --- Supprimer son compte");
        System.out.println("[2] --- Afficher ses informations");

        // lecture de l'entrée
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        switch(choix) {
            case 0: // Quitter
                System.out.println("Au revoir...");
                System.exit(0);
                break;
            case 1: // Supprimer son compte
                SupprimerCompte.main(c);
                break;
            case 2: // affciher ses informations de compte
                AfficherNum.main(c);
                break;
            default: // autres cas
                System.out.println("Ce choix n'existe pas !");
                break;
        }
    }
    public static void main(Connection c) {realmain(c);}

}
