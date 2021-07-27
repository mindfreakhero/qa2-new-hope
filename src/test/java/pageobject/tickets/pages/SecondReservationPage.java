package pageobject.tickets.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

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
    private final By SEAT_22_BTN = By.xpath(".//div[@onclick = 'seat(22)']");
    private final By FINAL_BOOK_BTN = By.id("book3");
    private final By AIRPORT_NAME = By.xpath(".//span[@class = 'bTxt']");
    private final By SUMMARY = By.xpath("//*[@id='response']/span[@class = 'bTxt']");
    private final By GET_PRICE_BTN = By.xpath(".//span[@onclick = 'setLang();']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public SecondReservationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void inputName(String name) {
        LOGGER.info("Inputting a name");
        baseFunc.inputValue(NAME, name);
    }

    public void inputSurname(String surname) {
        LOGGER.info("Inputting a surname");
        baseFunc.inputValue(SURNAME, surname);
    }

    public void inputDiscount(String discount) {
        LOGGER.info("Inputting a discount");
        baseFunc.inputValue(DISCOUNT, discount);
    }

    public void inputAdults(int adults) {
        LOGGER.info("Inputting an adults count");
        baseFunc.inputValue(HOW_MANY_TRAVELERS, String.valueOf(adults));
    }

    public void inputChildren(int children) {
        LOGGER.info("Inputting a children count");
        baseFunc.inputValue(HOW_MANY_CHILDREN,  String.valueOf(children));
    }

    public void inputLuggage(int bugs) {
        LOGGER.info("Inputting a luggage count");
        baseFunc.inputValue(LUGGAGE, String.valueOf(bugs));
    }

    public void selectFlightDate(String flight) {
        LOGGER.info("Selecting flight date!");
        baseFunc.selectFromDropDown(FLIGHT_DATE, flight);
    }

    public void clickOnBook() {
        LOGGER.info("Clicking on book");
        baseFunc.click(BOOK_BTN);
    }

    public void selectSeat() {
        LOGGER.info("Selecting a seat");
        baseFunc.click(SEAT_22_BTN);
    }

    public void clickOnFinalBook() {
        LOGGER.info("Clicking on book");
        baseFunc.click(FINAL_BOOK_BTN);

    }

    public List<WebElement> getAirports() {
        return baseFunc.findElements(AIRPORT_NAME);
    }

    public List<WebElement> getSummary(){
        LOGGER.info("Getting summary!");
    return baseFunc.findElements(SUMMARY);
    }

    public void clickOnPrice(){
        LOGGER.info("Getting price");
        baseFunc.click(GET_PRICE_BTN);
    }
}
