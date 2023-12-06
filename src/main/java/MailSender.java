import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MailSender {

    private final static String  SRV_ADDR = "localhost";
    private final static int     SRV_PORT = 1025;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private final static String  RGX_READ = "^\\d{3}\\s.*$";

    private BufferedReader in;
    private BufferedWriter out;

    private ArrayList<Group> groups;

    /**
     * Constructeur
     * @param arrayList Liste des groupes à qui envoyer les mails
     */
    public MailSender(ArrayList<Group> arrayList){
      this.groups = arrayList;
    }

    /**
     * Fonction principale, se connecte au serveur SMTP et envoie
     * les mails
     */
    public void Run(){

      //Connection to Server
      System.out.println(String.format("Attemping connection to %s:%d",SRV_ADDR,SRV_PORT));
      try (Socket socket = new Socket(SRV_ADDR, SRV_PORT)) {
         in = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
         out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING));
         
         System.out.println(getResponse());

         for(Group group : groups){
            //Hello
            sendEhlo();

            //Mail from
            sendMailFrom(group.sender());

            //Rcpt to
            sendRcptTo(group.receivers());

            //Data
            sendData(group);
         }

         //Quit
         sendQuit();
      } 
      catch(Exception e){
         System.out.println(e.getMessage());
      }
      finally{
         System.out.println("Closing the app ...");
      }
    }

    /**
     * Envoie le nom du domaine
     * @throws IOException
     */
    private void sendEhlo() throws IOException {
      sendAndGetResponse("ehlo amogus.ch");
    }

    /**
     * Envoie l'adresse de l'envoyeur
     * @param sender Adresse de l'envoyeur
     * @throws IOException
     */
    private void sendMailFrom(String sender) throws IOException {
      sendAndGetResponse("Mail from: <" + sender + ">");
    }

    /**
     * Envoie la liste d'adresses des destinataires
     * @param receivers liste des destinataires
     * @throws IOException
     */
    private void sendRcptTo(List<String> receivers) throws IOException {
      for(String receiver : receivers){
         sendAndGetResponse("rcpt to: <" + receiver + ">");
      }
    }

    /**
     * Envoie les en-têtes et le corp de l'email
     * @param group Group contant les informations à envoyer
     * @throws IOException
     */
    private void sendData(Group group) throws IOException{
         sendAndGetResponse("data");
         
         //From
         send("From: <" + group.sender() + ">");

         //To
         StringBuilder sb = new StringBuilder();
         sb.append("To: ");
         for(String receiver : group.receivers()){
            sb.append("<" + receiver + ">, ");
         }
         sb.deleteCharAt(sb.lastIndexOf(","));
         send(sb.toString());

         //Date
         send("Date: " + new Date());

         //Subject
         send("Subject: =?utf-8?Q?" + group.mailObject().replace(' ', '_') + "?=");

         //Content type
         send("Content-Type: text/plain; charset=utf-8");

         //Mail body
         send("\n" + group.mailContent().replace("\\n", "\r\n"));
         sendAndGetResponse("\r\n.\r");
    }

    /**
     * Envoie le message pour se déconnecter
     * @throws IOException
     */
    private void sendQuit() throws IOException{
      sendAndGetResponse("quit");
    }

    /**
     * Obtient les messages de réponse du serveurs
     * @return List<String> contenant les différents messages
     * @throws IOException
     */
    private List<String> getResponse() throws IOException{
      //Regex
      Pattern pattern = Pattern.compile(RGX_READ);
      Matcher matcher;

      //Messages
      String line = "";
      ArrayList<String> messages = new ArrayList<>();

      //Get the messages from sender
      do{
         line = in.readLine();
         messages.add(line);
         matcher = pattern.matcher(line);

      }while(!matcher.find());

      return messages;
    }
    
    /**
     * Envoie un message au serveur
     * @param s Message à envoyer
     * @throws IOException
     */
    private void send(String s) throws IOException{
         out.write(s + "\n");
         out.flush();
         System.out.println("> " + s);
    }

    /**
     * Envoie un message au serveur et attend sa réponse
     * @param s Message à envoyer
     * @throws IOException
     */
    private void sendAndGetResponse(String s) throws IOException{
      send(s);
      System.out.println(getResponse());
    }
}
