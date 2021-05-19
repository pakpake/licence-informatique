import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Requetes {
    static void testRequete(Connection c) {
        System.out.println("Nous sommes dans la classe Requetes.java");
        try {
            // ne pas modifier les 2 lignes suivante
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // si vous devez faire des requetes sql qui ne retournent rien
            // utilisez statement.executeUpdate 
            // sinon statement.executeQuery


            // statement.executeUpdate("insert into Abonnes values('108593075849224','Martin','Eric','15/06/1970','02/12/2020','Moustachu','Baccante',0);");

            // une requete de type select retourne un ResultSet qui est un pointeur
            // vers la liste des résultats
            ResultSet rs = statement.executeQuery("select * from Abonnes;");

            // pour parcourir les résultats, utilisez la boucle suivante
            while(rs.next())
            {
                // read the result set
                System.out.println("numSecu = " + rs.getString("numSecu"));
                System.out.println("pseudo  = " + rs.getString("identifiant"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {}
}
