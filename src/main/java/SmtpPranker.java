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

        if(args.length < 0){
            throw new RuntimeException("Not enough arguments : " + args.length);
        }
        GroupManager manager = new GroupManager(Integer.parseInt(args[0]));
   
        MailSender ms = new MailSender(manager.getGroups());
        ms.Run();
        
    }

}