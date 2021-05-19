import java.io.*;
import java.net.*;
import java.util.*;

/**
* A class used to test the DataTransport methods with an DataServer program
*
* @author 
*/
public class TestDataTransport
{
    private static void usage()
    {
        System.out.println("Usage: java TestDataTransport <host> <port> <filename>");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length != 3) {
            usage();
        }
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String filename = args[2];

            // open an input stream from the file
            FileInputStream in = new FileInputStream(filename);

            // open a connection with a server
            DataTransport transport = new DataTransport();
            transport.openClient(host, port);

            transport.send(in); // transfer the file to the server

            transport.close(); // close the connection to the server
            in.close(); // close the file

        } catch (NumberFormatException e) {
            usage();
        }
    }

}
