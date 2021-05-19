import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;


public class testsocket {

  public static void main(String[] args) {
    try {
      String host = "XXX.XXX.60.140";
      int port = 22;
      Socket socket1 = new Socket(host, port);
      InputStream in = socket1.getInputStream();

      // construction d'un tableau (objet) appelé data de type byte de 1024 cellules
      byte[] data = new byte[1024];
      // on recupere dans data ce que nous renvoie le flux d'entree du serveur
      // l est la longueur de la chaine recue
      int l = in.read(data,0,1024);

      //Convert byte[] to String
      String converted = new String(data);
      // affiche ce que nous renvoit le serveur
      System.out.println("ligne lue : " + converted );

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException o) {
      o.printStackTrace();
    }
  }
}
