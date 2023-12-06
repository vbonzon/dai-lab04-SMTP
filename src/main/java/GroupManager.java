/*
 * File : GroupManager.java
 * Authors : Valentin Bonzon & Guillaume Dunant
 * Description : From a email and content list, makes a list of Group
 */



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;


public class GroupManager {

    ArrayList<String> emailsList;
    ArrayList<String> contentList;
    int nbrGroup;
    
    
    public GroupManager(int nbrGroup){
        try {
            
            emailsList = getArrayFromFile("./config/victimes.txt");
            contentList = getArrayFromFile("./config/jokes.txt");


            this.nbrGroup = nbrGroup;
            if(nbrGroup == 0){
                throw new RuntimeException("Number of groups is not specified");
            }
           
        } catch (Exception e) {
            System.out.println("Error while creating the groupManager : " + e.getMessage());
        }
        
    }

    /**
     * Return an arraylist of group
     * @return
     */
    public ArrayList<Group> getGroups(){
        ArrayList<Group> listGroup = new ArrayList<>();
        try {
            
            if(emailsList == null || emailsList.size() == 0){
                throw new RuntimeException("The email list is empty");
            }
            if(contentList == null || contentList.size() == 0){
                throw new RuntimeException("The jokes list is empty");
            }
            if(contentList.size()/2 < nbrGroup){
                throw new RuntimeException("Not enough content for " + nbrGroup + " groups");
            }
            if(emailsList.size()/2 < nbrGroup){
                throw new RuntimeException("Not enough email addresses for " + nbrGroup + " groups");
            }
            for(int g = 0; g < nbrGroup; g++){
                
                ArrayList<String> emails = new ArrayList<>();
                
                int numMail = 5;
                while(nbrGroup * numMail > emailsList.size() && numMail >= 2){
                    numMail--;
                }
                for(int index = numMail*g; index < numMail*(g+1); index++){
                    emails.add(emailsList.get(index));
                }

                listGroup.add(new Group(emails, contentList.get(g*2), contentList.get(g*2+1)));
                
            }
        } catch (Exception e) {
            System.out.println("Error while creating the groups : " + e.getMessage());
            return null;
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
