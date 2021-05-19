import java.io.*;
import java.net.*;

/**
 * Interface that defines methods to be used by TCP clients and servers to
 * exchange textual lines.
 *
 * @author 
 */
public interface ILineTransport extends ITransport {

    // ---------------------------------------------------------------
    /**
     * Send the given line to the remote socket, using the output stream
     * attribute of this object opened with the open method.
     */
    public void send(String line) throws IOException;
		
    // ---------------------------------------------------------------
    /**
     * Return a line received from the remote socket, using the input stream
     * attribute of this object opened with the open method.
     */
    public String receive() throws IOException;

}

