import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DeleteUserInfo envoie un datagramme au format 3 pour informer ProcessUserInfo du départ d’un utilisateur.
   Ce programme est similaire à SendUserInfo sauf dans la constitution du datagramme
 */
public class DeleteUserInfo
{

    /**
     * Print a usage message and exit
     */
    private static void usage()
    {
        System.out.println("Usage: java DeleteUserInfo address port login");
        System.exit(-1);
    }

    /**
     * Program entry point
     *
     * @param args address1 port1 address2 port2 login
     */
    public static void main(String[] args) throws IOException
    {

        if (args.length != 3) {
            usage();
        }

        Inet4Address address = null;
        short port = 0;
        String login = null;
        try {
            address = (Inet4Address) Inet4Address.getByName(args[0]);
            port = Short.parseShort(args[1]);
            login = args[2];
        } catch (Exception e) {
            e.printStackTrace();
            usage();
        }

        DeleteUserInfo deleteUserInfo = new DeleteUserInfo();
        deleteUserInfo.sendPacket(address, port, login);
    }

    /**
     * Send a packet having the format 1 with the given informations
     *
     * @param address the receiver address
     * @param port    the receiver port number
     * @param login    the sender login to be included in the packet
     * @throws IOException if an error occurs while sending the packet
     */
    public void sendPacket(Inet4Address address, short port, String login) throws IOException
    {
        // make the bytes array that contains the informations
        byte[] buffer = new byte[18];
        int offset = 0;
        buffer[offset] = (byte) 3; // type 0x03
        offset++;
        byte[] loginBytes = login.getBytes();
        buffer[offset] = (byte) loginBytes.length; // n
        offset++;
        DataBufferizer.writeByteArray(loginBytes, buffer, offset, loginBytes.length); // login

        // make a datagram and send it to address1:port1
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(datagram);
        socket.close();
        System.out.println("\nSENT TO LOGOUT: " + DataBufferizer.bufferToString(buffer) + " to " + address + ":" + port);
    }
}
