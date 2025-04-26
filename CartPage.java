package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.aventstack.extentreports.ExtentTest;

public class CartPage extends BasePage {
    
    @FindBy(css = ".cart-item")
    private WebElement cartItem;
    
    @FindBy(css = ".cart-item:first-child .quantity-input")
    private WebElement quantityInput;
    
    @FindBy(css = ".cart-item:first-child .remove-item")
    private WebElement removeButton;
    
    @FindBy(css = ".checkout-button")
    private WebElement checkoutButton;
    
    @FindBy(css = ".empty-cart-message")
    private WebElement emptyCartMessage;

    public CartPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public boolean isItemInCart() {
        return cartItem.isDisplayed();
    }

    public void updateQuantity(int quantity) {
        log("Updating product quantity to: " + quantity);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void removeFirstItem() {
        log("Removing first item from cart");
        removeButton.click();
    }

    public boolean isCartEmpty() {
        return emptyCartMessage.isDisplayed();
    }

    public void proceedToCheckout() {
        log("Proceeding to checkout");
        checkoutButton.click();
    }
}