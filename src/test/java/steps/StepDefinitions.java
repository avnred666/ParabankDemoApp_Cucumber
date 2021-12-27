package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import pages.AccountsOverviewPage;
import pages.HomePage;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StepDefinitions {

    private WebDriver driver;
    protected HomePage homePage;
    ITestResult result;

    @Before
    public void openBrowser(){
        String fileDirectory = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",fileDirectory+"/src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver",fileDirectory+"/src/main/resources/geckodriver.exe");
        String browser = System.getProperty("browser");
        if(browser != "" && browser != null) {
            if (browser.equalsIgnoreCase("Chrome")) {
                driver = new ChromeDriver();
                System.setProperty("webdriver.chrome.driver",fileDirectory+"/src/main/resources/chromedriver.exe");
            } else if (browser.equalsIgnoreCase("Firefox")) {
                driver = new FirefoxDriver();
            } else {
                driver = new ChromeDriver();
            }
        }
    }


    @Given("user is in home page")
    public void user_is_in_home_page() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }
    @When("user gives username and password as follows")
    public void user_gives_username_and_password_as_follows(DataTable dataTable) {
        List<String> credentials = dataTable.transpose().asList();
        homePage.login(credentials);
    }
    @Then("user is taken to {string} page")
    public void user_is_taken_to_page(String pageName) {
        String pageTitle;
        AccountsOverviewPage accountsOverviewPage = PageFactory.initElements(driver,AccountsOverviewPage.class);
        pageTitle = accountsOverviewPage.getPageTitle();
        assertEquals(pageTitle,pageName,"Incorrect page title!");
    }

    @When("user gives valid {string} and {string}")
    public void userGivesValidUsernameAndPassword(String username, String password) {
        List<String> credentials = new ArrayList<>();
        credentials.add(username);
        credentials.add(password);
        homePage.login(credentials);
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName()); // ... and embed it in the report.
        }
        driver.quit();
    }
}
