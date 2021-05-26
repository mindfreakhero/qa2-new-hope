package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


public class HomePage {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE = By.tagName("article");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    // receive basefunc (with open browser window), svjazka page with basefunc;
    public HomePage(BaseFunc baseFunc) {
        // zapisivaem basefunc v teku6ij basefunc classa; 4tobi on bil dosputen v etom page;
        this.baseFunc = baseFunc;
    }

    public void acceptCookies() {
        LOGGER.info("Accepting cookies");
        baseFunc.click(ACCEPT_COOKIES_BTN);

    }

}
