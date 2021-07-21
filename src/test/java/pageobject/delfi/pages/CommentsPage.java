package pageobject.delfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.util.List;

public class CommentsPage {

    private final By EACH_COMMENT = By.xpath(".//div[@class = 'comment']");
    private final By MORE_REPLIES = By.xpath(".//button[@class = 'btn-loadmore small']");
    private final By TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final By REGISTERED_ANONYMUS_COMMENTS = By.xpath(".//span[@class = 'type-cnt']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc) {

        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(TITLE).trim();
    }

    public int calculateEachComment() {
        LOGGER.info("Calculating each comment");
        int commentsCount = 0;
        commentsCount = baseFunc.findElements(EACH_COMMENT).size();
        for (WebElement reply : baseFunc.findElements(MORE_REPLIES)) {
            commentsCount = commentsCount + baseFunc.removeBracketsAndParseToInt(reply);
        }
        return commentsCount;
    }

    public int calculateRegisteredAndAnonymusComments() {
        LOGGER.info("Calculating registered and anonymus comments");
        int commentsCount = 0;
        for (WebElement comments : baseFunc.findElements(REGISTERED_ANONYMUS_COMMENTS)) {
            commentsCount = commentsCount + baseFunc.removeBracketsAndParseToInt(comments);
        }
        return commentsCount;
    }

    public List<WebElement> getRegisteredAndAnonymusComments() {
        LOGGER.info("Getting registered and anonymus comments");
        return baseFunc.findElements(REGISTERED_ANONYMUS_COMMENTS);
    }

}
