package SmartBuyCR.Utils;

import com.microsoft.playwright.*;

public class BrowserFactory {

    // Method to create and return a Playwright instance
    public Playwright getPlaywrightInstance() {
        return Playwright.create(); // Creates a new instance of Playwright
    }

    // Method to get the browser instance based on the specified browser type
    public Browser getBrowserInstance(Playwright playwright, String browserType) {
        switch (browserType.toLowerCase()) {
            case "firefox":
                return playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "webkit":
                return playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "chromium":
            default:
                return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
    }
}
