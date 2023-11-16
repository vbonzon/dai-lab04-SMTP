import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;



import java.net.Socket;

public class SmtpPranker{
    public static void main(String[] args){
        /*MailSender ms = new MailSender();
        ms.Run();*/
        GroupManager manager = new GroupManager("./Ressources/victimes.txt", "./Ressources/jokes.txt", 3, "This is not a prank");
        /*for(Group gr : manager.getGroups()){
            System.out.print("Sender: " + gr.sender() + " receivers: ");
            for(String s : gr.receivers()){
                System.out.print(s + ", ");
            }
            System.out.print(" object: " + gr.mailObject());
            System.out.print("\n");
        }*/
    }

}