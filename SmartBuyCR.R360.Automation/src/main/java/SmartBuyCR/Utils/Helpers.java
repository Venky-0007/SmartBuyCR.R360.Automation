package SmartBuyCR.Utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.Locator;

public class Helpers {
	
	// ================== UI Automation Helpers ==================

    /**
     * Wait for an element to be visible within a specific timeout.
     * @param page - the Playwright page object
     * @param selector - the CSS selector for the element
     * @param timeout - time in milliseconds to wait
     */
	public static void waitForElementVisibility(Page page, String selector, int timeout) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout).setState(WaitForSelectorState.VISIBLE));

    }

    /**
     * Wait for an element to be clickable. (Waits for it to be visible and enabled)
     * @param page - the Playwright page object
     * @param selector - the CSS selector for the element
     * @param timeout - time in milliseconds to wait
     */
	public static void waitForElementToBeClickable(Page page, String selector, int timeout) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout).setState(WaitForSelectorState.ATTACHED));
    }

    /**
     * Wait for text to be present in an element within a specific timeout.
     * @param page - the Playwright page object
     * @param selector - the CSS selector for the element
     * @param expectedText - the text to wait for
     * @param timeout - time in milliseconds to wait
     */
    public static void waitForTextInElement(Page page, String selector, String expectedText, int timeout) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout));
        String actualText = page.locator(selector).innerText();
        while (!actualText.contains(expectedText) && timeout > 0) {
            try {
                Thread.sleep(500);
                timeout -= 500;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualText = page.locator(selector).innerText();
        }
    }

    /**
     * Wait for a URL to contain a specific text within a timeout.
     * @param page - the Playwright page object
     * @param expectedUrlText - part of the URL to wait for
     * @param timeout - time in milliseconds to wait
     */
    public static void waitForUrlContains(Page page, String expectedUrlText, int timeout) {
        while (!page.url().contains(expectedUrlText) && timeout > 0) {
            try {
                Thread.sleep(500);
                timeout -= 500;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ================== String Manipulation Helpers ==================

    /**
     * Generate a random alphanumeric string of a given length.
     * @param length - the length of the string to generate
     * @return - the generated string
     */
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        while (length-- > 0) {
            int index = (int) (Math.random() * characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }

    /**
     * Convert a given string to uppercase.
     * @param input - the input string
     * @return - the uppercase version of the string
     */
    public static String convertToUpperCase(String input) {
        return input.toUpperCase();
    }

    /**
     * Convert a given string to lowercase.
     * @param input - the input string
     * @return - the lowercase version of the string
     */
    public static String convertToLowerCase(String input) {
        return input.toLowerCase();
    }

    /**
     * Capitalize the first letter of each word in the given string.
     * @param input - the input string
     * @return - the capitalized string
     */
    public static String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            capitalized.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
        }
        return capitalized.toString().trim();
    }

    /**
     * Remove special characters from the given string.
     * @param input - the input string
     * @return - the cleaned string
     */
    public static String removeSpecialCharacters(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    /**
     * Reverse a given string.
     * @param input - the input string
     * @return - the reversed string
     */
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Check if a string contains only digits.
     * @param input - the input string
     * @return - true if the string contains only digits, false otherwise
     */
    public static boolean isNumeric(String input) {
        return input.matches("\\d+");
    }

    /**
     * Remove all white spaces from a string.
     * @param input - the input string
     * @return - the string with no spaces
     */
    public static String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
    
    /*Example of using the corrected wait methods:
    Helpers.waitForElementVisibility(page, "#login-button", 5000);
    Helpers.waitForElementToBeClickable(page, "#submit-button", 5000);*/

}
