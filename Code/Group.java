/*
 * File : Group.java
 * Authors : Valentin Bonzon & Guillaume Dunant
 * Description : Class group that represent a sender and a group of receivers
 */
import java.util.ArrayList;
public class Group {
    private String mailObject;
    private String sender;
    private ArrayList<String> receivers;
    private String mailContent;
    
    public Group(ArrayList<String> emailsList,String mailObject, String mailContent){
        if(emailsList.size() >= 2){
            sender = emailsList.get(0);
            receivers = new ArrayList<>(emailsList);
            receivers.remove(0);
        }
        
        setMail(mailObject, mailContent);
    }

    /**
     * Set the mail's object and content 
     * @param mailObject
     * @param mailContent
     */
    public void setMail(String mailObject, String mailContent){
        this.mailObject = mailObject; this.mailContent = mailContent;
    }

    public String sender(){
        return sender;
    }

    public ArrayList<String> receivers(){
        return receivers;
    }

    public String mailObject(){
        return mailObject;
    }
    public String mailContent(){
        return mailContent;
    }

}
