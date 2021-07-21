package pageobject.tickets.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class MainReservationPage {

    private final By DEPARTURE_DROPDOWN  = By.id("afrom");
    private final By ARRIVAL_DROPDOWN = By.id("bfrom");
    private final By GO_BUTTON = By.className("gogogo");

    private BaseFunc baseFunc;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public MainReservationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public SecondReservationPage selectAirports(String departure, String arrival) {
        LOGGER.info("Selection Airports");
        baseFunc.selectFromDropDown(DEPARTURE_DROPDOWN, departure);
        baseFunc.selectFromDropDown(ARRIVAL_DROPDOWN, arrival);
        baseFunc.click(GO_BUTTON);
        return new SecondReservationPage(baseFunc);
    }

}
