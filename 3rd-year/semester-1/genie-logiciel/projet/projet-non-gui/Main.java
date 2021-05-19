import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{
    void appels(Connection c) {
        // On se déplace dans la classe Requete en passant en paramètre le connecteur à la base de données
        MenuPrincipal.afficherMenu(c);
        // mettre à la suite les autres méthodes d'autrs classes et méthodes
    }

    public static void realMain()
    {
        Connection connection = null;
        try
        {
            // create a database connection to Librairie.db
            connection = DriverManager.getConnection("jdbc:sqlite:Librairie.db");

            // instanciation de la classe Main
            Main m = new Main();
            // appel de la méthode qui va faire les requêtes (en passant par la classe Requête)
            m.appels(connection);

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
    // obligatoire mais inutile sinon java gueule ...
    public static void main(String[] args) {
        realMain();
    }    
}

