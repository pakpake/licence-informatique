import java.io.*;
import java.net.*;

/**
 * Interface that defines methods to be used by TCP clients and servers to
 * open/close a connection.
 *
 * @author 
 */
public interface ITransport {

    // ---------------------------------------------------------------
    /**
     * Open client socket, input and output streams connected to the server at
     * the given remote host and given port. The socket, input and output
     * streams are defined as attributes of this object.
     */
    public void openClient(String host, int port) throws IOException;

    // ---------------------------------------------------------------
    /**
     * Open input and output streams connected to the given socket. The socket,
     * input and output streams are defined as attributes of this object.
     */
    public void open(Socket socket) throws IOException;

    // ---------------------------------------------------------------
    /**
     * Close all opened resources: socket, input and output streams
     */
    public void close() throws IOException;

}

