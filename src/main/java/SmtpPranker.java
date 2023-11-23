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
        GroupManager manager;
        for(String s : args){
            System.out.print(s + ",");
        }
        System.out.println();
        try {
            if(args.length < 4){
                throw new RuntimeException("Not enough arguments : " + args.length);
            }
            manager = new GroupManager(args[0], args[1], Integer.parseInt(args[3]) , args[2]);
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