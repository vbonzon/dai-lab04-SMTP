/*
 * File : SmtpPranker.java
 * Authors : Valentin Bonzon & Guillaume Dunant
 * Description : Main file of the SmtpPranker program
 */
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;



import java.net.Socket;

public class SmtpPranker{
    public static void main(String[] args){
        /*MailSender ms = new MailSender();
        ms.Run();*/
        try {
            GroupManager manager = new GroupManager("./Ressources/victimes.txt", "./Ressources/jokes.txt", 5, "This is not a prank");
        for(Group gr : manager.getGroups()){
            System.out.print("Sender: " + gr.sender() + " receivers: ");
            for(String s : gr.receivers()){
                System.out.print(s + ", ");
            }
            System.out.println("\nContent: " + gr.mailContent());
            
        }
        } catch (Exception e) {
            System.out.println("Error while creating groups : " + e.getMessage());
        }
        
    }

}