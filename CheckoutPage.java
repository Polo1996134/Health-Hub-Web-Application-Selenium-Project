package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;

public class CheckoutPage extends BasePage {
    
    @FindBy(id = "shipping-address")
    private WebElement shippingAddressField;
    
    @FindBy(id = "payment-method")
    private WebElement paymentMethodDropdown;
    
    @FindBy(css = ".place-order-button")
    private WebElement placeOrderButton;
    
    @FindBy(css = ".order-confirmation")
    private WebElement orderConfirmation;

    public CheckoutPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void enterShippingAddress(String address) {
        log("Entering shipping address: " + address);
        shippingAddressField.sendKeys(address);
    }

    public void selectPaymentMethod(String method) {
        log("Selecting payment method: " + method);
        new Select(paymentMethodDropdown).selectByVisibleText(method);
    }

    public void placeOrder() {
        log("Placing order");
        placeOrderButton.click();
    }

    public boolean isOrderConfirmationDisplayed() {
        return orderConfirmation.isDisplayed();
    }
}