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

// méthode de suppression de l'abonné
public static void deleteUser(Connection c, String num) {
    String table="Abonnes";
    try {
        if (isNumSecuIsAdmin(c,num)) table = "Bibliothecaires";
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        statement.executeUpdate("DELETE FROM "+table+" WHERE identifiant='"+num+"';");

    }catch (SQLException e) { System.err.println(e.getMessage()); } 
}

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

// vérifie si l'utilisateur connecté est autorisé à supprimer ce compte num
public static boolean isAllowedToRemoveUser(Connection c, String num) {
    return (isNumSecuIsAdmin(c,[**** Nom de la classe où numsecu est déclaré ****].numsecu) || [**** Nom de la classe où numsecu est déclaré ****].numsecu == num);
}

/* 
 * attention avant de détruire un abonné tu dois vérifier les choses suivantes :
 * 1. la personne connecté est un biblio (récup variable globale)
 * 2. confirmation avant suppression par demande du mdp du biblio (correspondant à 
 * son numéro de sécu)
 *
 * 3. autre cas : la personne qui demande la suppression est l'utilisateur lui même
 *
 * je t'ai fait une fonction isAllowedToRemoveUser(...) attention tu dois dire dans quelle
 * classe la variable globale numsecu est stocké
 */
