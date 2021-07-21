package pageobject.delfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;


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

    //take all article in a page and return article by id;
    public WebElement getArticleById(int id) {
        LOGGER.info("Getting article Nr." + (id + 1));
        // if we use assertions better to initialize "articles" before assertions;
        List<WebElement> articles = baseFunc.findElements(ARTICLE);

        //if list is not empty, everything is ok;
        Assertions.assertFalse(articles.isEmpty(), "There are no articles!");
        //if article count > searched article, everything is ok;
        Assertions.assertTrue(articles.size() > id, "Article amount is less than id");

        return articles.get(id);
    }

    public String getTitle(int id) {
        LOGGER.info("Getting articles + " + (id + 1) + " title");
        // WebElement article = getArticleById(id);
        // article = parent, TITLE = child;
        return baseFunc.getText(getArticleById(id), TITLE).trim();
    }

    public int getCommentsCount(int id) {
        LOGGER.info("Getting articles + " + (id + 1) + " comments count");
        if (baseFunc.findElements(getArticleById(id), COMMENTS).isEmpty()) {
            // if no comments in this article, method will stop;
            return 0;
        } else {
            String commentsCountToParse = baseFunc.getText(getArticleById(id), COMMENTS);
            // remove brackets from comments and parse String into int;
            return baseFunc.removeBracketsAndParseToInt(commentsCountToParse);
        }
    }

    // we are returning article page with basefunc only;
    public ArticlePage openArticle(int id) {
        LOGGER.info("Opening article Nr. " + (id + 1));
        baseFunc.click(getArticleById(id));
        return new ArticlePage(baseFunc);

    }

}
