import java.net.*;
import java.util.*;
import java.io.*;

public class DateServerAutre {
  public static void main(String[] args) throws Exception{
    if (args.length != 1) {
      System.out.println("Usage : java DataServer <port>");
      System.exit(-1);
    }
    int port = Integer.parseInt(args[0]);
    ServerSocket serverSocket=new ServerSocket(port);
    Date date=new Date();
    String sdate=date.toString();

    while (true) {
      Socket socket=serverSocket.accept();
      OutputStream output=socket.getOutputStream();
      PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
      out.println(date);
    }
  }
}
