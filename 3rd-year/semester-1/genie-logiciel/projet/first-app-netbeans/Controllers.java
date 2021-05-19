package apptest1;

import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


// Pour la connection et l'envoit de données avec la BDD
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Controllers {
    // instanciation de la classe Controllers

    // variable de classe permettant de donner le chemin complet où est stocké la bdd
    public static String url="/home/pakpak/genie-log/projet/database/Librairie.db";

    // cette variable permet de savoir si un utilisateur est connecté
    // elle est màj dans connexion
    // si elle contient la chaine vide, personne n'est connecté
    // si elle contient un numsecu on peut vérifier si c'est un biblio ou un abonne avec
    // les fonctions isNumSecuIsAdmin(...) et isNumSecuIsAbonne(...)
    
    //public static String numsecu="";       // variable globale

    // vérification du format du numéro de sécu
    public static boolean isNumSecuValid(String str) {
        String digits = "\\p{Digit}+";   // String ne contenant que des chiffres pour la vérification
        return (Pattern.matches(digits, str)    // que des chiffres
                && str.length() == 15           // longueur = 15
                && str.matches("^{1,2}.*"));    // commence par 1 ou 2
    }

    // vérification si numsecu est un admin, nécessite de passer le connecteur de la BDD
    // ouvert avant 
    public static boolean isNumSecuIsAdmin(String str) {
        
        int g=0;
        Connection c = null;          
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);
         
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // sql query to determine if giving other numSecu exists
            ResultSet rg = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires WHERE numSecu="+str+";");

            // on récupère la 1ère ligne du résultat, d'où le 1
            // g = rg.getInt(1);        // << ancienne forme de récupération de getInt
            while (rg.next()) { g = rg.getInt(1); }

            // close ResultSet, Statement, Connection
            rg.close();
            statement.close();
            c.close();
            
        }catch(SQLException e) { System.err.println(e.getMessage()); }
        // return true si g>0
        return g == 1;
    }

    // vérifie si le numéro de sécu existe dans la bdd
    public static boolean isNumsecuExistInBdd(String str) {
        //Connection connection = null;     // connection dans le Main
        int n=0;
        int x=0,y=0;
        
        Connection c = null;          
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);

            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // il faut que le numSecu soit unique peut importe si c'est un bilio ou pas
            // requête SQL pour savoir si j'ai plus que 1 numSecu identique dans chacune des tables
            ResultSet rsX = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires AS Bi WHERE Bi.numSecu="+str+" GROUP BY Bi.numSecu HAVING COUNT(*) >=1;");

            while (rsX.next()) { x = rsX.getInt(1); }

            ResultSet rsY = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.numSecu="+str+" GROUP BY Ab.numSecu HAVING COUNT(*) >=1;");

            while (rsY.next()) { y = rsY.getInt(1); }

            // on fait la somme des résultats précédents
            // si n>0, alors ce numéro existe déjà
            n = x+y;
            
            // close ResultSet, Statement, Connection
            rsX.close();
            rsY.close();
            statement.close();
            c.close();
            
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        // si n!=0, renvoie vrai
        return n != 0;
    }

    // vérification format date de naissance (si ce n'est pas un biblio !)
    // format = JJ/MM/YYYY
    public static boolean isDateNaissanceValid(String str) {
        return (str.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"));
    }

    public static boolean isIdentifiantValid(String str) {
        String punct = "\\p{Punct}+";     // not one of :  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
        String lower = "\\p{Lower}+";   // unqiement en lower case
        return (!Pattern.matches(punct, str) &&     // pas de ponctuation
                Pattern.matches(lower, str) &&      // tout en minuscule
                !isPseudoIsInBdd(str));           // pseudo n'est pas dans bdd
    }

    // vérification que le pseudo existe dans la bdd
    public static boolean isPseudoIsInBdd(String str) {
        int n=0;
        Connection c = null;          
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);
            
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            // sql querie
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.identifiant=\""+str+"\" GROUP BY Ab.identifiant HAVING COUNT(*) >=1;");
            while (rs.next()) { n = rs.getInt(1); }
            
            // close ResultSet, Statement, Connection
            rs.close();
            statement.close();
            c.close();
            
        } catch (SQLException e) { System.err.println(e.getMessage()); } 
        return n!=0;
    }

    // vérification du format du mot de passe
    public static boolean isMdpValid(String str) {
        String punct = "\\p{Punct}+";     // not one of :  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
        return !Pattern.matches(punct, str);
    }

    /*
     * Voilà le pseudo code pour chaque User qui veut créer son compte :
     *
     * (Si l'utilisateur veut se créer un compte bibliothécaire)
     *      1. entre le numéro de sécu d'un autre biblio existant
     *      2. appel de la fonction isNumSecuIsAdmin(c, str) pour vérifier que
     *      le numéro saisi est bien un numéro d'admin (tu dois aussi vérifier que le numsécu 
     *      à le bon format avec isNumSecuValid(str))
     *
     *      3. Rentre son numéro de sécu à lui (vérif format isNumSecuValid(str) et 
     *      si le numéro n'est pas dans la bdd avec isNumsecuExistInBdd(c,str))
     *      4. enregistre le nom, le prénom
     *      7. enregiste l'identifiant (vérif avec isPseudoIsInBdd(...) 
     *      et format avec isIdentifiantValid(...))
     *      8. enregistre le mdp (vérif ponctuation avec isMdpValid(...))
     *
     *
     *  Si l'utilisateur veut se créer un compte Abonné :
     *      reprendre les étapes 3 4 
     *
     *      5. enregistre la date de naissance (vérif format avec isDateNaissanceValid(str))
     *      6. N'oublie pas d'insérer la date d'inscription 
     *      (voir classe User : NewUser.getDateCreationCompte())
     *
     *      reprendre les étapes 7 8
     *
     *      9. insertion de toutes les informations dans la bdd en tant que abonné
     */



    /********************************************************************************/
    /********************************************************************************/

    public static boolean isNumSecuIsAbonne(String str) {
        int g=0;
        Connection c = null;          
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);
            
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // sql query to determine if giving other numSecu exists
            ResultSet rg = statement.executeQuery("SELECT COUNT(*) FROM Abonnes WHERE numSecu="+str+";");

            // on récupère la 1ère ligne du résultat, d'où le 1
            // g = rg.getInt(1);        // << ancienne forme de récupération de getInt
            while (rg.next()) { g = rg.getInt(1); }

            // close ResultSet, Statement, Connection
            rg.close();
            statement.close();
            c.close();
            
        }catch(SQLException e) { System.err.println(e.getMessage()); }
        // return true si g>0
        return g == 1;
    }

    // méthode de connexion (avec verification des informations de login)
    public static boolean connexion(String pseudo, String pass) {
        int n=0;
        int x=0;
        int y=0;
        
        Connection c = null;          
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);
                                
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
                while (rs.next()) { VueConnexion.numsecu = rs.getString(1); } 
                
                // close ResultSet rs
                rs.close();
                
            }
            
            // close ResultSet, Statement, Connection
            rsX.close();
            rsY.close();
            statement.close();
            c.close();
            
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return n==1;
    }

    /*
     * le mec se connecte et tu véfifes le format du pseudo et du mdp
     * ensuite on vérifie que il existe bien dans la bdd et ça met a jour le numsecu (variable globale)
     * tu changes de vues vers l'acceuil correspondant (biblio ou abonne)
     */

    /********************************************************************************/
    /********************************************************************************/

    // méthode de suppression de l'abonné
    public static void deleteUser(String num) {
        String table="Abonnes";
        Connection c = null;          
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);
            
            if (isNumSecuIsAdmin(num)) table = "Bibliothecaires";
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("DELETE FROM "+table+" WHERE  numSecu='"+num+"';");

            // close Statement, Connection
            statement.close();
            c.close();
            
        }catch (SQLException e) { System.err.println(e.getMessage()); } 
    }

    // vérifie si l'utilisateur connecté est autorisé à supprimer ce compte num
    public static boolean isAllowedToRemoveUser(String num) {
        return (isNumSecuIsAdmin(VueConnexion.numsecu) || VueConnexion.numsecu == num);
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
    
    public static boolean verif(String dateNaissance,String id, String mdp, String numSecu) {
        
        if(isNumsecuExistInBdd(numSecu)){
            return false;
        }
        if(!isNumSecuValid(numSecu)){
            return false;
        }
        if(!isIdentifiantValid(id)){
            return false;
        }
        if(!isMdpValid(mdp)){
            return false;
        }
        if(!isDateNaissanceValid(dateNaissance)){
            return false;
        }

        return true;
    }
    
    public static void addUser(User u) {
        Connection c = null;          
        try {
            c = DriverManager.getConnection("jdbc:sqlite:"+url);

            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            //Vérification si c'est un biblio on va remplir les données dans la bonne table
            String admin = "Abonnes";
            
            // si tu as mis ça je suppose que tu as changé le type de numSecu dans la classe User, alors que j'avais dit de pas le faire
            //Double d = u.getNumSecu();
            //String ns = d.toString();
            
            if(verif(u.getAnniv(), u.getPseudo(), u.getMdp(),u.getNumSecu())) {
                if (u.getAdmin()) {
                    admin = "Bibliothecaires";
                    // c'est un Biblithécaire
                    statement.executeUpdate("insert into "+admin+" values("+u.getNumSecu()+",'"+u.getNom()+"','"+u.getPrenom()+"','"+u.getPseudo()+"','"+u.getMdp()+"')");
                } else {
                    // c'est un Abonné
                    statement.executeUpdate("insert into "+admin+" values("+u.getNumSecu()+",'"+u.getNom()+"','"+u.getPrenom()+"','"+u.getAnniv()+"','"+u.getDateCreationCompte()+"','"+u.getPseudo()+"','"+u.getMdp()+"','"+(u.getListeNoire()?1:0)+"')");
                }
            }
            statement.close();
            c.close();

        } catch(SQLException e) { System.err.println(e.getMessage()); }
    }
    

}
