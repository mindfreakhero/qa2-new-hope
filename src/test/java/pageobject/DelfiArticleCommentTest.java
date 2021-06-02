package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.pages.ArticlePage;
import pageobject.pages.BaseFunc;
import pageobject.pages.CommentsPage;
import pageobject.pages.HomePage;

import java.util.List;

// checks if article title/comment count in homepage and  article title/ comment count  in article page are equal;
public class DelfiArticleCommentTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private final int ARTICLE_ID = 5;

    private BaseFunc baseFunc;


    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking title and comments count in home page, article page and comments page.");

        // create basefunc copy which contains chromedriver path and new driver/ windows maximize;
        baseFunc = new BaseFunc();
        baseFunc.openPage("http://delfi.lv");

        // home page ispolzuet teku6ee okno (basefunc);
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();

        // search for 4th article and get its title;
        String homePageTitle = homePage.getTitle(ARTICLE_ID);
        // get this articles comments count;
        int homePageCommentsCount = homePage.getCommentsCount(ARTICLE_ID);

        // click at article (article page is opened) and perehodim na article page;
        ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();


        // assertion if equals (expected, actual, error message)
        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        CommentsPage commentsPage = articlePage.openCommentsPage();

        // click on comments;
//        wait.until(ExpectedConditions.elementToBeClickable(ARTICLE_PAGE_COMMENTS));
//        driver.findElement(ARTICLE_PAGE_COMMENTS).click();
//
//        // get article title from comments;
//        String commentsPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();
//
//        int commentsCount = 0;
//        // if registered/anonymus comments are present, calculate them; if not calculate each comment;
//        if (driver.findElements(REGISTERED_ANONYMUS_COMMENTS).isEmpty()) {
//
//            // how much comments are on the page;
//            commentsCount = driver.findElements(EACH_COMMENT).size();
//
//            // comments section has more replies section, where other comments are minimized;
//            // search for more replies element, remove brackets and rewrite comments count (comment from page + comment from more replies(for each iteration));
//            for (WebElement reply : driver.findElements(MORE_REPLIES)) {
//                commentsCount = commentsCount + removeBrackets(reply);
//            }
//
//        } else {
//            for (WebElement reply : driver.findElements(REGISTERED_ANONYMUS_COMMENTS)) {
//                commentsCount = commentsCount + removeBrackets(reply);
//            }
//        }


//        Assertions.assertEquals(homePageTitle, commentsPageTitle, "Wrong title!");
//        Assertions.assertEquals(homePageCommentsCount, commentsCount, "Wrong comments count!");

    }

    // annotation to initialize this method after each test;
    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}



