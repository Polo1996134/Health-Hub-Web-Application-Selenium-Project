package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.aventstack.extentreports.ExtentTest;

public class AccountPage extends BasePage {
    
    @FindBy(linkText = "Order History")
    private WebElement orderHistoryLink;
    
    @FindBy(css = ".order-list")
    private WebElement orderList;
    
    @FindBy(css = ".profile-details")
    private WebElement profileDetails;

    public AccountPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void viewOrderHistory() {
        log("Viewing order history");
        orderHistoryLink.click();
    }

    public boolean areOrdersListed() {
        return orderList.isDisplayed();
    }

    public String getProfileName() {
        return profileDetails.getText();
    }
}