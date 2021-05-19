/*
@author: pakpake 
@title: tp3 - Classes Transport et LineTransport
*/
import java.io.*;
import java.net.*;
import java.util.*;

public class Transport implements ITransport{
  protected BufferedReader in;
  protected Socket s;
  protected PrintWriter out;
  public void openClient(String host, int port) throws IOException{
    s = new Socket(host,port);
    open(s);
  }
  public void open(Socket socket) throws IOException{
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    out = new PrintWriter(s.getOutputStream(),true);
  }
  public void close() throws IOException{
    in.close();
    s.close();
    out.close();
  }
}
