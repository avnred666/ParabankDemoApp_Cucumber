package pages;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends WaitHelper{

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@class = 'button']")
    WebElement submitButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void login(List<String> credentials) {
        waitForElementVisibility(usernameField);
        usernameField.sendKeys(credentials.get(0));
        passwordField.sendKeys(credentials.get(1));
        submitButton.click();
    }
}
