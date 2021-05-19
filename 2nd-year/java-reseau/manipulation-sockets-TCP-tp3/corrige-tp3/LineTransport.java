import java.io.*;
import java.net.*;

/**
* Class that implements the ILineTransport interface
*
* @author 
*/
public class LineTransport extends Transport implements ILineTransport {

    // ---------------------------------------------------------------
    /**
    * Send the given line to the remote socket, using the output stream
    * attribute of this object opened with the open method.
    */
    public void send(String line) throws IOException
    {
        super.writer.println(line);
    }

    // ---------------------------------------------------------------
    /**
    * Return a line received from the remote socket, using the input stream
    * attribute of this object opened with the open method.
    */
    public String receive() throws IOException
    {
        return super.reader.readLine();
    }

}
