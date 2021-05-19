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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// classe test de récupération et affichage des informations d'un utilisateur
public class CreationCompte {
    // creation d'un objet NewUser de type User
    static User NewUser = new User();

    static String admin;        // static car réutilisée dans d'autres méthodes
    static String numsecu;
    static int n,g;

    public static void rentreData(Connection c) {
        // lecture des entrées clavier
        Scanner sc = new Scanner(System.in);

        String str;

        String giveAdmin;
        String digits = "\\p{Digit}+";   // String ne contenant que des chiffres pour la vérification
        // autre format possible String digits = "[0-9]+";   
        String punct = "\\p{Punct}+";     // not one of :  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~

        String lower = "\\p{Lower}+";   // unqiement en lower case

        System.out.println("Création d'un nouvel utilisateur");
        System.out.println("===============================");

        System.out.println("Bibliothécaire : (oui/non)");
        // pour pouvoir s'inscrire comme un bibliothécaire,
        // l'usager doit posséder le numéro de sécurite social d'un bibliothécaire déjà inscrit

        // enregistrement de la réponse
        admin = sc.nextLine();
        // c'est un bibliothécaire 
        if (admin.equals("oui")) {
            System.out.println("\nVeuillez rentrer le numéro de sécurité social d'un bibliothécaire : ");
            // enregistrement du numSécu de l'autre biblio
            giveAdmin = sc.nextLine();
            // *** vérification du format du 2ème numéro(giveAdmin) ***
            while (Pattern.matches(digits, giveAdmin) == false || giveAdmin.length() != 15 || giveAdmin.matches("^{1,2}.*") == false) {
                System.out.println("\nMauvais format !");
                System.out.println("Format du numéro : 123456789012345");
                System.out.println("\nVeuillez ré-essayer : ");
                giveAdmin = sc.nextLine();
            }
            // le numéro est dans le bon format, on sort de la boucle
            // vérification que ce numéro existe bien dans la table Bibliothécaires
            if(!isAdmin(c,giveAdmin)) {
                System.out.println(" *** Ce numéro est inconnue à notre base de données ! ***");
                System.out.println(" Veuillez réessayer avec un numéro valide.");
                System.out.println(" .........................................");
                System.out.println(" Arrêt du processus d'inscription\n");
                rentreData(c);
            }
            System.out.println("Le numéro existe, vous pouvez être admis en tant que bibliothécaire !");
            // on enregistre le numéro
            NewUser.setAdmin(true);
        }

        // peut importe si c'est un (future) biblio ou abonné
        System.out.println("votre n° sécu :");
        numsecu = sc.nextLine();

        // vérification longueur et numéros
        while (Pattern.matches(digits, numsecu) == false || numsecu.length() != 15 || numsecu.matches("^{1,2}.*") == false) {
            System.out.println("\nMauvais format !");
            System.out.println("Format du numéro : 123456789012345");
            System.out.println("\nVeuillez ré-essayer : ");
            numsecu = sc.nextLine();
        }

        System.out.print("Vérif de l'unicité du numéro... ");
        // vérification de l'unicité du numéro
        // retour de la fonction != 0 donc, numéro non unique
        if (!isNumsecuUnique(c,numsecu)) {
            System.out.println("ERROR");
            System.out.println("\nLe numéro de sécurité sociale n'est pas unique !");
            System.out.println("Redirection à l'accueil...");
            Main.realMain();
        }
        // faire semblant d'attendre 1 sec
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // le numéro est unique
        System.out.println("OK");

        // enregistrement dans la colonne numSecu pour la bdd
        NewUser.setNumSecu(numsecu);

        System.out.println("nom\t:");
        str = sc.nextLine();
        NewUser.setNom(str);

        System.out.println("prenom :");
        str = sc.nextLine();
        NewUser.setPrenom(str);

        // si ce n'est pas un bibliothécaire
        if (NewUser.getAdmin() == false) {
            System.out.println("date de naissance (01/02/1970): ");
            // formatage de la date dans le format que l'on veut
            try {
                if(str.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
                    SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyyy");
                    Date date = f.parse(str);
                }
            } catch (ParseException e) {}
            // et enregistrement de la date
            str = sc.nextLine();
            // via le setter pour NewUser
            NewUser.setAnniv(str);

            // System.out.println("date Inscription"); automatique

        }

        System.out.println("identifiant : (pas de ponctuation)");
        str = sc.nextLine();
        while (Pattern.matches(punct, str) || !Pattern.matches(lower, str) || !isPseudoUnique(c,str)) {
            System.out.println("\nMauvais format ! Pas de punctuation, identifiants en minuscule, ou identifiant déjà existant !");
            System.out.println("Caractères interdits --> !\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~");
            System.out.println("Veuillez ré-essayer : ");
            str = sc.nextLine();
        }
        // l'identifiant est dans le bon format
        NewUser.setPseudo(str);

        System.out.println("mot de passe : (pas de ponctuation)"); 
        str = sc.nextLine();
        while(Pattern.matches(punct, str)) {
            System.out.println("\nMauvais format !  Pas de punctuation");
            System.out.println("Caractères interdits --> !\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~");
            System.out.println("Veuillez ré-essayer : ");
            str = sc.nextLine();
        }
        // le mdp est dans le bon format, on peut l'enregistrer dans notre objet
        NewUser.setMdp(str);
    }

