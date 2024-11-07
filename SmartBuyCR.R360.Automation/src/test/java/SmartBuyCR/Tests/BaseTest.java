package SmartBuyCR.Tests;

import com.microsoft.playwright.*;

import SmartBuy.ExtentReport.ReportManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import SmartBuyCR.Utils.ConfigReader;
import SmartBuyCR.Utils.Constants;
import SmartBuyCR.Utils.Helpers;        
import SmartBuyCR.Utils.BrowserFactory; 
import com.aventstack.extentreports.ExtentTest;

import org.testng.annotations.Listeners;
import java.lang.reflect.Method;
import SmartBuyCR.Listeners.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

	protected ExtentTest ReportLog;
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected ConfigReader configReader;
    protected Helpers helpers;
    protected BrowserFactory browserFactory;
    
    
    @BeforeMethod
    public void setUp(Method method) {
//        try {
//        	// Start a new test and retrieve the current test instance
//            ReportManager.getInstance();
//        	String testName = method.getName();
//            ReportManager.startTest(testName); // Start the test
//            ReportLog = ReportManager.getTest(); // Get the current test instance
//
//            // Set the URL and browser type from the Constants class
//            String baseUrl = Constants.BASE_URL; // Example: "https://example.com"
//            String browserType = Constants.Chromiumbrowser; // Example: "chrome"
//
//            // Initialize ConfigReader to read properties from a configuration file
//            configReader = new ConfigReader();
//
//            // Initialize BrowserFactory, which handles the creation of Playwright and Browser instances
//            browserFactory = new BrowserFactory();
//
//            // Create a new instance of Playwright using BrowserFactory
//            playwright = browserFactory.getPlaywrightInstance();
//
//            // Retrieve the browser type directly if it's already a constant/variable
//            browser = browserFactory.getBrowserInstance(playwright, browserType);
//
//            // Create a new BrowserContext to isolate browser sessions (cookies, localStorage, etc. are kept separate)
//            context = browser.newContext();
//            
//           // Create a new browser context with a specific viewport size
//            context = browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(1800, 1080)); // Set to a standard full HD resolution
//
//            // Create a new Page (equivalent to a browser tab) to interact with
//            page = context.newPage();
//
//            // Navigate the newly created page to the base URL
//            page.navigate(baseUrl);
//            ReportLog.info("Setup completed, browser launched and navigated to: " + baseUrl);
//            
//
//        } 
    	try {
    	    // Start a new test and retrieve the current test instance
    	    ReportManager.getInstance();
    	    String testName = method.getName();
    	    ReportManager.startTest(testName); // Start the test
    	    ReportLog = ReportManager.getTest(); // Get the current test instance

    	    // Set the URL and browser type from the Constants class
    	    String baseUrl = Constants.BASE_URL; // Example: "https://example.com"
    	    String browserType = Constants.Chromiumbrowser; // Example: "chrome"

    	    // Initialize ConfigReader to read properties from a configuration file
    	    configReader = new ConfigReader();

    	    // Initialize BrowserFactory, which handles the creation of Playwright and Browser instances
    	    browserFactory = new BrowserFactory();

    	    // Create a new instance of Playwright using BrowserFactory
    	    playwright = browserFactory.getPlaywrightInstance();

    	    // Retrieve the browser type directly if it's already a constant/variable
    	    browser = browserFactory.getBrowserInstance(playwright, browserType);

    	    // Create a new browser context with a specific viewport size
    	    context = browser.newContext(new Browser.NewContextOptions()
    	        .setViewportSize(1366, 768)); // Set to a standard full HD resolution

    	    // Create a new Page (equivalent to a browser tab) to interact with
    	    page = context.newPage();

    	    // Navigate the newly created page to the base URL
    	    page.navigate(baseUrl);
    	    ReportLog.info("Setup completed, browser launched and navigated to: " + baseUrl);

    	}
          catch (Exception e) {
            // Log any unexpected exceptions
        	ReportLog.info("An unexpected error occurred: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the page and context before closing the browser and Playwright
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

        // Log the teardown process
        ReportLog.info("Teardown completed, browser and Playwright closed.");
        ReportManager.flush();
    }
}
