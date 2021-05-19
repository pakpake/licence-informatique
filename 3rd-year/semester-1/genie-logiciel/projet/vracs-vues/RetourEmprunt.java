 package displaylivre;

//Imports de base pour la connexion et les requêtes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//Imports pour la date
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
  * Cette classe va permettre le retour d'un média
  * L'abonné a dépose son média devant le bibliothécaire
  * Le bibliothécaire clique sur le bouton "Retour média"
  * Il va devoir renseigner l'EAN du média retourné et le numéro de sécurité sociale de l'emprunteur
*/


public class RetourEmprunt{



//Constructeur
/*private String EAN;
private String numSecu;

public RetourEmprunt(String EANemp, String numSecuemp) {
        EAN = EANemp ;
        numSecu = numSecuemp;
}*/

  /**
   * Vérification de l'existence de l'abonné
   * @param numSecu, celui donné par la personne voulant rendre un emprunt
   * @return vrai (true) si le numSecu est celui d'un abonné ou faux (false) sinon
   */
 public boolean estUnAbonne(String numSecu){

   boolean retour = false;
   try {
       String url = "jdbc:sqlite:C:\\Users\\username\\Documents\\NetBeansProjects\\DisplayLivre\\src\\displaylivre\\Librairie.db";
      Connection con = DriverManager.getConnection(url);
       // ne pas modifier les 2 lignes suivante
       Statement statement = con.createStatement();
       statement.setQueryTimeout(30);  // set timeout to 30 sec.

    //On va chercher la liste des numSecus
     ResultSet idAbo = statement.executeQuery("select numSecu from Abonnes;");

     //On parcourt la liste des numSecus
     while(idAbo.next()){
        //Si un numSecu de la base de donnée correspond à l'numSecu donné on retourne true
         if(numSecu.equals(idAbo.getString("numSecu")))
           {retour = true;}
     }


   } catch (SQLException e) {
       System.err.println(e.getMessage());
 }
return retour;
 }



  /**
   * Vérification de l'existence d'un média
   * @param EAN, celui donné par le bibliothécaire voulant rendre un emprunt
   * @return vrai (true) si l'EAN est celui d'un média ou faux (false) sinon
   */

public boolean estUnMedia(String EAN){

     boolean retour = false;
     try {
         
         String url = "jdbc:sqlite:C:\\Users\\username\\Documents\\NetBeansProjects\\DisplayLivre\\src\\displaylivre\\Librairie.db";
         Connection con = DriverManager.getConnection(url);
         // ne pas modifier les 2 lignes suivante
         Statement statement = con.createStatement();
         statement.setQueryTimeout(30);  // set timeout to 30 sec.


     //On va chercher la liste des EAN
     ResultSet EANLivres = statement.executeQuery("select EAN from Livres;");

           //On parcourt la liste des EAN
           while(EANLivres.next())
           {//Si un EAN de la base de donnée correspond à l'EAN donné on retourne true

             if(EAN.equals(EANLivres.getString("EAN")))
                 {retour = true;}
           }
      ResultSet EANDVD = statement.executeQuery("select EAN from DVD;");
           while(EANDVD.next())
           {//Si un EAN de la base de donnée correspond à l'EAN donné on retourne true

             if(EAN.equals(EANDVD.getString("EAN")))
                 {retour = true;}
           }

         } catch (SQLException e) {
             System.err.println(e.getMessage());
       }

 return retour;
   }

   /**
    * Retour de l'emprunt
    * @param EAN du média à retourner
    * @param numSecu de l'abonné voulant rendre un emprunt
    */

public void retournerMedia(String EAN, String numSecu){

    
  try {
      
      String url = "jdbc:sqlite:C:\\Users\\username\\Documents\\NetBeansProjects\\DisplayLivre\\src\\displaylivre\\Librairie.db";
      Connection con = DriverManager.getConnection(url);
      
      // ne pas modifier les 2 lignes suivante
      Statement statement = con.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      //Initialisation de la date du jour, la date d'emprunt
      Date aujourdhui = new Date();

      SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
      String dateRetour = formater.format(aujourdhui);

      //On ajoute la date de retour à l'emprunt dans la base de données
      statement.executeUpdate("UPDATE Emprunts set dateRetour = \""+dateRetour+"\" where numSecu = \""+numSecu+"\" AND EAN = \""+EAN+"\";");


      } catch (SQLException e) {
          System.err.println(e.getMessage());
    }

}

}
