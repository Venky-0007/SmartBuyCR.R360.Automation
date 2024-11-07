package SmartBuyCR.Pages;

import SmartBuy.ExtentReport.ReportManager;
import SmartBuyCR.Utils.ConfigReader;
import SmartBuyCR.Utils.Constants;
import SmartBuyCR.Utils.Helpers;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Path;
import java.nio.file.Paths; 

public abstract class BasePage {

    protected Page page;  // Playwright page object
    protected ExtentTest ReportLog;
    protected ConfigReader Config;
    
    // Constructor
    public BasePage(Page page) {
        this.page = page;
     // Create a new test in the report
        //this.ReportLog = ReportManager.createTest(this.getClass().getSimpleName());
        this.ReportLog = ReportManager.getTest();
        this.Config = new ConfigReader();
    }
    
 

    // ====================== Common Page Methods ======================

    /**
     * Navigate to a specified URL
     * @param url - URL to navigate to
     */
    public void navigateToUrl(String url) {
        page.navigate(url);
    }

    /**
     * Get the current page title.
     * @return The page title as a String
     */
    public String getPageTitle() {
        return page.title();
    }

    /**
     * Get the current page URL.
     * @return The current URL as a String
     */
    public String getCurrentUrl() {
        return page.url();
    }

    /**
     * Click on an element.
     * @param selector - The CSS selector for the element
     */
    public void click(String selector) {
        Helpers.waitForElementVisibility(page, selector, Constants.DEFAULT_TIMEOUT);
        page.locator(selector).click();
    }

    /**
     * Type text into an input field.
     * @param selector - The CSS selector for the input element
     * @param text - The text to enter
     */
    public void typeText(String selector, String text) {
        Helpers.waitForElementVisibility(page, selector, Constants.DEFAULT_TIMEOUT);
        page.locator(selector).fill(text);
    }

    /**
     * Get the text of a specific element.
     * @param selector - The CSS selector of the element
     * @return The text content of the element
     */
    public String getElementText(String selector) {
        Helpers.waitForElementVisibility(page, selector, Constants.DEFAULT_TIMEOUT);
        return page.locator(selector).innerText();
    }

    /**
     * Check if an element is visible on the page.
     * @param selector - The CSS selector for the element
     * @return true if visible, false otherwise
     */
    public boolean isElementVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    /**
     * Select an option from a dropdown by visible text.
     * @param selector - The CSS selector for the dropdown
     * @param visibleText - The visible text of the option to select
     */
    public void selectDropdownByVisibleText(String selector, String visibleText) {
        Helpers.waitForElementVisibility(page, selector, Constants.DEFAULT_TIMEOUT);
        page.locator(selector).selectOption(new SelectOption().setLabel(visibleText));
    }

    /**
     * Check if a checkbox is selected.
     * @param selector - The CSS selector for the checkbox
     * @return true if selected, false otherwise
     */
    public boolean isCheckboxSelected(String selector) {
        Helpers.waitForElementVisibility(page, selector, Constants.DEFAULT_TIMEOUT);
        return page.locator(selector).isChecked();
    }

    /**
     * Toggle a checkbox (select/deselect).
     * @param selector - The CSS selector for the checkbox
     */
    public void toggleCheckbox(String selector) {
        if (!isCheckboxSelected(selector)) {
            click(selector);
        }
    }

    /**
     * Wait for an element to be clickable (attached to DOM).
     * @param selector - The CSS selector for the element
     */
    public void waitForElementToBeClickable(String selector) {
        Helpers.waitForElementToBeClickable(page, selector, Constants.DEFAULT_TIMEOUT);
    }

    /**
     * Wait for a specific text to be present in an element.
     * @param selector - The CSS selector of the element
     * @param text - The text to wait for
     */
    public void waitForTextInElement(String selector, String text) {
        Helpers.waitForTextInElement(page, selector, text, Constants.DEFAULT_TIMEOUT);
    }

    /**
     * Switch to an iframe by its CSS selector.
     * @param iframeSelector - The CSS selector for the iframe
     */
    
    public Frame switchToIframe(String frameNameOrIndex) {
        return page.frame(frameNameOrIndex);  // Return the Frame object
    }

    
    public void interactWithIframeElement(String frameNameOrIndex, String selector) {
        Frame iframe = switchToIframe(frameNameOrIndex);  // Get the Frame object
        iframe.locator(selector).click();  // Interact with an element inside the iframe
    }

    /**
     * Perform drag and drop.
     * @param sourceSelector - CSS selector of the element to drag
     * @param targetSelector - CSS selector of the target element
     */
    public void dragAndDrop(String sourceSelector, String targetSelector) {
        Locator source = page.locator(sourceSelector);
        Locator target = page.locator(targetSelector);
        source.dragTo(target);
    }

    /**
     * Scroll an element into view.
     * @param selector - The CSS selector of the element
     */
    public void scrollToElement(String selector) {
        Helpers.waitForElementVisibility(page, selector, Constants.DEFAULT_TIMEOUT);
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Take a screenshot of the current page.
     * @param path - The file path to save the screenshot
     */
    public void takeScreenshot(String path) {
    	// Convert the String path to a Path object using Paths.get()
        Path screenshotPath = Paths.get(path);
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));
    }

    /**
     * Handle JavaScript alerts, prompts, and confirmations.
     * @param action - Action to take ("accept" or "dismiss")
     */
    public void handleAlert(String action) {
        page.onDialog(dialog -> {
            if (action.equalsIgnoreCase("accept")) {
                dialog.accept();
            } else if (action.equalsIgnoreCase("dismiss")) {
                dialog.dismiss();
            }
        });
    }
}
