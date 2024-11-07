package SmartBuyCR.Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class InfiniaPage extends BasePage {

	public InfiniaPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void InfiniaRoundTripCalendarSameDay()
	{
		// Get today's date
        LocalDate today = LocalDate.now();
        
        // Define the desired format
        DateTimeFormatter formatterdatemonth = DateTimeFormatter.ofPattern("EEEE, dd MMMM");
        DateTimeFormatter formatterdate = DateTimeFormatter.ofPattern("dd");
        
        // Format the date
        String formattedDateMonh = today.format(formatterdatemonth)+",";
        LocalDate tomorrow = LocalDate.now().plusDays(1);
		String returnformattedDateMonh = tomorrow.format(formatterdatemonth)+",";
        String formateDate =today.format(formatterdate);
        String returnformateDate =tomorrow.format(formatterdate);
		// Click on the 'Travel Earn & Burn Reward' link
	    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Travel Earn & Burn Reward")).click();
	    ReportLog.info("Select Travel Earn & Burn Reward Tab");
	    
	    page.getByLabel("Round Trip").locator("div").nth(2).click();
	    page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Round Trip")).locator("span").click();
	    ReportLog.info("Selected RounTrip");

	    // Click on the 'From' label and select the airport
	    page.getByLabel("FromDEL, Indira Gandhi Airport").click();
	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("From").setExact(true)).fill("New Delhi");
	    page.getByText("New Delhi, India Indira Gandhi Airport DEL").click();
	    ReportLog.info("Selected From is New Delhi");
	    
	    // Click on the destination 'To' and fill the destination
	    page.getByText("BOM, Chatrapati Shivaji").click();
	    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("To").setExact(true)).fill("Mumbai");
	    page.locator("//*[text()='Mumbai, India ']").click();
	    ReportLog.info("Selected To is Mumbai");

	    // Add the departure date
	    page.locator("(//*[@placeholder='Select Date'])[1]").click();
	    page.getByLabel(formattedDateMonh).getByText(formateDate).click();
	    ReportLog.info("Add Departure Date is : "+formattedDateMonh);

	    // Selecting the return date (same date)
	    page.getByLabel(returnformattedDateMonh).getByText(returnformateDate).click();
	    ReportLog.info("Return Date is : "+returnformattedDateMonh);

	    // Submit the form
	    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Submit")).click();
	    ReportLog.info("Click on Submit Button");
	}
	
	public void RoundTripFlightBooking()
	{
		//Click on Non Stop flights from the Filters tab
		page.locator("//*[text()='Non-Stop ']").click();
		ReportLog.info("Selected the Non Stop Flights");
		
		// Click on the first element with the class "partner_logo_div"
		page.locator("(//*[@id='accordionFlushOnward']//*[text()='+3 more '])[1]").click();
		ReportLog.info("Clicked on the first partner list expansion.");

		// Click on the element with the ID corresponding to the selected onward flight
		page.locator("(//*[text()='Select'])[1]").click();
		ReportLog.info("Clicked on the selected onward flight.");

		// Click on the first element within the flight listing content for return flights
		page.locator("(//*[@id='accordionFlushReturn']//*[text()='+3 more '])[1]").click();
		ReportLog.info("Clicked on the first partner logo in the return flight section.");

		for(int i=1; i<=3; i++)
		{
			if(page.locator("(//*[@id='accordionFlushReturn']//*[@class='button-style returnButtons return-partner-Goibibo'])["+i+"]").isEnabled())
			{
				// Click on the Goibibo image in the return flight section //(//*[@id='accordionFlushReturn']//*[text()='Select'])[3]
				page.locator("(//*[@id='accordionFlushReturn']//*[@class='button-style returnButtons return-partner-Goibibo'])["+i+"]").click();
				break;
			}
		}
		ReportLog.info("Selected the Return Flight.");

		// Click the "Book" button
		page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
		
		// Wait for the "Book" button to be visible and scroll into view
		Locator bookButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book"));
		bookButton.waitFor();  // This waits for the element to appear in the DOM
		bookButton.scrollIntoViewIfNeeded();  // Scrolls the element into view if needed

		// Click the "Book" button
		bookButton.click();
		ReportLog.info("Clicked the 'Book' button.");

		// Click the "Base Fare" button
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Base Fare")).click();
		ReportLog.info("Clicked the 'Base Fare' button.");

		// Click the "Taxes and Fees" section in the main area
		page.getByRole(AriaRole.MAIN).locator("section").getByText("Taxes and Fees").click();
		ReportLog.info("Clicked the 'Taxes and Fees' section.");

		// Click the "Discount" section in the main area
		page.getByRole(AriaRole.MAIN).locator("section").getByText("Discount").click();
		ReportLog.info("Clicked the 'Discount' section.");

		// Click the "Grand Total" heading
		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Grand Total")).click();
		ReportLog.info("Clicked the 'Grand Total' heading.");

		// Click the "Continue" link
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue")).click();
		ReportLog.info("Clicked the 'Continue' link.");

		// Click the "+ Add New Adult" link to add a new passenger
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("+ Add New Adult")).click();
		ReportLog.info("Clicked the '+ Add New Adult' link.");

		// Click the "SelectTitle *" dropdown
		page.getByText("SelectTitle *").click();
		ReportLog.info("Clicked the 'SelectTitle *' dropdown.");

		// Select "Mr" as the title
		page.getByText("Mr", new Page.GetByTextOptions().setExact(true)).click();
		ReportLog.info("Selected 'Mr' as the title.");

		// Click the "First & Middle Name *" label
		page.getByLabel("First & Middle Name *").click();
		ReportLog.info("Clicked the 'First & Middle Name *' label.");

		// Fill in the "First & Middle Name" field
		page.getByPlaceholder("First & Middle Name").fill("Testuser");
		ReportLog.info("Filled in the 'First & Middle Name' field.");

		// Click the "Last Name *" field and fill it in
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Last Name \\*$"))).nth(4).click();
		ReportLog.info("Clicked the 'Last Name *' field.");
		page.getByPlaceholder("Last Name").fill("TestUser");
		ReportLog.info("Filled in the 'Last Name' field.");

		// Click the "Date of Birth *" field and fill it in
		//page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Date of Birth \\*$"))).nth(4).click();
		ReportLog.info("Clicked the 'Date of Birth *' field.");
		page.getByPlaceholder("Date of Birth").fill("22/05/1995");
		ReportLog.info("Filled in the 'Date of Birth' field.");

		// Click and fill in the "Email *" field
		page.getByLabel("Email *").click();
		page.getByLabel("Email *").press("ArrowRight");
		page.getByLabel("Email *").press("Control+a");
		page.getByLabel("Email *").fill("venkaiah.k@email.co");
		ReportLog.info("Filled in the 'Email *' field.");

		// Click and fill in the "Mobile Number" field
		page.getByPlaceholder("Mobile Number").click();
		page.getByPlaceholder("Mobile Number").fill("7013192481");
		ReportLog.info("Filled in the 'Mobile Number' field.");

		// Click the "Continue" button
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
		ReportLog.info("Clicked the 'Continue' button.");

		// Click on the seat selection canvas at a specific position
		page.locator("#canvas_flights_0_O_0").click(new Locator.ClickOptions().setPosition(274, 578));
		ReportLog.info("Clicked on the seat selection canvas.");

		// Click the "Continue" link again
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue")).click();
		ReportLog.info("Clicked the 'Continue' link.");

		// Check the checkbox to accept the booking information and terms
		page.getByLabel("I have reviewed and accept the booking information and Terms & Conditions").check();
		ReportLog.info("Checked the 'Terms & Conditions' checkbox.");

		// Click the "Continue" link again
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue")).click();
		ReportLog.info("Clicked the 'Continue' link.");

		// Click the message indicating a failed itinerary creation and retry
		page.getByText("Failed to create itinearry Click on Retry").click();
		ReportLog.info("Clicked on the message indicating a failed itinerary creation.");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Retry")).click();
		ReportLog.info("Clicked the 'Retry' button.");


		}
}
