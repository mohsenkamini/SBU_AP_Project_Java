class Transaction {
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return this.Id;
    }

    public void setType(String type) {
        this.Id = type;
    }

    public String getType() {
        return this.type;
    }


    Date date;
    double price;
    String Id;
    String type;
}