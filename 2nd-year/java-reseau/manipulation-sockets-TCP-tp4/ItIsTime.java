import java.io.*;
import java.net.*;
import java.util.*;

/*
@date: Mars 2020
@author: pakpake 
*/

public class ItIsTime implements IItIsTime {

  String machine_name;
  int server_port;
  String login;
  int nb;
  private LineTransport linetransport = new LineTransport();
  private DataTransport transport = new DataTransport();


  // constructeur
  public ItIsTime(String host, int port) {
    machine_name = host;
    server_port = port;
  } // constructeur

  public String connect(String tp, String grp, String key){
    try {
      String reply = "";
      linetransport.send("10"+tp+grp+key);
      reply = linetransport.receive();
      login = "";
      if (reply.substring(0,2).equals("20")) {
        login = reply.substring(2,reply.length());
      }else if (reply.substring(0,1).equals("5")) {
        if (reply.equals("50")){
          System.out.println("identification refused - wrong id");
        }
        if (reply.equals("51")){
          System.out.println("identification refused - out of time");
        }
        if (reply.equals("55")){
          System.out.println("unexpected command");
        }
      }
      return login;
    } catch(IOException e) {
      System.out.println("Unexpected error");
      return "";
    }
  } // connect

  public int send(String filename){
    try {
      // 1) envoyer le message "12"
      String reply="";
      linetransport.send("12");
      // 2) recevoir la reponse du server
      reply = linetransport.receive();
      // 3) si la reponse est "52", afficher message d'erreur et renvoyer "-1"
      int retour = -1;
      if (reply.equals("52")){
        System.out.println("error while opening connection");
      }else if (reply.substring(0,2).equals("22")) {
        // 4) Sinon si la reponse du server est "22"+nb
        // on récupère le numéro du port en entier
        nb = Integer.parseInt(reply.substring(2,reply.length()));

        // a) ouvrir une connexion avec le po// open a connection with a server
        // et transmettre le fichier (en binaire) via cette connexion (methode send de la classe DataTransport),
        transport.openClient(machine_name, nb);
        FileInputStream in = new FileInputStream(filename);
        transport.send(in); // transfer the file to the server
        transport.close(); // close the connection to the serverrt nb du server
        in.close(); // close the file

        // b) envoyer le message "13" sur le port habituel
        linetransport.send("13");
        // recevoir la réponse du server
        reply = linetransport.receive();
        if (reply.substring(0,2).equals("23")) {
          int size = Integer.parseInt(reply.substring(2,reply.length()));
          retour = size;
        }else if (reply.substring(0,2).equals("53")) {
          System.out.println("error while reading file");
        }else if (reply.substring(0,2).equals("55")) {
          System.out.println("unexpected command");
        }
      }
      return retour;
    } catch(IOException e){
      System.out.println("Unexpected error");
      return -1;
    }
  } // send

  public void close(){
    try{
      String reply = "";
      linetransport.send("14");
      reply = linetransport.receive();
      if (reply.equals("24")) {
        System.out.println("Connection closed.");
      }
      if (reply.equals("55")) {
        System.out.println("Unexpected command.");
      }
    } catch(IOException e){
      System.out.println("Unexpected error");
    }
  } // close

  private static void usage()
  {
    System.out.println("Usage: java ItIsTime <host> <port> <tp> <key> <grp> <filename>");
    System.exit(-1);
  } //usage

  public static void main(String[] args) {
    if (args.length != 6 || args[0].equals("-h")) {
      usage();
    } else {
      try {

        // variables
        String machine_name = args[0];
        int server_port = Integer.parseInt(args[1]);
        String tp = args[2];
        String key = args[3];
        String grp = args[4];
        String filename = args[5];
        String login;
        int nb;

        /* méthode main qui prend en parametre le NOM DE MACHINE,
        le NUMERO DE PORT DU SERVEUR, le NUMERO DU TP, votre CLÉ votre GRP DE TP
        et le FICHIER A TRANSMETTRE */

        // creation d'un objet de la classe ItIsTime
        ItIsTime itIsTime = new ItIsTime(machine_name, server_port);
        // ouverture de la connexion
        itIsTime.linetransport.openClient(itIsTime.machine_name, itIsTime.server_port);
        // appel des méthodes de classe
        itIsTime.connect(tp,grp,key);
        itIsTime.send(filename);
        itIsTime.close();
      }
      catch (IOException e) {
        System.out.println("Unexpected error");
      }
    }
  } //main

} // class
