package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    // Define with the client when a time is considered a timeout for reporting behaviors/bugs.
    private static final int DEFAULT_TIMEOUT = 5;

    public WebElement waitForElement(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return null; 
        }
    }

    public List<WebElement> waitForElements(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            return List.of();
        }
    }

    public WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            return null; 
        }
    }

    public List<WebElement> waitForElementsToBeClickable(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            return List.of();
        }
    }

    // Implementing overloading to also be able to specify a custom timeout if necessary.
    public WebElement waitForElement(WebDriver driver, By locator) {
        return waitForElement(driver, locator, DEFAULT_TIMEOUT);
    }

    public List<WebElement> waitForElements(WebDriver driver, By locator) {
        return waitForElements(driver, locator, DEFAULT_TIMEOUT);
    }

    public WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return waitForElementToBeClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    public List<WebElement> waitForElementsToBeClickable(WebDriver driver, By locator) {
        return waitForElementsToBeClickable(driver, locator, DEFAULT_TIMEOUT);
    }
}
