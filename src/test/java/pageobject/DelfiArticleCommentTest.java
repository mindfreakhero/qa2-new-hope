package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.pages.BaseFunc;
import pageobject.pages.HomePage;

import java.util.List;

// checks if article title/comment count in homepage and  article title/ comment count  in article page are equal;
public class DelfiArticleCommentTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    // perenosim driver v class 4tobi on bil dostupen dlja metodov;
    private WebDriver driver;


    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking title and comments count in home page, article page and comments page.");

        // create basefunc copy which contains chromedriver path and new driver/ windows maximize;
        BaseFunc baseFunc = new BaseFunc();
        baseFunc.openPage("http://delfi.lv");

        // home page ispolzuet teku6ee okno (basefunc);
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();

        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        // take 5th article;
        WebElement article = articles.get(0);

        // get text from article
        LOGGER.info("Getting article title and comments count");
        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText();
        // trim the spaces;
        homePageTitle = homePageTitle.trim();
        // get comments count as int
        int homePageCommentsCount = getCommentsCount(HOME_PAGE_COMMENTS, article);
        LOGGER.info("Title is: " + homePageTitle + " and comment count is " + homePageCommentsCount);


        // click at article (article page is opened);
        LOGGER.info("Opening article");
        article.findElement(HOME_PAGE_TITLE).click();

        // get text and comments as int w/o brackets;
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        articlePageTitle = articlePageTitle.trim();

        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);

        // assertion if equals (expected, actual, error message)
        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        // click on comments;
        wait.until(ExpectedConditions.elementToBeClickable(ARTICLE_PAGE_COMMENTS));
        driver.findElement(ARTICLE_PAGE_COMMENTS).click();

        // get article title from comments;
        String commentsPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();

        int commentsCount = 0;
        // if registered/anonymus comments are present, calculate them; if not calculate each comment;
        if (driver.findElements(REGISTERED_ANONYMUS_COMMENTS).isEmpty()) {

            // how much comments are on the page;
            commentsCount = driver.findElements(EACH_COMMENT).size();

            // comments section has more replies section, where other comments are minimized;
            // search for more replies element, remove brackets and rewrite comments count (comment from page + comment from more replies(for each iteration));
            for (WebElement reply : driver.findElements(MORE_REPLIES)) {
                commentsCount = commentsCount + removeBrackets(reply);
            }

        } else {
            for (WebElement reply : driver.findElements(REGISTERED_ANONYMUS_COMMENTS)) {
            commentsCount = commentsCount + removeBrackets(reply);
            }
        }


        Assertions.assertEquals(homePageTitle, commentsPageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, commentsCount, "Wrong comments count!");


        // ------ FOR CYCLE --------
//        for (int i = 0; i < titles.size(); i++) {
//            if (!titles.get(i).getText().isEmpty()) { //!true = false, if not empty;
//                System.out.println(i + ":" + titles.get(i).getText());
//            }
//
//
//        }
//        ------ FOR EACH. title - required list, WebElement we - where we put elements; ---------
//        for (WebElement we : titles) {
//            //           if (!we.getText().isEmpty()) {
//            //               System.out.println(we.getText());
//            //          }else {
//            //              System.out.println("-----------");
//            //          }
//
//            ----------- TERNARY условие ? true : false (else) -------------------
//            System.out.println(we.getText().isEmpty() ? "---------" : we.getText());
//            }
    }

    // search in driver (window) article comments, go into remove brackets method, return only comment count;
    private int getCommentsCount(By locator) {
        int commentsCount = 0;
        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
        }

        return commentsCount;
    }

    // search in webElement article comments, go into remove brackets method, returns only comment count;
    private int getCommentsCount(By locator, WebElement we) {
        int commentsCount = 0;
        if (!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));
        }
        return commentsCount;
    }

    // remove brackets from comments and parse String into int;
    private int removeBrackets(WebElement we) {
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(commentsCountText.lastIndexOf("(") + 1, commentsCountText.lastIndexOf(")"));// (36) -> 36
        return Integer.parseInt(commentsCountText); // 36 (String) -> 36 (int)

    }

    // annotation to initialize this method after each test;
    @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}



