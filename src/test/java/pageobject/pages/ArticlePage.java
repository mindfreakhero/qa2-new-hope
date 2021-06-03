package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {

    private final By TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    //getting title of article and trimming spaces;
    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(TITLE).trim();
    }

    // getting article w/o brackets and as int;
    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");
        if (baseFunc.findElements(COMMENTS).isEmpty()) {
            // if no comments in this article, method will stop;
            return 0;
        } else {
            String commentsCountToParse = baseFunc.getText(COMMENTS);
            // remove brackets from comments and parse String into int;
            return baseFunc.removeBracketsAndParseToInt(commentsCountToParse);
        }
    }

    //opening comments page;
    public CommentsPage openCommentsPage() {
        LOGGER.info("Opening article comments page");
        baseFunc.click(COMMENTS);
        return new CommentsPage(baseFunc);
    }
}
