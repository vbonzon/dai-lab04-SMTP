package Code;

import java.net.Socket;

public class SmtpPranker{
    public static void main(String[] args){
        MailSender ms = new MailSender();
        ms.Run();
    }
}