// pour compiler et executer le prgm
// javac DateServer.java
// java DateServer 13

/***
author :  pakpake
date : Fevrier 2020
**/

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
/*
import java.util.*;
import java.net.*;
import java.io.*;
*/

public class DateServer {

  public static void main(String[] args) {
    try {
      // nombre de paramètres
      int nbParam = args.length;

//      if (nbParam != 1 || args[0] != "-h") {
      if (nbParam != 1) {
        System.out.println("Usage : java DataServer <port>");
        System.exit(0);
      }

      // on recupere dans port le 1er argument
      // ici on convertit le string (argument 0 = le port ) en entier
      int port = Integer.parseInt(args[0]);
      // le host par défaut est le localhost
      // dateserver test : XXX.XXX.60.140
      String host = "127.0.0.1";
      //String host = "XXX.XXX.60.140";
      Socket socket1 = new Socket(host, port);
      OutputStream out = socket1.getOutputStream();   

      // Gestion de la date
      Date ddj = new Date(); // on recupere la date du jour
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // on fixe le format
      String dat = dateFormat.format(ddj); // on écrit dans dat la date au format voulut

      // construction d'un tableau (objet) appelé data de type byte de 1024 cellules
      // byte[] data = new byte[1024];

      // on convertit dat en byte
      byte[] data = dat.getBytes();
      out.write(data,0,1024);

      //Convert byte[] to String;
      String converted = new String(data);
      // affiche ce que nous renvoit le serveur
      System.out.println("Ligne envoyee par le  serveur => " + converted );

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException o) {
      o.printStackTrace();
    }
  }
}
