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

public class AfficherNum {

    // fonction principale
    public static void main(Connection c) {    
        // appel de la variable globale
        System.out.println("numero de sécu variable globale = "+SeConnecter.numsecu);
        //numeroSecu(c);

        String x;
        String pseudo="monchat";
        String pass="miaou";

        try {
            // create a database connection to Librairie.db
            // connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // vérification que l'Abonne existe bien
            ResultSet rsX = statement.executeQuery("SELECT numSecu FROM Abonnes AS Ab WHERE Ab.identifiant=\""+pseudo+"\" AND Ab.mdp=\""+pass+"\";");
            while (rsX.next()) { 
                x = rsX.getString(1);
                System.out.println("numero secu via requete SQL = "+x); 
            }

        }catch (SQLException e) { System.err.println(e.getMessage()); }
    }
}
