<<<<<<< HEAD
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class SmtpPranker{
    public static void main(String[] args){
        ArrayList<String> strArray= getArrayFromFile("..\\Ressources\\jokes.txt");
        if(strArray != null){
            for(String line : strArray){
                System.out.println(line);
            }
        }
        else{
            System.out.println("array is null");
        }
            
    }

    private static ArrayList<String> getArrayFromFile(String path){

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

    private ArrayList<String> readEmails(){
        return new ArrayList<>();
    }

    private ArrayList<String> readJokes(){
        return new ArrayList<>();
=======
package Code;

import java.net.Socket;

public class SmtpPranker{
    public static void main(String[] args){
        MailSender ms = new MailSender();
        ms.Run();
>>>>>>> 299a3e688ff9a4e117e057e069dc4e88b5ce14e9
    }
}