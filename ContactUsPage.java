package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.aventstack.extentreports.ExtentTest;

public class ContactUsPage extends BasePage {
    
    @FindBy(id = "name")
    private WebElement nameField;
    
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "message")
    private WebElement messageField;
    
    @FindBy(css = ".submit-button")
    private WebElement submitButton;
    
    @FindBy(css = ".success-message")
    private WebElement successMessage;

    public ContactUsPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void submitFeedback(String name, String email, String message) {
        log("Submitting feedback form");
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        messageField.sendKeys(message);
        submitButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }
}