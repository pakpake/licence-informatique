import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class AjoutMedia {

  //déclarations des attributs :
  //Attributs communs aux deux types
  private static String ean, titre , resume, langue, datePublication, motCles;
  private static int nombreEx=-1;
  private static byte etat;

  //Attributs de DVD
  private static String realisateur;
  private static String principauxActeurs;
  private static int duree;

  //Attributs de Livre
  private static String auteur;
  private static String editeur;

static void ajouterDVD(String ea,String title ,String resu,String lang,String datePub, String motC, int nb,byte state, String real, String acteurs,int dur){

  Connection c = null;

  if (verifString(ea,13)){
    ean=ea;
  }else{

    throw new IllegalArgumentException();
  }
  if (verifString(title,300)){
    titre=title;
  }else{
    throw new IllegalArgumentException();
  }
  if (verifString(resu,300)){
    resume=resu;
  }else{
    throw new IllegalArgumentException();

  }
  if (verifString(lang,50)){
    langue=lang;
  }else{
    throw new IllegalArgumentException();
  }
  if (verifString(datePub,10)){
    datePublication=datePub;
  }else{
    throw new IllegalArgumentException();
  }
  if (verifString(motC,300)){
    motCles=motC;
  }else{
    throw new IllegalArgumentException();
  }
  if (verifString(real,300)){
    realisateur=real;
  }else{
    throw new IllegalArgumentException();
  }
  if (verifString(acteurs,300)){
    principauxActeurs=acteurs;
  }else{
    throw new IllegalArgumentException();
  }
  if (0<dur && dur<200){
    duree=dur;
  }else{
    throw new IllegalArgumentException();
  }
  if (0<=nb && nb<101){
    nombreEx=nb;
  }else{
    throw new IllegalArgumentException();
  }


  if (nombreEx != -1 && (state==0 || state==1)){
    etat=state;
  }else{
    throw new IllegalArgumentException();
  }

  try {
      // ne pas modifier les 2 lignes suivante
      Statement statement = c.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
      String exemplaires= nb + "";
      ResultSet rs = statement.executeQuery("select * from DVD where EAN='"+ea+"';");
      if(rs.next()){
        String sql = "UPDATE DVD SET nombre =? where EAN=?";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1,rs.getInt("nombre")+nb);
        ps.setString(2,ea);
        ps.executeUpdate();

      }else{

      String duree= dur + "";
      String etat=state+"";
      statement.executeUpdate("INSERT INTO DVD VALUES ('"+ea+"','"+title+"','"+real+"','"+resu+"','"+lang+"','"+acteurs+"','"+datePub+"','"+motC+"','"+exemplaires+"','"+duree+"','"+etat+"');");

}
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
    finally{
        try{
            if(c != null)
                c.close();
        }
        catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
  }//fin void ajouterDVD



static void ajouterLivre(String ea,String title , String aut, String datePub, String resu, String lang, String motC, int ex, String ed, byte state){

  Connection c = null;

    if (verifString(ea,13)){
      ean=ea;
    }else{
      throw new IllegalArgumentException();
    }
    if (verifString(title,300)){
      titre=title;
    }else{
      throw new IllegalArgumentException();
    }
    if (verifString(resu,300)){
      resume=resu;
    }else{
      throw new IllegalArgumentException();

    }
    if (verifString(lang,50)){
      langue=lang;
    }else{
      throw new IllegalArgumentException();
    }
    if (verifString(datePub,10)){
      datePublication=datePub;
    }else{
      throw new IllegalArgumentException();
    }
    if (verifString(motC,300)){
      motCles=motC;
    }else{
      throw new IllegalArgumentException();
    }
    if (verifString(aut,300)){
      auteur=aut;
    }else{
      throw new IllegalArgumentException();
    }

    if (verifString(ed,300)){
      editeur=ed;
    }else{
      throw new IllegalArgumentException();
    }
    if (0<=ex && ex<101){
      nombreEx=ex;
    }else{
      throw new IllegalArgumentException();
    }
    if (nombreEx != -1 && (state==0 || state==1)){
      etat=state;
    }else{
      throw new IllegalArgumentException();
    }


    try {
        c= DriverManager.getConnection("jdbc:sqlite:Librairie.db");
        // ne pas modifier les 2 lignes suivante
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        String exemplaires= nombreEx + "";
        ResultSet rs = statement.executeQuery("select * from Livres where EAN='"+ean+"';");
        if(rs.next()){
          String sql = "UPDATE Livres SET nombre =? where EAN=?";

          PreparedStatement ps = c.prepareStatement(sql);
          ps.setInt(1,rs.getInt("nombre")+nombreEx);
          ps.setString(2,ean);
          ps.executeUpdate();

        }else{


        String etat=state+"";
        statement.executeUpdate("INSERT INTO Livres VALUES ('"+ean+"','"+titre+"','"+auteur+"','"+datePublication+"','"+resume+"','"+langue+"','"+motCles+"','"+exemplaires+"','"+editeur+"','"+etat+"');");

  }
      } catch (SQLException e) {
          System.err.println(e.getMessage());
      }
      finally{
          try{
              if(c != null)
                  c.close();
          }
          catch(SQLException e){
              // connection close failed.
              System.err.println(e.getMessage());
          }
      }
    }//void ajouterLivre



public static boolean verifString(String s,int i){

      if(s.length()<=i){
        return true;
      }else{
        return false;
      }

    }


}
