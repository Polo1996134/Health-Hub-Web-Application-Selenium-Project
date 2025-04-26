package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.aventstack.extentreports.ExtentTest;

public class LoginPage extends BasePage {
    
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(css = ".login-button")
    private WebElement loginButton;
    
    @FindBy(css = ".error-message")
    private WebElement errorMessage;
    
    @FindBy(linkText = "Create Account")
    private WebElement createAccountLink;

    public LoginPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void login(String email, String password) {
        log("Attempting login with email: " + email);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public void navigateToSignup() {
        log("Navigating to signup page");
        createAccountLink.click();
    }
}