import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.random.*;

public class GroupManager {

    ArrayList<String> emailsList;
    ArrayList<String> jokesList;
    int nbrGroup;
    String mailObject;
    
    public GroupManager(String emailsPath, String jokesPath, int nbrGroup, String mailObject){
       emailsList = getArrayFromFile(emailsPath);
       jokesList = getArrayFromFile(jokesPath);
       this.nbrGroup = nbrGroup;
       this.mailObject = mailObject;
    }


    public ArrayList<Group> getGroups(){

        ArrayList<Group> listGroup = new ArrayList<>();
        for(int g = 0; g < nbrGroup; g++){
            //TODO mettre en paramètre les n premiers emails de la liste
            ArrayList<String> emails = new ArrayList<>();
            
            int numMail = 5;
            while(nbrGroup * numMail > emailsList.size() && numMail >= 2){
                numMail--;
            }
            for(int index = numMail*g; index < numMail*(g+1); index++){
                emails.add(emailsList.get(index));
            }
             
            listGroup.add(new Group(emails, mailObject, jokesList.get(g)));
            
        }

        return listGroup;
    }

    private ArrayList<String> getArrayFromFile(String path){

        try (var reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(path), StandardCharsets.UTF_8
            )
        )) {

            String line;
            ArrayList<String> strArray = new ArrayList<String>();
            while((line = reader.readLine()) != null){
                strArray.add(line);
            }

            return strArray;


            
        } catch (Exception e) {
            System.out.println("Error while oppening a file : " + e);
        }
        
        return null;

        
    }



}
