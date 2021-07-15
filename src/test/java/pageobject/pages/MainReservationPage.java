package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class MainReservationPage {

    private final By DEPARTURE_DROPDOWN  = By.id("afrom");
    private final By ARRIVAL_DROPDOWN = By.id("bfrom");
    private final By GO_BUTTON = By.className("gogogo");

    private BaseFunc baseFunc;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public MainReservationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectFrom() {
        LOGGER.info("Selecting departure");
        baseFunc.selectFromDropDown(DEPARTURE_DROPDOWN, "RIX");

    }

    public void selectTo() {
        LOGGER.info("Selecting arrival");
        baseFunc.selectFromDropDown(ARRIVAL_DROPDOWN, "SFO");
    }

    public SecondReservationPage clickOnGo() {
        LOGGER.info("Clicking on GoGoGo!");
        baseFunc.click(GO_BUTTON);
        return new SecondReservationPage(baseFunc);
    }

}
