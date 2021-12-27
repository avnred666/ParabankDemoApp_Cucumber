package pages;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsOverviewPage extends WaitHelper{

    @FindBy(className = "title")
    private WebElement pageTitle;

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }


    public String getPageTitle() {
        waitForElementVisibility(pageTitle);
        return pageTitle.getText();
    }
}
