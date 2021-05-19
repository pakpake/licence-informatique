// pour compiler et executer le prgm
// javac DataClient.java
// java DataClient XXX.XXX.60.140 22

/***
author :  pakpake
date : Fevrier 2020
***/

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;


public class DataClient {

  public static void main(String[] args) {
    try {
      // nombre de paramètres
      int nbParam = args.length;

      // if (nbParam == 1 && args[0] == "-h") {
      //   System.out.println("Usage : java DataClient <hostName> <port>");
      //   exit(0);
      // }

      if (nbParam != 2) {
        System.out.println("Usage : java DataClient <hostName> <port>");
        System.exit(0);
      }

      // String host = "XXX.XXX.60.140";
      // int port = 22;
      String host = args[0];
      // on recupere dans le host le 1er argument et le port le 2eme
      // ici on convertit le string (argument 2 = le port ) en entier
      int port = Integer.parseInt(args[1]);
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
      System.out.println("Ligne lue => " + converted );
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException o) {
      o.printStackTrace();
    }
  }
}
