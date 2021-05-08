import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class homework {

    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLES = By.xpath(".//span[@class = 'list-article__headline']");
    private final By COMMENT_BTN = By.xpath(".//img[@src=\"/v5/img/icons/comment-v2.svg\"]");
    private final By TVNET_LOGO = By.xpath(".//img[@src=\"https://f.pmo.ee/logos/4133/7b1236dab95abca45083322781760e97.svg\"]");
    private final By TO_RUS_LANG = By.xpath(".//a[@class = 'menu-item' and text() = 'RUS']");
    private final By COMMENT_COUNT = By.xpath(".//.//span[@class = 'list-article__comment section-font-color']");


    public WebDriver openTvnetAndAcceptCookies() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");
        WebDriverWait wait = new WebDriverWait(browserWindow, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        return browserWindow;
    }

    @Test
    // open first article, open comment screen;
    public void firstTask() {
        // initialize browserWindow with all changes that have been made in previous method "openTvnetAndAcceptCookies"
        // because this task uses browserWindow too;
        WebDriver browserWindow = openTvnetAndAcceptCookies();
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        browserWindow.findElement(ARTICLES).click();
        browserWindow.findElement(COMMENT_BTN).click();
    }

    @Test
    //print in console first article text;
    public void secondTask() {
        WebDriver browserWindow = openTvnetAndAcceptCookies();
        WebElement articleTitle = browserWindow.findElement(ARTICLES);
        // search for element that contains text with comments count;
        WebElement comment = articleTitle.findElement(By.tagName("span"));
        // replace (title + comments count) comment count with empty space;
        String titleWithoutComment = articleTitle.getText().replace(comment.getText(), "");
        System.out.println(titleWithoutComment);
    }

    @Test
    public void thirdTask() {
        WebDriver browserWindow = openTvnetAndAcceptCookies();
        browserWindow.findElement(TO_RUS_LANG).click();
        browserWindow.findElement(ARTICLES).click();
        browserWindow.findElement(TVNET_LOGO).click();
    }


    @Test
    // print in console all articles text (w/o comments) in main page;
    public void fourthTask() {
        WebDriver browserWindow = openTvnetAndAcceptCookies();
        List<WebElement> articleTitles = browserWindow.findElements(ARTICLES);
        // iterate through list of article titles;
        for (int i = 0; i < articleTitles.size(); i++) {
            // find comment element in specific title;
            List<WebElement> comment = articleTitles.get(i).findElements(By.tagName("span"));
            // get specific article text;
            String titleText = articleTitles.get(i).getText();
            // iterate through comments (spans) in article;
            for (int f = 0; f < comment.size(); f++) {
                // get specific span text;
                String commentText = comment.get(f).getText();
                // take titletext, replace first spans text with empty string, save value in title text.
                // continue loop, take titletext (already w/o first spans text) and replace second span text with "";
                titleText = titleText.replace(commentText, "");
            }

            System.out.println(titleText);


        }
    }

    @Test
    // print in console all headlines text and comment amount;
    public void fifthTask() {
        WebDriver browserWindow = openTvnetAndAcceptCookies();
        List<WebElement> elementsList = browserWindow.findElements(ARTICLES);
        for (int i = 0; i < elementsList.size(); i++) {
            List<WebElement> commentCount = elementsList.get(i).findElements(COMMENT_COUNT);
            String elementText = elementsList.get(i).getText();
            if (commentCount.isEmpty()) {
                System.out.println("Article title: " + elementText);
                System.out.println("Comments: 0");
                System.out.println();
            } else {
                // create new string in element Text, which will contains only comment (chars between last "(" and last ")");
                String onlyCommentsFromArticle = elementText.substring(elementText.lastIndexOf("(") + 1, elementText.lastIndexOf(")"));
                String onlyArticleText = elementText.substring(0, elementText.lastIndexOf("("));
                int comments = Integer.parseInt(onlyCommentsFromArticle);
                System.out.println("Article title: " + onlyArticleText);
                System.out.println("Comments: " + comments);
                System.out.println();

            }

        }
    }
}
