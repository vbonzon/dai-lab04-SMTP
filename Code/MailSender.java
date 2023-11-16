//package Code;

import java.net.Socket;

public class MailSender {

    final static String SRV_ADDR = "localhost";
    final static int    SRV_PORT = 1025;


    public void Run(){
        System.out.println(String.format("Attemping connection to %s:%d",SRV_ADDR,SRV_PORT));
         try (Socket socket = new Socket(SRV_ADDR, SRV_PORT)) {
            

         } catch(Exception e){
            System.out.println(e.getMessage());
         }
    }
    
}
