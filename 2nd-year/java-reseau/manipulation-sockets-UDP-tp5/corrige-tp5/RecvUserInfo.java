import java.io.*;
import java.net.*;
import java.util.*;


/**
* A program that receives an information packet having the format 1
*
* @author 
*/
public class RecvUserInfo
{

  /**
  * Print a usage message and exit
  */
  private static void usage()
  {
    System.out.println("Usage: java RecvUserInfo port");
    System.exit(-1);
  }

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

    RecvUserInfo recvUserInfo = new RecvUserInfo();
    recvUserInfo.receivePacket(port);
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
    System.out.println("\nRECV: " + DataBufferizer.bufferToString(buffer) + " at " + port);

    // get informations from the received datagram
    int offset = 0;
    byte type = buffer[offset];
    offset++;
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
  }
}
