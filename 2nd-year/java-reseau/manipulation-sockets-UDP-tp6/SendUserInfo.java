import java.io.*;
import java.net.*;
import java.util.*;


public class SendUserInfo
{

    /**
     * Print a usage message and exit
     */
    private static void usage()
    {
        System.out.println("Usage: java SendUserInfo address1 port1 address2 port2 login");
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

        SendUserInfo sendUserInfo = new SendUserInfo();
        sendUserInfo.sendPacket(address1, port1, address2, port2, login);
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
        byte[] buffer = new byte[32];
        int offset = 0;
        buffer[offset] = (byte) 1; // type
        offset++;
        DataBufferizer.writeLong(System.currentTimeMillis(), buffer, offset); // date
        offset += 8;
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
        System.out.println("\nSENT: " + DataBufferizer.bufferToString(buffer) + " to " + address1 + ":" + port1);
    }
}
