import java.util.ArrayList;
public class Group {
    String mailObject;
    String sender;
    ArrayList<String> receivers;
    String mailContent;
    //TODO ajouter setters pour l'objet et le contenu du mail
    public Group(ArrayList<String> emailsList){
        if(emailsList.size() >= 2){
            sender = emailsList.get(0);
            receivers = emailsList;
            receivers.remove(0);
        }
    }


    public void setMail(String mailObject, String mailContent){
        this.mailObject = mailObject; this.mailContent = mailContent;
    }

}
