import java.io.*;
import java.net.*;

/**
 * Interface that defines methods to be used by TCP clients and servers to
 * exchange binary data.
 *
 * @author 
 */
public interface IDataTransport extends ITransport {

    // ---------------------------------------------------------------
    /**
     * Read data from the given input stream and send it to the remote socket,
     * using the output stream attribute of this object opened with the open
     * method. Returns the number of bytes sent to the remote host.
     */
    public int send(InputStream in) throws IOException;

    // ---------------------------------------------------------------
    /**
     * Read data from the remote socket, using the input stream attribute of
     * this object opened with the open method, and write it to the given
     * output stream. Returns the number of bytes received from the remote host.
     */
    public int receive(OutputStream out) throws IOException;

}

