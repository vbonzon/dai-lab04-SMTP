/*
 * File : Group.java
 * Authors : Valentin Bonzon & Guillaume Dunant
 * Description : Class group that represent a sender and a group of receivers
 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Group {
    private String mailObject;
    private String sender;
    private ArrayList<String> receivers;
    private String mailContent;
    

    private static final String REGEX_EMAIL = "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$"; 
    private Pattern regex = Pattern.compile(REGEX_EMAIL);

    public Group(ArrayList<String> emailsList,String mailObject, String mailContent){
        
        if(emailsList.size() >= 2){
            for(String email : emailsList){
                if(!checkEmail(email)){
                    throw new RuntimeException("The address : " + email + " is not a correct email address");
                }
            }
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

    /*
     * Get the mail's sender
     */
    public String sender(){
        return sender;
    }

    /*
     * Get the mail's receivers
     */
    public ArrayList<String> receivers(){
        return receivers;
    }

    /*
     * Get the mail object
     */
    public String mailObject(){
        return mailObject;
    }

    /*
     * Get the mail content
     */
    public String mailContent(){
        return mailContent;
    }

    /**
     * 
     * @param email
     * @return return true if the parameter is an email address
     */
    private Boolean checkEmail(String email){
        
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
        
    }

}
