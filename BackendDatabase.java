import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.*;

public class BackendDatabase {
    File db;
    BackendDatabase(File db) {
        this.db=db;
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    HashMap<Integer,String> userPasses=new HashMap<Integer,String>();

    public Boolean authenticate (String username, String password) {
        Boolean result=false;
        return result;
    }
    public Boolean isAuthenticated (String username) {
        Boolean result=false;
        return result;
    }
    public Ticket[] listAvailableTickets (Ticket desired) {
        // check created tickets and if the time and source and dest is okay
        // output the available tickets.
        Ticket[] result=new Ticket[1];
        return result;
    }
    public User loadUser (String username) {
        // load user info from json file and return User
        User result=new User result;
        return result;
    }
    public void storeUser (User user) {
        // store user info to json file 
    }

}
