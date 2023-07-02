class Ticket {
    int ID;
    Double price;
    String startDate;
    String endDate;

    String departureTime;
    String landingTime;
    String origin;
    String destination;
    int capacity;
    int booked;
    Vehicle vehicle;


    public void setDepartureTime(String s) {
        this.departureTime = s;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(String s) {
        this.landingTime = s;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public int getBooked() {
        return this.booked;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }


}