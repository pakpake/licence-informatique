import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.Integer;

public class SendUserInfo {

  // fonction d'usage du programme
  private static void usage()
  {
    System.out.println("Usage: java SendUserInfo <adresse1> <port1> <adresse2> <port2> <login>");
    System.exit(-1);
  }

  public static void main(String[] args) {
    if (args.length != 5){
      usage();
    }
    try{
      //read program arguments
      String adresse1 = args[0];
      int port1 = Integer.parseInt(args[1]);
      String adresse2 = args[2];
      int port2 = Integer.parseInt(args[3]);
      String login = args[4];

      //creation du datagram
      byte[] datagram = new byte[32];
      //récupération de la date
      long dateajd = System.currentTimeMillis();
      //System.out.println("date : "+dateajd);
      //System.out.println("adresse 2 : "+adresse2);

      //transformation de l'adresse2 en byteArray
      String[] sadresse = adresse2.split("\\.");
      byte[] badresse = {0,0,0,0};
      for (int i = 0; i<4; i++ ) {
        //System.out.println("sadresse"+i+" = "+sadresse[i]);
        badresse[i] = (byte) Integer.parseInt(sadresse[i]);
      }
      //transformation du login en byteArray
      byte[] blogin = login.getBytes();

      //codage du datagram
      byte type = 0x01;
      //System.out.println("avant : "+DataBufferizer.bufferToString(datagram));
      DataBufferizer.writeByte(type,datagram,0);
      DataBufferizer.writeLong(dateajd,datagram,1);
      DataBufferizer.writeByteArray(badresse,datagram,9,4);
      DataBufferizer.writeShort((short) port2,datagram,13);
      DataBufferizer.writeByte((byte) blogin.length,datagram,15);
      DataBufferizer.writeByteArray(blogin,datagram,16,blogin.length);
      //datagram complété
      System.out.println("datagram : "+DataBufferizer.bufferToString(datagram));

      //création du datagram packet
      InetAddress iadresse = InetAddress.getByName(adresse1);
      DatagramPacket dp = new DatagramPacket(datagram, datagram.length, iadresse, port1);

      //on envoie les données
      //DatagramSocket server = new DatagramSocket(port1,iadresse);
      DatagramSocket server = new DatagramSocket();
      server.send(dp);

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException ex){
      System.out.println("erreur de connexion "+ex);
    }
    catch (NumberFormatException e) {
      usage();
    }//catch

  }//main
}//class
