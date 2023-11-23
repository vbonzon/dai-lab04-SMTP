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

        if(args.length < 4){
            throw new RuntimeException("Not enough arguments : " + args.length);
        }
        GroupManager manager = new GroupManager(args[0], args[1], Integer.parseInt(args[3]) , args[2]);
            
        

        System.out.println(manager.getGroups().size());

        MailSender ms = new MailSender(manager.getGroups());
        ms.Run();
        
    }

}