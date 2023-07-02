import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

public class BackendDatabase {
    public String baseAddress;
    //public Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<User> loggedUsers = new ArrayList<User>();
    public ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    public ArrayList<Company> companies = new ArrayList<Company>();

    //public HashMap<Integer, String> userPasses = new HashMap<Integer, String>();

    public BackendDatabase(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public void update() {
        // try {
        JsonApplication.writeJson(this, this.baseAddress);
        // } catch (Exception a) {System.out.println("clone exception:"+a.getMessage());};
    }

    void addCompany(Company newComp) {
        companies.add(newComp);
    }

    User profileDetails(String username) {
        User currUser = null;
        for (int i = 0; i < users.size(); i++)
            if (username.equals(users.get(i).username)) {
                currUser = users.get(i);
            }
        return currUser;

    }

    ArrayList<Transaction> getUserTransaction(String username) {
        ArrayList<Transaction> trs = null;
        for (int i = 0; i < users.size(); i++)
            if (username.equals(users.get(i).username)) {
                trs = users.get(i).transactions;
            }
        return trs;

    }

    User getUserByUsername(String username) {
        User finalUser = null;
        for (int i = 0; i < users.size(); i++)
            if (username.equals(users.get(i).username))
                finalUser = users.get(i);
        return finalUser;
    }

    ArrayList<Ticket> userTickets(String username) {
        ArrayList<Ticket> resTickets = new ArrayList<Ticket>();
        for (int i = 0; i < users.size(); i++)
            if (username.equals(users.get(i).username)) {
                resTickets = users.get(i).tickets;
            }
        return resTickets;
    }

    ArrayList<Ticket> findTicket(String startDate) {
        ArrayList<Ticket> availableTickets = new ArrayList<Ticket>();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            if (t.startDate.equals(startDate))
                availableTickets.add(t);
        }
        return availableTickets;
    }


    public void SignUp(String email, String username, String pass) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(pass);
        users.add(newUser);
    }

    public boolean passwordChange(String username, String oldPassword, String newPass1, String newPass2) {
        User user = getUserByUsername(username);
        if (oldPassword.equals(user.password)) {
            if (newPass1.equals(newPass2)) {
                user.setPassword(newPass1);
                return true;
            }
        }
        return false;
    }

    public Boolean login(String username, String password) {
        for (int i = 0; i < users.size(); i++)
            if (username.equals(users.get(i).username)) {
                if (password.equals(users.get(i).getPassword()))
                    //adding the user to the loggedUsers array list
                    loggedUsers.add(users.get(i));
                return true;
            }
        return false;
    }

    public boolean deleteTicket(int ticketID) {
        for (int i = 0; i < tickets.size(); i++)
            if (tickets.get(i).ID == ticketID) {
                tickets.remove(tickets.get(i));
                return true;
            }
        return false;
    }

    public Boolean isAuthenticated(String username) {
        for (int i = 0; i < loggedUsers.size(); i++)
            if (username.equals(loggedUsers.get(i).username))
                return true;
        return false;
    }

    public Ticket[] listAvailableTickets(Ticket desired) {
        // check created tickets and if the time and source and dest is okay
        // output the available tickets.
        Ticket[] result = new Ticket[1];
        return result;
    }

    public User loadUser(String username) {
        // load user info from json file and return User
        User result = new User();
        return result;
    }

    public void storeUser(User user) {
        // store user info to json file 
    }

}
