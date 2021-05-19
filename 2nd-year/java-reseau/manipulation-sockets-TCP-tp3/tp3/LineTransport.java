/*
@author: pakpake 
@title: tp3 - Classes Transport et LineTransport
*/
import java.io.*;
import java.net.*;
import java.util.*;

public class LineTransport extends Transport implements ILineTransport{
       public void send(String line) throws IOException{
         out.println(line);
       }
       public String receive() throws IOException{
         return in.readLine();
       }
}
