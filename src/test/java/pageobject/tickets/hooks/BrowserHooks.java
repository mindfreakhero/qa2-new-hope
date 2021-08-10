package pageobject.tickets.hooks;

import io.cucumber.java.After;
import pageobject.BaseFunc;

public class BrowserHooks {

    private BaseFunc baseFunc;

    public BrowserHooks(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    @After("@close-browser")
    public void close_browser() {
        baseFunc.closeBrowser();
//    }
    }
}
