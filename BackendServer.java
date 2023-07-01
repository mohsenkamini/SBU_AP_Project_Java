import java.io.*;
import java.net.*;
import org.json.*;  
import com.google.gson.*;

public class BackendServer {
    BackendDatabase db;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main (String[] args){
        try {
            ServerSocket ss = new ServerSocket(8000);
            Socket s = ss.accept();     // establish connection
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            String line=null;
            while ( (line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            String content = sb.toString();
            String modified = content.substring(2, content.length());
            System.out.println(modified.charAt(modified.length()-2));
            
            JSONObject json = new JSONObject(modified);  
            String type = json.getString("type");
            System.out.println(type);
            ss.close(); 
        } catch (Exception a) {System.out.println(a);}
    }

    public APIResponse handleRequest (APIRequest req) {
        APIResponse result;
        if (!db.isAuthenticated(req.username))
        // check authentication 
            if (req.method.equals("POST") && req.route.equals("/user/login/"))
            {
                authenticate(req.username,req.payload.getAsJsonObject().get("password"));
                if (db.isAuthenticated(req.username)) {
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
            User user = db.loadUser(req.username);
            switch(req.method) {
                case "GET":
                    switch (req.route) {
                        case "/tickets/":
                            result.statusCode=200;
                            //result.payload=db.listAvailableTickets(req.payload.get("startDate"),req.payload.get("origin"),req.payload.get("origin"));
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
        return result;
    }
}