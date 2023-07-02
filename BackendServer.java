import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.*;

public class BackendServer {

    static BackendDatabase db;
    

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
        Gson gson = new GsonBuilder().setLenient().create();
        try {
            BackendDatabase tmp = gson.fromJson(new FileReader("./database.json"), BackendDatabase.class); 
            BackendServer.db = tmp;
            System.out.println("Loaded initial db from: "+db.baseAddress);
         } catch (IOException io) {
            System.out.println("No initial DB. Creating a new one...");
            BackendServer.db =new BackendDatabase("./database.json");
        };
        server.startServer(8000);
    }


    public static synchronized APIResponse handleRequest(APIRequest req) {
        APIResponse result = new APIResponse();
        if (!db.isAuthenticated(req.username))
            // check authentication
            if (req.method.equals("POST") && req.route.equals("/user/login/"))
            {
                User user = db.getUserByUsername(req.username);
                JsonObject logInPayloadJson = req.payload.getAsJsonObject();
                String inputPassword = logInPayloadJson.get("password").toString();
                if (db.login(user.username, inputPassword)) {
                    String finalUser = new Gson().toJson(user, User.class);
                    result.payload = new Gson().fromJson(finalUser, JsonElement.class);
                    result.statusCode = 200;
                    result.message = "Successfully logged in!";
                    db.update();
                }
                else {
                    result.statusCode = 401;
                    result.message = "Wrong credentials";
                }
            } else {
                if (req.method.equals("POST") && req.route.equals("/user/signup/")) {
                    JsonObject signUpPayloadJson = req.payload.getAsJsonObject();
                    String signUpUsername = req.username;
                    String signUpPass = signUpPayloadJson.get("password").toString();
                    String signUpEmail = signUpPayloadJson.get("email").toString();
                    db.SignUp(signUpEmail, signUpUsername, signUpPass);
                    result.statusCode = 200;
                    result.message = "Account created successfully!";
                    db.update();
                } else {
                    result.statusCode = 401;
                    result.message = "Please login first!";
                }
            }
        else {
            // requested is authenticated. now handle it:
            //User user = db.loadUser(req.username);
            switch (req.method) {
                case "GET":
                    switch (req.route) {
                        case "/tickets/":
                            result.statusCode = 200;
                            result.message="Tickets fetched";
                            JsonObject payloadJson = req.payload.getAsJsonObject();
                            ArrayList<Ticket> desiredTickets = db.findTicket(payloadJson.get("startDate").toString());
                            String finalTickets = new Gson().toJson(desiredTickets, desiredTickets.getClass());
                            System.out.println(finalTickets);
                            result.payload = new Gson().fromJson(finalTickets, JsonElement.class);
                            break;
                        case "/user/tickets/":
                            result.statusCode = 200;
                            result.message="User Tickets fetched";
                            //System.out.println(req.payload.toString());
                            //JsonObject payloadJsonUserTickets = req.payload.getAsJsonObject();// no need for that in this req
                            ArrayList<Ticket> userTickets = db.userTickets(req.username);
                            String finalUserTickets = new Gson().toJson(userTickets, userTickets.getClass());
                            //System.out.println("user tickets");
                            result.payload = new Gson().fromJson(finalUserTickets, JsonElement.class);
                            break;
                        case "/user/profile/":
                            result.statusCode = 200;
                            result.message="User profile fetched";
                            // JsonObject payloadJsonProfile = req.payload.getAsJsonObject();// no need for that in this req
                            User user = db.profileDetails(req.username);
                            String finalUserProfile = new Gson().toJson(user, User.class);
                            result.payload = new Gson().fromJson(finalUserProfile, JsonElement.class);
                            System.out.println(finalUserProfile);
                            break;
                        case "/user/transactions/":
                            result.statusCode = 200;
                            result.message="User transactions fetched";
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
                    switch (req.route) {
                        case "/company/create/":
                            // JsonObject companyCreatePayloadJson = req.payload.getAsJsonObject();
                            System.out.println(req.payload.getAsJsonObject().toString());
                            Company newComp = new Gson().fromJson(req.payload.getAsJsonObject().toString(), Company.class);
                            db.addCompany(newComp);
                            result.statusCode = 200;
                            result.message="Company "+newComp.name + " created.";
                            break;
                        case "/user/changepassword":
                            JsonObject passwordPayloadJson = req.payload.getAsJsonObject();
                            String newPass1= String.valueOf(passwordPayloadJson.get("newPassword1"));
                            String newPass2= String.valueOf(passwordPayloadJson.get("newPassword2"));
                            String oldPass= String.valueOf(passwordPayloadJson.get("oldPassword"));
                            boolean res=db.passwordChange(req.username, oldPass,newPass1,newPass2);
                            if(res) {
                                result.statusCode = 200;
                                result.message = "Password changed!";
                            }
                            else {
                                result.statusCode=400;
                                result.message="Failed to change the password!";
                            }

                        case "/company/addticket/":
                            // JsonObject companyCreatePayloadJson = req.payload.getAsJsonObject();
                            System.out.println(req.payload.getAsJsonObject().toString());
                            Ticket newTicket = new Gson().fromJson(req.payload.getAsJsonObject().toString(), Ticket.class);
                            System.out.println(newTicket.destination);
                            db.addTicket(newTicket);
                            result.statusCode = 200;
                            result.message="Added ticket for company "+req.company + ".";
                            break;
                        default:
                            result.statusCode = 400;
                            result.message = "Bad request";
                        break;

                    }
                    db.update();
                    break;
                case "PUT":
                db.update();
                    break;
                case "DELETE":
                db.update();
                    break;
                case "/company/deleteticket/":
                    JsonObject deleteTicketPayloadJson = req.payload.getAsJsonObject();
                    String ticketID = deleteTicketPayloadJson.get("ticketID").toString();
                    boolean isDeleted = db.deleteTicket(Integer.parseInt(ticketID));
                    break;
                default:
                    result.statusCode = 400;
                    result.message = "Bad request";
                    break;
            }
        }
        return result;
    }
}
