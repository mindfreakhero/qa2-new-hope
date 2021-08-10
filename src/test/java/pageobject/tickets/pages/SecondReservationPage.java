package pageobject.tickets.pages;

import model.Reservation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.math.BigDecimal;
import java.util.List;

public class SecondReservationPage {

    private final By NAME = By.id("name");
    private final By SURNAME = By.id("surname");
    private final By DISCOUNT = By.id("discount");
    private final By HOW_MANY_TRAVELERS = By.id("adults");
    private final By HOW_MANY_CHILDREN = By.id("children");
    private final By LUGGAGE = By.id("bugs");
    private final By FLIGHT_DATE = By.id("flight");
    private final By BOOK_BTN = By.id("book2");
    private final By SEAT = By.xpath(".//div[@class = 'seat']");
    private final By FINAL_BOOK_BTN = By.id("book3");
    private final By INFO_TXT = By.xpath(".//span[@class = 'bTxt']");
    private final By RESPONSE_SUMMARY = By.xpath("//*[@id='response']/span[@class = 'bTxt']");
    private final By GET_PRICE_BTN = By.xpath(".//span[@onclick = 'setLang();']");
    private final By RESPONSE = By.id("response");
    private final By SEAT_NUMBER = By.xpath(".//div[@class = 'line']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public SecondReservationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void submitPassengerInfo(Reservation reservation) {
        LOGGER.info("Inputting a name");
        baseFunc.inputValue(NAME, reservation.getName());
        baseFunc.inputValue(SURNAME, reservation.getSurname());
        baseFunc.inputValue(DISCOUNT, reservation.getDiscount());
        baseFunc.inputValue(HOW_MANY_TRAVELERS, reservation.getAdults());
        baseFunc.inputValue(HOW_MANY_CHILDREN, reservation.getChildren());
        baseFunc.inputValue(LUGGAGE, reservation.getBugs());
        baseFunc.selectFromDropDown(FLIGHT_DATE, reservation.getFullDate());
    }


    public void clickOnBook() {
        LOGGER.info("Clicking on book");
        baseFunc.click(BOOK_BTN);
    }

    public void selectSeat(int nr) {
        LOGGER.info("Selecting a seat");
        // checking if there is required seat;
        WebElement seat = findSeat(nr);
        Assertions.assertNotNull(seat, "Cannot find seat with this number!");
        // if there is seat - click on it;
        baseFunc.click(seat);


    }

    private WebElement findSeat(int seat) {
        LOGGER.info("Selecting a seat");
        for (WebElement we : baseFunc.findElements(SEAT)) {
            if (Integer.parseInt(we.getText()) == seat) {
                return we;
            }
        }
        // if not element is find, return null;
        return null;
    }

    public SuccessPage clickOnFinalBook() {
        LOGGER.info("Clicking on book");
        baseFunc.click(FINAL_BOOK_BTN);
        return new SuccessPage(baseFunc);

    }

    public List<WebElement> getAirports() {
        return baseFunc.findElements(INFO_TXT);
    }

    public List<WebElement> getSummary() {
        LOGGER.info("Getting summary!");
        return baseFunc.findElements(RESPONSE_SUMMARY);
    }

    public void clickOnPrice() {
        LOGGER.info("Getting price");
        baseFunc.click(GET_PRICE_BTN);
    }

    public BigDecimal getPrice() {
        String fullText = baseFunc.getText(RESPONSE);
        String price = StringUtils.substringBetween(fullText, "for ", " EUR");
        return new BigDecimal(price);
    }

    public int getSeatNr() {
        return Integer.parseInt(StringUtils.getDigits(baseFunc.getText(SEAT_NUMBER)));
    }

}
