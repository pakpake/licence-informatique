import java.io.*;
import java.net.*;
import java.util.*;

/**
* RequestUserInfo interroge ProcessUserInfo pour obtenir les informations concernant un utilisateur.
Ce programme est similaire a SendUserInfo sauf pour la constitution du datagramme
*/
public class RequestUserInfo
{
  /**
  * Print a usage message and exit
  */
  private static void usage()
  {
    System.out.println("Usage: java RequestUserInfo address1 port1 address2 port2 login");
    System.exit(-1);
  }

  /**
  * Program entry point
  *
  * @param args address1 port1 address2 port2 login
  */
  public static void main(String[] args) throws IOException
  {

    if (args.length != 5) {
      usage();
    }

    Inet4Address address1 = null, address2 = null;
    short port1 = 0, port2 = 0;
    String login = null;
    byte dist = -1;
    try {
      address1 = (Inet4Address) Inet4Address.getByName(args[0]);
      port1 = Short.parseShort(args[1]);
      address2 = (Inet4Address) Inet4Address.getByName(args[2]);
      port2 = Short.parseShort(args[3]);
      login = args[4];
    } catch (Exception e) {
      e.printStackTrace();
      usage();
    }

    RequestUserInfo requestUserInfo = new RequestUserInfo();
    requestUserInfo.sendPacket(address1, port1, address2, port2, login);

    requestUserInfo.receivePacket(port2,login);
  }

  /**
  * Send a packet having the format 1 with the given informations
  *
  * @param address1 the receiver address
  * @param port1    the receiver port number
  * @param address2 the sender address to be included in the packet
  * @param port2    the sender port number to be included in the packet
  * @param login    the sender login to be included in the packet
  * @throws IOException if an error occurs while sending the packet
  */
  public void sendPacket(Inet4Address address1, short port1, Inet4Address address2, short port2, String login) throws IOException
  {
    // make the bytes array that contains the informations
    byte[] buffer = new byte[24];
    int offset = 0;
    buffer[offset] = (byte) 2; // type 0x02
    offset++;
    DataBufferizer.writeByteArray(address2.getAddress(), buffer, offset, 4); // address
    offset += 4;
    DataBufferizer.writeShort(port2, buffer, offset); // port
    offset += 2;
    byte[] loginBytes = login.getBytes();
    buffer[offset] = (byte) loginBytes.length; // n
    offset++;
    DataBufferizer.writeByteArray(loginBytes, buffer, offset, loginBytes.length); // login

    // make a datagram and send it to address1:port1
    DatagramSocket socket = new DatagramSocket();
    DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, address1, port1);
    socket.send(datagram);
    socket.close();
    System.out.println("\nSENT REQUEST: " + DataBufferizer.bufferToString(buffer) + " \nTO " + address1 + " : " + port1);
  }

  /**
  * Receive a packet having the format 1 and display the informations it contains
  *
  * @param port the receiver port number
  * @param login the user's login requested
  * @throws IOException if an error occurs while receiveing the packet
  */
  public void receivePacket(int port, String login) throws IOException
  {
    // receive a datagram from port
    byte[] buffer = new byte[32];
    DatagramSocket socket = new DatagramSocket(port);
    DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
    socket.receive(datagram);
    socket.close();
    System.out.println("\nRECV: " + DataBufferizer.bufferToString(buffer) + " at " + port);

    // get informations from the received datagram
    int offset = 0;
    byte type = buffer[offset];
    offset++;
    // on reçoit buffer si buffer necontient que des 0, alors l'utilisateur n'est pas conncecté
    // il suffit de regarder la variable type
    if (type==0) { System.out.println("\n USER : "+login+" NOT CONNECTED !"); } else {
      Date date = new Date(DataBufferizer.readLong(buffer, offset));
      offset += 8;
      InetAddress address = InetAddress.getByAddress(DataBufferizer.readByteArray(buffer, offset, 4));
      offset += 4;
      short port2 = DataBufferizer.readShort(buffer, offset);
      offset += 2;
      byte n = buffer[offset];
      offset++;
      login = new String(DataBufferizer.readByteArray(buffer, offset, n));

      // display informations
      System.out.println("Type:    " + buffer[0]);
      System.out.println("Date:    " + date);
      System.out.println("Address: " + address.toString().substring(1));
      System.out.println("Port:    " + port2);
      System.out.println("Login:   " + login);
    }
  }
}
