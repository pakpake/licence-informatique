import java.io.*;
import java.net.*;

/**
* Class that implements the IDataTransport interface
*
* @author 
*/
public class Transport implements ITransport {

    /**
    * the socket connected to the remote host
    */
    private Socket socket;

    /**
    * the input stream to read binary data from the remote host (through the socket)
    */
    protected InputStream in;

    /**
    * the input stream to read lines of text from the remote host (through the socket)
    */
    protected BufferedReader reader;

    /**
    * the output stream to write binary data to the remote host (through the socket)
    */
    protected OutputStream out;

    /**
    * the output stream to write lines of text to the remote host (through the socket)
    */
    protected PrintStream writer;

    // ---------------------------------------------------------------
    /**
    * Open client socket, input and output streams connected to the server at
    * the given remote host and given port. The socket, input and output
    * streams are defined as attributes of this object.
    */
    public void openClient(String host, int port) throws IOException
    {
        Socket socket = new Socket(host, port);
        this.open(socket);
    }

    // ---------------------------------------------------------------
    /**
    * Open input and output streams connected to the given socket. The socket,
    * input and output streams are defined as attributes of this object.
    */
    public void open(Socket socket) throws IOException
    {
        this.socket = socket;
        this.in = socket.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(this.in));
        this.out = socket.getOutputStream();
        this.writer = new PrintStream(this.out, true);
    }

    // ---------------------------------------------------------------
    /**
    * Close all opened resources: socket, input and output streams
    */
    public void close() throws IOException
    {
        if (this.socket != null) {
            this.in.close();
            this.out.close();
            this.socket.close();
        }
    }

}
