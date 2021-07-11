package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Reservation;
import requesters.ReservationsRequestor;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class TicketReservationsStepDefs {
    private List<Reservation> ReservationsList;

    @Given("we are requesting list of reservations")
    public void request_list() throws IOException {
        ReservationsRequestor requester = new ReservationsRequestor();
        ReservationsList = requester.getReservationsList();
    }


    @Then("we are printing list of reservations in console")
    public void print_reservations() {

        for (Reservation reservations : ReservationsList) {
            System.out.println(reservations.toString());
        }

    }
}

