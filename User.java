import sun.util.resources.LocaleData;

import java.util.*;
import java.time.*;

class User {
    String currentUser;

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    String username;
    String fullName;
    String phoneNumber;
    String nationalId;
    Double balance;
    String password;
    String email;
    Boolean isLoggedIn;
    Ticket[] bookedTickets;


    public int findUser(String user) {
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).equals(user))
                return i;
        return -1;
    }

    public void SignIn(String user, String pass1, String pass2) throws usernameAlreadyExistsException, passwordsDoNotMatchException {
        for (int i = 0; i < users.size(); i++)
            if (findUser(user) != -1)
                throw new usernameAlreadyExistsException();
        if (pass1.equals(pass2)) {
            users.add(user);
            passwords.add(pass1);
        } else {
            throw new passwordsDoNotMatchException();
        }

    }

    public void LogIn(String user, String pass) throws wrongPasswordException, userDoesNotExistException {
        boolean userExists = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                userExists = true;
                if (passwords.get(i).equals(pass)) {
                    currentUser = users.get(i);
                    isLoggedIn = true;
                    break;
                } else {
                    throw new wrongPasswordException();
                }
            }
        }

        if (!userExists) {
            throw new userDoesNotExistException();
        }

    }

    public void changePassword(String oldPass, String newPass1, String newPass2) throws passwordsDoNotMatchException {
        if (isLoggedIn) {
            int index = findUser(currentUser);
            if (oldPass.equals(passwords.get(index))) {
                if (newPass1.equals(newPass2)) {
                    passwords.set(index, newPass1);
                } else
                    throw new passwordsDoNotMatchException();
            } else throw new passwordsDoNotMatchException(); //?

        }
    }

    public void changeUsername(String newUser) {
        if (isLoggedIn) {
            int index = findUser(currentUser);
            users.set(index, newUser);
        }
    }

    public void changePhoneNumber(String newNum) {
        setPhoneNumber(newNum);
    }

    public void changeFullName(String newName) {
        setFullName(newName);
    }

    public void changeEmail(String newEmail) {
        setEmail(newEmail);
    }

    public void changenationalID(String newId) {
        setNationalId(newId);
    }

    public void addBalance(Double amount) {
        if (isLoggedIn) {
            balance += amount;
            Transaction t = new Transaction();
            Date d = new Date();
            LocalDateTime ldt = LocalDateTime.now();
            String dateTime = ldt.toString();
            int index = dateTime.indexOf('T');
            String date = dateTime.substring(0, index - 1);
            String time = dateTime.substring(index, dateTime.length() - 1);
            d.setDate(date);
            d.setTime(time);
            t.setDate(d);
            t.setId("1234"); //?
            t.setPrice(amount);
            t.setType("Charge increase");
            transactions.add(t);

        }


    }

    public void removeBalance(Double amount) {
        if (isLoggedIn) {
            balance -= amount;
            Transaction t = new Transaction();
            Date d = new Date();
            LocalDateTime ldt = LocalDateTime.now();
            String dateTime = ldt.toString();
            int index = dateTime.indexOf('T');
            String date = dateTime.substring(0, index - 1);
            String time = dateTime.substring(index, dateTime.length() - 1);
            d.setDate(date);
            d.setTime(time);
            t.setDate(d);
            t.setId("1234"); //?
            t.setPrice(amount);
            t.setType("Charge reduction");
            transactions.add(t);

        }


    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }

    public void setBookedTickets(Ticket[] bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public Ticket[] getBookedTickets() {
        return this.bookedTickets;
    }

    class wrongPasswordException extends Exception {
        wrongPasswordException() {

        }

        String msg = "Wrong Password";
    }

    class userDoesNotExistException extends Exception {

        userDoesNotExistException() {
            String msg = "The username is incorrect.";
        }

    }

    class usernameAlreadyExistsException extends Exception {
        usernameAlreadyExistsException() {
        }

        String msg = "This username already exists. please pick another one.";

    }

    class passwordsDoNotMatchException extends Exception {
        passwordsDoNotMatchException() {
        }

        String msg = "Passwords do not match. Please try again.";
    }


}
