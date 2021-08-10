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
import pageobject.tickets.pages.SuccessPage;
import requesters.ReservationsRequestor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TicketReservationsStepDefs {
    private List<Reservation> ReservationsList;
    private Reservation given = new Reservation();
    private Reservation lastReservation;
    private SuccessPage successPage;

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


    @Then("response contains the correct info")
    public void response_contains() {

        Assertions.assertEquals(given.getName(), lastReservation.getName(), "Name does not match!");
        Assertions.assertEquals(given.getSurname(), lastReservation.getSurname(), "Surname does not match!");
        Assertions.assertEquals(given.getDiscount(), lastReservation.getDiscount(), "Discount does not match!");
        Assertions.assertEquals(given.getAdults(), lastReservation.getAdults(), "Passenger count does not match!");
        Assertions.assertEquals(given.getChildren(), lastReservation.getChildren(), "Children count does not match!");
        Assertions.assertEquals(given.getBugs(), lastReservation.getBugs(), "Luggage count does not match!");
        Assertions.assertEquals(given.getFullDate(), lastReservation.getFullDate(), "Flight date does not match!");
        Assertions.assertEquals(given.getSeat(), lastReservation.getSeat(), "Seat number does not match!");
        Assertions.assertEquals(given.getAto(), lastReservation.getAto(), "Arrival airport does not match!");
        Assertions.assertEquals(given.getAfrom(), lastReservation.getAfrom(), "Departure airport does not match!");


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
        System.out.println("");
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
        secondReservationPage.submitPassengerInfo(given);
        secondReservationPage.clickOnPrice();
    }

    @Then("name appears in summary")
    public void check_name_in_summary() {
        List<WebElement> summary = secondReservationPage.getSummary();
        String actual = summary.get(0).getText();

        Assertions.assertEquals(given.getName(), actual.substring(0, actual.length() - 1), "Wrong name in summary");
    }

    @Then("price calculated: {} EUR")
    public void check_price(BigDecimal price) {
        Assertions.assertEquals(price, secondReservationPage.getPrice(), "Wrong price!");


    }

    @When("we are pressing book button")
    public void click_on_book_btn() {
        secondReservationPage.clickOnBook();
    }

    @Then("selecting seat number")
    public void select_seat_number() {
        secondReservationPage.selectSeat(given.getSeat());
    }

    @Then("seat number appears on page")
    public void check_seat_number() {
        Assertions.assertEquals(given.getSeat(), secondReservationPage.getSeatNr(), "Wrong seat number is shown!");
    }

    @When("we are booking flight")
    public void book_flight() {
        successPage = secondReservationPage.clickOnFinalBook();
    }

    @Then("success message appears")
    public void check_success_msg() {
        Assertions.assertEquals("Thank You for flying with us!", successPage.getSuccessMsg(), "Wrong success message!");
    }
}

