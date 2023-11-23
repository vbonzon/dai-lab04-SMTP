/*
 * File : GroupManager.java
 * Authors : Valentin Bonzon & Guillaume Dunant
 * Description : From a email and content list, makes a list of Group
 */



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;


public class GroupManager {

    ArrayList<String> emailsList;
    ArrayList<String> jokesList;
    int nbrGroup;
    String mailObject;
    
    public GroupManager(String emailsPath, String jokesPath, int nbrGroup, String mailObject){
        emailsList = getArrayFromFile(emailsPath);
        jokesList = getArrayFromFile(jokesPath);
        this.nbrGroup = nbrGroup;
        if(nbrGroup == 0){
            throw new RuntimeException("Number of groups is not specified");
        }
        this.mailObject = mailObject;
        if(mailObject == null || mailObject == ""){
            throw new RuntimeException("Mail object is empty");
        }
    }

    /**
     * Return an arraylist of group
     * @return
     */
    public ArrayList<Group> getGroups(){

        ArrayList<Group> listGroup = new ArrayList<>();
        if(emailsList == null || emailsList.size() == 0){
            throw new RuntimeException("The email list is empty");
        }
        if(jokesList == null || jokesList.size() == 0){
            throw new RuntimeException("The jokes list is empty");
        }
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
    
    /**
     * Read a file and add each line in a arraylist
     * @param path
     * @return An arraylist of each line
     */
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
            System.out.println("Error while oppening a file : " + e.getMessage());
        }
        
        return null;
    }

}
