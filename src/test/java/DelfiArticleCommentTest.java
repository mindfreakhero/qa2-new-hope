import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.List;

public class DelfiArticleCommentTest {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");

    @Test
    public void titleAndCommentsCountCheck() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://delfi.lv");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));

        driver.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> titles = driver.findElements(HOME_PAGE_TITLE);
        for (int i = 0; i < titles.size(); i++) {
            if (!titles.get(i).getText().isEmpty()) { //!true = false, if not empty;
                System.out.println(i + ":" + titles.get(i).getText());
            }


        }
        // for - each. title - required list, WebElement we - where we put elements;
        for (WebElement we : titles) {
            //           if (!we.getText().isEmpty()) {
            //               System.out.println(we.getText());
            //          }else {
            //              System.out.println("-----------");
            //          }

            // условие ? true : false (else)
            System.out.println(we.getText().isEmpty() ? "---------" : we.getText());
            }
        }

    }

