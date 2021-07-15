package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Reservation;
import model.ReservationsResponse;
import org.junit.jupiter.api.Assertions;
import pageobject.pages.TicketReservationTest;
import requesters.ReservationsRequestor;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class TicketReservationsStepDefs {
    private List<Reservation> ReservationsList;
    private Reservation lastReservation;

    @Given("we are requesting list of reservations")
    public void request_list() throws IOException {
        ReservationsRequestor requester = new ReservationsRequestor();
        ReservationsList = requester.getReservationsList();
    }

    @And("last reservation is found")
    public void find_last_reservation() {
        lastReservation = ReservationsList.get(ReservationsList.size() - 1);

    }



    @Then("we are printing list of reservations in console")
    public void print_reservations() {

        for (Reservation reservations : ReservationsList) {
            System.out.println(reservations.toString());
        }

    }

    @Given("a new reservation with default data is created")
    public void create_new_reservation() {
        TicketReservationTest reservationTest = new TicketReservationTest();
        reservationTest.reservationCheck();
    }


    @Then("response contains the following:")
    public void response_contains(DataTable reservationList) {
        List<Map<String, String>> reservations = reservationList.asMaps();

        for(int i = 0; i < reservations.size(); i++) {
            Map<String, String> expectedResponse = reservations.get(i);

            Assertions.assertEquals(expectedResponse.get("name"), lastReservation.getName(), "Wrong name!");
            Assertions.assertEquals(expectedResponse.get("surname"), lastReservation.getSurname(), "Wrong surname!");
            Assertions.assertEquals(expectedResponse.get("afrom"), lastReservation.getAfrom(), "Wrong departure!");
            Assertions.assertEquals(expectedResponse.get("ato"), lastReservation.getAto(), "Wrong arrival!");
            Assertions.assertEquals(expectedResponse.get("bugs"), lastReservation.getBugs(), "Wrong bugs!");
            Assertions.assertEquals(expectedResponse.get("discount"), lastReservation.getDiscount(), "Wrong discount!");
            Assertions.assertEquals(expectedResponse.get("children"), lastReservation.getChildren(), "Wrong children count!");
            Assertions.assertEquals(expectedResponse.get("flight"), lastReservation.getFlight(), "Wrong flight date!");
            Assertions.assertEquals(expectedResponse.get("adults"), lastReservation.getAdults(), "Wrong adults count!");
            Assertions.assertEquals(expectedResponse.get("seat"), lastReservation.getSeat(), "Wrong seat!");
        }

    }
}

