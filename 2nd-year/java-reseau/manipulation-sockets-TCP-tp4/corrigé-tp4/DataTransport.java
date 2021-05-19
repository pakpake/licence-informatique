import java.io.*;
import java.net.*;

/**
* Class that implements the IDataTransport interface
*
* @author 
*/
public class DataTransport extends Transport implements IDataTransport {

    // ---------------------------------------------------------------
    /**
    * Read data from the given input stream and send it to the remote socket,
    * using the output stream attribute of this object opened with the open
    * method. Returns the number of bytes sent to the remote host.
    */
    public int send(InputStream in) throws IOException
    {
        return this.transfer(in, this.out);
    }

    // ---------------------------------------------------------------
    /**
    * Read data from the remote socket, using the input stream attribute of
    * this object opened with the open method, and write it to the given
    * output stream. Returns the number of bytes received from the remote host.
    */
    public int receive(OutputStream out) throws IOException
    {
        return this.transfer(this.in, out);
    }

    /**
    * Transfer all data read from the given input stream to the given output
    * stream
    */
    private int transfer(InputStream in, OutputStream out) throws IOException
    {
        byte[] data = new byte[1024];
        int totalLength = 0;
        int l = in.read(data, 0, 1024);
        while (l >= 0) {
            totalLength += l;
            out.write(data, 0, l);
            l = in.read(data, 0, 1024);
        }
        return totalLength;
    }
}
