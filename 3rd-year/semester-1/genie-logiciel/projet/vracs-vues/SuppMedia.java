/**
 * Classe SuppMedia
 *  classe créant des instances pour ajouter un média.
 *
 */
//TODO :
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class SuppMedia {
    //déclarations des attributs :
    //Attributs communs aux deux types
    private String ean;



    /**
     * Constructeurs de AjoutMedia, pour un DVD
     * @param :nom, prenom, numSecu, id, mdp
     * @return : None
     */
    public SuppMedia(String ea){
        // super();
        if (verifString(ea,13)){
          this.ean=ea;
        }else{
          System.out.println("EAN trop grand!");
          throw new IllegalArgumentException();
        }

        }





    /**
     * Constructeurs de SuppMedia, pour un Livre
     * @param :EAN,titre,auteutr, datePublication,resume,langue,motCles,nombreEx,editeur,etat
     * @return : None

public SuppMedia(String ea ,String title){
    // super();
    if (verifString(ea,13)){
      this.ean=ea;
    }else{
      System.out.println("EAN trop grand!");
      throw new IllegalArgumentException();
    }

    if (verifString(title,300)){
      this.titre=title;
    }else{
      System.out.println("Titre trop grand!");
      throw new IllegalArgumentException();
    }



}
*/

public boolean verifString(String s,int i){

  if(s.length()<=i){
    return true;
  }else{
    return false;
  }


}

public void consultInfosDVD(Connection c){
  RequetesSuppMedia.consultInfosDVD(c);
}

public void consultInfosLivre(Connection c){
  RequetesSuppMedia.consultInfosLivre(c);
}

public void suppMedia(Connection c){

  RequetesSuppMedia.suppMedia(c,ean);
}



public static void main(String[] args) {

  Connection connection = null;
    try
    {
        connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");
        boolean awette=false;
        Scanner S=new Scanner(System.in);

        //Supprimer un DVD

        String ena = "1234567890123";
        SuppMedia am= new SuppMedia(ena);

        //Supprimer un Livre
        String ane = "0987654321098";
        SuppMedia ma= new SuppMedia(ane);


        while(!awette){
          int choix;
          System.out.println("Que voulez vous faire? Supprimer DVD ou Supprimer Livre? \n");
          choix=S. nextInt();
          switch (choix) {
              case 1:  System.out.println("Suppresion de 1234567890123 ... ");

                       am.suppMedia(connection);

                       break;
              case 2:  System.out.println("Suppresion d'un 0987654321098... ");


                       ma.suppMedia(connection);
                       break;

              case 3:  System.out.println("Consultation table DVD... ");

                       am.consultInfosDVD(connection);
                       break;
              case 4:  System.out.println("Consultation table Livres... ");

                       ma.consultInfosLivre(connection);

                       break;
              case 0: awette=true;
                       break;
              default: System.out.println("Fin1");
                       break;
          }//fin switch
      }//fin while awette

      S.close();


    }
    catch(SQLException e)
    {
        // if the error message is "out of memory",
        // it probably means no database file is found
        System.err.println(e.getMessage());
    }
    finally{
        try{
            if(connection != null)
                connection.close();
        }
        catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }



}


}//fin classe
