import com.google.gson.*; 
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonApplication {
 
 public static void main (String [] args) {

   // write a java object to a json file:
   Dummy dummy = new Dummy(3, "Mohsen", "Iranian");
   //dummy.friends.add("majid");
   //dummy.friends.add("mmd");
   //dummy.friends.add("reza");
//
   //dummy.map.put(1,"Mango");  //Put elements in Map  
   //dummy.map.put(2,"Apple");    
   //dummy.map.put(3,"Banana");   
   //dummy.map.put(4,"Grapes");   

   BackendDatabase db = new BackendDatabase("./database.json");
   //User e = new User();
   Ticket t = new Ticket();
   //db.users.add(e);
   //db.loggedUsers.add(e);
   //db.tickets.add(t);
   //dummy.friends1.add(e);
   //writeJson(db, db.baseAddress);
   writeJson(dummy, "/tmp/somewhere.json");
   // turn a string into a jsonobject
   JsonObject jobj =  StringToJsonObject("{\"1\": \"Mango\"}");
   System.out.println(jobj.get("1"));
   // read a json file to a java object:
   Gson gson = new GsonBuilder().setPrettyPrinting().create();
   try {
      Dummy d2 = gson.fromJson(new FileReader("/tmp/somewhere.json"), Dummy.class); 
      System.out.println(d2.id);
   } catch (IOException io) {System.out.println("IO exception:" + io);};
 }
 
 public static void writeJson(Object obj, String fileAddress) { 
   try {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    FileWriter writer = new FileWriter(fileAddress);
    gson.toJson(obj, writer);
    writer.flush(); 
    writer.close();
   } catch (IOException io) {System.out.println("IO exception:" + io);};
 }  
 public static JsonObject StringToJsonObject (String jsonLine ) {
  JsonElement jelement = new JsonParser().parse(jsonLine);
  JsonObject  jobject = jelement.getAsJsonObject();
  return jobject;
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
    private String nationality;
    ArrayList<String> friends = new ArrayList<String>();
    ArrayList<User> friends1 = new ArrayList<User>();
    HashMap<Integer,String> map=new HashMap<Integer,String>();//Creating HashMap    
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<User> loggedUsers = new ArrayList<User>();
    public ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    public Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public Dummy(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }
    
    public Dummy(int id, String name) {
        this(id, name, null);
    }
}