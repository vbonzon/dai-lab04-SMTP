/*
 * File : SmtpPranker.java
 * Authors : Valentin Bonzon & Guillaume Dunant
 * Description : Main file of the SmtpPranker program
 */

import java.util.List;

public class SmtpPranker{
    public static void main(String[] args){

        if(args.length == 0){
            System.out.println("You need to add an argument to tell the number of groups!");
            System.exit(1);
        }

        
        GroupManager manager = new GroupManager(Integer.parseInt(args[0]));
        List<Group> groups = manager.getGroups();

        if(groups != null){
            MailSender ms = new MailSender(manager.getGroups());
            ms.Run();
        }
    }
}