package SmartBuyCR.Pages;

import com.microsoft.playwright.Page;

public class PageCoordinator {
    private HomePage homepage; 
    private LoginPage loginpage; 
    private InfiniaPage infiniapage;

    public PageCoordinator(Page page) {
        this.homepage = new HomePage(page);
        this.loginpage = new LoginPage(page);
        this.infiniapage = new InfiniaPage(page);
    }

    // Method to return the HomePage instance
    public HomePage getHomePage() {
        return homepage;
    }

    // Method to return the LoginPage instance (if needed)
    public LoginPage getLoginPage() {
        return loginpage;
    }
    
 // Method to return the InfiniaPage instance (if needed)
    public InfiniaPage getInfiniaPage() {
        return infiniapage;
    }
}
