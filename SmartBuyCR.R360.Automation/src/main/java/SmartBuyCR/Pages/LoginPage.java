package SmartBuyCR.Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class LoginPage extends BasePage {

	public LoginPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void InfiniaLogin() {
		String UserName=Config.LoginUsername;
		String UserPassword=Config.LoginPassword;
    	// Click on 'Infinia' button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Infinia")).click();
        ReportLog.info("Select Infinia");

        // Click on 'Proceed' link
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed")).click();
        ReportLog.info("Click on Proceed");

        // Click on 'Login/Sign up' link
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login/Sign up")).click();
        ReportLog.info("Click on Login/Sign Up");

        // Enter mobile number
        page.getByPlaceholder("Enter Your Mobile Number or").click();
        ReportLog.info("Click on Mobile number field");
        page.getByPlaceholder("Enter Your Mobile Number or").fill(UserName);
        ReportLog.info("Entered Mobile Number is : "+UserName);

        // Click continue
        page.locator("#sign-continue").click(); // Using page to access locator directly
        ReportLog.info("Click on Continue");

        // Enter password
        page.getByPlaceholder("Enter password").click();
        ReportLog.info("Click on Password Field");
        page.getByPlaceholder("Enter password").fill(UserPassword);
        ReportLog.info("Password Entered is : "+UserPassword);

        // Sign in
        page.locator("#signin-step2 button").filter(new Locator.FilterOptions().setHasText("Sign In")).click();
        ReportLog.info("Click on Signin Button");
        
        page.locator("//*[@alt='closeCookieConsent']").click();

	    // Wait for the page to load completely before proceeding
	    page.waitForLoadState(LoadState.NETWORKIDLE);
	    
    }
	
	public void DinersLogin() {
		String UserName=Config.LoginUsername;
		String UserPassword=Config.LoginPassword;
    	// Click on 'Diners Club' button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Diners Club")).click();
        ReportLog.info("Select Diners Club");

        // Click on 'Proceed' link
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed")).click();
        ReportLog.info("Click on Proceed");

        // Click on 'Login/Sign up' link
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login /Sign up")).click();
        ReportLog.info("Click on Login/Sign Up");

        // Enter mobile number
        page.getByPlaceholder("Enter Your Mobile Number or").click();
        ReportLog.info("Click on Mobile number field");
        page.getByPlaceholder("Enter Your Mobile Number or").fill(UserName);
        ReportLog.info("Entered Mobile Number is : "+UserName);

        // Click continue
        page.locator("#sign-continue").click(); // Using page to access locator directly
        ReportLog.info("Click on Continue");

        // Enter password
        page.getByPlaceholder("Enter password").click();
        ReportLog.info("Click on Password Field");
        page.getByPlaceholder("Enter password").fill(UserPassword);
        ReportLog.info("Password Entered is : "+UserPassword);

        // Sign in
        page.locator("#signin-step2 button").filter(new Locator.FilterOptions().setHasText("Sign In")).click();
        ReportLog.info("Click on Signin Button");
    }

}
