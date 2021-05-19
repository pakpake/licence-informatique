import java.io.*;
import java.net.*;
import java.util.*;

public class ReceiveUserInfo {

  // fonction d'usage du programme
  private static void usage()
  {
    System.out.println("Usage: java ReceiveUserInfo <port>");
    System.exit(-1);
  }
  public static void main(String[] args) {
    if (args.length != 1 || args[0].equals("-h")) {
      usage();
    }
    try {
      int port = Integer.parseInt(args[0]);
      DatagramSocket server = new DatagramSocket(port);

      //programme qui tourne en boucle jusqu'à ^C
      while (true) {
        //on déclare un buffer de taille 32 pour recevoir les datagram
        byte[] buffer = new byte[32];
        // on déclare le packet
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        // on attend la reception du packet
        server.receive(packet);

        // on récupère et on affiche simplement.
        buffer = packet.getData();
        System.out.println("\nData reçues = " + DataBufferizer.bufferToString(buffer));
        String monadresse = packet.getAddress().toString().substring(1);
        System.out.println("Adresse réception = " + monadresse + "\nPort réception = " + packet.getPort());

        int offset=0; //incrément pour l'offset

        //type
        byte type = DataBufferizer.readByte(buffer,offset);
        offset += 1;
        //date
        Date date = new Date(DataBufferizer.readLong(buffer,offset));
        offset += 8;
        //adresse
        InetAddress adresse = InetAddress.getByAddress(DataBufferizer.readByteArray(buffer,offset,4));
        offset += 4;
        //port
        int port2 = (int) DataBufferizer.readShort(buffer,offset);
        offset += 2;
        //login
        //taille login
        int tlogin = (int) DataBufferizer.readByte(buffer,offset);
        offset += 1;
        String login = new String(DataBufferizer.readByteArray(buffer,offset,tlogin));

        //affichage complet
        System.out.println("-----------------------------------------");
        System.out.println("Type      : "+type);
        System.out.println("Date      : "+date);
        System.out.println("Adresse   : "+adresse.toString().substring(1));
        System.out.println("Port      : "+port2);
        System.out.println("Login     : "+login);
        System.out.println("-----------------------------------------");


        //On réinitialise la taille du datagramme, pour les futures réceptions
        packet.setLength(buffer.length);
      }



    } catch (Exception e) {
      e.printStackTrace();
      usage();
    }

  }
}
