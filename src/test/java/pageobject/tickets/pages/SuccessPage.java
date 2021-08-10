package pageobject.tickets.pages;

import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class SuccessPage {
    private BaseFunc baseFunc;

    public SuccessPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    private final By SUCCESS_MSG = By.xpath(".//div[@class = 'finalTxt']");

    public String getSuccessMsg() {
        return baseFunc.getText(SUCCESS_MSG);
    }
}
