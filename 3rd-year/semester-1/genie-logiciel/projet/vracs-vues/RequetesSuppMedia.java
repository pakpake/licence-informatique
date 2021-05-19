import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class RequetesSuppMedia {


static void suppMedia(Connection c,String ea){
  try {
      // ne pas modifier les 2 lignes suivante
      Statement statement = c.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from DVD where EAN='"+ea+"';");

      if(rs.next()){//si la ligne existe et possède d'autres élements
      System.out.println("DVD?");
        if(rs.getInt("nombre")>1){
          System.out.println("Ha il a des exemplaires");
          String sql = "UPDATE DVD SET nombre =? where EAN=?";

          PreparedStatement ps = c.prepareStatement(sql);
          ps.setInt(1,rs.getInt("nombre")-1);
          ps.setString(2,ea);
          ps.executeUpdate();
        }else{

            System.out.println("Il est dead");
          statement.executeUpdate("DELETE FROM DVD where EAN='"+ea+"';");
        }
      }else{
        System.out.println("C'est pas un DVD");
        System.out.println("\n");
      }

      ResultSet sr = statement.executeQuery("select * from Livres where EAN='"+ea+"';");

      if(sr.next()){//si la ligne existe et possède d'autres élements

      System.out.println("C'est un livre");
      if(sr.getInt("nombre")>1){

          System.out.println("A til des exem");
          int ex=sr.getInt("nombre")-1;
        statement.executeUpdate("UPDATE Livres SET nombre ='"+ex+"' where EAN='"+ea+"';");
      }else{
        System.out.println("Deeed");
        statement.executeUpdate("DELETE FROM Livres where EAN='"+ea+"';");
      }


  }else{

    System.out.println("C'est pas un Livre");
    System.out.println("\n");

  }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
  }//fin void ajouterDVD

  static void consultInfosDVD(Connection c) {
      //System.out.println("Consulter les informations d\"un abonné précis \n");
      try {
          Statement statement = c.createStatement();
          statement.setQueryTimeout(30);

          //
          ResultSet rs = statement.executeQuery("select * from DVD;");
          while(rs.next())
          {
              // read the result set
              System.out.println("\n");
              System.out.println("EAN = " + rs.getString("EAN"));
              System.out.println("Titre  = " + rs.getString("titre"));
              System.out.println("nombreEx = " + rs.getInt("nombre"));
              System.out.println("Etat  = " + rs.getByte("etat"));
              System.out.println("\n");
          }
      } catch (SQLException e) {
          System.err.println(e.getMessage());
      }
  }//void consultInfosDVD


  /*static void suppLivre(Connection c,String ean, String titre,){
    try {
        // ne pas modifier les 2 lignes suivante
        Statement statement = c.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        ResultSet rs = statement.executeQuery("select * from Livres where EAN='"+ean+"';");
        if(rs.next()){//si la ligne existe et possède d'autres élements
            if(rs.etat){//Le document existe mais faut verif aussi s'il est pas em^prunté
            statement.executeUpdate("DELETE FROM DVD where EAN='"+ea+"';");
            }else{
              System.out.println("Action impossible: Livre emprunté");
            }
        }else{

        System.out.println("Action impossible: Livre inexistant");
  }
      } catch (SQLException e) {
          System.err.println(e.getMessage());
      }
    }*/

    static void consultInfosLivre(Connection c) {
        //System.out.println("Consulter les informations d\"un abonné précis \n");
        try {
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);

            //
            ResultSet rs = statement.executeQuery("select * from Livres;");
            while(rs.next())
            {
                // read the result set
                System.out.println("\n");
                System.out.println("EAN = " + rs.getString("EAN"));
                System.out.println("Titre  = " + rs.getString("titre"));
                System.out.println("nombreEx = " + rs.getInt("nombre"));
                System.out.println("Etat  = " + rs.getByte("etat"));
                System.out.println("\n");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }//void consultInfosLivre




}
