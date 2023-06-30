
import com.google.gson.*; 
import java.io.*;
import java.net.*;

public class JsonApplication {
 
 public static void main (String [] args) {

   // write a java object to a json file:
   Dummy dummy = new Dummy(3, "Mohsen", "Iranian");
   writeJson(dummy, "/tmp/somewhere.json");

   // read a json file to a java object:
   Gson gson = new Gson();
   try {
      Dummy d2 = gson.fromJson(new FileReader("/tmp/somewhere.json"), Dummy.class); 
      System.out.println(d2.id);
   } catch (IOException io) {System.out.println("IO exception:" + io);};
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
/* 
 public static Object readJson(String fileAddress) { 
   Object object;
   try {
    Gson gson = new Gson();
	 object = gson.fromJson(new FileReader(fileAddress), Object.class); 
    } catch (IOException io) {System.out.println("IO exception:" + io);};

   return object;
 } */ 
 
 
}

class Dummy {
    int id;
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