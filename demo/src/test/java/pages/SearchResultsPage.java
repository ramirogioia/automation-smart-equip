package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Waiter;

public class SearchResultsPage extends BasePage {

    private final By allButtonValidatorLocator = By.xpath("//div[@id='react-duckbar']//li/a[contains(normalize-space(.), 'All')]");
    private final By resultTitlesLocator = By.xpath("//li[@data-layout='organic']//article[@data-testid='result']//div[3]");
    private final By allRegionsMenuLocator = By.xpath("//a[@data-testid='region-filter-label' and text()='All regions']");
    private final By allRegionsMenuAlternativeLocator = By.xpath("//a[@data-testid='region-filter-label' and text()='Argentina']");
    private final By allRegionsMenuOptionsLocator = By.xpath("//div[@data-testid='dropdown-options']/div[2]/*");


    public SearchResultsPage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    @Override
    public void assertPage() {
        super.assertPage();
        WebElement allButton = waiter.waitForElementToBeClickable(driver, allButtonValidatorLocator);
        if (allButton == null || !allButton.isDisplayed()) {
            throw new AssertionError("Expected All button to be visible but it was not.");
        }
    }

    public void assertSearchTermInResults(String searchTerm) {
        List<WebElement> results = waiter.waitForElements(driver, resultTitlesLocator);
        
        if (results.isEmpty()) {
            throw new AssertionError("No results found for the search term: " + searchTerm);
        }
        
        for (WebElement result : results) {
            String title = result.getText().toLowerCase();
            if (!title.contains(searchTerm.toLowerCase())) {
                throw new AssertionError("The title '" + title + "' does not contain the search term '" + searchTerm + "'");
            }
        }
    }

    public void clickAndAssertRegionsDropdown(int expectedRowCount) {
        WebElement regionsMenu = waiter.waitForElementToBeClickable(driver, allRegionsMenuLocator);
        
        if (regionsMenu == null) {
            regionsMenu = waiter.waitForElementToBeClickable(driver, allRegionsMenuAlternativeLocator);
            if (regionsMenu == null) {
                throw new AssertionError("Regions menu is not detected from the current page.");
            }
        }

        regionsMenu.click();
        List<WebElement> allRegionsMenuOptions = waiter.waitForElementsToBeClickable(driver, allRegionsMenuOptionsLocator);

        if (allRegionsMenuOptions.size() < expectedRowCount){
            throw new AssertionError("The region menu options do not contain the required number of entries specified by parameters. Expected: " + expectedRowCount + "'.");
        }
    }
}
