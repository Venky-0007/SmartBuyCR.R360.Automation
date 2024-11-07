package SmartBuyCR.Pages;

import com.microsoft.playwright.Page;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends BasePage {

    public HomePage(Page page) {
        super(page);
        // Constructor implementation here if needed
    }

    public void Privilege() {
    	
    	// Wait for the Proceed link to be visible
        page.waitForSelector("text=Proceed");
        //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed")).click();
        page.locator("(//*[text()='Proceed'])[1]").click();
        ReportLog.info("Click on Proceed Button");
        
        // Wait for the Don't Allow button to be visible
        page.waitForSelector("text=Don't Allow");
        //page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Don't Allow")).click();
        page.locator("//*[@id='optInText']").click();
        ReportLog.info("Click on Don't Allow Button");
        
        // Wait for the Privileges link to be visible
        page.waitForSelector("text=Privileges");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privileges")).click();
        ReportLog.info("Select Privilege");
        
    }
    
    

}


