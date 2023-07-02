import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.*;

public class BackendServer {

    static BackendDatabase db = new BackendDatabase();
    Gson gson = new GsonBuilder().setLenient().create();

    /*private synchronized void handleClientRequest(Socket clientSocket) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            String content = sb.toString();
            String modified = content.substring(2, content.length());
            System.out.println(modified.charAt(modified.length() - 2));
            JsonElement jsonElement = JsonParser.parseString(modified);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            System.out.println( jsonObject.get("method"));
            //getting the request and handling it
            APIRequest req = new APIRequest();
            //APIResponse response = handleRequest(req);
          
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            clientSocket.close();
        }
    }*/

    public synchronized void startServer(int portNumber) {
        try {
            ServerSocket ss = new ServerSocket(portNumber);
            while (true) {
                Socket clientSocket = ss.accept();
                /*Thread clientThread = new Thread(() -> {
                    try {
                      System.out.println("enter thread");
                      handleClientRequest(clientSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });*/
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
        }

    }

    public static void main(String[] args) {
        BackendServer server = new BackendServer();
        server.startServer(8000);
    }


    public static synchronized APIResponse handleRequest(APIRequest req) {
        APIResponse result = new APIResponse();
        if (!db.isAuthenticated(req.username))
            // check authentication
//            if (req.method.equals("POST") && req.route.equals("/user/login/"))
//            {
//                db.login(req.username,req.payload.getAsJsonObject().get("password"));
//                if (db.isAuthenticated(req.username)) {
//                    result.statusCode = 200;
//                    result.message = "ورود موفق";
//                } else {
//                    result.statusCode = 401;
//                    result.message = "نام کاربری یا رمزعبور اشتباه است.";
//                }
//            } else {
//                if (req.method.equals("POST") && req.route.equals("/user/signup/")) {
//
//                    result.statusCode = 200;
//                    result.message = "حساب کاربری شما با موفقیت ساخته شد.";
//                } else {
//                    result.statusCode = 403;
//                    result.message = "لطفا ابتدا وارد حساب کاربری خود شوید.";
//                }
//            }
//        else {
//            // requested is authenticated. now handle it:
//            User user = db.loadUser(req.username);
            switch (req.method) {
                case "GET":
                    switch (req.route) {
                        case "/tickets/":
                            result.statusCode = 200;
                            JsonObject payloadJson = req.payload.getAsJsonObject();
                            ArrayList<Ticket> desiredTickets = db.findTicket(payloadJson.get("startDate").toString());
                            String finalTickets = new Gson().toJson(desiredTickets, desiredTickets.getClass());
                            System.out.println(finalTickets);
                            result.payload = new Gson().fromJson(finalTickets, JsonElement.class);
                            break;
                        case "/user/tickets/":
                            result.statusCode = 200;
                            //System.out.println(req.payload.toString());
                            //JsonObject payloadJsonUserTickets = req.payload.getAsJsonObject();// no need for that in this req
                            ArrayList<Ticket> userTickets = db.userTickets(req.username);
                            String finalUserTickets = new Gson().toJson(userTickets, userTickets.getClass());
                            //System.out.println("user tickets");
                            result.payload = new Gson().fromJson(finalUserTickets, JsonElement.class);
                            break;
                        case "/user/profile/":
                            result.statusCode = 200;
                            // JsonObject payloadJsonProfile = req.payload.getAsJsonObject();// no need for that in this req
                            User user = db.profileDetails(req.username);
                            String finalUserProfile = new Gson().toJson(user, User.class);
                            result.payload = new Gson().fromJson(finalUserProfile, JsonElement.class);
                            System.out.println(finalUserProfile);
                            break;
                        case "/user/transactions/":
                            result.statusCode = 200;
                            // JsonObject payloadJsonTransactions = req.payload.getAsJsonObject();  // no need for that in this req
                            ArrayList<Transaction> userTransactions = db.getUserTransaction(req.username);
                            String finalUserTransaction = new Gson().toJson(userTransactions, Transaction.class);
                            result.payload = new Gson().fromJson(finalUserTransaction, JsonElement.class);
                            System.out.println(finalUserTransaction);
                            break;
                        default:
                            result.statusCode = 400;
                            result.message = "Bad request";
                            break;
                    }
                    break;
                case "POST":
                case "/user/signup/":


                case "/user/login/":
                    User user=req.get
                    String password=req.username.
                    ArrayList<String> userLogInDetails=db.login(req.username, req.pa)

                    break;
                case "PUT":
                    break;
                case "DELETE":
                    break;
                default:
                    result.statusCode = 400;
                    result.message = "Bad request";
                    break;
            }
//        }
        return result;
    }
}