import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTvNetTest {
    private final By LOCATOR_BY_ID = By.id("elementId");
    private final By LOCATOR_BY_NAME = By.name("elementName");
    private final By LOCATOR_BY_TAGNAME = By.tagName("nameOfTag");
    private final By LOCATOR_BY_CLASS = By.className("elementClassName");
    // Xpath: .// (dot = search in element not in window, * = search for any tag)
    // div[@class = 'full class attribute'] or [contains(@class, 'part of attribute')]
    // e.g. .//*[contains(@class, 'list-article__meta-info flex')]
    private final By LOCATOR_BY_XPATH = By.xpath (".//*[contains(@class, 'list-article__meta-info flex')]");

    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");

    @Test
    public void firstTest() {
        // set path to the saved webdriver
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        //open new browser window;
        WebDriver browserWindow = new ChromeDriver();
        // maximize the window;
        browserWindow.manage().window().maximize();
        //open tvnet link;
        browserWindow.get("http://tvnet.lv");
        // wait (checks if elements is shown in window, if no in 10 sec then fails);
        WebDriverWait wait = new WebDriverWait(browserWindow, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        //find and click Accept cookies button;
        // findElement returns one element (if more than one returns first, if no elements test fails);
        // findElements returns list (if more elements returns all, if no elements returns empty;)
        //method returns WebElement (pointer), wee need to store it in variable;
//        WebElement acceptBtn =  browserWindow.findElement(ACCEPT_COOKIES_BTN);
//        acceptBtn.click();
        //more convenient way:
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();


    }
}
