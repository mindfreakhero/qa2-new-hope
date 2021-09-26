package pageobject.tickets;

import model.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;
import pageobject.delfi.pages.HomePage;
import pageobject.tickets.pages.MainReservationPage;
import pageobject.tickets.pages.SecondReservationPage;
import pageobject.tickets.pages.SuccessPage;

import java.math.BigDecimal;
import java.util.List;

public class TicketsTest {
    private final String URL = "http://qaguru.lv:8089/tickets/";
    Reservation given = new Reservation();
    BaseFunc baseFunc = new BaseFunc();

    @BeforeEach
    public void preData() {

        given.setAfrom("RIX");
        given.setAto("SVO");
        given.setName("random");
        given.setSurname("Vi");
        given.setDiscount("CCCCCC");
        given.setAdults(3);
        given.setChildren(2);
        given.setBugs(1);
        given.setFullDate("17-05-2018");
        given.setSeat(22);
    }

    @Test
    public void checkReservation() {

        baseFunc.openPage(URL);
        MainReservationPage mainReservationPage = new MainReservationPage(baseFunc);

         mainReservationPage.selectAirports(given.getAfrom(), given.getAto())
                 .checkIfAirportsAre(given.getAfrom(), given.getAto())
                 .submitPassengerInfo(given)
                 .checkIfNameIs(given.getName())
                 .checkIfTotalPriceIs(new BigDecimal(2690))
                 .clickOnBook()
                 .selectSeat(given.getSeat())
                 .checkIfSeatIs(given.getSeat())
                 .clickOnFinalBook()
                 .checkIfMessageIs();

       // secondReservationPage.clickOnPrice();


    }

    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}
