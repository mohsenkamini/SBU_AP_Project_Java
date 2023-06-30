
import com.google.gson.*; 
import java.io.*;
import java.net.*;

public class JsonApplication {
 
 public static void main (String [] args) {

   // write a java object to a json file:
   Dummy dummy = new Dummy(2, "Mohsen", "Iranian");
   writeJson(dummy, "/tmp/somewhere.json");

   
 }
 
 public static void writeJson(Object obj, String fileAddress) { 
   try {
    Gson gson = new Gson();
    FileWriter writer = new FileWriter(fileAddress);
    gson.toJson(obj, writer);
    writer.flush();
    writer.close();
   } catch (IOException io) {System.out.println("IO exception:" + io);};
 }  
 
 /*private static Student readJSON() throws FileNotFoundException { 
    GsonBuilder builder = new GsonBuilder(); 
    Gson gson = builder.create(); 
    BufferedReader bufferedReader = new BufferedReader(
       new FileReader("student.json"));   
    
    Student student = gson.fromJson(bufferedReader, Student.class); 
    return student; 
 } */
}

class Dummy {
    private int id;
    private String name;
    private transient String nationality;

    public Dummy(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }
    
    public Dummy(int id, String name) {
        this(id, name, null);
    }
}