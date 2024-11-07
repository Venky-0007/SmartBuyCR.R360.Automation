package SmartBuyCR.Utils;

import org.testng.annotations.DataProvider;

public class TestData {

	 @DataProvider(name = "browserData")
	    public Object[][] browserData() {
	        return new Object[][]{
	            {1, "https://example1.com", "chrome"},    // Set 1
	            {2, "https://example2.com", "firefox"},   // Set 2
        };
    }
}

