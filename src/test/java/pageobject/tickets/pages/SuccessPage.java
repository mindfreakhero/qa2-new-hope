package pageobject.tickets.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class SuccessPage {
    private  final String SUCCESS_MSG = "Thank You for flying with us!";
    private BaseFunc baseFunc;

    public SuccessPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    private final By SUCCESS_TXT = By.xpath(".//div[@class = 'finalTxt']");

    public String getSuccessMsg() {
        return baseFunc.getText(SUCCESS_TXT);
    }

    public SuccessPage checkIfMessageIs() {
        Assertions.assertEquals(SUCCESS_MSG, getSuccessMsg(), "Wrong success message!");
        return this;
    }
}
