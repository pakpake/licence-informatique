import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ProcessUserInfo
{
/**
  * Memorise les informations des utilsateurs connectés
  */

  /**
  * Print a usage message and exit
  */
  private static void usage()
  {
    System.out.println("Usage: java ProcessUserInfo port");
    System.exit(-1);
  }

  // on va utiliser un hashmap pour stocker l'information des utilisateurs
  // Cette structure permet d'eviter les doublons dans le stockage des informations
  // la clé est le login et la valeur le datagramme complet au format 1
  static Map<String, byte[]> hm = new HashMap<>();

  /**
  * Program entry point
  *
  * @param args port
  */
  public static void main(String[] args) throws IOException
  {
    if (args.length != 1 || args[0].equals("-h")) {
      usage();
    }
    short port = 0;
    try {
      port = Short.parseShort(args[0]);
    } catch (Exception e) {
      e.printStackTrace();
      usage();
    }

    // boucle sans fin pour recevoir l'information de plusieurs utilisateurs
    while(true) {
      // On attend de recevoir un datagramme de format 1, 2 ou 3
      ProcessUserInfo processUserInfo = new ProcessUserInfo();
      processUserInfo.receivePacket(port);
      // on affiche le nombre d'utilisateurs connectés
      System.out.println("\nNombre d'utilisateurs connectés : "+hm.size());
      // et la liste des utilsateurs connectés
      // Récupérer les clés (utilisateurs connectés)
      System.out.print("{");
      for (Map.Entry<String, byte[]> entry : hm.entrySet()) {
          String key = entry.getKey();
          System.out.print(" " + key + " ;");
      }
      System.out.println(" }");
      System.out.println("------------------------------------------------\n");
    }
  }

  /**
  * Receive a packet having the format 1 and display the informations it contains
  *
  * @param port the receiver port number
  * @throws IOException if an error occurs while receiveing the packet
  */
  public void receivePacket(int port) throws IOException
  {
    // receive a datagram from port
    byte[] buffer = new byte[32];
    DatagramSocket socket = new DatagramSocket(port);
    DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
    socket.receive(datagram);
    socket.close();
    // on affiche le datagramme reçu (même si ce n'est pa utile)
    System.out.println("\nRECV : " + DataBufferizer.bufferToString(buffer) + " \nPORT : " + port);

    // get informations from the received datagram
    int offset = 0;
    // on recupere le type de datagramme
    byte type = buffer[offset];
    offset++;
    // en fonction du type on a des actions differentes
    switch(type) {
      case 1: // un utilisateur se connecte
        // on decode le reste du datagramme
        Date date = new Date(DataBufferizer.readLong(buffer, offset));
        offset += 8;
        InetAddress address = InetAddress.getByAddress(DataBufferizer.readByteArray(buffer, offset, 4));
        offset += 4;
        short port2 = DataBufferizer.readShort(buffer, offset);
        offset += 2;
        byte n = buffer[offset];
        offset++;
        String login = new String(DataBufferizer.readByteArray(buffer, offset, n));

        // display informations
        System.out.println("Type:    " + buffer[0]);
        System.out.println("Date:    " + date);
        System.out.println("Address: " + address.toString().substring(1));
        System.out.println("Port:    " + port2);
        System.out.println("Login:   " + login);

        // on stocke les informations dans notre hashmap
        hm.put(login, buffer);
        break;
      case 2: // On recoit un paquet de RequestUserInfo
        // on decode le reste du datagramme
        InetAddress myaddress = InetAddress.getByAddress(DataBufferizer.readByteArray(buffer, offset, 4));
        offset += 4;
        short myport = DataBufferizer.readShort(buffer, offset);
        offset += 2;
        byte myn = buffer[offset];
        offset++;
        String mylogin = new String(DataBufferizer.readByteArray(buffer, offset, myn));

        try { // on attend 2 secondes que le requestUserInfo soit prêt à recevoir le datagramme
          TimeUnit.SECONDS.sleep(2);
        }  catch (InterruptedException e) {
          // gestion de l'erreur
        }
        // si l'utilisateur est connecté
        if (hm.containsKey(mylogin)) {
          buffer = hm.get(mylogin); // on récupère son datagramme
        } else { // sinon on remplit un datagramme de 0
          for (int i=0; i<32; i++) {
            buffer[i]=0;
          }
        }
        // on envoie le datagrmme quoi qu'il advienne

        // ProcessUserInfo répond à la requête en renvoyant aux coordonnées
        // indiquées dans la requête un datagramme au format 1
        // que l'on avait stocké dans la hashmap
        DatagramSocket mysocket = new DatagramSocket();
        DatagramPacket mydatagram = new DatagramPacket(buffer, buffer.length, myaddress, myport);
        mysocket.send(mydatagram);
        mysocket.close();
        System.out.println("\nSENT BACK INFO: " + DataBufferizer.bufferToString(buffer) + " \nTO " + myaddress + " : " + myport);
        break;
      case 3: // On deconnecte l'utilisateur
        // on decode le reste du datagramme
        byte lon = buffer[offset];
        offset++;
        String lologin = new String(DataBufferizer.readByteArray(buffer, offset, lon));
        // si l'utilsateur est connecté
        if (hm.containsKey(lologin)) {
          // on le supprime de la hashmap
          hm.remove(lologin);
          // on indique que l'utilisateur est maintenant déconnecté
          System.out.println("\nUSER "+lologin+" déconnecté !");
        } else // sinon on renvoie un message pour dire que cet utilisateur n'est pas connecté
          System.out.println("\nUSER " + lologin + " n'est pas connecté !");
        break;
      default:
        // Si le format de datagramme n'est pas bon, on renvoie une erreur
        System.out.println("ProcessUserInfo: wrong format");
        System.exit(-1);
    }

  }
}
