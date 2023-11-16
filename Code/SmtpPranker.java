import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;



import java.net.Socket;

public class SmtpPranker{
    public static void main(String[] args){
        MailSender ms = new MailSender();
        ms.Run();
        GroupManager manager = new GroupManager("./Ressources/victimes.txt", "./Ressources/jokes.txt", 2);
        manager.getGroups();
    }

}