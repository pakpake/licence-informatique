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

// cette variable permet de savoir si un utilisateur est connecté
// elle est màj dans connexion
// si elle contient la chaine vide, personne n'est connecté
// si elle contient un numsecu on peut vérifier si c'est un biblio ou un abonne avec
// les fonctions isNumSecuIsAdmin(...) et isNumSecuIsAbonne(...)
public static String numsecu="";       // variable globale

// vérification si numsecu est un admin, nécessite de passer le connecteur de la BDD
// ouvert avant 
public static boolean isNumSecuIsAdmin(Connection c, String str) {
    int g=0;
    try {
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        // sql query to determine if giving other numSecu exists
        ResultSet rg = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires WHERE numSecu="+str+";");

        // on récupère la 1ère ligne du résultat, d'où le 1
        // g = rg.getInt(1);        // << ancienne forme de récupération de getInt
        while (rg.next()) { g = rg.getInt(1); }

    }catch(SQLException e) { System.err.println(e.getMessage()); }
    // return true si g>0
    return g == 1;
}

public static boolean isNumSecuIsAbonne(Connection c, String str) {
    int g=0;
    try {
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        // sql query to determine if giving other numSecu exists
        ResultSet rg = statement.executeQuery("SELECT COUNT(*) FROM Abonnes WHERE numSecu="+str+";");

        // on récupère la 1ère ligne du résultat, d'où le 1
        // g = rg.getInt(1);        // << ancienne forme de récupération de getInt
        while (rg.next()) { g = rg.getInt(1); }

    }catch(SQLException e) { System.err.println(e.getMessage()); }
    // return true si g>0
    return g == 1;
}

// vérification du format du pseudo
public static boolean isIdentifiantValid(Connection c,String str) {
    String punct = "\\p{Punct}+";     // not one of :  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
    String lower = "\\p{Lower}+";   // unqiement en lower case
    return (!Pattern.matches(punct, str) &&     // pas de ponctuation
            Pattern.matches(lower, str) &&      // tout en minuscule
            !isPseudoIsInBdd(c,str))            // pseudo n'est pas dans bdd
}

// vérification du format du mot de passe
public static boolean isMdpValid(String str) {
    String punct = "\\p{Punct}+";     // not one of :  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
    return !Pattern.matches(punct, str);
}

// méthode de connexion (avec verification des informations de login)
public static boolean connexion(Connection c, String pseudo, String pass) {
    int n=0;
    int x=0;
    int y=0;
    try {
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
        // le gars est connecté 
        if (n==1) {
            String kic="Abonnes";       // valeur par defaut
            if (x==1) kic="Abonnes";
            else if (y==1) kic="Bibliothecaires";

            ResultSet rs = statement.executeQuery("SELECT numSecu FROM "+kic+" AS Ki WHERE Ki.identifiant=\""+pseudo+"\" AND Ki.mdp=\""+pass+"\";");
            // enregistrement du numero de secu dans la variable globale
            while (rs.next()) { numsecu = rs.getString(1); } 
        }
    }catch (SQLException e) { System.err.println(e.getMessage()); }
    return n==1;
}

/*
 * le mec se connecte et tu véfifes le format du pseudo et du mdp
 * ensuite on vérifie que il existe bien dans la bdd et ça met a jour le numsecu (variable globale)
 * tu changes de vues vers l'acceuil correspondant (biblio ou abonne)
 */
