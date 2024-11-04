package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Waiter;

public class HomePage extends BasePage {

    private final By searchBoxLocator = By.id("searchbox_input");
    private final By sloganTextLocator = By.className("minimal-homepage_taglineText__owJPH");
    private final By searchButtonLocator = By.cssSelector("button[aria-label='Search']");


    public HomePage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    @Override
    public void assertPage() {
        super.assertPage();
        WebElement slogan = waiter.waitForElementToBeClickable(driver, sloganTextLocator);
        if (!slogan.isDisplayed()) {
            throw new AssertionError("Expected the slogan to be visible but it was not.");
        }
    }

    public void searchFor(String searchTerm) {
        WebElement searchBoxElement = waiter.waitForElementToBeClickable(driver, searchBoxLocator);
        if (searchBoxElement != null) {
            searchBoxElement.sendKeys(searchTerm);
            
            WebElement searchButtonElement = waiter.waitForElementToBeClickable(driver, searchButtonLocator);
            if (searchButtonElement != null) {
                searchButtonElement.click();
            } else {
                throw new AssertionError("Expected the search button to be clickable but it was not.");
            }
        } else {
            throw new AssertionError("Expected the search box to be clickable but it was not.");
        }
    }
}