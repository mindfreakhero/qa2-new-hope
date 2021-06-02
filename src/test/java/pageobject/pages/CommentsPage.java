package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

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

}
