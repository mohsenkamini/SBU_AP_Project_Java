import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class Company {

    String name;
    String image;
    public ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    HashMap<Ticket,Date> sales=new HashMap<Ticket,Date>();
    User admin;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getAdmin() {
        return this.admin;
    }

}