    // methode pour savoir si le numero de secu est unique ou non
    public static boolean isNumsecuUnique(Connection c,String numero) {
        //Connection connection = null;     // connection dans le Main
        n=0;
        int x=0,y=0;

        try {
            // create a database connection to Librairie.db
            // connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            /*** REQUETES SQL ***/

            // il faut que le numSecu soit unique peut importe si c'est un bilio ou pas
            // requête SQL pour savoir si j'ai plus que 1 numSecu identique dans chacune des tables
            ResultSet rsX = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires AS Bi WHERE Bi.numSecu="+numero+" GROUP BY Bi.numSecu HAVING COUNT(*) >=1;");

            while (rsX.next()) { x = rsX.getInt(1); }

            ResultSet rsY = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.numSecu="+numero+" GROUP BY Ab.numSecu HAVING COUNT(*) >=1;");

            while (rsY.next()) { y = rsY.getInt(1); }

            // on fait la somme des résultats précédents
            // si n>0, alors ce numéro existe déjà
            n = x+y;
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        // si n=0, renvoie vrai
        return n == 0;
    }

    // methode pour savoir si le pseudo est unique ou non
    public static boolean isPseudoUnique(Connection c,String pseudo) {
        n=0;

        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            // sql querie
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Abonnes AS Ab WHERE Ab.identifiant=\""+pseudo+"\" GROUP BY Ab.identifiant HAVING COUNT(*) >=1;");

            while (rs.next()) { n = rs.getInt(1); }

        } catch (SQLException e) { System.err.println(e.getMessage()); } 
        return n==0;
    }


    // est-ce que le numSécu appartient bien à un Bibliothécaire 
    public static boolean isAdmin(Connection c,String numSecuOtherAdmin) {
        g=0;
        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // sql query to determine if giving other numSecu exists
            ResultSet rg = statement.executeQuery("SELECT COUNT(*) FROM Bibliothecaires WHERE numSecu="+numSecuOtherAdmin+";");

            // on récupère la 1ère ligne du résultat, d'où le 1
            g = rg.getInt(1);

        }catch(SQLException e) { System.err.println(e.getMessage()); }
        // return true si g>0
        return g == 1;
    }

    // méthode d'affichage des données lues auparavant (avec l'objet User instancié avant)
    public static void afficheData() {
        // utilisation des getters
        System.out.println("\n\t\t Biblio  :"+NewUser.getAdmin());
        // par défaut il n'est pas sur liste noire (logique)
        System.out.println("\t\t Sur liste noire :"+NewUser.getListeNoire());
        System.out.println("\t\t n°Sécu  :"+NewUser.getNumSecu());
        System.out.println("\t\t nom\t :"+NewUser.getNom());
        System.out.println("\t\t prenom  :"+NewUser.getPrenom());
        if (NewUser.getAdmin() == false) {
            System.out.println("\t\t date de naissance (01/02/1970) : "+NewUser.getAnniv());
            System.out.println("\t\t date d'inscrption : "+NewUser.getDateCreationCompte());
        }
        System.out.println("\t\t identifiant : "+NewUser.getPseudo());
        System.out.println("\t\t mot de passe : "+NewUser.getMdp());
        System.out.println("\t\t ========================================");
    }

    // connexion à la bdd Librairie.db
    public static void insertValues(Connection c) {
        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            //Vérification si c'est un biblio on va remplir les données dans la bonne table
            if (NewUser.getAdmin() == true) admin = "Bibliothecaires";
            else admin = "Abonnes";

            // insertion des valeurs dans la bdd Librairie dans la bonne table
            if (NewUser.getAdmin() == true) {
                // c'est un Biblithécaire
                statement.executeUpdate("insert into "+admin+" values("+NewUser.getNumSecu()+",'"+NewUser.getNom()+"','"+NewUser.getPrenom()+"','"+NewUser.getPseudo()+"','"+NewUser.getMdp()+"')");
            } else {
                // c'est un Abonné
                statement.executeUpdate("insert into "+admin+" values("+NewUser.getNumSecu()+",'"+NewUser.getNom()+"','"+NewUser.getPrenom()+"','"+NewUser.getAnniv()+"','"+NewUser.getDateCreationCompte()+"','"+NewUser.getPseudo()+"','"+NewUser.getMdp()+"','"+(NewUser.getListeNoire()?1:0)+"')");
            }

            // on récupère les données que l'utilisateur vient de rentrer
            // = celle qu'il n'a pas rentrée comme la date courante et 
            // le fait qu'il ne soit pas sur la liste noire
            System.out.println("****** TABLE = "+admin+" ******");
            ResultSet rs = statement.executeQuery("select * from "+admin+" where numSecu="+NewUser.getNumSecu());
            while(rs.next()) {
                // read the result set
                System.out.println("numSecu = " + rs.getString("numSecu"));
                System.out.println("ListeNoire = " + rs.getString("listeNoire"));
                System.out.println("nom     = " + rs.getString("nom"));
                System.out.println("prenom  = " + rs.getString("prenom"));
                // uniquement si ce n'est pas un bibliothécaire
                if (NewUser.getAdmin() == false) { 
                    System.out.println("anniv   = " + rs.getString("dateNaissance"));
                    System.out.println("inscri  = " + rs.getString("dateInscription"));
                }
                System.out.println("pseudo  = " + rs.getString("identifiant"));
                System.out.println("mdp     = " + rs.getString("mdp"));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(Connection c) {
        System.out.println("Remplissez vos informations :");
        rentreData(c);
        System.out.println(""); 
        System.out.println("Affichage des informations que vous venez de remplir :"); 
        System.out.println("===================================================="); 
        // affiche unqiuement celles de l'objet NewUser
        afficheData();
        System.out.println("\n\t... Connection à la base de données ...\n");
        // faire semblant d'attendre 1 sec le temps que ça recherche les données
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Affichages des données de la base de données : "); 
        System.out.println("==========================================="); 
        // insertion d'abord et affichage ensuite
        insertValues(c);
        // on laisse le temps (3) au nouvel usager de voir ses informations 
        // et on le redirige vers la page de connexion
        System.out.println("\nVous allez être redigé à l'accueil du logiciel ...\n");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.realMain();
    }  
}

