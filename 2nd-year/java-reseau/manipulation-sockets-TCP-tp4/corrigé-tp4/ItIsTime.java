import java.io.*;
import java.util.*;

/**
* Class that implements the IItIsTime interface
*
* @author 
*/
public class ItIsTime implements IItIsTime {

    /**
    * the server host name
    */
    private String host;

    /**
    * Transport instance used to send/receive requests/replies from/to the server
    */
    private LineTransport lineTransport;

    // ------------------------------------------------------------
    /**
    * Print an usage message and exit
    */
    private static void usage()
    {
        System.out.println("Usage: java ItIsTime host port tp key group filename");
        System.exit(-1);
    }

    // ------------------------------------------------------------
    /**
    * Application entry point
    */
    public static void main(String[] args) throws IOException
    {
        if (args.length != 6) {
            usage();
        }
        try {
            // read the program arguments
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String tp = args[2];
            String key = args[3];
            String grp = args[4];
            String filename = args[5];

            // create an ItIsTime instance
            ItIsTime itIsTime = new ItIsTime(host, port);

            // connect
            String login = itIsTime.connect(tp, grp, key);
            if (login != null) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Your login: " + login + ". Continue (yes/no) ? ");
                String rep = scanner.next();
                if (rep.equals("yes")) {

                    // send
                    int nbBytes = itIsTime.send(filename);
                    if (nbBytes >= 0) {
                        System.out.println(nbBytes + " byte(s) received by the server");

                        // close
                        itIsTime.close();
                    }
                } else {
                    System.out.println("Aborting...");
                }
            }
        } catch (NumberFormatException e) {
            usage();
        }
    }

    // ------------------------------------------------------------
    /**
    * Constructor. Initialize and open the LineTransport instance
    */
    public ItIsTime(String host, int port) throws IOException
    {
        this.host = host;
        this.lineTransport = new LineTransport();
        this.lineTransport.openClient(host, port);
    }

    // ------------------------------------------------------------
    /**
    * Send the message "10" + tp + grp + key and receive the server's reply.
    * The server's reply may be:
    * - "20" + login => return the login name
    * - "50", "51", "55" => print an error message and return null
    */
    public String connect(String tp, String grp, String key)
    {
        try {
            // send the request to the server
            String request = "10" + tp + grp + key;
            this.lineTransport.send(request);

            // receive the reply from the server
            String reply = this.lineTransport.receive();

            // extract the code from the reply
            String code = reply.substring(0,2);
            String login = null;
            switch (code) {
                case "20": // extract the login from the reply
                return reply.substring(2);
                case "50":
                System.out.println("identification refused - wrong id");
                break;
                case "51":
                System.out.println("identification refused - out of time");
                break;
                case "55":
                System.out.println("unexpected command");
                break;
                default:
                System.out.println("unexpected reply code: " + code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ------------------------------------------------------------
    /**
    * Send the message "12" and receive the server's reply:
    * - "52"  => print an error message and return -1
    * - "22" + nb => connect to this remote port, transmit the file and
    *   close. Send the message "13" and receive the server's reply:
    *    - "23" + size => return size
    *    - "53", "55" => print an error message and return -1
    */
    public int send(String filename)
    {
        try {
            // send the request to the server
            this.lineTransport.send("12");

            // receive the reply from the server
            String reply = this.lineTransport.receive();

            // extract the code from the reply
            int port = -1; // the port number for the file transfer
            String code = reply.substring(0,2);
            switch (code) {
                case "22": // get the port nummber
                port = Integer.parseInt(reply.substring(2));
                break;
                case "52":
                System.out.println("error while opening connection");
                break;
                default:
                System.out.println("unexpected reply code: " + code);
            }

            if (port < 0) {
                return -1;
            }

            // open a connection to the server with the received port
            DataTransport dataTransport = new DataTransport();
            dataTransport.openClient(this.host, port);

            // send the file to the server
            FileInputStream in = new FileInputStream(filename);
            int nbSent = dataTransport.send(in);
            dataTransport.close();

            if (nbSent < 0) {
                return -1;
            }

            // send the request to the server
            this.lineTransport.send("13");

            // receive the reply from the server
            reply = this.lineTransport.receive();

            // extract the code from the reply
            int nbRcvd = -1; // the number of bytes received by the server
            code = reply.substring(0,2);
            switch (code) {
                case "23": // get the size
                nbRcvd = Integer.parseInt(reply.substring(2));
                if (nbRcvd != nbSent) {
                    System.out.println("the number of bytes sent ("+nbSent+") doesn't match the number of bytes received ("+nbRcvd+")");
                }
                break;
                case "53":
                System.out.println("error while reading file");
                break;
                case "55":
                System.out.println("unexpected command");
                break;
                default:
                System.out.println("unexpected reply code: " + code);
            }
            return nbRcvd;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // ------------------------------------------------------------
    /**
    * Send the message "14" and receive the server's reply:
    * - "24" => return
    * - "55" => print an error message and return
    */
    public void close()
    {
        try {
            // send the request to the server
            this.lineTransport.send("14");

            // receive the reply from the server
            String reply = this.lineTransport.receive();

            // extract the code from the reply
            String code = reply.substring(0,2);
            switch (code) {
                case "24":
                System.out.println("connection closed");
                break;
                case "55":
                System.out.println("error while opening connection");
                break;
                default:
                System.out.println("unexpected reply code: " + code);
            }

            this.lineTransport.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
