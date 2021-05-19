import java.io.*;
import java.net.*;
import java.util.*;

/**
* A class used to test the LineTransport methods with an EchoServer program
*
* @author 
*/
public class TestLineTransport
{
    private static void usage()
    {
        System.out.println("Usage: java TestLineTransport <host> <port>");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length != 2) {
            usage();
        }
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);

            // open a reader for input from the standard input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // open a connection with a server
            LineTransport transport = new LineTransport();
            transport.openClient(host, port);

            String inputLine = reader.readLine(); // read a line from the standard input stream
            while (inputLine != null) {
                transport.send(inputLine); // send the line to the server
                String receivedLine = transport.receive(); // read the server reply
                System.out.println(receivedLine); // print the server reply to the standard output stream
                inputLine = reader.readLine(); // read the next line from the standard input stream
            }
            transport.close(); // close the connection to the server
        } catch (NumberFormatException e) {
            usage();
        }
    }

}
