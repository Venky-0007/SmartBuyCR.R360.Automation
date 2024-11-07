package SmartBuyCR.Tests;

import org.testng.annotations.Test;
import SmartBuyCR.Pages.PageCoordinator;
import com.aventstack.extentreports.ExtentTest;
import SmartBuy.ExtentReport.ReportManager;

public class SampleTest extends BaseTest {
	
	private ExtentTest ReportLog;

    @Test
    public void Infinia_FlightRoundTripBooking() 
    {
        try 
        {
            PageCoordinator coordinator = new PageCoordinator(page);
            // Call the privilege method on HomePage
            coordinator.getHomePage().Privilege();
            coordinator.getLoginPage().InfiniaLogin();
            coordinator.getInfiniaPage().InfiniaRoundTripCalendarSameDay();
            coordinator.getInfiniaPage().RoundTripFlightBooking();
            
            ReportLog.info("Sample test cases executed successfully");
            ReportLog.pass("Test Launch URL test case executed successfully.");
        } 
        catch (AssertionError e) 
        {
            // Log the failure
        	ReportLog.fail("Test failed due to: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
    }
    
	@Test
    public void DinersClub_FlightRoundTripBooking()
    {
		try
		{
            PageCoordinator coordinator = new PageCoordinator(page);
            // Call the privilege method on HomePage
            coordinator.getHomePage().Privilege();
            coordinator.getLoginPage().DinersLogin();
		}
		catch (AssertionError e) 
        {
            // Log the failure
			ReportLog.fail("Test failed due to: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
		
    }
}
