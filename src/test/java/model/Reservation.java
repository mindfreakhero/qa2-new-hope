package model;

public class Reservation {

    private String id;
    private String name;
    private String Surname;
    private String afrom;
    private String ato;
    private String bugs;
    private String discount;
    private String children;
    private String flight;
    private String adults;
    private String seat;

    @Override
    public String toString() {
        return "Reservation for client id " + id + ":" + '\n' +
                "Name: " + name + '\n' +
                "Surname: " + Surname + '\n' +
                "Departure: " + afrom + '\n' +
                "Arrival: " + ato + '\n' +
                "Bugs: " + bugs + '\n' +
                "Discount: " + discount + '\n' +
                "Children: " + children + '\n' +
                "Flight: " + flight + '\n' +
                "Adults: " + adults + '\n' +
                "Seat: " + seat + '\n';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getAfrom() {
        return afrom;
    }

    public void setAfrom(String afrom) {
        this.afrom = afrom;
    }

    public String getAto() {
        return ato;
    }

    public void setAto(String ato) {
        this.ato = ato;
    }

    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}