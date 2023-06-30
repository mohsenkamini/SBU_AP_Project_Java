import java.util.Map;

class Company {

    String name;
    String image;
    Ticket[] tickets;
    Map<Ticket, Date> sales;
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

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public Ticket[] getTickets() {
        return this.tickets;
    }

    public void setSales(Map<Ticket, Date> sales) {
        this.sales = sales;
    }

    public Map<Ticket, Date> getSales() {
        return this.sales;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getAdmin() {
        return this.admin;
    }

}