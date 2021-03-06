package model;

import org.apache.commons.lang3.RandomStringUtils;

public class Reservation {

    private long id;
    private String name;
    private String Surname;
    private String afrom;
    private String ato;
    private int bugs;
    private String discount;
    private int children;
    private String flight;
    private int adults;
    private int seat;
    private String fullDate;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //if we receive random, then insert random word;
        this.name = name.equals("random") ? RandomStringUtils.randomAlphabetic(10): name;
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

    public int getBugs() {
        return bugs;
    }

    public void setBugs(int bugs) {
        this.bugs = bugs;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.setFullDate(flight + "-05-2018");
        this.flight = flight;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }
}