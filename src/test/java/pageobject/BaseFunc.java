package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class BaseFunc {
    // this.getClass = take this class;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    WebDriver driver;
    WebDriverWait wait;

    // constuctor
    public BaseFunc() {
        LOGGER.info("Starting web browser");
        System.setProperty("webdriver.chrome.driver", "c://Program Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        LOGGER.info("Opening home page: " + url);

        // if url is w/o http, add it;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }

    // wait for element to be clickable and click;
    public void click(By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement element) {
        LOGGER.info("Clicking on WebElement");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public List<WebElement> findElements(By locator) {
        LOGGER.info("Getting list of elements by: " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    public List<WebElement> findElements(WebElement parent, By child) {
        LOGGER.info("Getting all child elements");
        return parent.findElements(child);
    }

    public WebElement findElement(By locator) {
        LOGGER.info("Getting an element");
        return driver.findElement(locator);
    }

    public String getText(WebElement parent, By child) {
        LOGGER.info("Getting text for child element by locator");
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child)).getText();
    }

    public String getText(By locator) {
        LOGGER.info("Getting text by locator");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();

    }

    public String getText(WebElement we) {
        LOGGER.info("Getting text from WebElement");
        wait.until(ExpectedConditions.visibilityOf(we));
        String text = we.getText();
        return text;
    }

    public String getText(By parent, By child){
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child)).getText();
    }

    public int removeBracketsAndParseToInt(String number) {
        LOGGER.info("Removing brackets and parsing int");
        number = number.substring(number.lastIndexOf("(") + 1, number.lastIndexOf(")")); //(36) -> 36
        return Integer.parseInt(number);
    }

    public int removeBracketsAndParseToInt(WebElement we) {
        LOGGER.info("Removing brackets and parsing int");
        String number = getText(we);
        number = number.substring(number.lastIndexOf("(") + 1, number.lastIndexOf(")")); //(36) -> 36
        return Integer.parseInt(number);
    }

    public void closeBrowser() {
        LOGGER.info("Closing browser window");
        // if browser is presented, than we will close it;
        if (driver != null) {
            driver.quit();
        }
    }

    public void selectFromDropDown(By locator, String dropDownItem) {
        LOGGER.info("Selecting" + dropDownItem + " from dropdown menu");
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(dropDownItem);
    }

    public void inputValue(By locator, String text) {
        LOGGER.info("Inputting some value");
        WebElement value = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //clear the input;
        value.clear();
        value.sendKeys(text);
    }

    public void inputValue(By locator, int text){
        inputValue(locator, String.valueOf(text));

    }

}
