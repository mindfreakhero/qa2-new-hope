package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;
import pageobject.tickets.pages.MainReservationPage;
import pageobject.tickets.pages.SecondReservationPage;
import requesters.ReservationsRequestor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TicketReservationsStepDefs {
    private List<Reservation> ReservationsList;
    private Reservation given = new Reservation();
    private Reservation lastReservation;

    private BaseFunc baseFunc;
    private MainReservationPage mainReservationPage;
    private SecondReservationPage secondReservationPage;
    Map<String, String> info;

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

//    @Given("a new reservation with default data is created")
//    public void create_new_reservation() {
//        TicketReservationTest reservationTest = new TicketReservationTest();
//        reservationTest.reservationCheck();
//    }


    @Then("response contains the following:")
    public void response_contains(DataTable reservationList) {
        List<Map<String, String>> reservations = reservationList.asMaps();

        for (int i = 0; i < reservations.size(); i++) {
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

    @Given("flight from {string} to {string}")
    public void set_airports(String departure, String arrival) {
        given.setAfrom(departure);
        given.setAto(arrival);

    }

    @Given("passenger info:")
    public void set_info(Map<String, String> info) {
        given.setName(info.get("name"));
        given.setSurname(info.get("surname"));
        given.setDiscount(info.get("discount"));
        given.setAdults(Integer.parseInt(info.get("adults")));
        given.setChildren(Integer.parseInt(info.get("children")));
        given.setBugs(Integer.parseInt(info.get("luggage")));
        given.setFullDate(info.get("flight"));
    }

    @When("seat number is: {int}")
    public void set_seat_nr(int seatNumber) {
        given.setSeat(seatNumber);
    }

    @When("we are opening home page")
    public void open_home_page() {
        baseFunc = new BaseFunc();
        baseFunc.openPage("http://qaguru.lv:8089/tickets/");
        mainReservationPage = new MainReservationPage(baseFunc);
    }

    @When("selecting airports")
    public void selecting_airports() {
        secondReservationPage = mainReservationPage.selectAirports(given.getAfrom(), given.getAto());
    }

    @Then("airports are displayed on second page")
    public void check_airports() {
        List<WebElement> airports = secondReservationPage.getAirports();
        Assertions.assertEquals(given.getAfrom(), airports.get(0).getText(), "Wrong departure name!");
        Assertions.assertEquals(given.getAto(), airports.get(1).getText(), "Wrong arrival name");
    }

    @When("we are submitting passenger info")
    public void submit_passenger_info() {
        secondReservationPage.inputName(given.getName());
        secondReservationPage.inputSurname(given.getSurname());
        secondReservationPage.inputDiscount(given.getDiscount());
        secondReservationPage.inputAdults(given.getAdults());
        secondReservationPage.inputChildren(given.getChildren());
        secondReservationPage.inputLuggage(given.getBugs());
        secondReservationPage.selectFlightDate(given.getFullDate());
        secondReservationPage.clickOnPrice();

    }

    @Then("name appears in summary")
    public void check_name_in_summary() {
        List<WebElement> summary = secondReservationPage.getSummary();
        Assertions.assertEquals(given.getName(), summary.get(0).getText(), "Wrong name in summary");
    }
}

