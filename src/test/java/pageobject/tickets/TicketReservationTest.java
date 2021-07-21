package pageobject.tickets;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pageobject.BaseFunc;
import pageobject.tickets.pages.MainReservationPage;
import pageobject.tickets.pages.SecondReservationPage;

//
//public class TicketReservationTest {
//    private final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(this.getClass());
//
//    private BaseFunc baseFunc;
//
//    @Test
//    public void reservationCheck() {
//        LOGGER.info("This test is creating a reservation and checks if reservation was properly saved");
//
//        baseFunc = new BaseFunc();
//        baseFunc.openPage("http://qaguru.lv:8089/tickets/");
//
//        MainReservationPage mainReservationPage = new MainReservationPage(baseFunc);

//        mainReservationPage.selectFrom();
//        mainReservationPage.selectTo();

//        SecondReservationPage secondReservationPage = mainReservationPage.clickOnGo();
//        secondReservationPage.inputName();
//        secondReservationPage.inputSurname();
//        secondReservationPage.inputDiscount();
//        secondReservationPage.inputAdults();
//        secondReservationPage.inputChildren();
//        secondReservationPage.inputLuggage();
//
//        secondReservationPage.selectFlightDate();
//
//        secondReservationPage.clickOnBook();
//        secondReservationPage.selectSeat();
//        secondReservationPage.clickOnFinalBook();
//
//    }

//    @AfterEach
//    public void closeBrowser() {
//        baseFunc.closeBrowser();
//    }
//}
