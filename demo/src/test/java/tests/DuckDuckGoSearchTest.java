package tests;

import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ApiInteractor;
import utils.Waiter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DuckDuckGoSearchTest {

    private WebDriver driver;
    private Waiter waiter;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @BeforeEach
    public void setUp() {
        // Setup Chromedriver using bonigarcia library to avoid static versions and configurations.
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        waiter = new Waiter();
        homePage = new HomePage(driver, waiter);
        searchResultsPage = new SearchResultsPage(driver, waiter);
        
        // Navigate to DuckDuckGo.
        driver.manage().window().maximize();
        homePage.navigatePage();
    }

    @Test
    @Order(1)
    public void testSearchForAndroid() {
        String searchTerm = "android";
        homePage.assertPage();
        homePage.searchFor(searchTerm);
        searchResultsPage.assertPage();
        searchResultsPage.assertSearchTermInResults(searchTerm);
    }

    @Test
    @Order(2)
    public void testAllRegionsMenuOptions() {
        searchResultsPage.navigatePage("https://duckduckgo.com/?t=h_&q=s&ia=web");
        searchResultsPage.assertPage();
        searchResultsPage.clickAndAssertRegionsDropdown(10);
    }

    @Test
    @Order(3)
    public void testDuckDuckGoApiIconUrl() {
        ApiInteractor apiInteractor = new ApiInteractor();
        String query = "Simpson";
        
        JSONArray relatedTopics = apiInteractor.getRelatedTopics(query);

        assertNotNull(relatedTopics, "RelatedTopics should not be null.");

        apiInteractor.printIconUrls(relatedTopics);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test.
        if (driver != null) {
            driver.quit();
        }
    }
}
