import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

public class MailSender {

    private final static String  SRV_ADDR = "localhost";
    private final static int     SRV_PORT = 1025;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private final static String  RGX_READ = "^\\d{3}\\s.*$";

    private BufferedReader in;
    private BufferedWriter out;

    public void Run(){

      //Connection to Server
      System.out.println(String.format("Attemping connection to %s:%d",SRV_ADDR,SRV_PORT));
      try (Socket socket = new Socket(SRV_ADDR, SRV_PORT)) {
         in = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
         out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING));
            
         System.out.println(getResponse());
         send("ehlo amogus.ch");

         System.out.println(getResponse());
         send("quit");
         System.out.println(getResponse());
      } 
      catch(Exception e){
         System.out.println(e.getMessage());
      }
      finally{
         System.out.println("Closing the app ...");
      }
    }

    private List<String> getResponse() throws IOException{
      //Regex
      Pattern pattern = Pattern.compile(RGX_READ);
      Matcher matcher;

      //Messages
      String line = "";
      LinkedList<String> messages = new LinkedList<>();

      //Get the messages from sender
      do{
         line = in.readLine();
         messages.add(line);
         matcher = pattern.matcher(line);

      }while(!matcher.find());

      return messages;
    }
    
    private void send(String s) throws IOException{
         out.write(s + "\n");
         out.flush();
         System.out.println("> " + s);
    }
}
