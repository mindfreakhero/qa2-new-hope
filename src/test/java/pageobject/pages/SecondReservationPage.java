package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

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

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public SecondReservationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void inputName() {
        LOGGER.info("Inputting a name");
        baseFunc.inputValue(NAME, "Anna");
    }

    public void inputSurname() {
        LOGGER.info("Inputting a surname");
        baseFunc.inputValue(SURNAME, "Vi");
    }

    public void inputDiscount() {
        LOGGER.info("Inputting a discount");
        baseFunc.inputValue(DISCOUNT, "hello");
    }

    public void inputAdults() {
        LOGGER.info("Inputting an adults count");
        baseFunc.inputValue(HOW_MANY_TRAVELERS, "3");
    }

    public void inputChildren() {
        LOGGER.info("Inputting a children count");
        baseFunc.inputValue(HOW_MANY_CHILDREN, "0");
    }

    public void inputLuggage() {
        LOGGER.info("Inputting a luggage count");
        baseFunc.inputValue(LUGGAGE, "1");
    }

    public void selectFlightDate() {
        LOGGER.info("Selecting flight date!");
        baseFunc.selectFromDropDown(FLIGHT_DATE, "17-05-2018");
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
}
