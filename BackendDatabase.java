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
    public APIResponse handleRequest (APIRequest req) {
        APIResponse result;
        if (!isAuthenticated(req.username))
        // check authentication 
            if (req.method.equals("POST") && req.route.equals("/user/login/"))
            {
                authenticate(req.username,req.payload.password);
                if (isAuthenticated(req.username)) {
                    result.statusCode=200;
                    result.message="ورود موفق";
                }
                else {
                    result.statusCode=401;
                    result.message="نام کاربری یا رمزعبور اشتباه است.";
                }
            }
            else {
                if (req.method.equals("POST") && req.route.equals("/user/signup/")) {
                    
                    result.statusCode=200;
                    result.message="حساب کاربری شما با موفقیت ساخته شد.";
                }
                else {
                result.statusCode=403;
                result.message="لطفا ابتدا وارد حساب کاربری خود شوید.";
                }
            }
        else {
        // requested is authenticated. now handle it:
            User user = loadUser(req.username);
            switch(req.method) {
                case "GET":
                    switch (req.route) {
                        case "/tickets/":
                            result.statusCode=200;
                            result.payload=listAvailableTickets(req.payload);
                            break;
                        case "/user/tickets/":
                            result.statusCode=200;
                            result.payload=user.getTickets();
                            break;
                        case "/user/profile/":
                            result.statusCode=200;
                            result.payload=user;
                            break;
                        case "/user/transactions/":
                            result.statusCode=200;
                            result.payload=user.getTickets();
                            break;
                        default:
                            result.statusCode=400;
                            result.message="Bad request";
                            break;
                    }
                    break;
                case "POST":
                    break;
                case "PUT":
                    break;
                case "DELETE":
                    break;
                default:
                    result.statusCode=400;
                    result.message="Bad request";
                    break;
            }
        }
    }
    return result;

}
