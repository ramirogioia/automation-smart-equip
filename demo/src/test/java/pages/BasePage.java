package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Waiter;

public class BasePage {
    protected WebDriver driver;
    protected Waiter waiter; 
    protected static final String REDUCED_BASE_URL = "duckduckgo.com/";
    protected static final String BASE_URL = "https://start.duckduckgo.com/";

    public BasePage(WebDriver driver, Waiter waiter) {
        this.driver = driver;
        this.waiter = waiter;
    }

    public void navigatePage() {
        driver.get(BASE_URL);
        
        // Wait for the page to load completely
        new WebDriverWait(driver, 10).until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void navigatePage(String url) {
        driver.get(url);
        
        // Wait for the page to load completely
        new WebDriverWait(driver, 10).until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public String getUrl() {
        return BASE_URL;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void assertPage() {
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains(REDUCED_BASE_URL)) {
            throw new AssertionError("Expected URL: " + BASE_URL + " but found: " + currentUrl);
        }
    }
}